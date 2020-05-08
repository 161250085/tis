package cn.edu.nju.tis.service;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.model.User;

public interface UserService {
    User findByUsernameAndPassword(String username, String password);
    ResultMessageBean<Object> addUser(String username, String password) throws Exception;
}
