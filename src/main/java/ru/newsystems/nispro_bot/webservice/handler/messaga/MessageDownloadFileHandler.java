package ru.newsystems.nispro_bot.webservice.handler.messaga;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.domain.Attachment;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;
import ru.newsystems.nispro_bot.base.model.state.MessageState;
import ru.newsystems.nispro_bot.base.repo.local.MessageLocalRepo;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static ru.newsystems.nispro_bot.webservice.Telegram.Notification.resultOperationToChat;


@Component
public class MessageDownloadFileHandler implements MessageHandler {

    private final MessageLocalRepo localRepo;
    private final VirtaBot bot;

    public MessageDownloadFileHandler(MessageLocalRepo localRepo, VirtaBot bot) {
        this.localRepo = localRepo;
        this.bot = bot;
    }

    @Override
    public boolean handleUpdate(Update update) throws TelegramApiException {
        String text = update.getMessage().getText();
        if (text == null) return false;
        if (MessageState.getState(text).equals(MessageState.DOWLOADFILE)) {
            TicketJ ticket = localRepo.get(update.getMessage().getChatId()).getTicket();
            List<Attachment> attachments = ticket.getArticles().get(ticket.getArticles().size() - 1).getAttachments();
            bot.execute(SendChatAction.builder()
                    .chatId(String.valueOf(update.getMessage().getChatId()))
                    .action(ActionType.UPLOADDOCUMENT.toString())
                    .build());
            if (attachments.size() != 0) {
                attachments.forEach(e -> {
                    try {
                        prepareFileToSend(update, e);
                    } catch (TelegramApiException ex) {
                        ex.printStackTrace();
                    }
                });
                resultOperationToChat(update, bot, true);
            } else {
                resultOperationToChat(update, bot, false);
            }
            return true;
        } else {
            return false;
        }
    }

    private void prepareFileToSend(Update update, Attachment e) throws TelegramApiException {
        byte[] decode = Base64.getDecoder().decode(e.getContent().getBytes(StandardCharsets.UTF_8));
        bot.execute(SendDocument.builder()
                .chatId(update.getMessage().getChatId().toString())
                .document(new InputFile(new ByteArrayInputStream(decode), e.getFilename()))
                .build());
    }
}
