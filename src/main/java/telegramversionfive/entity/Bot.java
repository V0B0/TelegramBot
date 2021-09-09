package telegramversionfive.entity;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegramversionfive.hendlers.Redirected;
import telegramversionfive.hendlers.commands.DataNotes;
import telegramversionfive.hendlers.commands.DataToDo;

import java.util.HashMap;
import java.util.Map;

@Setter
public class Bot extends TelegramWebhookBot {
    private static final Logger log = LoggerFactory.getLogger(Bot.class);

    private String botUserName;
    private String botToken;
    private String webHookPath;

    private Redirected redirected;
    private Map<Long, Redirected> users = new HashMap<>();

    public Bot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        log.info("Получено новое сообщение: message:\"{}\" chatId:\"{}\"",
                update.getMessage().getText(), update.getMessage().getChatId());
        if (!update.hasCallbackQuery()) {
            long chatId = update
                    .getMessage()
                    .getChatId();
            if (users.containsKey(chatId))
                redirected = users.get(chatId);
            else {
                redirected = new Redirected();
                users.put(chatId, redirected);
                DataToDo.newQueue(chatId);
                DataNotes.newLink(chatId);
            }
            return (BotApiMethod<?>) redirected.handle(update);
//        return new SendMessage(update.getMessage().getChatId().toString(),update.getMessage().getText());
        } else {
            return redirected.handleCallBack(update.getCallbackQuery());
        }
    }

    @Override
    public String getBotUsername() {
        log.info("in getBotUsername()");
        log.info("return "+botUserName);
        return botUserName;
    }

    @Override
    public String getBotToken() {
        log.info("in getBotToken()");
        log.info("return "+botToken);
        return botToken;
    }

    @Override
    public String getBotPath() {
        log.info("in getBotPath()");
        log.info("return "+webHookPath);
        return webHookPath;
    }
}
