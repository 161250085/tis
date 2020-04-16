package cn.edu.nju.tis.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "coa_info_items")
@IdClass(COAInfoItemKey.class)
public class COAInformationItem implements Serializable {
    @Id
    @Column(name = "info_item_id")
    private Integer infoId;

    @Id
    @Column(name = "coa_id")
    private Integer coaId;

    public COAInformationItem() {
    }

    public COAInformationItem(Integer infoId, Integer coaId) {
        this.infoId = infoId;
        this.coaId = coaId;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getCoaId() {
        return coaId;
    }

    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
    }

    @Override
    public String toString() {
        return "COAInformationItem{"+
                "信息项id="+infoId+
                "案由id="+coaId+'}';
    }
}
