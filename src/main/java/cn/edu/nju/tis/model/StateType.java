package cn.edu.nju.tis.model;

public enum StateType {
    UNDER_REVIEWED("UNDER_REVIEWED"),
    REGISTERED("REGISTERED"),
    NOT_PASSED("NOT_PASSED");
    public final String value;
    StateType(String value) {
        this.value = value;
    }
}
