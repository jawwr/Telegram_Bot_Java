import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

class Lesson{
    public String name;
    public String teacher;
    public String auditorium;
    public String time_start;
    public String time_end;
    public Lesson(){}
}
class Day{
    public String name;
    public Lesson[] lessons;
    public Day(){}
}
class Week{
    public String name;
    public Day[] day;
    public Week(){}
}
public class LessonsContainer {
    public Week first_week;
    public Week second_week;

    public LessonsContainer(){}
    public LessonsContainer(GroupName group) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var container = mapper.readValue(Paths.get("src/files/lessons_"+ group.name() +".json").toFile(),LessonsContainer.class);
        this.first_week = container.first_week;
        this.second_week = container.second_week;
    }
}
