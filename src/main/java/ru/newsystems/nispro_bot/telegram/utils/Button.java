package ru.newsystems.nispro_bot.telegram.utils;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.domain.Article;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;
import ru.newsystems.nispro_bot.base.model.dto.callback.*;
import ru.newsystems.nispro_bot.base.model.state.DirectionState;
import ru.newsystems.nispro_bot.base.model.state.ReplyKeyboardButton;
import ru.newsystems.nispro_bot.base.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Button {
    public static final double COUNT_ITEM_IN_PAGE = 6.0;

    public static double getAllPages(int size) {
        return Math.ceil((double) size / COUNT_ITEM_IN_PAGE);
    }

    public static List<List<InlineKeyboardButton>> prepareButtonsFromTickets(List<TicketJ> tickets, int page, int fullSize, String login) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        prepareRowButtonFromTickets(tickets, buttons, login);
        prepareNavigationButtonFromTickets(page, buttons, fullSize);
        return buttons;
    }

    public static List<List<InlineKeyboardButton>> prepareButtonsFromArticles(Long chatId, Article article, int page, TicketJ ticket) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(List.of(InlineKeyboardButton.builder()
                .text(ReplyKeyboardButton.COMMENT.getLabel() + " Отправить комментарий")
                .callbackData(StringUtil.serialize(new SendDataDTO(ticket.getTicketNumber())))
                .build()));


        if (article.getAttachments() != null && article.getAttachments().size() > 0) {
            buttons.add(List.of(InlineKeyboardButton.builder()
                    .text(ReplyKeyboardButton.DOWNLOAD.getLabel() + " Выгрузить документы")
                    .callbackData(StringUtil.serialize(new DownloadFilesDTO(chatId, ticket.getTicketNumber(), article.getArticleID(), "d")))
                    .build()));
        }
        prepareNavigationButtonFromArticles(page, buttons, ticket);
        return buttons;
    }

    public static List<List<InlineKeyboardButton>> prepareButtonsFromAllArticles(Long chatId, List<Article> articles, int page, TicketJ ticket, String login) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(List.of(InlineKeyboardButton.builder()
                .text(ReplyKeyboardButton.COMMENT.getLabel() + " Отправить комментарий")
                .callbackData(StringUtil.serialize(new SendDataDTO(ticket.getTicketNumber())))
                .build()));
        String state = ticket.getState();

        if (!checkLoginForStatus(login, ticket.getResponsible(), ticket.getState()).isEmpty()) {
            addChangeStateButton(ticket, buttons, state);
        }

        if (articles.stream().anyMatch(e -> e.getAttachments() != null && e.getAttachments().size() > 0)) {
            buttons.add(List.of(InlineKeyboardButton.builder()
                    .text(ReplyKeyboardButton.DOWNLOAD.getLabel() + " Выгрузить документы")
                    .callbackData(StringUtil.serialize(new DownloadFilesDTO(chatId, ticket.getTicketNumber(), 0L, "d")))
                    .build()));
        }
        buttons.add(List.of(InlineKeyboardButton.builder()
                .text(ReplyKeyboardButton.HOME.getLabel() + "Заявок")
                .callbackData(StringUtil.serialize(new TicketsHomeViewDTO(0)))
                .build()));
        return buttons;
    }

    private static void addChangeStateButton(TicketJ ticket, List<List<InlineKeyboardButton>> buttons, String state) {
        switch (state){
            case "open":
            case "открыта":
                buttons.add(List.of(
                        InlineKeyboardButton.builder()
                                .text(ReplyKeyboardButton.SEND_BACK.getLabel() + " Вернуть")
                                .callbackData(StringUtil.serialize(new ChangeStatusDTO(ticket.getTicketNumber(), "b", null)))
                                .build(),
                        InlineKeyboardButton.builder()
                                .text(ReplyKeyboardButton.SEND_CLOSE.getLabel() + " Закрыть")
                                .callbackData(StringUtil.serialize(new ChangeStatusDTO(ticket.getTicketNumber(), "c", null)))
                                .build()));
                break;
            case "new":
            case "новая":
                buttons.add(List.of(
                        InlineKeyboardButton.builder()
                                .text(ReplyKeyboardButton.COMMENT.getLabel() + " Принять в работу")
                                .callbackData(StringUtil.serialize(new ChangeStatusDTO(ticket.getTicketNumber(), "a", null)))
                                .build(),
                        InlineKeyboardButton.builder()
                                .text(ReplyKeyboardButton.SEND_CLOSE.getLabel() + " Закрыть")
                                .callbackData(StringUtil.serialize(new ChangeStatusDTO(ticket.getTicketNumber(), "c", null)))
                                .build()));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }
    }


    private static void prepareNavigationButtonFromTickets(int page, List<List<InlineKeyboardButton>> buttons, int fullSizeData) {
        if (fullSizeData > 6) {
            double allPages = getAllPages(fullSizeData);
            if (page == 1) {
                buttons.add(List.of(InlineKeyboardButton.builder()
                        .text(ReplyKeyboardButton.TO.getLabel())
                        .callbackData(StringUtil.serialize(new TicketsNavigationViewDTO(page, DirectionState.TO.getDirection())))
                        .build()));
            } else if (allPages == page) {
                buttons.add(List.of(InlineKeyboardButton.builder()
                        .text(ReplyKeyboardButton.BACK.getLabel())
                        .callbackData(StringUtil.serialize(new TicketsNavigationViewDTO(page, DirectionState.BACK.getDirection())))
                        .build()));
            } else {
                buttons.add(List.of(InlineKeyboardButton.builder()
                        .text(ReplyKeyboardButton.BACK.getLabel())
                        .callbackData(StringUtil.serialize(new TicketsNavigationViewDTO(page, DirectionState.BACK.getDirection())))
                        .build(), InlineKeyboardButton.builder()
                        .text(ReplyKeyboardButton.TO.getLabel())
                        .callbackData(StringUtil.serialize(new TicketsNavigationViewDTO(page, DirectionState.TO.getDirection())))
                        .build()));
            }
        }
    }

    private static void prepareNavigationButtonFromArticles(int page, List<List<InlineKeyboardButton>> buttons, TicketJ ticket) {
        int fullSizeData = ticket.getArticles().size();
        buttons.add(List.of(InlineKeyboardButton.builder()
                .text(ReplyKeyboardButton.HOME.getLabel() + "Заявок")
                .callbackData(StringUtil.serialize(new TicketsHomeViewDTO(0)))
                .build()));
        if (fullSizeData > 1) {
//            double allPages = getAllPages(fullSizeData);
            if (page == 1) {
                buttons.add(List.of(InlineKeyboardButton.builder()
                        .text(ReplyKeyboardButton.TO.getLabel())
                        .callbackData(StringUtil.serialize(new ArticlesNavigationViewDTO(page, DirectionState.TO.getDirection(), ticket.getTicketNumber())))
                        .build()));
            } else if (fullSizeData == page) {
                buttons.add(List.of(InlineKeyboardButton.builder()
                        .text(ReplyKeyboardButton.BACK.getLabel())
                        .callbackData(StringUtil.serialize(new ArticlesNavigationViewDTO(page, DirectionState.BACK.getDirection(), ticket.getTicketNumber())))
                        .build()));
            } else {
                buttons.add(List.of(InlineKeyboardButton.builder()
                        .text(ReplyKeyboardButton.BACK.getLabel())
                        .callbackData(StringUtil.serialize(new ArticlesNavigationViewDTO(page, DirectionState.BACK.getDirection(), ticket.getTicketNumber())))
                        .build(), InlineKeyboardButton.builder()
                        .text(ReplyKeyboardButton.TO.getLabel())
                        .callbackData(StringUtil.serialize(new ArticlesNavigationViewDTO(page, DirectionState.TO.getDirection(), ticket.getTicketNumber())))
                        .build()));
            }
        }
    }

    private static String checkLoginForStatus(String login, String responsible, String status){
        if (login.equals(responsible)){
//            if (status.equals("закрыта успешно") || status.equals("closed successful")){
//                return "✅";
//            }
            return"\uD83D\uDD14";
        }
        return "";
    }

    private static void prepareRowButtonFromTickets(List<TicketJ> tickets, List<List<InlineKeyboardButton>> buttons, String login) {
        if (tickets.size() <= 3) {
            tickets.forEach(ticket -> buttons.add(List.of(InlineKeyboardButton.builder()
                    .text(ticket.getTicketNumber() + checkLoginForStatus(login, ticket.getResponsible(), ticket.getState()))
                    .callbackData(StringUtil.serialize(new TicketViewDTO(ticket.getTicketNumber(), 0)))
                    .build())));
        } else if (tickets.size() == 4) {
            List<InlineKeyboardButton> firstRow = getListInlineKeyboardFromTickets(tickets, 0, 2, login);
            List<InlineKeyboardButton> secondRow = getListInlineKeyboardFromTickets(tickets, 2, 4, login);
            buttons.add(firstRow);
            buttons.add(secondRow);
        } else if (tickets.size() == 5) {
            List<InlineKeyboardButton> firstRow = getListInlineKeyboardFromTickets(tickets, 0, 2, login);
            List<InlineKeyboardButton> secondRow = getListInlineKeyboardFromTickets(tickets, 2, 4, login);
            List<InlineKeyboardButton> thirdRow = getListInlineKeyboardFromTickets(tickets, 4, 5, login);
            buttons.add(firstRow);
            buttons.add(secondRow);
            buttons.add(thirdRow);
        } else {
            List<InlineKeyboardButton> firstRow = getListInlineKeyboardFromTickets(tickets, 0, 2, login);
            List<InlineKeyboardButton> secondRow = getListInlineKeyboardFromTickets(tickets, 2, 4, login);
            List<InlineKeyboardButton> thirdRow = getListInlineKeyboardFromTickets(tickets, 4, 6, login);
            buttons.add(firstRow);
            buttons.add(secondRow);
            buttons.add(thirdRow);
        }
    }

    private static void prepareRowButtonFromArticles(List<Article> articles, List<List<InlineKeyboardButton>> buttons) {
        if (articles.size() <= 3) {
            articles.forEach(article -> buttons.add(List.of(InlineKeyboardButton.builder()
                    .text(String.valueOf(article.getArticleID()))
                    .callbackData(StringUtil.serialize(new ArticleViewDTO(String.valueOf(article.getArticleID()), 0)))
                    .build())));
        } else if (articles.size() == 4) {
            List<InlineKeyboardButton> firstRow = getListInlineKeyboardFromArticles(articles, 0, 2);
            List<InlineKeyboardButton> secondRow = getListInlineKeyboardFromArticles(articles, 2, 4);
            buttons.add(firstRow);
            buttons.add(secondRow);
        } else if (articles.size() == 5) {
            List<InlineKeyboardButton> firstRow = getListInlineKeyboardFromArticles(articles, 0, 2);
            List<InlineKeyboardButton> secondRow = getListInlineKeyboardFromArticles(articles, 2, 4);
            List<InlineKeyboardButton> thirdRow = getListInlineKeyboardFromArticles(articles, 4, 5);
            buttons.add(firstRow);
            buttons.add(secondRow);
            buttons.add(thirdRow);
        } else {
            List<InlineKeyboardButton> firstRow = getListInlineKeyboardFromArticles(articles, 0, 2);
            List<InlineKeyboardButton> secondRow = getListInlineKeyboardFromArticles(articles, 2, 4);
            List<InlineKeyboardButton> thirdRow = getListInlineKeyboardFromArticles(articles, 4, 6);
            buttons.add(firstRow);
            buttons.add(secondRow);
            buttons.add(thirdRow);
        }
    }

    private static List<InlineKeyboardButton> getListInlineKeyboardFromTickets(List<TicketJ> tickets, int start, int end, String login) {
        return IntStream.range(start, end)
                .mapToObj(index -> InlineKeyboardButton.builder()
                        .text(tickets.get(index).getTicketNumber() + checkLoginForStatus(login, tickets.get(index).getResponsible(),  tickets.get(index).getState()))
                        .callbackData(StringUtil.serialize(new TicketViewDTO(tickets.get(index).getTicketNumber(), 0)))
                        .build())
                .collect(Collectors.toList());
    }

    private static List<InlineKeyboardButton> getListInlineKeyboardFromArticles(List<Article> articles, int start, int end) {
        return IntStream.range(start, end)
                .mapToObj(index -> InlineKeyboardButton.builder()
                        .text(String.valueOf(articles.get(index).getArticleID()))
                        .callbackData(StringUtil.serialize(new ArticleViewDTO(String.valueOf(articles.get(index)
                                .getArticleID()), 0)))
                        .build())
                .collect(Collectors.toList());
    }

    public static void editedInlineKeyboard(Update update, InlineKeyboardMarkup.InlineKeyboardMarkupBuilder inlineKeyboardMarkupBuilder, VirtaBot bot) throws TelegramApiException {
        bot.execute(EditMessageReplyMarkup.builder()
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(inlineKeyboardMarkupBuilder.build())
                .build());
    }
}
