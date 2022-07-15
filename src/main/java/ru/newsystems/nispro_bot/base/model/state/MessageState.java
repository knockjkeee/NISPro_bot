package ru.newsystems.nispro_bot.base.model.state;

import lombok.Getter;

@Getter
public enum MessageState {
    SHOW("Показать"),
    EXIT("Закрыть"),
    CREATETICKET("Создать заявку"),
    SENDCOMMENT("Отправить сообщение"),
    DOWLOADFILE("Выгрузить документы из последнего сообщения"),
    NONE("Empty");

    private String name;

    MessageState(String name) {
        this.name = name;
    }

    public static MessageState getState(String value) {
        return switch (value) {
            case "Показать" -> SHOW;
            case "Закрыть" -> EXIT;
            case "Отправить сообщение" -> SENDCOMMENT;
            case "Выгрузить документы из последнего сообщения" -> DOWLOADFILE;
            default -> NONE;
        };
    }
}
