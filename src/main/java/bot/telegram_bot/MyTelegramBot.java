package bot.telegram_bot;

import bot.handler.CommandHandler;
import bot.util.PropertiesUtil;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class MyTelegramBot extends TelegramLongPollingBot {

    private static BotState state;
    private final CommandHandler handler;

    public static BotState GetBotState(){return state;}
    public static void SetBotState(BotState newState){state = newState;}

    public MyTelegramBot(){
        handler = new CommandHandler();
        state = BotState.None;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (update.hasMessage()) {
            try{
                answer(message);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void answer(Message message) throws TelegramApiException, IOException { execute(handler.Handler(message)); }

    @Override
    public String getBotUsername() {
        return "TelegramBotOnJava";
    }

    @Override
    public String getBotToken() {
        return PropertiesUtil.get("bot.token");
    }
}