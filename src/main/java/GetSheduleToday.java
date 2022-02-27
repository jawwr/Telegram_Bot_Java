import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public class GetSheduleToday implements ICommand{
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
        if (DateTimeWork.getDayOfWeek() == 6)
            return "<b>Сегодня нет пар</b>";
        var day = DateTimeWork.checkWeekNumber() == 1
                ? lessonsContainer.first_week.day[DateTimeWork.getDayOfWeek()]
                : lessonsContainer.second_week.day[DateTimeWork.getDayOfWeek()];
        String str = "<b>[ " + day.name + " ]</b>\n\n";
        for(var les:day.lessons){
            int i = 1;
            if(!DateTimeWork.timeCompare(les.time_start) && DateTimeWork.timeCompare(les.time_end)){
                str += "<b>";
                str += ("" + i + ")" + " Пара: " + les.name +
                        "\nАудитория: " + les.auditorium +
                        "\nПреподаватель: " + les.teacher +
                        "\nНачало: " + les.time_start +
                        "\nКонец: " + les.time_end);
                str += "\n\n";
                i++;
                str += "</b>";
            }
            else {
                str += ("<b>" + i + ")" + " Пара: </b>" + les.name +
                        "\n<b>Аудитория: </b>" + les.auditorium +
                        "\n<b>Преподаватель: </b>" + les.teacher +
                        "\n<b>Начало: </b>" + les.time_start +
                        "\n<b>Конец: </b>" + les.time_end);
                str += "\n\n";
                i++;
            }
        }
        return str.toString();
    }
}
