package ru.newsystems.nispro_bot.base.model.state;

public enum UpdateHandlerStage {
    CALLBACK,
    MESSAGE,
    COMMAND,
    HIDE;

    public int getOrder() {
        return ordinal();
    }
}
