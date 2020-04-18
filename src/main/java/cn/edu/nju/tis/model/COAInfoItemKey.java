package cn.edu.nju.tis.model;

import java.io.Serializable;

public class COAInfoItemKey implements Serializable {
    private Integer infoId;
    private Integer coaId;

    public Integer getInfo_item_id() {
        return infoId;
    }

    public void setInfo_item_id(Integer info_item_id) {
        this.infoId = info_item_id;
    }

    public Integer getCoa_id() {
        return coaId;
    }

    public void setCoa_id(Integer coa_id) {
        this.coaId = coa_id;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((infoId == null) ? 0 : infoId.hashCode());
        result = PRIME * result + ((coaId == null) ? 0 : coaId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final COAInfoItemKey other = (COAInfoItemKey)obj;
        if(infoId == null){
            if(other.infoId != null){
                return false;
            }
        }else if(!infoId.equals(other.infoId)){
            return false;
        }
        if(coaId == null){
            return other.coaId== null;
        }else return coaId.equals(other.coaId);
    }
}
