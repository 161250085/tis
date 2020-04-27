package cn.edu.nju.tis.vo;

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
    ConcurrentHashMap<String,String> itemAndCode;
    List<String> existedItem;

    public CauseOfActionVO(){}

    public CauseOfActionVO(String type, String coaName, String userAccount, ConcurrentHashMap<String, String> itemAndCode, List<String> existedItem) {
        this.type = type;
        this.coaName = coaName;
        this.userAccount = userAccount;
        this.itemAndCode = itemAndCode;
        this.existedItem = existedItem;
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

    public ConcurrentHashMap<String, String> getItemAndCode() {
        return itemAndCode;
    }

    public void setItemAndCode(ConcurrentHashMap<String, String> itemAndCode) {
        this.itemAndCode = itemAndCode;
    }

    public List<String> getExistedItem() {
        return existedItem;
    }

    public void setExistedItem(List<String> existedItem) {
        this.existedItem = existedItem;
    }
}
