package bot.models;

import bot.GroupName;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

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
