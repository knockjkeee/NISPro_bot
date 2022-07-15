package ru.newsystems.nispro_bot.base.utils;

public class NumberUtil {
    public static final int LENGTH_CHAR_BY_ID = 14;
    private static final int LENGTH_CHAR_BY_TICKET_NUMBER = 15;

    public static long getId(String text) {
        if (isCurrentLength(text, LENGTH_CHAR_BY_ID)) {
            try {
                return Long.parseLong(text);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    public static long getIdByTicketNumber(String text) {
        if (text == null) return 0;
        if (isCurrentLength(text, LENGTH_CHAR_BY_TICKET_NUMBER)) {
            try {
                return Long.parseLong(text);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    public static boolean isCurrentLength(String text, int len) {
        if (len == LENGTH_CHAR_BY_ID) {
            return text.length() <= len;
        }
        if (len == LENGTH_CHAR_BY_TICKET_NUMBER) {
            return text.length() >= len;
        }
        return false;
    }
}
