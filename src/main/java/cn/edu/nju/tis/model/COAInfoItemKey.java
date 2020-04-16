package cn.edu.nju.tis.model;

import java.io.Serializable;

public class COAInfoItemKey implements Serializable {
    private Integer info_item_id;
    private Integer coa_id;

    public COAInfoItemKey(){}

    public COAInfoItemKey(Integer info_item_id, Integer coa_id) {
        this.info_item_id = info_item_id;
        this.coa_id = coa_id;
    }

    public Integer getInfo_item_id() {
        return info_item_id;
    }

    public void setInfo_item_id(Integer info_item_id) {
        this.info_item_id = info_item_id;
    }

    public Integer getCoa_id() {
        return coa_id;
    }

    public void setCoa_id(Integer coa_id) {
        this.coa_id = coa_id;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((info_item_id == null) ? 0 : info_item_id.hashCode());
        result = PRIME * result + ((coa_id == null) ? 0 : coa_id.hashCode());
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
        if(info_item_id == null){
            if(other.info_item_id != null){
                return false;
            }
        }else if(!info_item_id.equals(other.info_item_id)){
            return false;
        }
        if(coa_id == null){
            return other.coa_id == null;
        }else return coa_id.equals(other.coa_id);
    }
}
