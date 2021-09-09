package telegramversionfive.hendlers.commands;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CommandConcept implements Command {
    @Override
    public PartialBotApiMethod<Message> execute(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        switch (message){
            case "/concept":
                SendPhoto sendPhoto = new SendPhoto();
                try {
                    URL url = new URL("https://www.pinterest.ru/pin/40462096642257280/");
                    Image image = ImageIO.read(url);
                    sendPhoto.setPhoto(new InputFile(String.valueOf(image)));
                    return sendPhoto;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            default:
                return sendMessage;
        }
    }

    @Override
    public Command getHandler() {
        return null;
    }
}
