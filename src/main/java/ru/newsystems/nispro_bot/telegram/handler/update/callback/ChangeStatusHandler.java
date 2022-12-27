package ru.newsystems.nispro_bot.telegram.handler.update.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
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
@Slf4j
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
        dto.setTelegramId(registration.getIdTelegram());

        String tgValue = ticket.getDynamicField()
                .stream()
                .filter(e -> e.getName().equals("Telegram"))
                .findFirst()
                .get()
                .getValue();

        log.info("Состояние - {}", state);
        log.info("DTO - {} => {}", dto.getTicketId(), dto.getDirection());

        ticketUpdate = rest.getTicketOperationUpdate(update, dto, tgValue, ticket.getOwner());

        if (ticketUpdate.isPresent() && ticketUpdate.get().getError() == null) {
            resultOperationToChat(update, bot, true);
        }else{
            log.info("Ошибка в операции смены статуса по заявке #{}", dto.getTicketId());
            resultOperationToChat(update, bot, false);
        }
    }
}
