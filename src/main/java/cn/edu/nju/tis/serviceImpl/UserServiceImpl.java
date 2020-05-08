package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.model.User;
import cn.edu.nju.tis.model.UserType;
import cn.edu.nju.tis.repository.UserRepository;
import cn.edu.nju.tis.service.UserService;
import cn.edu.nju.tis.utils.MethodUtil;
import cn.edu.nju.tis.utils.ResultMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author liuxueying
 * @Date 2020/4/18 9:33 PM
 * @Version 1.0
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findFirstByNameAndPsw(username, password);
    }

    @Override
    public ResultMessageBean<Object> addUser(String username, String password) throws Exception {
        //如果用户名存在直接返回失败
        if(userRepository.findByName(username)!=null) {
            return ResultMessageUtil.error(-1, "用户名已存在");
        }
        userRepository.save(new User(password, username, UserType.ORDINARY));
        return ResultMessageUtil.success();
    }

}
