package ru.newsystems.nispro_bot.base.model.state;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum ReplyKeyboardButton {
    HOME("\uD83C\uDFE0 В список "),
    BACK("◀️ Назад"),
    TO("Вперед ▶️"),
    DOWNLOAD("\uD83D\uDD87"),
    COMMENT("\uD83D\uDCDD"),
    NONE("???"),
    SEND_BACK("⭕️"),
    SEND_CLOSE("\uD83D\uDFE2")
    ;

    private String label;

    ReplyKeyboardButton(String label) {
        this.label = label;
    }

    public static ReplyKeyboardButton getState(String label) {
        return switch (label) {
            case "Домой" -> HOME;
            case "Назад" -> BACK;
            case "Дальше" -> TO;
            default -> NONE;
        };
    }

    public static Optional<ReplyKeyboardButton> parse(String name) {
        return Arrays.stream(values())
                .filter(b -> b.name().equalsIgnoreCase(name) || b.label.equalsIgnoreCase(name))
                .findFirst();
    }
}
