package cn.edu.nju.tis.model;

import javax.persistence.*;

@Entity
@Table(name = "info_items")
public class InformationItem {
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

    public InformationItem(){}

    public InformationItem(String name, String code, String account){
        this.name = name;
        this.code = code;
        this.account = account;
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

    @Override
    public String toString() {
        return "InformationItem{"+
                "信息项id="+id+
                "信息项名="+name+
                "代码="+code+
                "创建人"+account+'}';
    }
}
