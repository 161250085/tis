package cn.edu.nju.tis.model;

import cn.edu.nju.tis.utils.AccountUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
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
    private UserType type;

    public User(){}

    public User(String psw, String name, UserType type){
        this.account = AccountUtil.randomAccount(16);
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (this.type == UserType.Manager) {
            authorities.add(new SimpleGrantedAuthority("MANAGER"));
        } else if (this.type == UserType.Ordinary) {
            authorities.add(new SimpleGrantedAuthority("Ordinary"));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return psw;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
