package ru.newsystems.nispro_bot.webservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PreviewController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TelegramBotNotificationService service;

    public PreviewController(TelegramBotNotificationService service) {
        this.service = service;
    }

   // @SneakyThrows
    @PostMapping("/sendNote")
    public ResponseEntity<HandleServices> sendNote(@Valid @RequestBody String json) {
        HandleServices handle = null;
        try {
            handle = objectMapper.readValue(json, HandleServices.class);
        } catch (JsonProcessingException e) {
            log.error("При добавлении нового комментария возникли проблемы при парсинге входящего обьекта");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        log.debug("Добавление нового комментария к заявке №{}", handle.getTicket().getTicketNumber());
        service.saveEntity(handle.getTicket(), handle.getTicket()
                .getArticles()
                .get(handle.getTicket().getArticles().size() - 1), handle.getEvent().getEvent() + ":sendNote");
        return new ResponseEntity<>(handle, HttpStatus.OK);
    }

    //@SneakyThrows
    @PostMapping("/newTicket")
    public ResponseEntity<HandleServices> newTicket(@Valid @RequestBody String json) {
        HandleServices handle = null;
        try {
            handle = objectMapper.readValue(json, HandleServices.class);
        } catch (JsonProcessingException e) {
            log.error("При создании новой заявки возникли проблемы при парсинге входящего обьекта");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        log.debug("Создание новой заявки №{}", handle.getTicket().getTicketNumber());
        service.saveEntity(handle.getTicket(), handle.getTicket().getArticles().get(0), handle.getEvent().getEvent() + ":newTicket");
        return new ResponseEntity<>(handle, HttpStatus.OK);
    }

//    @SneakyThrows
    @PostMapping("/changeResponsible")
    public ResponseEntity<HandleServices> changeResponsible(@Valid @RequestBody String json) {
        HandleServices handle = null;
        try {
            handle = objectMapper.readValue(json, HandleServices.class);
        } catch (JsonProcessingException e) {
            log.error("При смене ответственного возникли проблемы при парсинге входящего обьекта");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        log.debug("Изменение ответственного по заявке №{}", handle.getTicket().getTicketNumber());
        service.saveEntity(handle.getTicket(), handle.getTicket().getArticles().get(0));
        return new ResponseEntity<>(handle, HttpStatus.OK);
    }

}
