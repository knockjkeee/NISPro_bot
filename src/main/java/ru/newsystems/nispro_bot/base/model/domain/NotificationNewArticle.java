package ru.newsystems.nispro_bot.base.model.domain;

import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramReceiveNotificationNewArticle;
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

@Data
@Builder
public class NotificationNewArticle implements Runnable {
    TelegramNotificationRepo repo;
    private VirtaBot bot;
    private RestNISService rest;

    @Override
    public void run() {
        List<TelegramReceiveNotificationNewArticle> newArticles = repo.findAll();
        if (newArticles.size() > 0) {
            newArticles.stream()
                    .filter(i -> i.getIsVisibleForCustomer() == 1 && i.getLoginCountRegistration() == 0)
                    .forEach(newArticle -> {
                        try {
                            Long id = Long.parseLong(newArticle.getIdTelegram());
                            sendNotification(newArticle);
                        } catch (NumberFormatException ignored) {
                        }
                    });
        }
        repo.deleteAll();
    }

    private void sendNotification(TelegramReceiveNotificationNewArticle newArticle) {

        Optional<TicketSearchDTO> currentTicket =
                rest.getTicketOperationSearch(List.of(Long.valueOf(newArticle.getTicketNumber())), Long.valueOf(newArticle.getIdTelegram()));
        Article article = null;
        if (currentTicket.isPresent()){
            List<Long> ticketIDs = currentTicket.get().getTicketIDs();
            Optional<TicketGetDTO> ticketOperationGet = rest.getTicketOperationGet(ticketIDs, Long.valueOf(newArticle.getIdTelegram()));
            if (ticketOperationGet.isPresent()) {
                article = ticketOperationGet.get().getTickets().get(0).getArticles().stream().filter(e -> Objects.equals(e.getArticleID(), newArticle.getArticleId())).findFirst().get();
            }
        }

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(List.of(InlineKeyboardButton
                .builder()
                .text(ReplyKeyboardButton.COMMENT.getLabel() + " Отправить комментарий")
                .callbackData(StringUtil.serialize(new SendDataDTO(newArticle.getTicketNumber())))
                .build()));

        try {
            if (Long.parseLong(newArticle.getIdTelegram()) < 0) {
                bot.execute(SendMessage.builder()
                        .chatId(newArticle.getIdTelegram())
                        .text("<pre>✉️ Новое сообщение \nЗаявка № " + newArticle.getTicketNumber() + "\n</pre>" +
                                "<pre>От: " + newArticle.getCreateBy() + "</pre>" + "\n<b>Тема: </b><i>" +
                                newArticle.getSubject() + "</i>\n<b>Сообщение: </b><i>" + newArticle.getBody() + "</i>")
                        .parseMode(ParseMode.HTML)
                        .protectContent(true)
                        .build());
            } else {
                bot.execute(SendMessage.builder()
                        .chatId(newArticle.getIdTelegram())
                        .text("<pre>✉️ Новое сообщение \nЗаявка № " + newArticle.getTicketNumber() + "\n</pre>" +
                                "<pre>От: " + newArticle.getCreateBy() + "</pre>" + "\n<b>Тема: </b><i>" +
                                newArticle.getSubject() + "</i>\n<b>Сообщение: </b><i>" + newArticle.getBody() + "</i>")
                        .parseMode(ParseMode.HTML)
                        .protectContent(true)
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                        .build());
            }

            if (article == null || (article.getAttachments() == null || article.getAttachments().isEmpty())) return;
            article.getAttachments().forEach(e -> {
                try {
                    prepareFileToSend(newArticle.getIdTelegram(), e);
                } catch (TelegramApiException ex) {
                    ex.printStackTrace();
                }
            });

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void prepareFileToSend(String id, Attachment e) throws TelegramApiException {
        byte[] decode = Base64.getDecoder().decode(e.getContent().getBytes(StandardCharsets.UTF_8));
        bot.execute(SendDocument
                .builder()
                .chatId(id)
                .document(new InputFile(new ByteArrayInputStream(decode), e.getFilename()))
                .build());
    }
}
