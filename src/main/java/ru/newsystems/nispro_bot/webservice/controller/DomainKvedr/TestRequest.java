package ru.newsystems.nispro_bot.webservice.controller.DomainKvedr;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TestRequest {
    String text;
    int number;
}
