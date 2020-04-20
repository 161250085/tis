package cn.edu.nju.tis.bean;

import cn.edu.nju.tis.model.User;
import cn.edu.nju.tis.model.UserType;

public class UserBean {
    public String account;
    public String userName;
    public String type;

    public UserBean(){}

    public UserBean(User user){
        this.account = user.getAccount();
        this.userName = user.getName();
        this.type = user.getType();
    }

    public UserBean(String account, String name, UserType type){
        this.account = account;
        this.userName = name;
        this.type = type.value;
    }
}
