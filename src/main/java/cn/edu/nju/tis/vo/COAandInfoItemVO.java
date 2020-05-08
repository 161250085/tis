package cn.edu.nju.tis.vo;

import cn.edu.nju.tis.model.InformationItem;

import java.util.List;

/**
 * @ClassName COAandInfoItemVO
 * @Description TODO
 * @Author liuxueying
 * @Date 2020/4/21 4:41 PM
 * @Version 1.0
 **/
public class COAandInfoItemVO {
    private Integer id;
    private String type;
    private String name;
    private String state;
    private String account;
    private List<InformationItem> infoItems;

    public COAandInfoItemVO(Integer id, String type, String name, String state, String account, List<InformationItem> infoItems) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.state = state;
        this.account = account;
        this.infoItems = infoItems;
    }

    public COAandInfoItemVO() {
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getAccount() { return account; }

    public void setAccount(String account) { this.account = account; }

    public List<InformationItem> getInfoItems() { return infoItems; }

    public void setInfoItems(List<InformationItem> infoItems) { this.infoItems = infoItems; }
}
