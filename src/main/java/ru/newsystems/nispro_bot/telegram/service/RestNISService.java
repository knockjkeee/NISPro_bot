package ru.newsystems.nispro_bot.telegram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.domain.Article;
import ru.newsystems.nispro_bot.base.model.domain.Error;
import ru.newsystems.nispro_bot.base.model.domain.TicketJ;
import ru.newsystems.nispro_bot.base.model.dto.callback.ChangeStatusDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.RequestDataDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketSearchDTO;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketUpdateCreateDTO;
import ru.newsystems.nispro_bot.base.model.state.ErrorState;
import ru.newsystems.nispro_bot.base.utils.StringUtil;
import ru.newsystems.nispro_bot.webservice.services.TelegramBotRegistrationService;

import java.util.*;
import java.util.stream.Collectors;

import static ru.newsystems.nispro_bot.base.utils.NumberUtil.getIdByTicketNumber;

@Service
@Slf4j
public class RestNISService {

    private final RestTemplate restTemplate;
    private final TelegramBotRegistrationService service;

    @Value("${nis.pro.path}")
    private String GENERICINTERFACE_PL_WEBSERVICE_TICKET;
    @Value("#{'${settings.array.state}'.split(',')}")
    private List<String> LIST_STATE;

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
        String urlGet = getUrl(
                "TicketGet?" + (registration.isCustomerLogin() ? "CustomerUserLogin=" : "UserLogin="), registration);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = getRequestHeaderTickerGet(id);
        ResponseEntity<TicketGetDTO> response =
                restTemplate.exchange(urlGet, HttpMethod.POST, requestEntity, TicketGetDTO.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            TicketGetDTO body = response.getBody();
            List<TicketJ> resCollect = body.getTickets() != null ? body.getTickets().stream().peek(ticket -> {
                List<Article> collect = ticket.getArticles().stream().filter(article ->
                        article.getIiVisibleForCustomer() == 1).collect(Collectors.toList());
                ticket.setArticles(collect);
            }).collect(Collectors.toList()) : null;
            body.setTickets(resCollect);
            return Optional.of(body);
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
        String urlSearch = getUrl("TicketSearch?" + (registration.isCustomerLogin() ? "CustomerUserLogin=" : "UserLogin="), registration);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = getRequestHeaderTickerSearch(listTicketNumbers);
        ResponseEntity<TicketSearchDTO> response =
                restTemplate.exchange(urlSearch, HttpMethod.POST, requestEntity, TicketSearchDTO.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public Optional<TicketUpdateCreateDTO> getTicketOperationUpdate(Update update, RequestDataDTO data) {
        Long msgId = update.getMessage().getChatId();
        String userName = update.getMessage().getFrom().getFirstName() + "/" + update.getMessage().getFrom().getUserName();

        TelegramBotRegistration registration = registration(msgId);
        if (registration.getCompany() == null) {
            TicketUpdateCreateDTO temp = new TicketUpdateCreateDTO();
            Error error = new Error();
            error.setErrorCode(ErrorState.NOT_AUTHORIZED.getCode());
            temp.setError(error);
            return Optional.of(temp);
        }
        String urlUpdate = getUrl("TicketUpdate?" + (registration.isCustomerLogin() ? "CustomerUserLogin=" : "UserLogin="), registration);
        HttpEntity<Map<String, Object>> requestEntity = getRequestHeaderTickerUpdate(update, data, userName);
        ResponseEntity<TicketUpdateCreateDTO> response =
                restTemplate.exchange(urlUpdate, HttpMethod.POST, requestEntity, TicketUpdateCreateDTO.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public Optional<TicketUpdateCreateDTO> getTicketOperationUpdate(Update update, ChangeStatusDTO data, String dynamicField, String owner) {
        Long msgId = update.getCallbackQuery().getMessage().getChatId();
        TelegramBotRegistration registration = registration(msgId);
        if (registration.getCompany() == null) {
            TicketUpdateCreateDTO temp = new TicketUpdateCreateDTO();
            Error error = new Error();
            error.setErrorCode(ErrorState.NOT_AUTHORIZED.getCode());
            temp.setError(error);
            return Optional.of(temp);
        }
        String urlUpdate = getUrl("TicketUpdate?" + (registration.isCustomerLogin() ? "CustomerUserLogin=" : "UserLogin="), registration);

        HttpEntity<Map<String, Object>> requestEntity = getRequestHeaderTickerUpdate(dynamicField, data, owner);
        ResponseEntity<TicketUpdateCreateDTO> response =
                restTemplate.exchange(urlUpdate, HttpMethod.POST, requestEntity, TicketUpdateCreateDTO.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public Optional<TicketUpdateCreateDTO> getTicketOperationCreate(Update update, RequestDataDTO data, TelegramBotRegistration regGroup) {
        String userName = update.getMessage().getFrom().getFirstName() + "/" + update.getMessage().getFrom().getUserName();
        Long msgId = update.getMessage().getChatId();

        TelegramBotRegistration registration = registration(msgId);
        if (registration.getCompany() == null) {
            TicketUpdateCreateDTO temp = new TicketUpdateCreateDTO();
            Error error = new Error();
            error.setErrorCode(ErrorState.NOT_AUTHORIZED.getCode());
            temp.setError(error);
            return Optional.of(temp);
        }
        String urlCreate =
                !Objects.nonNull(regGroup) ? getUrl("TicketCreate?" + (registration.isCustomerLogin() ? "CustomerUserLogin=" : "UserLogin="), registration) : getUrlByGroup(registration);
        HttpEntity<Map<String, Object>> requestEntity =
                getRequestHeaderTickerCreate(update, data, registration, userName, regGroup);
        ResponseEntity<TicketUpdateCreateDTO> response =
                restTemplate.exchange(urlCreate, HttpMethod.POST, requestEntity, TicketUpdateCreateDTO.class);
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

        String url = getUrl("TicketSearch?" + (registration.isCustomerLogin() ? "CustomerUserLogin=" : "UserLogin="), registration);
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
        map.add("DynamicFields", "1");
        if (listId != null) listId.forEach(e -> map.add("TicketID", e));
        return new HttpEntity<>(map, getHttpHeaders());
    }

    private HttpEntity<Map<String, Object>> getRequestHeaderTickerUpdate(String dynamicField, ChangeStatusDTO data,  String owner) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> ticket = new HashMap<>();

        map.put("TicketNumber",data.getTicketId());

        List<Object> dynamic = new ArrayList<>();
        Map<String, Object> dynamic_field = new HashMap<>();

        if (dynamicField == null) {
            dynamic_field.put("Name", "Telegram");
            dynamic_field.put("Value", data.getTelegramId());
            dynamic.add(dynamic_field);
            map.put("DynamicField", dynamic);
        }

        String state = null;
        switch (data.getDirection()) {
            case "a" -> state = "открыта";
            case "b" -> {
                state = "новая";
                ticket.put("Responsible", owner);
            }
            case "c" -> {
                state = "закрыта успешно";
                ticket.put("Responsible", owner);
            }
        }

        if (state != null) ticket.put("State", state);
        map.put("Ticket", ticket);

        return new HttpEntity<>(map, getHttpHeaders());
    }


    private HttpEntity<Map<String, Object>> getRequestHeaderTickerUpdate(Update update, RequestDataDTO data, String userName) {

        String title = getCurrentTitleFromTicket(update, data);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> arc = new HashMap<>();

        arc.put("ContentType", "text/plain; charset=utf8");
        if (title.isEmpty()) {
            arc.put("Subject", "Комментарий добавлен с помощью telegram bot [автор: " + userName + "].");
        }else {
            arc.put("Subject", title);
        }

        arc.put("Body", data.getArticle().getBody());

        map.put("TicketNumber", data.getTicketNumber());
        map.put("CommunicationChannelID", 4);
        map.put("Article", arc);

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
        return new HttpEntity<>(map, getHttpHeaders());
    }

    private String getCurrentTitleFromTicket(Update update, RequestDataDTO data) {
        String title;
        try {
            long tk = getIdByTicketNumber(String.valueOf(data.getTicketNumber()));
            Optional<TicketSearchDTO> ticketSearch = getTicketOperationSearch(List.of(tk), update.getMessage().getChatId());
            List<Long> ticketsId = ticketSearch.get().getTicketIDs();
            Optional<TicketGetDTO> ticket = getTicketOperationGet(ticketsId, update.getMessage().getChatId());
            title = ticket.get().getTickets().get(0).getTitle();
        } catch (Exception e) {
            title = "";
        }
        return title;
    }

    private HttpEntity<Map<String, Object>> getRequestHeaderTickerCreate(Update update, RequestDataDTO data, TelegramBotRegistration registration, String userName, TelegramBotRegistration regGroup) {

        String titleTicket = getCurrentTitleFromTicket(update, data);

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> article = new HashMap<>();
        Map<String, Object> ticket = new HashMap<>();
        List<Object> dynamic = new ArrayList<>();
        Map<String, Object> dynamic_field = new HashMap<>();

        String title = !StringUtil.isBlank(data.getTitle()) ?
                data.getTitle() + " [автор: " + userName + "]." :
                data.getArticle().getBody() + " [автор: " + userName + "].";
        ticket.put("Priority", "3 normal");
        ticket.put("Title", title);
        ticket.put("State", "open");
        ticket.put("Type", "Unclassified");

        dynamic_field.put("Name", "Telegram");
        if (regGroup == null) {
            dynamic_field.put("Value", registration.getIdTelegram());
            ticket.put("Queue", registration.getQueueName());
            ticket.put("CustomerUser", registration.getCustomerUser());
            if (!registration.getLogin().equals(registration.getCustomerUser())){
                ticket.put("Owner", registration.getLogin());
                ticket.put("Responsible", registration.getLogin());
            }

        } else {
            map.put(regGroup.isCustomerLogin() ? "CustomerUserLogin" : "UserLogin", regGroup.getLogin());
            map.put("Password", regGroup.getPassword());
            dynamic_field.put("Value", regGroup.getIdTelegram());
            ticket.put("Queue", regGroup.getQueueName());
            ticket.put("CustomerUser", regGroup.getCustomerUser());

        }

        map.put("Ticket", ticket);
        dynamic.add(dynamic_field);
        map.put("DynamicField", dynamic);

        article.put("ContentType", "text/plain; charset=utf8");
        if (titleTicket.isEmpty()) {
            article.put("Subject", title);
        }else {
            article.put("Subject", titleTicket);
        }
        article.put("Body", data.getArticle().getBody());
        map.put("Article", article);

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
        return new HttpEntity<>(map, getHttpHeaders());
    }

    private HttpEntity<MultiValueMap<String, Object>> getRequestHeaderTickerSearch(List<Long> listTicketNumbers) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        listTicketNumbers.forEach(e -> map.add("TicketNumber", e));
        return new HttpEntity<>(map, getHttpHeaders());
    }

    public HttpEntity<Map<String, Object>> getRequestHeaderTickerSearch() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> dynamicField = new HashMap<>();
        dynamicField.put("Empty", 0);
        map.put("DynamicField_Telegram", dynamicField);
        map.put("States", LIST_STATE.toArray());
        return new HttpEntity<>(map, getHttpHeaders());
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public TelegramBotRegistration registration(Long id) {
        TelegramBotRegistration byTelegramId = service.getByTelegramId(String.valueOf(id));
        byTelegramId.setUrl(byTelegramId.getUrl() + GENERICINTERFACE_PL_WEBSERVICE_TICKET);
        return byTelegramId;
    }

    private String getUrl(String operation, TelegramBotRegistration registration) {
        return registration.getUrl() + operation + registration.getLogin() + "&Password=" + registration.getPassword();
    }

    private String getUrlByGroup(TelegramBotRegistration registration) {
        return registration.getUrl() + "TicketCreate";
    }
}
