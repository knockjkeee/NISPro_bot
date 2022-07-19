package ru.newsystems.nispro_bot.telegram.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.domain.Error;
import ru.newsystems.nispro_bot.base.model.dto.domain.RequestDataDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketSearchDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketUpdateCreateDTO;
import ru.newsystems.nispro_bot.base.model.state.ErrorState;
import ru.newsystems.nispro_bot.webservice.services.TelegramBotRegistrationService;

import java.util.*;

@Service
public class RestNISService {
    private final RestTemplate restTemplate;

    private final TelegramBotRegistrationService service;

    @Value("${nis.pro.url}")
    private String baseUrl;
    @Value("${nis.pro.path}")
    private String path;
    @Value("${nis.pro.login}")
    private String login;
    @Value("${nis.pro.password}")
    private String password;

    public RestNISService(RestTemplate restTemplate, TelegramBotRegistrationService service) {
        this.restTemplate = restTemplate;
        this.service = service;
    }

    public Optional<TicketGetDTO> getTicketOperationGet(List<Long> id, Long msgId) {
        TelegramBotRegistration registration = registration(msgId);
        if (registration.getCompany() == null) {
            TicketGetDTO temp = new TicketGetDTO();
            Error error = new Error();
            error.setErrorCode(ErrorState.NOT_AUTHORIZED.getCode());
            temp.setError(error);
            return Optional.of(temp);
        }
        String urlGet = getUrl("TicketGet?UserLogin=", registration);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = getRequestHeaderTickerGet(id);
        ResponseEntity<TicketGetDTO> response = restTemplate.exchange(urlGet, HttpMethod.POST, requestEntity, TicketGetDTO.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public Optional<TicketSearchDTO> getTicketOperationSearch(List<Long> listTicketNumbers, Long msgId) {
        TelegramBotRegistration registration = registration(msgId);
        if (registration.getCompany() == null) {
            TicketSearchDTO temp = new TicketSearchDTO();
            Error error = new Error();
            error.setErrorCode(ErrorState.NOT_AUTHORIZED.getCode());
            temp.setError(error);
            return Optional.of(temp);
        }
        String urlSearch = getUrl("TicketSearch?UserLogin=", registration);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = getRequestHeaderTickerSearch(listTicketNumbers);
        ResponseEntity<TicketSearchDTO> response = restTemplate.exchange(urlSearch, HttpMethod.POST, requestEntity, TicketSearchDTO.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public Optional<TicketUpdateCreateDTO> getTicketOperationUpdate(RequestDataDTO data, Long msgId) {
        TelegramBotRegistration registration = registration(msgId);
        if (registration.getCompany() == null) {
            TicketUpdateCreateDTO temp = new TicketUpdateCreateDTO();
            Error error = new Error();
            error.setErrorCode(ErrorState.NOT_AUTHORIZED.getCode());
            temp.setError(error);
            return Optional.of(temp);
        }
        String urlUpdate = getUrl("TicketUpdate?UserLogin=", registration);
        HttpEntity<Map<String, Object>> requestEntity = getRequestHeaderTickerUpdate(data);
        ResponseEntity<TicketUpdateCreateDTO> response = restTemplate.exchange(urlUpdate, HttpMethod.POST, requestEntity, TicketUpdateCreateDTO.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public Optional<TicketUpdateCreateDTO> getTicketOperationCreate(RequestDataDTO data, Long msgId) {
        TelegramBotRegistration registration = registration(msgId);
        if (registration.getCompany() == null) {
            TicketUpdateCreateDTO temp = new TicketUpdateCreateDTO();
            Error error = new Error();
            error.setErrorCode(ErrorState.NOT_AUTHORIZED.getCode());
            temp.setError(error);
            return Optional.of(temp);
        }
        String urlCreate = getUrl("TicketCreate?UserLogin=", registration);
        HttpEntity<Map<String, Object>> requestEntity = getRequestHeaderTickerCreate(data, registration);
        ResponseEntity<TicketUpdateCreateDTO> response = restTemplate.exchange(urlCreate, HttpMethod.POST, requestEntity, TicketUpdateCreateDTO.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public Optional<TicketSearchDTO> getTicketOperationSearch(Long msgId) {
        TelegramBotRegistration registration = registration(msgId);
        if (registration.getCompany() == null) {
            TicketSearchDTO temp = new TicketSearchDTO();
            Error error = new Error();
            error.setErrorCode(ErrorState.NOT_AUTHORIZED.getCode());
            temp.setError(error);
            return Optional.of(temp);
        }

        String url = getUrl("TicketSearch?UserLogin=", registration);
        HttpEntity<Map<String, Object>> requestEntity = getRequestHeaderTickerSearch();
        ResponseEntity<TicketSearchDTO> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, TicketSearchDTO.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    private HttpEntity<MultiValueMap<String, Object>> getRequestHeaderTickerGet(List<Long> listId) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("Extended", "1");
        map.add("AllArticles", "1");
        map.add("Attachments", "1");
        if (listId != null) listId.forEach(e -> map.add("TicketID", e));
        return new HttpEntity<>(map, getHttpHeaders(MediaType.APPLICATION_JSON));
    }

    private   HttpEntity<Map<String, Object>> getRequestHeaderTickerUpdate(RequestDataDTO data) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> arc = new HashMap<>();

        arc.put("ContentType", "text/plain; charset=utf8");
        arc.put("Subject", "Комментарий добавлен с помощью telegram bot");
        arc.put("Body", data.getArticle().getBody());

        map.put("TicketNumber", data.getTicketNumber());
        map.put("CommunicationChannelID", 4);
        map.put("Article", arc);
//
        if (data.getAttaches() != null && data.getAttaches().size() > 0) {
            List<Object> obj = new ArrayList<>();

            data.getAttaches().forEach(e -> {
                Map<String, Object> attach = new HashMap<>();
                attach.put("Content", e.getContent());
                attach.put("ContentType", e.getContentType());
                attach.put("Filename", e.getFilename());
                obj.add(attach);
            });
            map.put("Attachment", obj);
        }
        return new HttpEntity<>(map, getHttpHeaders(MediaType.APPLICATION_JSON));
    }


    private  HttpEntity<Map<String, Object>> getRequestHeaderTickerCreate(RequestDataDTO data, TelegramBotRegistration registration) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> arc = new HashMap<>();
        Map<String, Object> ticket = new HashMap<>();

        ticket.put("QueueID", registration.getQueueId());
        ticket.put("Priority", registration.getCustomerUser());
        ticket.put("CustomerUser", "PRc");
        ticket.put("Title", "Тикет создан с помощью telegram bot");
        ticket.put("State", "open");
        ticket.put("Type", "Unclassified");
        map.put("Ticket", ticket);

        arc.put("ContentType", "text/plain; charset=utf8");
        arc.put("Subject", "Комментарий добавлен с помощью telegram bot");
        arc.put("Body", data.getArticle().getBody());
        map.put("Article", arc);
//
        if (data.getAttaches() != null && data.getAttaches().size() > 0) {
            List<Object> obj = new ArrayList<>();
            data.getAttaches().forEach(e -> {
                Map<String, Object> attach = new HashMap<>();
                attach.put("Content", e.getContent());
                attach.put("ContentType", e.getContentType());
                attach.put("Filename", e.getFilename());
                obj.add(attach);
            });
            map.put("Attachment", obj);
        }
        return new HttpEntity<>(map, getHttpHeaders(MediaType.APPLICATION_JSON));
    }


    private HttpEntity<MultiValueMap<String, Object>> getRequestHeaderTickerSearch(List<Long> listTicketNumbers) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        listTicketNumbers.forEach(e -> map.add("TicketNumber", e));
        return new HttpEntity<>(map, getHttpHeaders(MediaType.APPLICATION_JSON));
    }

    public HttpEntity<Map<String, Object>> getRequestHeaderTickerSearch() {
        Map<String, Object> map = new HashMap<>();
        map.put("States", "open");
        return new HttpEntity<>(map, getHttpHeaders(MediaType.APPLICATION_JSON));
    }

    private HttpHeaders getHttpHeaders(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return headers;
    }

    public  TelegramBotRegistration registration(Long id) {
        return service.getByTelegramId(String.valueOf(id));
    }


    private String getUrl(String operation, TelegramBotRegistration registration) {
        return registration.getUrl() + operation + registration.getLogin() + "&Password=" + registration.getPassword();
    }

}
