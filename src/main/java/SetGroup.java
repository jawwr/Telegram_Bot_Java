import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

public class SetGroup {

    public static SendMessage setNewGroup(Message newGroup) throws IOException {
         var group = newGroup.getText().split("@");
         if (group[1].equals("ПрИ_101")){
             DataBase.changeUserGroup(newGroup.getFrom(), GroupName.ПрИ_101);
             SendMessage sendMessage = SendMessage.builder().chatId(newGroup.getChatId().toString()).text("Готово").build();
             KeyboardButtons.setDefaultButtons(sendMessage);
             return sendMessage;
         }
         else if(group[1].equals("ПрИ_102")){
             DataBase.changeUserGroup(newGroup.getFrom(), GroupName.ПрИ_102);
             SendMessage sendMessage = SendMessage.builder().chatId(newGroup.getChatId().toString()).text("Готово").build();
             KeyboardButtons.setDefaultButtons(sendMessage);
             return sendMessage;
         }
         else
             return SendMessage.builder().chatId(newGroup.getChatId().toString()).text("Неверное значение").build();
    }
}
