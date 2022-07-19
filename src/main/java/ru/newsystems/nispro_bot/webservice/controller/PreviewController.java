package ru.newsystems.nispro_bot.webservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
@Log4j2
public class PreviewController {

    @GetMapping("/test")
    public String getFilterVal() {
        log.error("EYyyyyq!!!");
       return "Test";
    }
}
