package cn.edu.nju.tis.model;

public enum UserType {
    MANAGER("Manager"),
    ORDINARY("Ordinary");
    public final String value;
    UserType(String value) {
        this.value = value;
    }
}
