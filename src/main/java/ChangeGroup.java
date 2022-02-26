import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

public class ChangeGroup implements ICommand{
    //TODO возможно схлопнуть с newgorup
    @Override
    public SendMessage Answer(Message message) throws IOException {
        MyTelegramBot.SetBotState(BotState.WaitChangeGroup);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Выберите новую группу");
        KeyboardButtons.setGroupButton(sendMessage);
        return sendMessage;
    }
}
