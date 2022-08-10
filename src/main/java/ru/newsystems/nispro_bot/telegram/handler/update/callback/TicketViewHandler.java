package ru.newsystems.nispro_bot.telegram.handler.update.callback;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.domain.Article;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;
import ru.newsystems.nispro_bot.base.model.dto.callback.TicketViewDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;
import ru.newsystems.nispro_bot.config.cache.CacheStore;

import java.util.List;
import java.util.Optional;

import static ru.newsystems.nispro_bot.telegram.utils.Button.editedInlineKeyboard;
import static ru.newsystems.nispro_bot.telegram.utils.Button.prepareButtonsFromAllArticles;
import static ru.newsystems.nispro_bot.telegram.utils.Messages.prepareTextForTickerAndAllArticle;
import static ru.newsystems.nispro_bot.telegram.utils.Notification.queryIsMissing;


@Component
public class TicketViewHandler extends CallbackUpdateHandler<TicketViewDTO> {

    private final VirtaBot bot;
    private final CacheStore<TicketGetDTO> cache;

    public TicketViewHandler(VirtaBot bot, CacheStore<TicketGetDTO> cache) {
        this.bot = bot;
        this.cache = cache;
    }

    @Override
    protected Class<TicketViewDTO> getDtoType() {
        return TicketViewDTO.class;
    }

    @Override
    protected SerializableInlineType getSerializableType() {
        return SerializableInlineType.TICKET_VIEW;
    }

    @Override
    protected void handleCallback(Update update, TicketViewDTO dto) throws TelegramApiException {

        TicketGetDTO ticket = cache.get(update.getCallbackQuery().getMessage().getChatId());
        if (ticket != null) {
//            bot.execute(SendChatAction
//                    .builder()
//                    .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
//                    .action(ActionType.TYPING.toString())
//                    .build());

            Optional<TicketJ> currentTicket = ticket
                    .getTickets()
                    .stream()
                    .filter(e -> e.getTicketNumber().equals(dto.getTicketId()))
                    .findFirst();
            if (currentTicket.isPresent()) {
                int page = 1;
                TicketJ ticketView = currentTicket.get();
                //TODO отключаем навигацию на коментарии
//                Article article = ticketView.getArticles().get(0);

                List<Article> articles = ticketView.getArticles();
                List<List<InlineKeyboardButton>> inlineKeyboard = prepareButtonsFromAllArticles(update.getCallbackQuery().getMessage().getChatId(), articles, page, ticketView);
                String resultText = prepareTextForTickerAndAllArticle(page, ticketView, articles);

                bot.execute(EditMessageText
                        .builder()
                        .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                        .messageId(update.getCallbackQuery().getMessage().getMessageId())
                        .text(resultText)
                        .parseMode(ParseMode.HTML)
                        .build());
                editedInlineKeyboard(update, InlineKeyboardMarkup.builder().keyboard(inlineKeyboard), bot);
            } else {
                errorByQuery(update);
            }
        } else {
            errorByQuery(update);
        }
    }

    private void errorByQuery(Update update) throws TelegramApiException {
        editedInlineKeyboard(update, InlineKeyboardMarkup.builder().clearKeyboard(), bot);
        queryIsMissing(update, bot);
    }
}
