package cn.edu.nju.tis.model;

import cn.edu.nju.tis.utils.UserType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_account")
    private String account;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_psw")
    private String psw;

    @Column(name = "user_type")
    private UserType type;

    public User(){}

    public User(String account, String psw, String name, UserType type){
        this.account = account;
        this.psw = psw;
        this.name = name;
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{"+
                "账号="+account+
                "密码="+psw+
                "用户名="+name+
                "用户类型="+type+'}';
    }
}
