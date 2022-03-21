package bot.command;

import bot.handler.KeyboardButtons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AnotherAnswer implements ICommand {

    @Override
    public SendMessage answer(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Я не знаю такой команды!");
        KeyboardButtons.setDefaultButtons(sendMessage);
        return sendMessage;
    }
}
