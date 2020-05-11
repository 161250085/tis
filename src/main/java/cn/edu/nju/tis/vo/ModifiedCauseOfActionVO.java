package cn.edu.nju.tis.vo;

import cn.edu.nju.tis.model.InformationItem;

import java.util.List;

/**
 * @ClassName ModifiedCauseOfActionVO
 * @Description TODO
 * @Author liuxueying
 * @Date 2020/5/7 4:20 PM
 * @Version 1.0
 **/
public class ModifiedCauseOfActionVO {
    Integer coaId;
    String account;
    String type;
    String coaName;
    List<InformationItem> items;
    List<InformationItem> existedItems;
    String importPackages;

    public ModifiedCauseOfActionVO(){}

    public ModifiedCauseOfActionVO(Integer coaId, String account, String type, String coaName, List<InformationItem> items, List<InformationItem> existedItems, String importPackages) {
        this.coaId = coaId;
        this.account = account;
        this.type = type;
        this.coaName = coaName;
        this.items = items;
        this.existedItems = existedItems;
        this.importPackages = importPackages;
    }


    public Integer getCoaId() {
        return coaId;
    }

    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoaName() {
        return coaName;
    }

    public void setCoaName(String coaName) {
        this.coaName = coaName;
    }

    public List<InformationItem> getItems() {
        return items;
    }

    public void setItems(List<InformationItem> items) {
        this.items = items;
    }


    public List<InformationItem> getExistedItems() {
        return existedItems;
    }

    public void setExistedItems(List<InformationItem> existedItems) {
        this.existedItems = existedItems;
    }

    public String getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(String importPackages) {
        this.importPackages = importPackages;
    }
}
