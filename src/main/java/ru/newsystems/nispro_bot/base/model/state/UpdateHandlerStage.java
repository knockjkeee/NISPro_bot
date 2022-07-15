package ru.newsystems.nispro_bot.base.model.state;

public enum UpdateHandlerStage {
    CALLBACK,
    TICKET,
    ID,
    ARTICLE,
    MESSAGE,
    COMMAND,
    REPLY_BUTTON;

    public int getOrder() {
        return ordinal();
    }
}
