package cn.edu.nju.tis.service;

import cn.edu.nju.tis.model.User;

public interface LoginService {
    User findByUsernameAndPassword(String username, String password);
}
