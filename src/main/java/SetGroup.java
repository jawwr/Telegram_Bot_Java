import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.io.IOException;

//TODO сделать всплывающие плитки со всеми командами
public class SetGroup {
    public static SendMessage SetNewGroup(Message newGroup) throws IOException {
         var group = newGroup.getText().split("@");
         if (group[1].equals("ПрИ-101")){
             DataBase.ChangeUserGroup(newGroup.getFrom(),GroupName.PrE_101);
             return SendMessage.builder().chatId(newGroup.getChatId().toString()).text("Готово").replyMarkup(new ReplyKeyboardMarkup()).build();
         }
         else if(group[1].equals("ПрИ-102")){
             DataBase.ChangeUserGroup(newGroup.getFrom(),GroupName.PrE_102);
             return SendMessage.builder().chatId(newGroup.getChatId().toString()).text("Готово").build();
         }
         else
             return SendMessage.builder().chatId(newGroup.getChatId().toString()).text("Неверное значение").replyMarkup(new ReplyKeyboardMarkup()).build();
    }
    public static SendMessage SetChangeGroup(Message newGroup) throws IOException {
        var group = newGroup.getText().split("@");
        if (group[1].equals("ПрИ-101")){
            DataBase.ChangeUserGroup(newGroup.getFrom(),GroupName.PrE_101);
            return SendMessage.builder().chatId(newGroup.getChatId().toString()).text("Готово").replyMarkup(new ReplyKeyboardMarkup()).build();
        }
        else if(group[1].equals("ПрИ-102")){
            DataBase.ChangeUserGroup(newGroup.getFrom(),GroupName.PrE_102);
            return SendMessage.builder().chatId(newGroup.getChatId().toString()).text("Готово").replyMarkup(new ReplyKeyboardMarkup()).build();
        }
        else
            return SendMessage.builder().chatId(newGroup.getChatId().toString()).text("Неверное значение").replyMarkup(new ReplyKeyboardMarkup()).build();
    }
}
