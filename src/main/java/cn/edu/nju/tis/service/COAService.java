package cn.edu.nju.tis.service;

import cn.edu.nju.tis.bean.ResultMessageBean;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface COAService {
    ResultMessageBean<Object> addCOA(String type, String coaName, String userAccount, ConcurrentHashMap<String,String> itemAndCode, List<String> existedItem) throws Exception;
    ResultMessageBean<Object> approveCOA(Integer coaId, ConcurrentHashMap<String,String> itemAndCode, int is_approved) throws Exception;
}
