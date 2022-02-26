class Lesson{
    public String name;
    public String teacher;
    public String auditorium;
    public String time_start;
    public String time_end;
    public Lesson(){}
}
class Day{
    public Lesson[] lessons;
    public Day(){}
}
class Week{
    public Day[] days;
    public Week(){}
}

public class LessonsContainer {
    public Week firstWeek;
    public Week secondWeek;

}
//TODO доделать классы
//TODO сделать считывание с json
//TODO сделать методы для работы

//TODO сделать классы для работы с самим расписанием
