package cn.edu.nju.tis.vo;

import cn.edu.nju.tis.model.InformationItem;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName CauseOfActionVO
 * @Description TODO
 * @Author liuxueying
 * @Date 2020/4/24 4:13 PM
 * @Version 1.0
 **/
public class CauseOfActionVO {
    String type;
    String coaName;
    String userAccount;
    List<InformationItem> items;
    List<String> existedItem;
    String importPackages;

    public CauseOfActionVO(){}

    public CauseOfActionVO(String type, String coaName, String userAccount, List<InformationItem> items, List<String> existedItem, String importPackages) {
        this.type = type;
        this.coaName = coaName;
        this.userAccount = userAccount;
        this.items = items;
        this.existedItem = existedItem;
        this.importPackages = importPackages;
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

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public List<String> getExistedItem() {
        return existedItem;
    }

    public void setExistedItem(List<String> existedItem) {
        this.existedItem = existedItem;
    }

    public String getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(String importPackages) {
        this.importPackages = importPackages;
    }

    public List<InformationItem> getItems() {
        return items;
    }

    public void setItems(List<InformationItem> items) {
        this.items = items;
    }
}
