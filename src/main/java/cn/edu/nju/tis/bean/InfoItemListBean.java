package cn.edu.nju.tis.bean;

import cn.edu.nju.tis.model.InformationItem;

import java.util.List;
public class InfoItemListBean {
    public Integer infoItemId;
    public String infoItemName;
    public String itemCode;
    public String account;
    List<InfoItemListBean> infoItemListBeanList;

    public InfoItemListBean(){}

    public InfoItemListBean(InformationItem informationItem){
        this.infoItemId = informationItem.getId();
        this.infoItemName = informationItem.getName();
        this.itemCode = informationItem.getCode();
        this.account = informationItem.getAccount();
    }

    public Integer getInfoItemId() {
        return infoItemId;
    }

    public void setInfoItemId(Integer infoItemId) {
        this.infoItemId = infoItemId;
    }

    public String getInfoItemName() {
        return infoItemName;
    }

    public void setInfoItemName(String infoItemName) {
        this.infoItemName = infoItemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<InfoItemListBean> getInfoItemListBeanList() {
        return infoItemListBeanList;
    }

    public void setInfoItemListBeanList(List<InfoItemListBean> infoItemListBeanList) {
        this.infoItemListBeanList = infoItemListBeanList;
    }
}
