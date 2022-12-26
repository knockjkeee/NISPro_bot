package ru.newsystems.nispro_bot.telegram.handler.update.callback;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.dto.callback.TicketsHomeViewDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;
import ru.newsystems.nispro_bot.config.cache.CacheStore;

import java.util.List;

import static ru.newsystems.nispro_bot.telegram.utils.Button.editedInlineKeyboard;
import static ru.newsystems.nispro_bot.telegram.utils.Button.prepareButtonsFromTickets;
import static ru.newsystems.nispro_bot.telegram.utils.Notification.queryIsMissing;


@Component
public class TicketsHomeViewHandler extends CallbackUpdateHandler<TicketsHomeViewDTO> {

    private final VirtaBot bot;
    private final CacheStore<TicketGetDTO> cache;

    public TicketsHomeViewHandler(VirtaBot bot, CacheStore<TicketGetDTO> cache) {
        this.bot = bot;
        this.cache = cache;
    }

    @Override
    protected Class<TicketsHomeViewDTO> getDtoType() {
        return TicketsHomeViewDTO.class;
    }

    @Override
    protected SerializableInlineType getSerializableType() {
        return SerializableInlineType.TICKET_HOME;
    }

    @Override
    protected void handleCallback(Update update, TicketsHomeViewDTO dto, TelegramBotRegistration registration) throws TelegramApiException {

        TicketGetDTO ticket = cache.get(update.getCallbackQuery().getMessage().getChatId());
        if (ticket != null) {
//            bot.execute(SendChatAction
//                    .builder()
//                    .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
//                    .action(ActionType.TYPING.toString())
//                    .build());

            List<List<InlineKeyboardButton>> inlineKeyboard = prepareButtonsFromTickets(ticket.getTickets(), 1, ticket.getTickets().size(), registration.getLogin());

            bot.execute(EditMessageText
                    .builder()
                    .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                    .messageId(update.getCallbackQuery().getMessage().getMessageId())
                    .text("<pre>Количество открытых заявок: <b>" + ticket.getTickets().size() + "</b></pre>")
                    .parseMode(ParseMode.HTML)
                    .build());
            editedInlineKeyboard(update, InlineKeyboardMarkup.builder().keyboard(inlineKeyboard), bot);

        } else {
            editedInlineKeyboard(update, InlineKeyboardMarkup.builder().clearKeyboard(), bot);
            queryIsMissing(update, bot);
        }
    }


}
