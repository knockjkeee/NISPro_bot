package ru.newsystems.nispro_bot.telegram.handler.update.callback;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.domain.DynamicField;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;
import ru.newsystems.nispro_bot.base.model.dto.callback.ChangeStatusDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketSearchDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketUpdateCreateDTO;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;
import ru.newsystems.nispro_bot.telegram.service.RestNISService;

import java.util.List;
import java.util.Optional;

import static ru.newsystems.nispro_bot.base.utils.NumberUtil.getIdByTicketNumber;
import static ru.newsystems.nispro_bot.telegram.utils.Notification.resultOperationToChat;

@Component
@Log4j2
public class ChangeStatusHandler extends CallbackUpdateHandler<ChangeStatusDTO>{

    private final RestNISService rest;
    private final VirtaBot bot;

    public ChangeStatusHandler(RestNISService rest, VirtaBot bot) {
        this.rest = rest;
        this.bot = bot;
    }


    @Override
    protected Class<ChangeStatusDTO> getDtoType() {
        return ChangeStatusDTO.class;
    }

    @Override
    protected SerializableInlineType getSerializableType() {
        return SerializableInlineType.CHANGE_STATUS;
    }

    @Override
    protected void handleCallback(Update update, ChangeStatusDTO dto, TelegramBotRegistration registration) throws TelegramApiException {
        long tk = getIdByTicketNumber(String.valueOf(dto.getTicketId()));

        Optional<TicketSearchDTO> ticketSearch = rest.getTicketOperationSearch(List.of(tk), update.getCallbackQuery().getMessage().getChatId());
        List<Long> ticketsId = ticketSearch.get().getTicketIDs();
        Optional<TicketGetDTO> ticketGetDTO = rest.getTicketOperationGet(ticketsId,  update.getCallbackQuery().getMessage().getChatId());
        TicketJ ticket = ticketGetDTO.get().getTickets().get(0);
        String state = ticket.getState();

        Optional<TicketUpdateCreateDTO> ticketUpdate;
        dto.setDynamicField(registration.getIdTelegram());

        DynamicField telegramDynamicField = ticket.getDynamicField()
                .stream()
                .filter(e -> e.getName().equals("Telegram"))
                .findFirst()
                .get();

        switch (state) {
            case "open":
            case "открыта":
                ticketUpdate = rest.getTicketOperationUpdate(update, dto, "закрыта успешно", telegramDynamicField.getValue() == null);
                break;
            case "new":
            case "новая":
                ticketUpdate = rest.getTicketOperationUpdate(update, dto, "открытa", telegramDynamicField.getValue() == null);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }
        if (ticketUpdate.isPresent()) {
            resultOperationToChat(update, bot, true);
        }else{
            resultOperationToChat(update, bot, false);
        }
    }
}
