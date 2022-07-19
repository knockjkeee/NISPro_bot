package ru.newsystems.nispro_bot.base.model.state;

import lombok.Getter;

@Getter
public enum ErrorState {

    NOT_AUTHORIZED("not authorized", "403")
    ;

    private String msg;
    private String code;

    ErrorState(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
}
