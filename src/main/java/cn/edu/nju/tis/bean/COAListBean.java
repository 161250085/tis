package cn.edu.nju.tis.bean;

import cn.edu.nju.tis.model.COAType;

import java.util.List;

public class COAListBean {
    public Integer coaId;
    public COAType type;
    public String coaName;
    public String account;
    List<COAListBean> coaListBeans;

    public COAListBean(){}

    public COAListBean(Integer coaId, COAType type, String coaName, String account) {
        this.coaId = coaId;
        this.type = type;
        this.coaName = coaName;
        this.account = account;
    }

    public Integer getCoaId() {
        return coaId;
    }

    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
    }

    public COAType getType() {
        return type;
    }

    public void setType(COAType type) {
        this.type = type;
    }

    public String getCoaName() {
        return coaName;
    }

    public void setCoaName(String coaName) {
        this.coaName = coaName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<COAListBean> getCoaListBeans() {
        return coaListBeans;
    }

    public void setCoaListBeans(List<COAListBean> coaListBeans) {
        this.coaListBeans = coaListBeans;
    }
}
