package cn.edu.nju.tis.service;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.model.InformationItem;

import java.util.List;

public interface COAService {
    ResultMessageBean<Object> addCOA(String type, String coaName, String userAccount, String importPackages, List<InformationItem> items, List<String> existedItem) throws Exception;

    ResultMessageBean<Object> modifyCOA(String userAccount,Integer coaId, String type, String coaName, List<InformationItem> items, List<InformationItem> existedItems,String importPackages);

    ResultMessageBean<Object> passCOA(Integer coaId) throws Exception;

    ResultMessageBean<Object> notPassCOA(Integer coaId);

}
