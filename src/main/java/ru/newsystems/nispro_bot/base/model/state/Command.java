package ru.newsystems.nispro_bot.base.model.state;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.newsystems.nispro_bot.base.utils.StringUtil;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum Command {


//    my_id - Информация для регистрации
//    my_ticket - Показать мои открытые заявки
//    create_ticket - Создать заявку
//    help - Помощь в работе
//    about - Обо мне

//    Взаимодействие с НИС-про|1.0

//    Этот бот демонстрирует большинство возможностей
//    взаимодействия с информационной системой
//    НИС-про|1.0


    MY_ID("/my_id", "Информация для регистрации"),
    MY_TICKET("/my_ticket", "Показать мои открытые заявки"),
    CREATE_TICKET("/create_ticket", "Создать заявку"),
    HELP("/help", "Помощь в работе"),
    ABOUT("/about", "Обо мне"),
    START("/start", "Init work"),
;
    private final String name;
    private final String desc;

    public static Optional<Command> parseCommand(String command) {
        if (StringUtil.isBlank(command)) {
            return Optional.empty();
        }
        String formatName = StringUtil.trim(command).toLowerCase();
        return Stream.of(values()).filter(c -> c.name.equalsIgnoreCase(formatName)).findFirst();
    }
}
