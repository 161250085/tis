package cn.edu.nju.tis.model;

public enum COAType {
    CRIMINAL("CRIMINAL"),
    CIVIL("CIVIL"),
    ADMINISTRATIVE("ADMINISTRATIVE");
    public final String value;
    COAType(String value){
        this.value = value;
    }
}
