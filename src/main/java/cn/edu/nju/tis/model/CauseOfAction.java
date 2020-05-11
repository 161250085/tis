package cn.edu.nju.tis.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cause_of_actions")
public class CauseOfAction implements Serializable {
    @Id
    @Column(name = "coa_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.edu.nju.tis.utils.ManulInsertGenerator")
    private Integer id;

    @Column(name = "coa_type")
    private String type;

    @Column(name = "coa_name")
    private String name;

    @Column(name = "account")
    private String account;

    private String state;

    @Column(name = "import_packages")
    private String importPackages;

    public CauseOfAction(){
    }

    public CauseOfAction(COAType type, String name, String account){
        this.type = type.value;
        this.name = name;
        this.account = account;
        this.state = StateType.UNDER_REVIEWED.value;
    }

    public CauseOfAction(Integer id, COAType type, String name, String account){
        this.id = id;
        this.type = type.value;
        this.name = name;
        this.account = account;
        this.state = StateType.UNDER_REVIEWED.value;
    }

    public CauseOfAction(COAType type, String name, String account, String importPackages) {
        this.type = type.value;
        this.name = name;
        this.account = account;
        this.state = StateType.UNDER_REVIEWED.value;
        this.importPackages = importPackages;
    }

    public CauseOfAction(Integer coaId, COAType type, String name, String account, String importPackages) {
        this.type = type.value;
        this.name = name;
        this.account = account;
        this.state = StateType.UNDER_REVIEWED.value;
        this.importPackages = importPackages;
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

    public String getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(String importPackages) {
        this.importPackages = importPackages;
    }

    @Override
    public String toString() {
        return "COA{" +
                "案由id=" + id +
                "案由种类=" + type +
                "案由名称=" + name +
                "创建人=" + account +
                "导入包=" + importPackages+
                "案由状态="+state+'}';
    }
}