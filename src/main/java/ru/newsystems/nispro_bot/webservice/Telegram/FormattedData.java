package ru.newsystems.nispro_bot.webservice.Telegram;

import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.domain.Article;
import ru.newsystems.nispro_bot.base.model.domain.Attachment;
import ru.newsystems.nispro_bot.base.model.dto.domain.RequestDataDTO;
import ru.newsystems.nispro_bot.base.model.state.ContentTypeState;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class FormattedData {
    public static Attachment prepareAttachmentFromDocument(Update update, VirtaBot bot) throws TelegramApiException {
        Document document = update.getMessage()
                .getDocument();
        String base64 = prepareBase64(document.getFileId(), false, bot);
        return prepareAttach(base64, document.getMimeType(), document.getFileName());
    }

    public static Attachment prepareAttachmentFromPhoto(Update update, VirtaBot bot) throws TelegramApiException {
        String filePath = getFilePath(update, bot);
        String base64 = getBase64(filePath, bot);
        String fileName = filePath.split("/")[1];
        String contentType = ContentTypeState.getState(fileName.split("\\.")[1])
                .getContent();
        return prepareAttach(base64, contentType, fileName);
    }

    public static RequestDataDTO prepareReqWithMessage(List<String> replyTexts, String body) {
        RequestDataDTO req = new RequestDataDTO();
        req.setTicketNumber(replyTexts.size() > 1 ? Long.parseLong(replyTexts.get(1)) : 0);
        Article article = new Article();
        article.setBody(body);
        req.setArticle(article);
        return req;
    }

    public static RequestDataDTO prepareReqWithPhoto(Update update, List<String> replyTexts, String body, VirtaBot bot) throws TelegramApiException {
        String filePath = getFilePath(update, bot);
        String base64 = getBase64(filePath, bot);
        String fileName = filePath.split("/")[1];
        String contentType = ContentTypeState.getState(fileName.split("\\.")[1])
                .getContent();
        return prepareReqWithAttachment(replyTexts, body, base64, contentType, fileName);
    }

    public static RequestDataDTO prepareReqWithDocument(Update update, List<String> replyTexts, String body, VirtaBot bot) throws TelegramApiException {
        Document document = update.getMessage()
                .getDocument();
        String base64 = prepareBase64(document.getFileId(), false, bot);
        return prepareReqWithAttachment(replyTexts, body, base64, document.getMimeType(), document.getFileName());
    }

    private static RequestDataDTO prepareReqWithAttachment(List<String> replyTexts, String body, String base64, String contentType, String fileName) {
        RequestDataDTO req = prepareReqWithMessage(replyTexts, body);
        Attachment attach = prepareAttach(base64, contentType, fileName);
        req.setAttaches(List.of(attach));
        return req;
    }

    private static Attachment prepareAttach(String base64, String contentType, String fileName) {
        Attachment attach = new Attachment();
        attach.setContent(base64);
        attach.setContentType(contentType);
        attach.setFilename(fileName);
        return attach;
    }

    private static String getFilePath(Update update, VirtaBot bot) throws TelegramApiException {
        List<PhotoSize> photos = update.getMessage()
                .getPhoto();
        PhotoSize photo = photos.size() == 2 ? photos.get(1) : photos.get(0);
        return prepareBase64(photo.getFileId(), true, bot);
    }

    public static List<String> splitMessageText(String caption, String s) {
        return Arrays.stream(caption.split(s))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    private static String prepareBase64(String fileId, boolean isPhoto, VirtaBot bot) throws TelegramApiException {
        GetFile getFile = new GetFile();
        getFile.setFileId(fileId);
        String filePath = bot.execute(getFile)
                .getFilePath();
        return isPhoto ? filePath : getBase64(filePath, bot);
    }

    private static String getBase64(String filePath, VirtaBot bot) throws TelegramApiException {
        File file = bot.downloadFile(filePath);
        try {
            byte[] encode = Base64.getEncoder()
                    .encode(Files.readAllBytes(file.toPath()));
            return new String(encode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
