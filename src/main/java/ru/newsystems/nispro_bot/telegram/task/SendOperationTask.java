package ru.newsystems.nispro_bot.telegram.task;

import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.domain.Attachment;
import ru.newsystems.nispro_bot.base.model.dto.domain.RequestDataDTO;
import ru.newsystems.nispro_bot.telegram.service.RestNISService;

import java.util.ArrayList;
import java.util.List;

import static ru.newsystems.nispro_bot.telegram.utils.Action.sendCreateTicket;
import static ru.newsystems.nispro_bot.telegram.utils.Action.sendNewComment;


@Data
@Builder
public class SendOperationTask implements Runnable {
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
            }else{
                sendCreateTicket(update, req, restNISService, bot);
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
        } else if (attaches != null){
            result.addAll(attaches);
            result.add(attach);
            req.setAttaches(result);
        }
    }
}
