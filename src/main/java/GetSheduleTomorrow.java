import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public class GetSheduleTomorrow implements ICommand{
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
        int dayTomorrow = DateTimeWork.getDayOfWeek() + 1;
        Day day;
        if (dayTomorrow == 7) {
            dayTomorrow = 0;
            day = DateTimeWork.checkWeekNumber() != 1 ? lessonsContainer.first_week.day[dayTomorrow] : lessonsContainer.second_week.day[dayTomorrow];
        }
        else if(dayTomorrow == 6) {
            return "<b>Завтра пар нет</b>";
        }
        else {
            day = DateTimeWork.checkWeekNumber() == 1 ? lessonsContainer.first_week.day[dayTomorrow] : lessonsContainer.second_week.day[dayTomorrow];
        }
        String str ="<b>[ " + day.name + " ]</b>\n\n";
        int i = 1;
        for(var les:day.lessons){
            str += ("<b>" + i + ")" + " Пара: </b>" + les.name +
                    "\n<b>Аудитория: </b>" + les.auditorium +
                    "\n<b>Начало: </b>" + les.time_start +
                    "\n<b>Конец: </b>" + les.time_end);
            str += "\n\n";
            i++;
        }
        return str;
    }
}
