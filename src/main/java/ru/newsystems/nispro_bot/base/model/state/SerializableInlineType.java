package ru.newsystems.nispro_bot.base.model.state;

public enum SerializableInlineType {
    DOWNLOAD(0),
    SEND_COMMENT(1),
    TICKETS_NAVIGATION(2),
    TICKET_VIEW(3),
    TICKET_HOME(4),
    ARTICLE_NAVIGATION(5),
    ARTICLE_VIEW(6),
    ;

    private final int index;

    SerializableInlineType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
