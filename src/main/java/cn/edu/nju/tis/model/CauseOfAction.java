package cn.edu.nju.tis.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cause_of_actions")
public class CauseOfAction implements Serializable {
    @Id
    @Column(name = "coa_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "coa_type")
    private String type;

    @Column(name = "coa_name")
    private String name;

    @Column(name = "account")
    private String account;

    private String state;

    public CauseOfAction(){
    }

    public CauseOfAction(COAType type, String name, String account){
        this.type = type.value;
        this.name = name;
        this.account = account;
        this.state = StateType.UNDER_REVIEWED.value;
    }

    public CauseOfAction(Integer id, COAType type, String name, String account, String state) {
        this.id = id;
        this.type = type.value;
        this.name = name;
        this.account = account;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(COAType type) {
        this.type = type.value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setState(StateType state) {
        this.state = state.value;
    }

    @Override
    public String toString() {
        return "COA{" +
                "案由id=" + id +
                "案由种类=" + type +
                "案由名称=" + name +
                "创建人=" + account +
                "案由状态="+state+'}';
    }
}