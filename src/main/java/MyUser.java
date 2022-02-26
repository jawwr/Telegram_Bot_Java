public class MyUser {
    public String userName;
    public GroupName selectGroup;
    public long id;

    public MyUser(String userName, GroupName selectGroup, long Id){
        this.userName = userName;
        this.selectGroup = selectGroup;
        this.id = Id;
    }
    public MyUser(){}
    public MyUser(String userName, long id){
        this.userName = userName;
        this.id = id;
    }
    public void ChangeGroup(GroupName newGroup){
        this.selectGroup = newGroup;
    }
    public void SetSelectGroup(GroupName groupName){
        this.selectGroup = groupName;
    }
}
