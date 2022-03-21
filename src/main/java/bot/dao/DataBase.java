package bot.dao;

import bot.GroupName;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

import java.sql.*;


@Component
public class DataBase {
    private static final String url = "jdbc:postgresql://localhost:5432/telegram_bot_db";
    private static final String name = "postgres";
    private static final String password = "Senya2003";

    private static Connection connection;

    static {
        try{
            connection = DriverManager.getConnection(url,name,password);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){}
    }
    public void addUser(User fromUser){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO telegram_bot_db(id,name,first_name) VALUES(?,?,?)");
            preparedStatement.setLong(1,fromUser.getId());
            preparedStatement.setString(2,fromUser.getUserName());
            preparedStatement.setString(3, fromUser.getFirstName());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void changeUserGroup(User fromUser, GroupName newGroup){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE telegram_bot_db SET set_group=(SELECT id FROM groups WHERE name=?) WHERE id=?");
            preparedStatement.setString(1, newGroup.name());
            preparedStatement.setLong(2,fromUser.getId());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public GroupName getUserGroup(User user){
        GroupName groupName = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT groups.name FROM groups JOIN telegram_bot_db AS bd ON bd.set_group=groups.id");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                groupName = GroupName.valueOf(resultSet.getString("name"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return groupName;
    }
    public boolean checkUserGroup(User user){
        boolean flag = false;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT groups.name FROM groups JOIN telegram_bot_db AS bd ON bd.set_group=groups.id");
            ResultSet resultSet = preparedStatement.executeQuery();

            flag = !resultSet.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return flag;
    }
    public boolean checkUser(User user){
        boolean flag = false;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM telegram_bot_db WHERE id=?");
            preparedStatement.setLong(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            flag = !resultSet.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return flag;
    }
}
