import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

public class StartMessage implements ICommand {

    @Override
    public SendMessage Answer(Message message) throws IOException {
        DataBase.AddUser(message.getFrom());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Привет," + message.getFrom().getUserName() + ", я телеграм бот - помощник с расписанием");
        KeyboardButtons.setDefaultButtons(sendMessage);

        return sendMessage;
        //TODO сделать одну всплывающую плитку с установкой группы, если группа установлена, то подкреплять командами
    }
}
