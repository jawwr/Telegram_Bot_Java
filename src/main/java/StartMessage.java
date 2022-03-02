import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

public class StartMessage implements ICommand {

    @Override
    public SendMessage answer(Message message) throws IOException {
        DataBase.addUser(message.getFrom());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Привет, " + message.getFrom().getUserName() + ", я телеграм бот - помощник с расписанием");

        KeyboardButtons.setDefaultButtons(sendMessage);

        return sendMessage;
    }
    public static SendMessage setStartMessage(Message message) throws IOException {
        DataBase.addUser(message.getFrom());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Привет, " + message.getFrom().getUserName() + ", я телеграм бот - помощник с расписанием.\n" +
                "Для взаимодействия с ботом необходимо установить группу!");
        KeyboardButtons.SetSelectGroup(sendMessage);

        return sendMessage;
    }
    public static SendMessage setGroupMessage(Message message) throws IOException {
        DataBase.addUser(message.getFrom());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Для взаимодействия с ботом необходимо установить группу!");
        KeyboardButtons.SetSelectGroup(sendMessage);

        return sendMessage;
    }
}
