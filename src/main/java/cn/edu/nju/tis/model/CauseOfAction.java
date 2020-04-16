package cn.edu.nju.tis.model;

import cn.edu.nju.tis.utils.COAType;

import javax.persistence.*;

@Entity
@Table(name = "cause_of_actions")
public class CauseOfAction {
    @Id
    @Column(name = "coa_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "coa_type")
    @Enumerated(EnumType.STRING)
    private COAType type;

    @Column(name = "coa_name")
    private String name;

    private String user_id;

    public CauseOfAction(){
    }

    public CauseOfAction(COAType type, String name, String user_id){
        this.type = type;
        this.name = name;
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public COAType getType() {
        return type;
    }

    public void setType(COAType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "COA{" +
                "案由id=" + id +
                "案由种类=" + type +
                "案由名称=" + name +
                "创建人=" + user_id +'}';
    }
}
