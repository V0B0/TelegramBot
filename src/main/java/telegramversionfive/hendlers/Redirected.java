package telegramversionfive.hendlers;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegramversionfive.enums.State;
import telegramversionfive.hendlers.commands.Command;
import telegramversionfive.hendlers.commands.CommandConcept;
import telegramversionfive.hendlers.commands.CommandNotes;
import telegramversionfive.hendlers.commands.CommandToDo;
import telegramversionfive.keyboards.KeyBoards;

@Getter
public class Redirected {
    private static final Logger log = LoggerFactory.getLogger(Redirected.class);

    private State state;

    public Redirected() {
        log.info("Redirected init");
        this.state = State.OTHER;
    }

    private State init(String message) {
        log.info("message "+message);
        switch (message){
            case "/todo":
                log.info("state TODO");
                return State.TODO;
            case "/notes":
                log.info("state NOTES");
                return State.NOTES;
            case "/concept":
                log.info("state CONCEPT");
                return State.CONCEPT;
            case "Список дел":
                return State.TODO;
            case "Идея для тату":
                return State.CONCEPT;
            case "Заметки":
                return State.NOTES;
            default:
                return state;
        }
    }

    public PartialBotApiMethod<Message> handle(Update update) {
        String message = update.getMessage().getText();
        log.info("inner handle() message "+message);
        state = init(message);
        Command command;
        switch (state){
            case TODO:
                command = new CommandToDo();
                log.info("case TODO");
                return command.execute(update.getMessage().getChatId(), message);
            case NOTES:
                command = new CommandNotes();
                return command.execute(update.getMessage().getChatId(), message);
            case CONCEPT:
                command = new CommandConcept();
                return command.execute(update.getMessage().getChatId(), message);
//            case OTHER:
//                NonCommand nonCommand = new NonCommand();
//                return nonCommand.execute(update);
            default:
                log.info("case default; message " + message);
                log.info("expect echo");
                SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), message);
                KeyBoards.setKeyBoardDefault(sendMessage);
                return sendMessage;
        }

    }

    public SendMessage handleCallBack(CallbackQuery callbackQuery) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(callbackQuery.getMessage().getChatId().toString());
        sendMessage.setText(callbackQuery.getData());
        return sendMessage;
    }
}
