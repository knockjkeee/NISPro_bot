package ru.newsystems.nispro_bot.base.model.dto.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.newsystems.nispro_bot.base.model.domain.Article;
import ru.newsystems.nispro_bot.base.model.domain.Attachment;

import java.util.List;

@Data
@RequiredArgsConstructor
public class RequestDataDTO {
    private Long ticketNumber;
    private Article article;
    List<Attachment> attaches;
}
