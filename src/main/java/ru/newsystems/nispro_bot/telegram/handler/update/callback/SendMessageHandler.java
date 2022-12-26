package ru.newsystems.nispro_bot.telegram.handler.update.callback;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.newsystems.nispro_bot.base.integration.VirtaBot;
import ru.newsystems.nispro_bot.base.model.db.TelegramBotRegistration;
import ru.newsystems.nispro_bot.base.model.dto.callback.SendDataDTO;
import ru.newsystems.nispro_bot.base.model.state.MessageState;
import ru.newsystems.nispro_bot.base.model.state.SerializableInlineType;

@Component
public class SendMessageHandler extends CallbackUpdateHandler<SendDataDTO> {

  private final VirtaBot bot;

  public SendMessageHandler(VirtaBot bot) {
    this.bot = bot;
  }

  @Override
  protected Class<SendDataDTO> getDtoType() {
    return SendDataDTO.class;
  }

  @Override
  protected SerializableInlineType getSerializableType() {
    return SerializableInlineType.SEND_COMMENT;
  }

  @Override
  protected void handleCallback(Update update, SendDataDTO dto, TelegramBotRegistration registration) throws TelegramApiException {
    String text = "<pre>" + MessageState.SENDCOMMENT.getName() + " по заявке №" + dto.getTicketId() + "</pre>";
    bot.execute(
            SendMessage.builder()
                    .text(text)
                    .replyToMessageId(update.getCallbackQuery().getMessage().getMessageId())
                    .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                    .parseMode(ParseMode.HTML)
                    .replyMarkup(ForceReplyKeyboard.builder().forceReply(true).build())
                    .build());
//    bot.execute(AnswerCallbackQuery.builder()
//            .cacheTime(10)
//            .text("\t⚠️ Форма подачи: ⚠️\n\n<Заголовок> # <Сообщение>\n\nЗагрузка документов опциональна")
//            .showAlert(true)
//            .callbackQueryId(update.getCallbackQuery().getId())
//            .build());
  }
}
