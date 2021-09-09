package telegramversionfive.hendlers.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import telegramversionfive.enums.StateToDo;
import telegramversionfive.keyboards.KeyBoards;

import java.util.ArrayList;
import java.util.Queue;

public class CommandToDo implements Command{
    private static final Logger log = LoggerFactory.getLogger(CommandToDo.class);

    @Override
    public PartialBotApiMethod<Message> execute(Long chatId, String message) {
        String strChatId = String.valueOf(chatId);
        log.info("/todo command inner execute; message "+message);
        Queue<String> data = DataToDo.getQueue(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(strChatId);
        KeyBoards.setKeyBoardForToDo(sendMessage);
        if (message.equals("/todo")||message.equals("Список дел")) {
            ArrayList<String> list = new ArrayList<>(data);
            StringBuilder sb = new StringBuilder("Ваш список дел:");
            int i = 0;
            for (String s : list)
                sb.append("\n").append(++i).append(". ").append(s);
            sendMessage.setText(sb.toString());
        } else if (message.equals("Получить задание")){
            log.info("expected message \"Value\"");
            sendMessage.setText(data.peek()!=null? data.poll() : "Список дел пуст");
        } else if (message.equals("Добавить задание")) {
            sendMessage.setText("Введите задачу:");
            DataToDo.setState(StateToDo.WaitTask);
        } else if (DataToDo.getState()==StateToDo.WaitTask){
            log.info("expected message \"Успешно добавлено\"");
            if (data.add(message)) {
                DataToDo.setState(StateToDo.NOT);
                sendMessage.setText("Успешно добавлено! \nОсталось дел: " + data.size());
            } else {
                sendMessage.setText("Произошла ошибка, попробуйте снова:");
            }
        } else {
            sendMessage.setText("Произошло недопонимание");
        }
        return sendMessage;
    }

    @Override
    public Command getHandler() {
        log.info("inner getHandler()");
        return new CommandToDo();
    }
}
