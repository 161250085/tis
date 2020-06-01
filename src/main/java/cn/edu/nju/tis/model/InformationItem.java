package cn.edu.nju.tis.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "info_items")
public class InformationItem implements Serializable {
    @Id
    @Column(name = "info_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "info_item_name")
    private String name;

    @Column(name = "item_code")
    private String code;

    @Column(name = "user_id")
    private String account;

    @Column(name = "coa_id")
    private Integer coaId;

    private String state;

    private String description;

    private String example;

    public InformationItem(){ }

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

    public InformationItem(int id, String name, String account, Integer coaId){
        this.id = id;
        this.name = name;
        this.account = account;
        this.coaId = coaId;
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

    public InformationItem(String name, String code, String account, Integer coaId) {
        this.name = name;
        this.code = code;
        this.account = account;
        this.coaId = coaId;
        this.state = StateType.UNDER_REVIEWED.value;
    }

    public InformationItem(String name, String code, String account, Integer coaId, String description, String example) {
        this.name = name;
        this.code = code;
        this.account = account;
        this.coaId = coaId;
        this.description = description;
        this.example = example;
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

    public Integer getCoaId() {
        return coaId;
    }

    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return "InformationItem{"+
                "信息项id="+id+
                "信息项名="+name+
                "代码="+code+
                "创建人="+account+
                "状态="+state+
                "描述="+description+'}';
    }
}
