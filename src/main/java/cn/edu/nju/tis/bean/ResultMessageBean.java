package cn.edu.nju.tis.bean;

public class ResultMessageBean<T> {
    //三个常量，分别是成功，失败还有错误
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String ERROR = "error";

    //三个私有变量，分别是返回码、返回信息、返回数据
    private Integer resultCode;
    private String resultMessage;
    private T resultData;

    //两个构造方法，分别是有返回码、返回信息和返回数据，空参数
    public ResultMessageBean(Integer resultCode ,String resultMessage, T resultData){
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultData = resultData;
    }

    public ResultMessageBean(){}


    public Integer getResultCode() { return resultCode; }

    public void setResultCode(Integer resultCode) { this.resultCode = resultCode; }

    public String getResultMessage() { return resultMessage; }

    public void setResultMessage(String resultMessage) { this.resultMessage = resultMessage; }

    public T getResultData() { return resultData; }

    public void setResultData(T resultData) { this.resultData = resultData; }

}
