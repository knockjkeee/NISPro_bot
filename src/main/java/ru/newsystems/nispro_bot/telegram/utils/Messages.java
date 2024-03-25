package ru.newsystems.nispro_bot.telegram.utils;


import ru.newsystems.nispro_bot.base.model.domain.Article;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;
import ru.newsystems.nispro_bot.base.model.state.TicketState;

import java.util.List;
import java.util.stream.Collectors;

import static ru.newsystems.nispro_bot.base.utils.StringUtil.getDateTimeFormat;


public class Messages {

    public static String prepareTextTicket(TicketJ ticket) {
        String formatDateTime = getDateTimeFormat(ticket.getCreated());
        return "<b>Результат поиска:</b>"
               + "\nId <i>"
               + ticket.getTicketID()
               + "</i>"
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
                + article.getTo().replaceAll("<", "").replaceAll(">", "")
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
                + "\n\n<b>Комментарий №"
                + page
                + " (id "
                + article.getArticleID()
                + ")"
                + ":</b>";
        return prepareTextArticle(article, sizeAttach, lastComment);
    }

    public static String prepareTextForTickerAndAllArticle(int page, TicketJ ticketView, List<Article> article) {
        String ticketText = prepareTextTicket(ticketView);
        String countTMsgText = ticketText + "\n<i>Количество комментариев:</i>  " + ticketView
                .getArticles()
                .size() + "\n";
        String comments = article.stream().map(e -> {
            return "\n<b>Комментарий №" + e.getArticleID() + " от " + e.getFrom().replaceAll("<", "").replaceAll(">", "") + "</b>" + "\n >>> " +
                    e.getBody().replaceAll("[\n\r]$", "");
        }).collect(Collectors.joining("\n"));

        return countTMsgText + comments + "\n\n \uD83D\uDCBE \uD83D\uDDC2 <i>Количество файлов прикрепленных к комментариям:</i> \t"
                + (int) article.stream()
                .filter(e -> e.getAttachments() != null)
                .mapToLong(x -> x.getAttachments().size())
                .sum();
    }

    public static String prepareHelpMsg() {
        return """
                <b>Рекомендации для работы</b><i>Существующий функционал:</i>

                1 Быстрый поиск - Для быстрого поиска Вам необходимо сообщить боту № Заявки. Это числовое значение из 15 символов. В результате бот продемонстрирует детализированный контекст искомой заявок с прикрепленным последним комментарием. Из Функций Вам будет доступно:
                    * Добавить комментарий
                    * Выгрузить из последнего комментария документы если они прикреплены

                2 Расширенный поиск - Воспользоваться этой функцией Вы можете, выбрав из команд бота команду <b>/my_ticket</b>. Вашему вниманию откроется детализированный навигационная панель с вашими активными заявками. Самая важная особенность этого функционала, Вы всегда сможете проконтролировать полный контекст, со всеми комментариями и файлами по любой активной заявки созданной Вами. Из Функций Вам будет доступно все тоже что есть и в формате быстрого поиска:
                    * Добавить комментарий
                    * Выгрузить из последнего комментария документы если они прикреплены

                3 Создание заявки - Воспользоваться этой функцией Вы можете, выбрав из команд бота команду <b>/create_ticket</b>. От Вашего имени создастся заявка в очередь, которая числиться за Вами в системе""";
    }
}
