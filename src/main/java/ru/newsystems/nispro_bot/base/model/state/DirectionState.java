package ru.newsystems.nispro_bot.base.model.state;

import lombok.Getter;

@Getter
public enum DirectionState {

    TO("to"),
    BACK("back")
    ;

    private String direction;


    DirectionState(String direction) {
        this.direction = direction;
    }
}
