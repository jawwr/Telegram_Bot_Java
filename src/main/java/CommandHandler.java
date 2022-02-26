import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.io.IOException;

public final class CommandHandler extends BotCommand {
    public SendMessage Handler(Message message) throws IOException {
        if(message.getText().startsWith("/"))
            return commandHandler(message);
        else if(message.getText().startsWith("@")){
            return groupHandler(message);
        }
        else
            return messageHandler(message);
    }

    //TODO сделать проверку на то, есть ли группа или нет, если нет, то постоянно при командах выводить сообщение об установлении группы
    private SendMessage groupHandler(Message message) throws IOException {
        if (MyTelegramBot.GetBotState() == BotState.WaitNewGroup){
            MyTelegramBot.SetBotState(BotState.None);
            return SetGroup.SetNewGroup(message);
        }
        else if(MyTelegramBot.GetBotState() == BotState.WaitChangeGroup){
            MyTelegramBot.SetBotState(BotState.None);
            return SetGroup.SetChangeGroup(message);
        }
        else
            return SendMessage.builder().chatId(message.getChatId().toString()).text("Ошибка").build();
    }
    private SendMessage commandHandler(Message command) throws IOException {
        MyTelegramBot.SetBotState(BotState.None);
        ICommand commandAnswer = null;
        switch (command.getText()){
            case "/start":
                commandAnswer = new StartMessage();
                break;
            case "/lesson":
                break;
            case "/new_group":
                commandAnswer = new NewGroup();
                break;
            case "/change_group":
                commandAnswer = new ChangeGroup();
                break;
            default:
                commandAnswer = new AnotherAnswer();
        }
        return commandAnswer.Answer(command);
    }
    private SendMessage messageHandler(Message message){
        return SendMessage.builder().text("some").chatId(message.getChatId().toString()).build();
    }
}
