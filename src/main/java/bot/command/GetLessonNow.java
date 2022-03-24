package bot.command;

import bot.dao.DataBase;
import bot.models.Day;
import bot.models.Lesson;
import bot.other_logic.DateTimeWork;
import bot.models.LessonsContainer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

public class GetLessonNow implements ICommand {

    private DataBase dataBase;
    public GetLessonNow(){
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
    private String setText(Message message) throws IOException {
        LessonsContainer lessonsContainer = new LessonsContainer(dataBase.getUserGroup(message.getFrom()));
        if (DateTimeWork.getDayOfWeek() == 6)
            return "<b>Сегодня нет пар</b>";
        Day day = DateTimeWork.checkWeekNumber() == 1 ? lessonsContainer.first_week.day[DateTimeWork.getDayOfWeek()] : lessonsContainer.second_week.day[DateTimeWork.getDayOfWeek()];
        if (!DateTimeWork.timeCompare(day.lessons[day.lessons.length - 1].time_end))
            return "<b>Пары кончились</b>";
        if(DateTimeWork.timeCompare(day.lessons[0].time_start))
            return "<b>Пары еще не начались</b>\n<b>Первая пара: </b>" + day.lessons[0].name + "\n<b>Начало: </b>" + day.lessons[0].time_start + "\n<b>Аудитория: </b>" + day.lessons[0].auditorium;
        for(Lesson les:day.lessons){
            if(!DateTimeWork.timeCompare(les.time_start) && DateTimeWork.timeCompare(les.time_end))
                return "<b>Сейчас идет пара: </b>" + les.name + "\n<b>Аудитория: </b>" + les.auditorium + "<b>\nПреподаватель: </b>" + les.teacher + "\n<b>Начало: </b>" + les.time_start + "\n<b>Конец: </b>" + les.time_end;
        }
        for (int i = 0;i < day.lessons.length;i++){
            if (!DateTimeWork.timeCompare(day.lessons[i].time_end) && DateTimeWork.timeCompare(day.lessons[i + 1].time_start))
                return "<b>Сейчас перемена,\nСледующая пара: </b>" + day.lessons[i + 1].name;
        }
        return null;
    }
}
