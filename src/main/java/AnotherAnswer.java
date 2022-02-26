import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AnotherAnswer implements ICommand {

    @Override
    public SendMessage Answer(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Я не знаю такой команды!");
//TODO прикрепить плитки
        return sendMessage;
    }
}
