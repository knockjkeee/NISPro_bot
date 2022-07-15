package ru.newsystems.nispro_bot.webservice.handler.update.callback;

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
import ru.newsystems.nispro_bot.base.model.dto.callback.ArticlesNavigationViewDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;
import ru.newsystems.nispro_bot.webservice.config.cache.CacheStore;

import java.util.List;
import java.util.Optional;

import static ru.newsystems.nispro_bot.webservice.Telegram.Button.editedInlineKeyboard;
import static ru.newsystems.nispro_bot.webservice.Telegram.Button.prepareButtonsFromArticles;
import static ru.newsystems.nispro_bot.webservice.Telegram.Messages.prepareTextForTickerAndArticle;
import static ru.newsystems.nispro_bot.webservice.Telegram.Notification.queryIsMissing;


@Component
public class ArticlesNavigationViewHandler extends CallbackUpdateHandler<ArticlesNavigationViewDTO> {

    private final VirtaBot bot;
    private final CacheStore<TicketGetDTO> cache;

    public ArticlesNavigationViewHandler(VirtaBot bot, CacheStore<TicketGetDTO> cache) {
        this.bot = bot;
        this.cache = cache;
    }

    @Override
    protected Class<ArticlesNavigationViewDTO> getDtoType() {
        return ArticlesNavigationViewDTO.class;
    }

    @Override
    protected SerializableInlineType getSerializableType() {
        return SerializableInlineType.ARTICLE_NAVIGATION;
    }

    @Override
    protected void handleCallback(Update update, ArticlesNavigationViewDTO dto) throws TelegramApiException {

        TicketGetDTO ticket = cache.get(update.getCallbackQuery().getMessage().getChatId());
        if (ticket != null) {
            Optional<TicketJ> currentTicket = ticket
                    .getTickets()
                    .stream()
                    .filter(e -> e.getTicketNumber().equals(dto.getTicketNumber()))
                    .findFirst();
            if (currentTicket.isPresent()) {
                if (dto.getDirection().equals("to")) {
                    int page = dto.getPage() + 1;
                    TicketJ ticketView = currentTicket.get();
                    Article article = ticketView.getArticles().get(dto.getPage());
                    List<List<InlineKeyboardButton>> inlineKeyboard = prepareButtonsFromArticles(update.getCallbackQuery().getMessage().getChatId(), article, page, ticketView);
                    prepareDataAndExecute(update, page, ticketView, article, inlineKeyboard);
                }
                if (dto.getDirection().equals("back")) {
                    int page = dto.getPage() - 1;
                    TicketJ ticketView = currentTicket.get();
                    Article article = ticketView.getArticles().get(page);
                    List<List<InlineKeyboardButton>> inlineKeyboard = prepareButtonsFromArticles(update.getCallbackQuery().getMessage().getChatId(), article, page, ticketView);
                    prepareDataAndExecute(update, page, ticketView, article, inlineKeyboard);
                }
            } else {
                errorByQuery(update);
            }
        } else {
            errorByQuery(update);
        }
    }

    private void prepareDataAndExecute(Update update, int page, TicketJ ticket, Article article, List<List<InlineKeyboardButton>> inlineKeyboard) throws TelegramApiException {
        String resultText = prepareTextForTickerAndArticle(page, ticket, article);

        bot.execute(EditMessageText
                .builder()
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .text(resultText)
                .parseMode(ParseMode.HTML)
                .build());
        editedInlineKeyboard(update, InlineKeyboardMarkup.builder().keyboard(inlineKeyboard), bot);
    }


    private void errorByQuery(Update update) throws TelegramApiException {
        editedInlineKeyboard(update, InlineKeyboardMarkup.builder().clearKeyboard(), bot);
        queryIsMissing(update, bot);
    }
}
