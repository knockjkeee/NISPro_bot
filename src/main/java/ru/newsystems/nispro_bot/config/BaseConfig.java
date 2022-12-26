package ru.newsystems.nispro_bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.config.cache.CacheStore;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
//@PropertySource(value = "classpath:/application.properties", encoding="UTF-8")
public class BaseConfig {
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public ScheduledExecutorService getExecutor() {
        return Executors.newScheduledThreadPool(20);
    }

    @Bean
    public CacheStore<TicketGetDTO> ticketGetDTOCache(){
        return new CacheStore<>(20, TimeUnit.MINUTES);
    }
}
