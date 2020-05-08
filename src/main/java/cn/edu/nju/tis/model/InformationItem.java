package cn.edu.nju.tis.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "info_items")
public class InformationItem implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "info_item_id")
    private Integer id;

    @Column(name = "info_item_name")
    private String name;

    @Column(name = "item_code")
    private String code;

    @Column(name = "user_id")
    private String account;

    private String state;

    public InformationItem(){}

    public InformationItem(int id){
        this.id = id;
    }

    public InformationItem(int id, String name){
        this.id = id;
        this.name = name;
    }

    public InformationItem(int id, String name, String account){
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public InformationItem(String name, String code, String account){
        this.name = name;
        this.code = code;
        this.account = account;
        this.state = StateType.UNDER_REVIEWED.value;
    }

    public InformationItem(int id, String name, String code, String account){
        this.id = id;
        this.name = name;
        this.code = code;
        this.account = account;
        this.state = StateType.UNDER_REVIEWED.value;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getState() {
        return state;
    }

    public void setState(StateType type) {
        this.state = type.value;
    }

    @Override
    public String toString() {
        return "InformationItem{"+
                "信息项id="+id+
                "信息项名="+name+
                "代码="+code+
                "创建人"+account+
                "状态"+'}';
    }
}
