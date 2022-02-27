import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public class GetWeekShedule implements ICommand{
    @Override
    public SendMessage answer(Message message) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(setText(message));
        sendMessage.enableHtml(true);
        return sendMessage;
    }
    private @NotNull String setText(Message message) throws IOException {
        LessonsContainer lessonsContainer = new LessonsContainer(DataBase.getUserGroup(message.getFrom()));
        var week = DateTimeWork.checkWeekNumber() == 1 ? lessonsContainer.first_week : lessonsContainer.second_week;
        String str ="<b>" + week.name + "</b>\n\n";
        for(var day:week.day){
            str += "———————————";
            str += "\n<b>[ " + day.name + " ]</b>\n";
            int i = 1;
            for(var les : day.lessons) {
                str += ("\n<b>" + i + ")" + " Пара: </b>" + les.name +
                            "\n<b>Начало: </b>" + les.time_start +
                            "\n<b>Конец: </b>" + les.time_end);
                str += "\n";
                i++;
            }
        }
        return str;
    }
}
