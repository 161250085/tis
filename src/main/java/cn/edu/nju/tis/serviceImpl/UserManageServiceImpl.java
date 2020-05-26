package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.model.User;
import cn.edu.nju.tis.model.UserType;
import cn.edu.nju.tis.repository.UserRepository;
import cn.edu.nju.tis.service.UserManageService;
import cn.edu.nju.tis.utils.ResultMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class UserManageServiceImpl implements UserManageService {
        @Autowired
        UserRepository userRepository;
        //密码只能包含大小写字母和数字，并且长度在8-16位
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        @Override
        public ResultMessageBean<Object> addUser(String userName, String psw) {
            if(userRepository.findByName(userName)!=null){
                return ResultMessageUtil.error(-1,"用户名已存在");
            }
            if(userName.equals("")){
                return ResultMessageUtil.error(-1,"用户名不能为空");
            }
            if(!psw.matches(regex)){
                return ResultMessageUtil.error(-1, "密码只能是8-16位字母与数字组成");
            }
            userRepository.save(new User(psw,userName, UserType.ORDINARY));
            return ResultMessageUtil.success();
        }

        @Override
        public ResultMessageBean<Object> modifyUser(String userID, String userName, String psw) {
            if(userRepository.findByName(userName)!=null){
                return ResultMessageUtil.error(-1,"用户名已存在");
            }
            if(userName.equals("")){
                return ResultMessageUtil.error(-1,"用户名不能为空");
            }
            if(!psw.matches(regex)){
                return ResultMessageUtil.error(-1, "密码只能是8-16位字母与数字组成");
            }
            userRepository.modifyUser(userName, psw, userID);
            return ResultMessageUtil.success();
        }
    }
