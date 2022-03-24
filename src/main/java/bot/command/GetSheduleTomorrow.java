package bot.command;

import bot.models.Lesson;
import bot.other_logic.DateTimeWork;
import bot.dao.DataBase;
import bot.models.Day;
import bot.models.LessonsContainer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public class GetSheduleTomorrow implements ICommand {

    private DataBase dataBase;
    public GetSheduleTomorrow(){
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
        int dayTomorrow = DateTimeWork.getDayOfWeek() + 1;
        Day day;
        if (dayTomorrow == 7 || dayTomorrow == 6) {
            dayTomorrow = 0;
            day = DateTimeWork.checkWeekNumber() != 1 ? lessonsContainer.first_week.day[dayTomorrow] : lessonsContainer.second_week.day[dayTomorrow];
        }
        else {
            day = DateTimeWork.checkWeekNumber() == 1 ? lessonsContainer.first_week.day[dayTomorrow] : lessonsContainer.second_week.day[dayTomorrow];
        }
        StringBuilder str = new StringBuilder();
        str.append("<b>[ " + day.name + " ]</b>\n\n");
        int i = 1;
        for(Lesson les:day.lessons){
            str.append("<b>").append(i).append(")").append(" Пара: </b>").append(les.name).append("\n<b>Аудитория: </b>").append(les.auditorium).append("\n<b>Начало: </b>").append(les.time_start).append("\n<b>Конец: </b>").append(les.time_end);
            str.append("\n\n");
            i++;
        }
        return str.toString();
    }
}
