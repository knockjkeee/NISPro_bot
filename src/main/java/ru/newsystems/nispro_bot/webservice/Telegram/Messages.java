package ru.newsystems.nispro_bot.webservice.Telegram;


import ru.newsystems.nispro_bot.base.model.domain.Article;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;
import ru.newsystems.nispro_bot.base.model.state.TicketState;

import static ru.newsystems.nispro_bot.base.utils.StringUtil.getDateTimeFormat;


public class Messages {

    public static String prepareTextTicket(TicketJ ticket) {
        String formatDateTime = getDateTimeFormat(ticket.getCreated());
        return "<pre>Результат поиска:</pre>"
                + "\n№ <i>"
                + ticket.getTicketNumber()
                + "</i>"
                + "\t\t\t"
                + TicketState.getState(ticket.getState()).getLabel()
                + "\t"
                + TicketState.getState(ticket.getLock()).getLabel()
                + "\n<i>От:</i> \t"
                + formatDateTime
                + "\n<i>Очередь:</i> \t"
                + ticket.getQueue()
                + "\n<i>Приоритет:</i> \t"
                + ticket.getPriority()
                + "\n<i>Заголовок:</i> \t"
                + ticket.getTitle();
    }

    public static String prepareTextArticle(Article article, int sizeAttach, String text) {
        String formatDateTime = getDateTimeFormat(article.getCreateTime());
        return text
                + "\n<i>От:</i> \t"
                + formatDateTime
                + "\n<i>Заголовок:</i> \t"
                + article.getSubject()
                + "\n<i>От кого:</i> \t"
                + article.getFrom().replaceAll("<", "").replaceAll(">", "")
                + "\n<i>Кому:</i> \t"
                + article.getTo()
                + "\n<i>Тело сообщения:</i> "
                + article.getBody()
                + "\n<i>Количество файлов прикрепленных к комментарию:</i> \t"
                + sizeAttach;
    }

    public static String prepareTextForTickerAndArticle(int page, TicketJ ticketView, Article article) {
        String ticketText = prepareTextTicket(ticketView);
        String countTMsgText = ticketText + "\n<i>Количество комментариев:</i>  " + page + " из " + ticketView
                .getArticles()
                .size();
        int sizeAttach = article.getAttachments() == null ? 0 : article.getAttachments().size();
        String lastComment = countTMsgText
                + "\n\n<pre>Комментарий №"
                + page
                + " (id "
                + article.getArticleID()
                + ")"
                + ":</pre>";
        return prepareTextArticle(article, sizeAttach, lastComment);
    }
}
