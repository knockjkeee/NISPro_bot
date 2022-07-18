package ru.newsystems.nispro_bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.newsystems.nispro_bot.base.model.dto.domain.TicketGetDTO;
import ru.newsystems.nispro_bot.config.cache.CacheStore;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class BaseConfig {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
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
