package bot.command;

import bot.other_logic.DateTimeWork;
import bot.dao.DataBase;
import bot.models.LessonsContainer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public class GetSheduleToday implements ICommand {

    private DataBase dataBase;
    public GetSheduleToday(){
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
        if (DateTimeWork.getDayOfWeek() == 6)
            return "<b>Сегодня нет пар</b>";
        var day = DateTimeWork.checkWeekNumber() == 1
                ? lessonsContainer.first_week.day[DateTimeWork.getDayOfWeek()]
                : lessonsContainer.second_week.day[DateTimeWork.getDayOfWeek()];
        StringBuilder str = new StringBuilder();
         str.append("<b>[ " + day.name + " ]</b>\n\n");
        for(var les:day.lessons){
            int i = 1;
            if(!DateTimeWork.timeCompare(les.time_start) && DateTimeWork.timeCompare(les.time_end)){
                str.append("<b>");
                str.append(i).append(")").append(" Пара: ").append(les.name).append("\nАудитория: ").append(les.auditorium).append("\nПреподаватель: ").append(les.teacher).append("\nНачало: ").append(les.time_start).append("\nКонец: ").append(les.time_end);
                str.append("\n\n");
                i++;
                str.append("</b>");
            }
            else {
                str.append("<b>").append(i).append(")").append(" Пара: </b>").append(les.name).append("\n<b>Аудитория: </b>").append(les.auditorium).append("\n<b>Преподаватель: </b>").append(les.teacher).append("\n<b>Начало: </b>").append(les.time_start).append("\n<b>Конец: </b>").append(les.time_end);
                str.append("\n\n");
                i++;
            }
        }
        return str.toString();
    }
}
