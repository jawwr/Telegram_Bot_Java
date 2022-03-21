package bot.models;

import bot.GroupName;

public class MyUser {
    public String userFirstName;
    public String userName;
    public GroupName selectGroup;
    public long id;

    public MyUser(String userFirstName,String userName, GroupName selectGroup, long Id){
        this.userFirstName = userFirstName;
        this.userName = userName;
        this.selectGroup = selectGroup;
        this.id = Id;
    }
    public MyUser(){}
    public MyUser(String userFirstName,String userName, long id){
        this.userFirstName = userFirstName;
        this.userName = userName;
        this.id = id;
    }
    public GroupName getSelectGroup(){return selectGroup;}
    public boolean checkGroup(){
        return selectGroup == null;
    }
}
