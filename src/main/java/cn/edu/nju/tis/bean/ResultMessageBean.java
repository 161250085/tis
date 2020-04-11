package cn.edu.nju.tis.bean;

public class ResultMessageBean {
//    三个常量，分别是成功，失败还有错误
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String ERROR = "error";
//    两个私有变量，分别是返回信息和返回码
    private String resultMessage;
    private String resultCode;
//    三个构造方法，分别是有返回码和返回信息，只有返回码，空参数
    public ResultMessageBean(String resultMessage, String resultCode){
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public ResultMessageBean(String resultCode){
        this.resultCode = resultCode;
    }

    public ResultMessageBean(){}


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
