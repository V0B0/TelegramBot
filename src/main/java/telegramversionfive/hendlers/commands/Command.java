package telegramversionfive.hendlers.commands;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command {
    PartialBotApiMethod<Message> execute(Long chatId, String message);
    Command getHandler();
}
