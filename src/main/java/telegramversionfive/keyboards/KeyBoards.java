package telegramversionfive.keyboards;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyBoards {

//    Клавиатура при входе в Список дел
    public static void setKeyBoardForToDo(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Получить задание");
        keyboardRow.add("Добавить задание");

        keyboardRows.add(keyboardRow);

        keyboard.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(keyboard);
    }

//    Стандартная клавиатура бота
    public static void setKeyBoardDefault(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow0 = new KeyboardRow();
        keyboardRow0.add("Список дел");
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("Идея для тату");
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("Заметки");

        keyboardRows.add(keyboardRow0);
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);

        keyboard.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(keyboard);
    }

//    Кнопки под сообщением
    public static void setDefault(SendMessage sendMessage) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Список");
        inlineKeyboardButton1.setCallbackData("писька");
        inlineKeyboardButton2.setText("Заметки");
        inlineKeyboardButton2.setCallbackData("пуська");
        inlineKeyboardButton3.setText("Fic");
        inlineKeyboardButton3.setCallbackData("fuck");
        List<InlineKeyboardButton> keyboardButtons = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonList = new ArrayList<>();
        keyboardButtons.add(inlineKeyboardButton1);
        keyboardButtons.add(inlineKeyboardButton3);
        keyboardButtonList.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtons);
        rowList.add(keyboardButtonList);
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }

    //Кнопка под сообщением
    public static void setInline(SendMessage sendMessage) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Кнопка");
        button.setCallbackData("/todo");
        buttons1.add(button);
        buttons.add(buttons1);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        sendMessage.setReplyMarkup(markupKeyboard);
    }

    public static void setNotesDefault(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow0 = new KeyboardRow();
        keyboardRow0.add("Добавить");
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("Получить");

        keyboardRows.add(keyboardRow0);
        keyboardRows.add(keyboardRow1);

        keyboard.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(keyboard);
    }


    public static void setGetOrPutChoice(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow0 = new KeyboardRow();
        keyboardRow0.add("Ссылку");
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("Видео");
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("Другое");
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("Назад");


        keyboardRows.add(keyboardRow0);
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);

        keyboard.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(keyboard);
    }

    public static void setCountChoice(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow0 = new KeyboardRow();
        keyboardRow0.add("Первый");
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("Последний");
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("Показать все");
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("Назад");

        keyboardRows.add(keyboardRow0);
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);

        keyboard.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(keyboard);
    }

    public static void setNoButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("Назад");
        keyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow3));
        sendMessage.setReplyMarkup(keyboardMarkup);
    }
}
