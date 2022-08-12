package ru.newsystems.nispro_bot.telegram.task;

import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.domain.Attachment;
import ru.newsystems.nispro_bot.base.model.dto.domain.RequestDataDTO;
import ru.newsystems.nispro_bot.base.utils.StringUtil;
import ru.newsystems.nispro_bot.telegram.service.RestNISService;
import ru.newsystems.nispro_bot.webservice.services.TelegramBotRegistrationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.newsystems.nispro_bot.telegram.utils.Action.sendCreateTicket;
import static ru.newsystems.nispro_bot.telegram.utils.Action.sendNewComment;


@Data
@Builder
public class SendOperationTask implements Runnable {
    private int countRedirect;
    private List<Long> forwardId;
    private Long selfId;
    private TelegramBotRegistrationService registrationService;
    private RequestDataDTO req;
    private boolean isSendComment;
    private Update update;
    private VirtaBot bot;
    private RestNISService restNISService;

    @Override
    public void run() {
        try {
            if (isSendComment) {
                sendNewComment(update, req, restNISService, bot);
            } else {
                if (countRedirect != 1) {
                    if (forwardId == null) {
                        sendCreateTicket(update, req, restNISService, bot, null);
                    } else {
                        List<Long> collectForwardId = forwardId.stream().filter(e -> !Objects.equals(e, selfId)).collect(Collectors.toList());
                        List<TelegramBotRegistration> registrationServiceAll = registrationService.findAll();
                        List<TelegramBotRegistration> collect = registrationServiceAll.stream().filter(e -> {
                            String chatMembers = e.getChatMembers();
                            if (!StringUtil.isBlank(chatMembers)) {
                                String[] split = chatMembers.split(";");
                                return Arrays.stream(split).anyMatch(x -> Objects.equals(x, String.valueOf(collectForwardId.get(0))));
                            }
                            return false;
                        }).collect(Collectors.toList());
                        sendCreateTicket(update, req, restNISService, bot, collect.get(0));
                    }
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void updateAttachment(Attachment attach) {
        List<Attachment> attaches = req.getAttaches();
        List<Attachment> result = new ArrayList<>();
        if (attaches == null && attach != null) {
            result.add(attach);
            req.setAttaches(result);
        } else if (attaches != null) {
            result.addAll(attaches);
            result.add(attach);
            req.setAttaches(result);
        }
    }
}
