package ru.newsystems.nispro_bot.base.model.state;

import lombok.Getter;

@Getter
public enum TicketState {
    NEW("new", "\uD83D\uDD18"),
    NEW2("новая", "\uD83D\uDD18"),
    CLOSED_SECCESSFUL("closed successful", "\uD83D\uDFE1"),
    CLOSED_SECCESSFUL2("закрыта успешно", "\uD83D\uDFE1"),
    CLOSED_UNSECCESSFUL("closed unsuccessful", "\uD83D\uDFE0"),
    OPEN("open", "\uD83D\uDFE2"),
    OPEN2("открыта", "\uD83D\uDFE2"),
    REMOVED("removed", "\uD83D\uDD34"),
    PENDING_REMINDER("pending reminder", "\uD83D\uDD50"),
    PENDING_AUTO_CLOSE_UP("pending auto close+", "\uD83D\uDD52"),
    PENDING_AUTO_CLOSE_DW("pending auto close-", "\uD83D\uDD53"),
    MERGED("merged", "\uD83D\uDD36"),
    CLOSE_WITH_WORKAROUND("closed with workaround", "\uD83D\uDD37"),
    REPORT("Отчетность", "\uD83D\uDCDD"),
    LOCK("lock", "\uD83D\uDD10"),
    UNLOCK("unlock", "\uD83D\uDD12"),
    NONE("NONE", "⚪️");

    private String name;
    private String label;

    TicketState(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public static TicketState getState(String value) {
        return switch (value) {
            case "new" -> NEW;
            case "новая" -> NEW2;
            case "closed successful" -> CLOSED_SECCESSFUL;
            case "закрыта успешно" -> CLOSED_SECCESSFUL2;
            case "closed unsuccessful" -> CLOSED_UNSECCESSFUL;
            case "open" -> OPEN;
            case "открыта" -> OPEN2;
            case "removed" -> REMOVED;
            case "pending reminder" -> PENDING_REMINDER;
            case "pending auto close+" -> PENDING_AUTO_CLOSE_UP;
            case "pending auto close-" -> PENDING_AUTO_CLOSE_DW;
            case "merged" -> MERGED;
            case "closed with workaround" -> CLOSE_WITH_WORKAROUND;
            case "Отчетность" -> REPORT;
            case "lock" -> LOCK;
            case "unlock" -> UNLOCK;
            default -> NONE;
        };
    }

}
