package ru.newsystems.nispro_bot.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.newsystems.nispro_bot.base.model.domain.handleServices.HandleServices;
import ru.newsystems.nispro_bot.webservice.services.TelegramBotNotificationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/handleServices")
@Log4j2
public class PreviewController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TelegramBotNotificationService service;

    public PreviewController(TelegramBotNotificationService service) {
        this.service = service;
    }

    @SneakyThrows
    @PostMapping("/sendNote")
    public ResponseEntity<HandleServices> sendNote(@Valid @RequestBody String json) {
        HandleServices handle = objectMapper.readValue(json, HandleServices.class);
        if (handle == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        service.saveEntity(handle.getTicket(), handle.getTicket()
                .getArticles()
                .get(handle.getTicket().getArticles().size() - 1), handle.getEvent().getEvent() + ":sendNote");
        return new ResponseEntity<>(handle, HttpStatus.OK);
    }

    @SneakyThrows
    @PostMapping("/newTicket")
    public ResponseEntity<HandleServices> newTicket(@Valid @RequestBody String json) {
        HandleServices handle = objectMapper.readValue(json, HandleServices.class);
        if (handle == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        service.saveEntity(handle.getTicket(), handle.getTicket().getArticles().get(0), handle.getEvent().getEvent() + ":newTicket");
        return new ResponseEntity<>(handle, HttpStatus.OK);
    }

}
