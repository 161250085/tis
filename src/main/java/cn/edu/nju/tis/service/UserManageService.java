package cn.edu.nju.tis.service;

import cn.edu.nju.tis.bean.ResultMessageBean;

public interface UserManageService {
    ResultMessageBean<Object>  addUser(String userName, String psw);
    ResultMessageBean<Object> modifyUser(String userID, String userName,String psw);
}
