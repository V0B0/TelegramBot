package telegramversionfive.hendlers.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import telegramversionfive.enums.StateNotes;
import telegramversionfive.keyboards.KeyBoards;

import java.util.Deque;
import java.util.Objects;

public class CommandNotes implements Command{
    private static final Logger log = LoggerFactory.getLogger(CommandNotes.class);

    @Override
    public PartialBotApiMethod<Message> execute(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        StateNotes state = DataNotes.getState();
        DataNotes.setPreviousState(state);
        switch (message) {
            case "/notes":
            case "Заметки":
                sendMessage.setText("Выберите действие:");
                KeyBoards.setNotesDefault(sendMessage);
                break;
            case "Добавить":
                sendMessage.setText("Выберите действие:");
                DataNotes.setState(StateNotes.Put);
                KeyBoards.setGetOrPutChoice(sendMessage);
                break;
            case "Получить":
                sendMessage.setText("Выберите действие:");
                DataNotes.setState(StateNotes.Get);
                KeyBoards.setGetOrPutChoice(sendMessage);
                break;
            case "Ссылку":
                if (state == StateNotes.Get) {
                    sendMessage.setText("Выберите действие:");
                    DataNotes.setState(StateNotes.GetLink);
                    KeyBoards.setCountChoice(sendMessage);
                } else if (state == StateNotes.Put) {
                    sendMessage.setText("Введите значение");
                    DataNotes.setState(StateNotes.PutLink);
                    KeyBoards.setNoButtons(sendMessage);
                }
                break;
            case "Видео":
                if (state == StateNotes.Get) {
                    sendMessage.setText("Выберите действие:");
                    DataNotes.setState(StateNotes.GetVideo);
                    KeyBoards.setCountChoice(sendMessage);
                } else if (state == StateNotes.Put) {
                    sendMessage.setText("Введите значение");
                    DataNotes.setState(StateNotes.PutVideo);
                    KeyBoards.setNoButtons(sendMessage);
                }
                break;
            case "Другое":
                if (state == StateNotes.Get) {
                    sendMessage.setText("Выберите действие:");
                    DataNotes.setState(StateNotes.GetOther);
                    KeyBoards.setCountChoice(sendMessage);
                } else if (state == StateNotes.Put) {
                    sendMessage.setText("Введите значение");
                    DataNotes.setState(StateNotes.PutOther);
                    KeyBoards.setNoButtons(sendMessage);
                }
                break;
            case "Первый":
                if (state == StateNotes.GetLink){
                    Deque<String> list = DataNotes.getList(chatId, "link");
                    sendMessage.setText(Objects.requireNonNull(list).peekFirst()!=null? list.pollFirst() : "Список пуст");
                } else if (state == StateNotes.GetVideo){
                    Deque<String> list = DataNotes.getList(chatId, "video");
                    sendMessage.setText(Objects.requireNonNull(list).peekFirst()!=null? list.pollFirst() : "Список пуст");
                } else if (state == StateNotes.GetOther){
                    Deque<String> list = DataNotes.getList(chatId, "other");
                    sendMessage.setText(Objects.requireNonNull(list).peekFirst()!=null? list.pollFirst() : "Список пуст");
                }
                break;
            case "Последний":
                if (state == StateNotes.GetLink){
                    Deque<String> list = DataNotes.getList(chatId, "link");
                    sendMessage.setText(Objects.requireNonNull(list).getLast());
                } else if (state == StateNotes.GetVideo){
                    Deque<String> list = DataNotes.getList(chatId, "video");
                    sendMessage.setText(Objects.requireNonNull(list).getLast());
                } else if (state == StateNotes.GetOther){
                    Deque<String> list = DataNotes.getList(chatId, "other");
                    sendMessage.setText(Objects.requireNonNull(list).getLast());
                }
                break;
            case "Показать все":
                if (state == StateNotes.GetLink){
                    Deque<String> list = DataNotes.getList(chatId, "link");
                    StringBuilder sb = new StringBuilder("All Values:");
                    for (String s : Objects.requireNonNull(list))
                        sb.append("\n").append(s);
                    sendMessage.setText(sb.toString());
                } else if (state == StateNotes.GetVideo){
                    Deque<String> list = DataNotes.getList(chatId, "video");
                    StringBuilder sb = new StringBuilder("All Values:");
                    for (String s : Objects.requireNonNull(list))
                        sb.append("\n").append(s);
                    sendMessage.setText(sb.toString());
                } else if (state == StateNotes.GetOther){
                    Deque<String> list = DataNotes.getList(chatId, "other");
                    StringBuilder sb = new StringBuilder("All Values:");
                    for (String s : Objects.requireNonNull(list))
                        sb.append("\n").append(s);
                    sendMessage.setText(sb.toString());
                }
                break;
            default:
                if (state==StateNotes.PutLink){
                    Deque<String> list = DataNotes.getList(chatId, "link");
                    if (Objects.requireNonNull(list).add(message))
                        sendMessage.setText("Успешно добавлено");
                    else
                        sendMessage.setText("add error");
                } else if (state==StateNotes.PutVideo) {
                    Deque<String> list = DataNotes.getList(chatId, "video");
                    if (Objects.requireNonNull(list).add(message))
                        sendMessage.setText("Успешно добавлено");
                    else
                        sendMessage.setText("add error");
                }else if (state==StateNotes.PutOther) {
                    Deque<String> list = DataNotes.getList(chatId, "other");
                    if (Objects.requireNonNull(list).add(message))
                        sendMessage.setText("Успешно добавлено");
                    else
                        sendMessage.setText("add error");
                }
        }
        return sendMessage;
    }

    @Override
    public Command getHandler() {
        return null;
    }
}
