package cn.edu.nju.tis.utils;

import cn.edu.nju.tis.bean.ResultMessageBean;
/**
 * @ClassName ResultMessageUtil
 * @Description TODO
 * @Author liuxueying
 * @Date 2020/4/18 9:23 PM
 * @Version 1.0
 **/
public class ResultMessageUtil {
    //当正确时返回的值
    public static ResultMessageBean success(Object resultData){
        ResultMessageBean result = new ResultMessageBean();
        result.setResultCode(0);
        result.setResultMessage(ResultMessageBean.SUCCESS);
        result.setResultData(resultData);
        return result;
    }

    public static ResultMessageBean success(){
        return success(null);
    }

    //当错误时返回的值
    public static ResultMessageBean error(int resultCode,String resultMessage){
        ResultMessageBean result = new ResultMessageBean();
        result.setResultCode(resultCode);
        result.setResultMessage(resultMessage);
        return result;
    }
}
