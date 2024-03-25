package ru.newsystems.nispro_bot.telegram.handler.message;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.domain.Article;
import ru.newsystems.nispro_bot.base.model.domain.Attachment;
import ru.newsystems.nispro_bot.base.model.dto.domain.RequestDataDTO;
import ru.newsystems.nispro_bot.base.model.state.MessageState;
import ru.newsystems.nispro_bot.base.utils.StringUtil;
import ru.newsystems.nispro_bot.telegram.service.RestNISService;
import ru.newsystems.nispro_bot.telegram.task.SendDataDTO;
import ru.newsystems.nispro_bot.telegram.task.SendLocalRepo;
import ru.newsystems.nispro_bot.telegram.task.SendOperationTask;
import ru.newsystems.nispro_bot.webservice.services.TelegramBotRegistrationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static ru.newsystems.nispro_bot.telegram.utils.Action.sendCreateTicket;
import static ru.newsystems.nispro_bot.telegram.utils.Action.sendNewComment;
import static ru.newsystems.nispro_bot.telegram.utils.FormattedData.*;
import static ru.newsystems.nispro_bot.telegram.utils.Notification.resultOperationToChat;


@Log4j2
@Component
public class MessageSendDataHandler implements MessageHandler {

    public static final int DELAY_FOR_ADD_DOCS_OR_PIC = 2;
    public static final int DELAY_AFTER_ADD_MSG = 4;
    private final SendLocalRepo localRepo;
    private final ScheduledExecutorService executor;
    private final TelegramBotRegistrationService registrationService;
    private final RestNISService restNISService;
    private final VirtaBot bot;

    public MessageSendDataHandler(SendLocalRepo localRepo, ScheduledExecutorService executor, TelegramBotRegistrationService registrationService, RestNISService restNISService, VirtaBot bot) {
        this.localRepo = localRepo;
        this.executor = executor;
        this.registrationService = registrationService;
        this.restNISService = restNISService;
        this.bot = bot;
    }

    @Override
    public boolean handleUpdate(Update update, boolean isRedirect) throws TelegramApiException {

        boolean isReplyText = update.getMessage().getReplyToMessage() != null;
        List<String> replyTexts = isReplyText ? splitMessageText(update.getMessage().getReplyToMessage().getText(),
                "№") : Collections.emptyList();
        boolean isSendComment = isReplyText && replyTexts.get(0).contains(MessageState.SENDCOMMENT.getName());
        boolean isCreateTicket = isReplyText && replyTexts.get(0).contains(MessageState.CREATETICKET.getName());
        User isForward = update.getMessage().getForwardFrom();
        String forward = update.getMessage().getForwardSenderName();
        log.info("====>>>" + forward);
        log.info("====>>>" + isForward);
        if (isSendComment || isCreateTicket || isRedirect) {
            User forwardUser = update.getMessage().getForwardFrom();
            if (update.getMessage().hasText()) {
                if (isRedirect) {
                    if (isForward == null) {
                        prepareMsg(update, replyTexts, isSendComment, true, forwardUser, registrationService);
                        return true;
                    } else {
                        prepareAddMsg(update, true, forwardUser);
                        return true;
                    }
                } else {
                    prepareMsg(update, replyTexts, isSendComment, false, forwardUser, registrationService);
                    return true;
                }
            }
            if (update.getMessage().hasPhoto()) {
                return preparePhoto(update, replyTexts, isSendComment, isRedirect, forwardUser, registrationService);
            }
            if (update.getMessage().hasDocument()) {
                if (prepareDocument(update, replyTexts, isSendComment, isRedirect, forwardUser)) return true;
                return true;
            }
            return false;
        }
        return false;
//        }
//        return false;
    }

    private boolean prepareDocument(Update update, List<String> replyTexts, boolean isSendComment, boolean isRedirect, User forwardUser) throws TelegramApiException {
        SendDataDTO sendDataDTO = localRepo.get(update.getMessage().getChatId());
        if (sendDataDTO != null && !sendDataDTO.getSchedule().isDone()) {
            prepareDataWithUpdateSchedule(update,
                    sendDataDTO,
                    prepareAttachmentFromDocument(update, bot),
                    isRedirect,
                    forwardUser);
            return true;
        }
        RequestDataDTO req = prepareReqWithDocument(update,
                replyTexts,
                update.getMessage().getCaption(),
                bot,
                isRedirect);
        if (isSendComment) {
            sendNewComment(update, req, restNISService, bot);
        } else {
            sendCreateTicket(update, req, restNISService, bot, null);
        }
        return false;
    }

