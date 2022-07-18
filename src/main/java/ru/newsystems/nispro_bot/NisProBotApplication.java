package ru.newsystems.nispro_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"ru.newsystems.nispro_bot.*"})
@EnableJpaRepositories("ru.newsystems.nispro_bot.base.*")
public class NisProBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(NisProBotApplication.class, args);
    }

}
