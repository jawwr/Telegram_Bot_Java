import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class NewGroup implements ICommand {
    @Override
    public SendMessage answer(Message message) {
        MyTelegramBot.SetBotState(BotState.WaitNewGroup);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Выберите вашу группу");
        KeyboardButtons.setGroupButton(sendMessage);
        return sendMessage;
    }
}
