package ru.newsystems.nispro_bot.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class StringUtil {

    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        return str.trim().length() == 0;
    }

    public static String trim(String str) {
        if (isBlank(str)) {
            return "";
        }
        return str.trim();
    }

    public static String serialize(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot serialize " + o, e);
        }
    }

    public static <T> Optional<T> deserialize(String json, Class<T> c) {
        try {
            return Optional.ofNullable(new ObjectMapper().readValue(json, c));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static String getDateTimeFormat(String created) {
        String ticketDateTime = created.replaceAll("\\s+", "T");
        return LocalDateTime
                .parse(ticketDateTime)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm:ss"));
    }
}
