package cn.edu.nju.tis.model;

import cn.edu.nju.tis.utils.AccountUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_account")
    //用户account，自动生成,16位包含大小写字母和数字的随机字符串
    private String account;
    //用户名
    @Column(name = "user_name")
    private String name;
    //密码
    @Column(name = "user_psw")
    private String psw;

    @Column(name = "user_type")
    private String type;

    public User() {
    }

    public User(String psw, String name, UserType type) {
        this.account = AccountUtil.randomAccount(16);
        this.psw = psw;
        this.name = name;
        this.type = type.value;
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

    public String getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type.value;
    }
}