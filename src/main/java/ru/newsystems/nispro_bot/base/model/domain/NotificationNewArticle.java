package ru.newsystems.nispro_bot.base.model.domain;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramReceiveNotificationNewArticle;
import ru.newsystems.nispro_bot.base.model.dto.callback.ChangeStatusDTO;
import ru.newsystems.nispro_bot.base.model.dto.callback.SendDataDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketSearchDTO;
import ru.newsystems.nispro_bot.base.model.state.ReplyKeyboardButton;
import ru.newsystems.nispro_bot.base.repo.TelegramNotificationRepo;
import ru.newsystems.nispro_bot.base.utils.StringUtil;
import ru.newsystems.nispro_bot.telegram.service.RestNISService;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

//@Data
@Builder
@Slf4j
public class NotificationNewArticle implements Runnable {
    TelegramNotificationRepo repo;
    private VirtaBot bot;
    private RestNISService rest;

    @Override
    public void run() {
        List<TelegramReceiveNotificationNewArticle> newArticles = repo.findAll();
        log.info("Найдены новые {} записи в таблице оповещения NotificationNewArticle", newArticles.size());
        if (newArticles.size() > 0) {
            newArticles.stream().filter(i -> i.getIsVisibleForCustomer() == 1).forEach(newArticle -> {
                try {
                    Long id = Long.parseLong(newArticle.getIdTelegram());
                    sendNotification(newArticle);
                } catch (NumberFormatException ignored) {
                    log.info("Ошибка в парсере id у {}", newArticle.getIdTelegram());
                }
            });
        }
        repo.deleteAll();
        log.info("Очистка таблицы - NotificationNewArticle");
    }

    private void sendNotification(TelegramReceiveNotificationNewArticle newArticle) {
        TicketJ mainTicket =  null;
        Optional<TicketSearchDTO> currentTicket =
                rest.getTicketOperationSearch(List.of(Long.valueOf(newArticle.getTicketNumber())), Long.valueOf(newArticle.getIdTelegram()));
        Article article = null;
        if (currentTicket.isPresent()) {
            List<Long> ticketIDs = currentTicket.get().getTicketIDs();
            if (ticketIDs != null) {
                Optional<TicketGetDTO> ticketOperationGet =
                        rest.getTicketOperationGet(ticketIDs, Long.valueOf(newArticle.getIdTelegram()));
                if (ticketOperationGet.isPresent()) {
                    mainTicket = ticketOperationGet.get().getTickets().get(0);
                    article = ticketOperationGet.get()
                            .getTickets()
                            .get(0)
                            .getArticles()
                            .stream()
                            .filter(e -> Objects.equals(e.getArticleID(), newArticle.getArticleId()))
                            .findFirst()
                            .get();
                }else {
                    log.info("Ошибка в поиске запроса по номеру {}", newArticle.getTicketNumber());
                }
            }else {
                log.info("Количество возвращенных id по запросу от {} равно {}", newArticle.getTicketNumber(),currentTicket.get().getTicketIDs().size());
            }
        }

        List<List<InlineKeyboardButton>> buttonsReceiveNote = new ArrayList<>();
        buttonsReceiveNote.add(List.of(InlineKeyboardButton.builder()
                .text(ReplyKeyboardButton.COMMENT.getLabel() + " Отправить комментарий")
                .callbackData(StringUtil.serialize(new SendDataDTO(newArticle.getTicketNumber())))
                .build()));

        try {
            if (newArticle.isResponsible() && mainTicket != null) {

                List<List<InlineKeyboardButton>> changeStatus = new ArrayList<>();
                changeStatus.add(List.of(InlineKeyboardButton.builder()
                        .text(ReplyKeyboardButton.COMMENT.getLabel() + " Принять в работу")
                        .callbackData(StringUtil.serialize(new ChangeStatusDTO(newArticle.getTicketNumber(), "a", null)))
                        .build()));

                log.info("Смена ответственного по заявке № {}", newArticle.getTicketNumber());
                bot.execute(SendMessage.builder()
                        .chatId(newArticle.getIdTelegram())
                        .text("<pre>❗ На Вас назначили новую задачу" +
                                "\nЗаявка № " + mainTicket.getTicketNumber() + "</pre>" +
                                "\n<pre>От: " +  mainTicket.getOwner() + "</pre>" +
                                "\n<b>Тема: </b><i>" + mainTicket.getTitle()+ "</i>")
                        .parseMode(ParseMode.HTML)
                        .protectContent(true)
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(changeStatus).build())
                        .build());
            } else if (Long.parseLong(newArticle.getIdTelegram()) < 0) {
                log.info("Оповещении группы по заявке № {}", newArticle.getTicketNumber());
                bot.execute(SendMessage.builder()
                        .chatId(newArticle.getIdTelegram())
                        .text(getDefaultNotificationText(newArticle))
                        .parseMode(ParseMode.HTML)
                        .protectContent(true)
                        .build());
//                if (article != null) sendFile(newArticle, article);
            } else if (newArticle.getLoginCountRegistration() == 0) {
                log.info("Оповещении по заявке № {}", newArticle.getTicketNumber());
                bot.execute(SendMessage.builder()
                        .chatId(newArticle.getIdTelegram())
                        .text(getDefaultNotificationText(newArticle))
                        .parseMode(ParseMode.HTML)
                        .protectContent(true)
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttonsReceiveNote).build())
                        .build());
            }
        } catch (TelegramApiException e) {
            log.info("Ошибка в отправке сообщения пользователю", e);
        } finally {
            if (article != null && !newArticle.isResponsible()) sendFile(newArticle, article);
        }
    }

    private String getDefaultNotificationText(TelegramReceiveNotificationNewArticle newArticle) {
        return "<pre>✉️ Новое сообщение " + "" +
                "\nЗаявка № " + newArticle.getTicketNumber() + "</pre>" + "" +
                "\n<pre>От: " + newArticle.getCreateBy() + "</pre>" + "" +
                "\n<b>Тема: </b><i>" + newArticle.getSubject() + "</i>" +
                "\n<b>Сообщение: </b><i>" + newArticle.getBody() + "</i>";
    }

    private void sendFile(TelegramReceiveNotificationNewArticle newArticle, Article article) {
        if (article == null || (article.getAttachments() == null || article.getAttachments().isEmpty())) return;
        article.getAttachments().forEach(e -> {
            try {
                prepareFileToSend(newArticle.getIdTelegram(), e);
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void prepareFileToSend(String id, Attachment e) throws TelegramApiException {
        byte[] decode = Base64.getDecoder().decode(e.getContent().getBytes(StandardCharsets.UTF_8));
        bot.execute(SendDocument.builder()
                .chatId(id)
                .document(new InputFile(new ByteArrayInputStream(decode), e.getFilename()))
                .build());
    }
}
