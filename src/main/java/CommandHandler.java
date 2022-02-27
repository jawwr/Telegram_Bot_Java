import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    private SendMessage groupHandler(Message message) throws IOException {
        if (MyTelegramBot.GetBotState() == BotState.WaitNewGroup){
            MyTelegramBot.SetBotState(BotState.None);
            return SetGroup.setNewGroup(message);
        }
        else
            return SendMessage.builder().chatId(message.getChatId().toString()).text("Ошибка").build();
    }
    private SendMessage commandHandler(Message command) throws IOException {
        if(DataBase.checkUser(command.getFrom()) || DataBase.checkUserGroup(command.getFrom()))
            return commandHandlerWithoutGroup(command);
        MyTelegramBot.SetBotState(BotState.None);
        ICommand commandAnswer = switch (command.getText()) {
            case "/start" -> new StartMessage();
            case "/lesson" -> new GetLessonNow();
            case "/set_group" -> new NewGroup();
            case "/schedule" -> new GetSheduleToday();
            case "/tomorrow" -> new GetSheduleTomorrow();
            case "/week" -> new GetWeekShedule();
            default -> new AnotherAnswer();
        };
        return commandAnswer.answer(command);
    }
    private SendMessage commandHandlerWithoutGroup(Message message) throws IOException {
        if(message.getText().equals("/start"))
            return StartMessage.setStartMessage(message);
        else if(message.getText().equals("/set_group")){
            NewGroup newGroup = new NewGroup();
            return newGroup.answer(message);
        }
        else
            return StartMessage.setGroupMessage(message);
    }
    private SendMessage messageHandler(Message message) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(setSomeMessage(message));
        sendMessage.setChatId(message.getChatId().toString());
        return sendMessage;
    }
    private String setSomeMessage(Message message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var phrases = Arrays.asList(mapper.readValue(Paths.get("src/files/phrase.json").toFile(),String[].class)) ;
        Random random = new Random();
        return phrases.get(random.nextInt(phrases.size()));
    }
}