    private void prepareAddMsg(Update update, boolean isRedirect, User forwardUser) throws TelegramApiException {
        SendDataDTO sendDataDTO = localRepo.get(update.getMessage().getChatId());
        if (sendDataDTO != null && !sendDataDTO.getSchedule().isDone()) {
            prepareDataWithUpdateSchedule(update, sendDataDTO, null, isRedirect, forwardUser);
        }
    }

    private boolean preparePhoto(Update update, List<String> replyTexts, boolean isSendComment, boolean isRedirect, User forwardUser, TelegramBotRegistrationService registrationService) throws TelegramApiException {
        SendDataDTO sendDataDTO = localRepo.get(update.getMessage().getChatId());
        if (sendDataDTO != null && !sendDataDTO.getSchedule().isDone()) {
            prepareDataWithUpdateSchedule(update,
                    sendDataDTO,
                    prepareAttachmentFromPhoto(update, bot),
                    isRedirect,
                    forwardUser);
            return true;
        } else {
            RequestDataDTO req = prepareReqWithPhoto(update,
                    replyTexts,
                    update.getMessage().getCaption(),
                    bot,
                    isRedirect);
            if ((sendDataDTO == null || sendDataDTO.getSchedule().isDone()) &&
                update.getMessage().getMediaGroupId() != null) {
                prepareTaskForExecutor(update, req, isSendComment, isRedirect, forwardUser, registrationService);
                return true;
            }
            if (isSendComment) {
                sendNewComment(update, req, restNISService, bot);
            } else {
                sendCreateTicket(update, req, restNISService, bot, null);
            }
            return true;
        }
    }

    private void prepareMsg(Update update, List<String> replyTexts, boolean isSendComment, boolean isRedirect, User forwardUser, TelegramBotRegistrationService registrationService) {
        RequestDataDTO req = prepareReqWithMessage(replyTexts, update.getMessage().getText(), isRedirect);
        prepareTaskForExecutor(update, req, isSendComment, isRedirect, forwardUser, registrationService);
    }

    private void prepareDataWithUpdateSchedule(Update update, SendDataDTO sendDataDTO, Attachment attachment, boolean isRedirect, User forwardUser) {
        sendDataDTO.stopSchedule();
        SendOperationTask task = sendDataDTO.getTask();
        updateTaskForExecutor(update, sendDataDTO, task, attachment, isRedirect, forwardUser);
    }

    private void updateTaskForExecutor(Update update,
                                       SendDataDTO sendDataDTO,
                                       SendOperationTask task,
                                       Attachment attachment,
                                       boolean isRedirect,
                                       User forwardUser) {
        if (attachment != null) {
            task.updateAttachment(attachment);
        }
        if (isRedirect) {
            task.setCountRedirect(task.getCountRedirect() + 1);
            String text = update.getMessage().getText();
            if (!StringUtil.isBlank(text)) {
                Article article = task.getReq().getArticle();
                article.setBody(article.getBody() + "\n\n" + text);
                task.getReq().setArticle(article);
            }
            if (forwardUser != null) {
                //task.setForwardId(forwardUser.getId());
                List<Long> forwardId = task.getForwardId();
                forwardId.add(forwardUser.getId());
                task.setForwardId(forwardId);
            }
        }
        ScheduledFuture<?> schedule = executor.schedule(task, DELAY_AFTER_ADD_MSG, TimeUnit.SECONDS);
        sendDataDTO.setSchedule(schedule);
        sendDataDTO.setTask(task);
        localRepo.update(update.getMessage().getDate().longValue() - 1, sendDataDTO);
    }

    private void prepareTaskForExecutor(Update update, RequestDataDTO req, boolean isSendComment, boolean isRedirect, User forwardUser, TelegramBotRegistrationService registrationService) {
        SendOperationTask task = SendOperationTask
                .builder()
                .req(req)
                .update(update)
                .bot(bot)
                .restNISService(restNISService)
                .registrationService(registrationService)
                .isSendComment(isSendComment)
                .selfId(update.getMessage().getChatId())
                .build();
        if (isRedirect) {
            task.setCountRedirect(task.getCountRedirect() + 1);
            if (forwardUser == null) {
//                task.setForwardId(forwardUser.getId());
                List<Long> forwardId = new ArrayList<>();
//                forwardId.add(forwardUser.getId());
                task.setForwardId(forwardId);
            }
        }
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

