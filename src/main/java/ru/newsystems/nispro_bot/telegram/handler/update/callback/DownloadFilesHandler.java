package ru.newsystems.nispro_bot.telegram.handler.update.callback;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.domain.Attachment;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;
import ru.newsystems.nispro_bot.base.model.dto.MessageGetDTO;
import ru.newsystems.nispro_bot.base.model.dto.callback.DownloadFilesDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;
import ru.newsystems.nispro_bot.base.repo.local.MessageLocalRepo;
import ru.newsystems.nispro_bot.config.cache.CacheStore;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DownloadFilesHandler extends CallbackUpdateHandler<DownloadFilesDTO> {

    private final MessageLocalRepo localRepo;
    private final VirtaBot bot;
    private final CacheStore<TicketGetDTO> cache;

    public DownloadFilesHandler(MessageLocalRepo localRepo, VirtaBot bot, CacheStore<TicketGetDTO> cache) {
        this.localRepo = localRepo;
        this.bot = bot;
        this.cache = cache;
    }

    @Override
    protected Class<DownloadFilesDTO> getDtoType() {
        return DownloadFilesDTO.class;
    }

    @Override
    protected SerializableInlineType getSerializableType() {
        return SerializableInlineType.DOWNLOAD;
    }

    @Override
    protected void handleCallback(Update update, DownloadFilesDTO dto) throws TelegramApiException {
        Long id = update.getCallbackQuery().getMessage().getChatId();
        bot.execute(SendChatAction
                .builder()
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .action(ActionType.UPLOADDOCUMENT.toString())
                .build());

        MessageGetDTO messageGetDTO = localRepo.get(id);
        List<Attachment> attachments;
        TicketJ ticket;
        if (dto.getFrom().equals("s")) {
            ticket = messageGetDTO.getTicket();
            attachments = ticket.getArticles().get(ticket.getArticles().size() - 1).getAttachments();
        } else {
            ticket = cache.get(update.getCallbackQuery().getMessage().getChatId())
                    .getTickets()
                    .stream()
                    .filter(e -> e.getTicketNumber().equals(dto.getTicketId()))
                    .findFirst()
                    .get();
            //TODO отключаем навигацию на коментарии
//            Article article = ticket
//                    .getArticles()
//                    .stream()
//                    .filter(e -> Objects.equals(e.getArticleID(), dto.getArticleId()))
//                    .findFirst()
//                    .get();

            attachments = ticket
                    .getArticles()
                    .stream()
                    .filter(e -> e.getAttachments() != null)
                    .flatMap(x -> x.getAttachments().stream())
                    .collect(Collectors.toList());
            //TODO отключаем навигацию на коментарии
//            attachments = article.getAttachments();
        }

        if (attachments == null) {
            String text = "<pre>⛔️ Файлы для скачивания отсутствуют в последнем комментарии.</pre>";
            bot.execute(SendMessage
                    .builder()
                    .text(text)
                    .replyToMessageId(update.getCallbackQuery().getMessage().getMessageId())
                    .chatId(update.getCallbackQuery().getMessage().getChatId().toString())
                    .parseMode(ParseMode.HTML)
                    .build());
        } else if (attachments.size() != 0) {
            attachments.forEach(e -> {
                try {
                    prepareFileToSend(update, e);
                } catch (TelegramApiException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    private void prepareFileToSend(Update update, Attachment e) throws TelegramApiException {
        byte[] decode = Base64.getDecoder().decode(e.getContent().getBytes(StandardCharsets.UTF_8));
        bot.execute(SendDocument
                .builder()
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .document(new InputFile(new ByteArrayInputStream(decode), e.getFilename()))
                .build());
    }
}
