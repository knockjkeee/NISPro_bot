package ru.newsystems.nispro_bot.webservice.handler.messaga;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.domain.Attachment;
import ru.newsystems.nispro_bot.base.model.dto.domain.RequestDataDTO;
import ru.newsystems.nispro_bot.base.model.state.MessageState;
import ru.newsystems.nispro_bot.webservice.service.RestService;
import ru.newsystems.nispro_bot.webservice.task.SendDataDTO;
import ru.newsystems.nispro_bot.webservice.task.SendLocalRepo;
import ru.newsystems.nispro_bot.webservice.task.SendOperationTask;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static ru.newsystems.nispro_bot.webservice.Telegram.Action.sendCreateTicket;
import static ru.newsystems.nispro_bot.webservice.Telegram.Action.sendNewComment;
import static ru.newsystems.nispro_bot.webservice.Telegram.FormattedData.*;
import static ru.newsystems.nispro_bot.webservice.Telegram.Notification.resultOperationToChat;


@Component
public class MessageSendDataHandler implements MessageHandler {

    public static final int DELAY_FOR_ADD_DOCS_OR_PIC = 2;
    public static final int DELAY_AFTER_ADD_MSG = 4;
    private final SendLocalRepo localRepo;
    private final ScheduledExecutorService executor;
    private final RestService restService;
    private final VirtaBot bot;

    public MessageSendDataHandler(SendLocalRepo localRepo, ScheduledExecutorService executor, RestService restService, VirtaBot bot) {
        this.localRepo = localRepo;
        this.executor = executor;
        this.restService = restService;
        this.bot = bot;
    }

    @Override
    public boolean handleUpdate(Update update) throws TelegramApiException {
        if (update.getMessage().getReplyToMessage() != null) {
            List<String> replyTexts = splitMessageText(update.getMessage().getReplyToMessage().getText(), "№");
            boolean isSendComment = replyTexts.get(0).contains(MessageState.SENDCOMMENT.getName());
            boolean isCreateTicket = replyTexts.get(0).contains(MessageState.CREATETICKET.getName());
            if (isSendComment || isCreateTicket) {
                if (update.getMessage().hasText()) {
                    prepareMsg(update, replyTexts, isSendComment);
                    return true;
                }
                if (update.getMessage().hasPhoto()) {
                    return preparePhoto(update, replyTexts, isSendComment);
                }
                if (update.getMessage().hasDocument()) {
                    if (prepareDocument(update, replyTexts, isSendComment)) return true;
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean prepareDocument(Update update, List<String> replyTexts, boolean isSendComment) throws TelegramApiException {
        SendDataDTO sendDataDTO = localRepo.get(update.getMessage().getChatId());
        if (sendDataDTO != null && !sendDataDTO.getSchedule().isDone()) {
            prepareDataWithUpdateSchedule(update, sendDataDTO, prepareAttachmentFromDocument(update, bot));
            return true;
        }
        RequestDataDTO req = prepareReqWithDocument(update, replyTexts, update.getMessage().getCaption(), bot);
        if (isSendComment) {
            sendNewComment(update, req, restService, bot);
        }else{
            sendCreateTicket(update, req, restService, bot);
        }
        return false;
    }

    private boolean preparePhoto(Update update, List<String> replyTexts, boolean isSendComment) throws TelegramApiException {
        SendDataDTO sendDataDTO = localRepo.get(update.getMessage().getChatId());
        if (sendDataDTO != null && !sendDataDTO.getSchedule().isDone()) {
            prepareDataWithUpdateSchedule(update, sendDataDTO, prepareAttachmentFromPhoto(update, bot));
            return true;
        } else {
            RequestDataDTO req = prepareReqWithPhoto(update, replyTexts, update.getMessage().getCaption(), bot);
            if ((sendDataDTO == null || sendDataDTO.getSchedule().isDone())
                    && update.getMessage().getMediaGroupId() != null) {
                prepareTaskForExecutor(update, req, isSendComment);
                return true;
            }
            if (isSendComment) {
                sendNewComment(update, req, restService, bot);
            }else{
                sendCreateTicket(update, req, restService, bot);
            }
            return true;
        }
    }

    private void prepareMsg(Update update, List<String> replyTexts, boolean isSendComment) {
        RequestDataDTO req = prepareReqWithMessage(replyTexts, update.getMessage().getText());
        prepareTaskForExecutor(update, req, isSendComment);
    }

    private void prepareDataWithUpdateSchedule(Update update, SendDataDTO sendDataDTO, Attachment attachment) {
        sendDataDTO.stopSchedule();
        SendOperationTask task = sendDataDTO.getTask();
        updateTaskForExecutor(update, sendDataDTO, task, attachment);
    }

    private void updateTaskForExecutor(Update update, SendDataDTO sendDataDTO, SendOperationTask task, Attachment attachment) {
        task.updateAttachment(attachment);
        ScheduledFuture<?> schedule = executor.schedule(task, DELAY_AFTER_ADD_MSG, TimeUnit.SECONDS);
        sendDataDTO.setSchedule(schedule);
        sendDataDTO.setTask(task);
        localRepo.update(update.getMessage().getDate().longValue() - 1, sendDataDTO);
    }

    private void prepareTaskForExecutor(Update update, RequestDataDTO req, boolean isSendComment) {
        SendOperationTask task = SendOperationTask.builder()
                .req(req)
                .update(update)
                .bot(bot)
                .restService(restService)
                .isSendComment(isSendComment)
                .build();
        ScheduledFuture<?> schedule = executor.schedule(task, DELAY_FOR_ADD_DOCS_OR_PIC, TimeUnit.SECONDS);
        SendDataDTO sendDataDTO = SendDataDTO.builder().task(task).schedule(schedule).build();
        localRepo.update(update.getMessage().getChatId(), sendDataDTO);
    }

    private boolean checkCorrectlySendFormatSubjectMessage(Update update, List<String> splitMessage) throws TelegramApiException {
        if (splitMessage.size() != 2) {
            resultOperationToChat(update, bot, false);
            return true;
        }
        return false;
    }
}

