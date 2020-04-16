package cn.edu.nju.tis.model;

import cn.edu.nju.tis.utils.COAInfoItemKey;

import javax.persistence.*;

@Entity
@Table(name = "coa_info_items")
@IdClass(COAInfoItemKey.class)
public class COAInformationItem {
    @Id
    @Column(name = "info_item_id")
    private Integer info_id;

    @Id
    private Integer coa_id;

    public COAInformationItem() {
    }

    public COAInformationItem(Integer info_id, Integer coa_id) {
        this.info_id = info_id;
        this.coa_id = coa_id;
    }

    public Integer getInfo_id() {
        return info_id;
    }

    public void setInfo_id(Integer info_id) {
        this.info_id = info_id;
    }

    public Integer getCoa_id() {
        return coa_id;
    }

    public void setCoa_id(Integer coa_id) {
        this.coa_id = coa_id;
    }

    @Override
    public String toString() {
        return "COAInformationItem{"+
                "信息项id="+info_id+
                "案由id="+coa_id+'}';
    }
}
