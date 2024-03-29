package ru.newsystems.nispro_bot.telegram.handler.update.callback;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;
import ru.newsystems.nispro_bot.base.model.dto.callback.TicketsNavigationViewDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.base.model.state.DirectionState;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;
import ru.newsystems.nispro_bot.config.cache.CacheStore;
import ru.newsystems.nispro_bot.telegram.utils.Button;

import java.util.List;
import java.util.stream.Collectors;

import static ru.newsystems.nispro_bot.telegram.utils.Button.editedInlineKeyboard;
import static ru.newsystems.nispro_bot.telegram.utils.Button.prepareButtonsFromTickets;
import static ru.newsystems.nispro_bot.telegram.utils.Notification.queryIsMissing;


@Component
public class TicketsNavigationViewHandler extends CallbackUpdateHandler<TicketsNavigationViewDTO> {

    private final VirtaBot bot;
    private final CacheStore<TicketGetDTO> cache;

    public TicketsNavigationViewHandler(VirtaBot bot, CacheStore<TicketGetDTO> cache) {
        this.bot = bot;
        this.cache = cache;
    }

    @Override
    protected Class<TicketsNavigationViewDTO> getDtoType() {
        return TicketsNavigationViewDTO.class;
    }

    @Override
    protected SerializableInlineType getSerializableType() {
        return SerializableInlineType.TICKETS_NAVIGATION;
    }

    @Override
    protected void handleCallback(Update update, TicketsNavigationViewDTO dto, TelegramBotRegistration registration) throws TelegramApiException {

        TicketGetDTO ticket = cache.get(update.getCallbackQuery().getMessage().getChatId());
        if (ticket != null) {
            String direction = dto.getDirection();
            if (direction.equals(DirectionState.TO.getDirection())) {
                List<TicketJ> tickets = ticket
                        .getTickets()
                        .stream()
                        .skip((long) (dto.getPage() * Button.COUNT_ITEM_IN_PAGE))
                        .collect(Collectors.toList());

                List<List<InlineKeyboardButton>> inlineKeyboard = prepareButtonsFromTickets(tickets, dto.getPage()
                        + 1, ticket.getTickets().size(), registration.getLogin());

                editedInlineKeyboard(update, InlineKeyboardMarkup.builder().keyboard(inlineKeyboard), bot);
            }
            if (direction.equals(DirectionState.BACK.getDirection())) {
                List<TicketJ> tickets = ticket
                        .getTickets()
                        .stream()
                        .skip((long) ((dto.getPage() - 1) * Button.COUNT_ITEM_IN_PAGE - Button.COUNT_ITEM_IN_PAGE))
                        .collect(Collectors.toList());

                List<List<InlineKeyboardButton>> inlineKeyboard = prepareButtonsFromTickets(tickets, dto.getPage()
                        - 1, ticket.getTickets().size(), registration.getLogin());

                editedInlineKeyboard(update, InlineKeyboardMarkup.builder().keyboard(inlineKeyboard), bot);
            }
        } else {
            editedInlineKeyboard(update, InlineKeyboardMarkup.builder().clearKeyboard(), bot);
            queryIsMissing(update, bot);
        }
    }


}
