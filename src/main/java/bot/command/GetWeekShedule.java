package bot.command;

import bot.dao.DataBase;
import bot.other_logic.DateTimeWork;
import bot.models.LessonsContainer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public class GetWeekShedule implements ICommand {

    private DataBase dataBase;
    public GetWeekShedule(){
        this.dataBase = new DataBase();
    }

    @Override
    public SendMessage answer(Message message) throws IOException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(setText(message));
        sendMessage.enableHtml(true);
        return sendMessage;
    }
    private @NotNull String setText(Message message) throws IOException {
        LessonsContainer lessonsContainer = new LessonsContainer(dataBase.getUserGroup(message.getFrom()));
        var week = DateTimeWork.checkWeekNumber() == 1 ? lessonsContainer.first_week : lessonsContainer.second_week;
        StringBuilder str = new StringBuilder("<b>" + week.name + "</b>\n\n");
        for(var day:week.day){
            str.append("———————————");
            str.append("\n<b>[ ").append(day.name).append(" ]</b>\n");
            int i = 1;
            for(var les : day.lessons) {
                str.append("\n<b>").append(i).append(")").append(" Пара: </b>").append(les.name).append("\n<b>Начало: </b>").append(les.time_start).append("\n<b>Конец: </b>").append(les.time_end);
                str.append("\n");
                i++;
            }
        }
        return str.toString();
    }
}
