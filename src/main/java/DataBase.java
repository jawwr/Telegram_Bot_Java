import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.telegram.telegrambots.meta.api.objects.User;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DataBase {
    private static List<MyUser> userList;

    public DataBase() throws IOException {
        if (!(new File("src/files/DataBase.json").exists())){
            Files.createFile(Path.of("src/files/DataBase.json"));
            Files.writeString(Path.of("src/files/DataBase.json"),"[]");
        }
        ObjectMapper mapper = new ObjectMapper();
        userList = new ArrayList<>(Arrays.asList(mapper.readValue(Paths.get("src/files/DataBase.json").toFile(), MyUser[].class)));
    }
    public static List<MyUser> GetBD(){
        return userList;
    }
    public static void AddUser(@NotNull User fromUser) throws IOException {
        MyUser newUser = new MyUser(fromUser.getUserName(),fromUser.getId());
        if(userList.stream().filter(x -> x.id == fromUser.getId()).count() == 0)
            userList.add(newUser);
        WriteInFile();
    }
    public static void ChangeUserGroup(User fromUser, GroupName newGroup) throws IOException {
        for (var user:userList){
            if(user.id == fromUser.getId()){
                user.selectGroup = newGroup;
                break;
            }
        }
        WriteInFile();
    }
    private static void WriteInFile() throws IOException {
        ObjectWriter writer = new ObjectMapper().writer(new DefaultPrettyPrinter());
        writer.writeValue(Paths.get("src/files/DataBase.json").toFile(),userList);
    }
}
