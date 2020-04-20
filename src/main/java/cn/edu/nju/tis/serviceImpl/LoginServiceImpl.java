package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.model.User;
import cn.edu.nju.tis.repository.UserRepository;
import cn.edu.nju.tis.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName LoginServiceImpl
 * @Description TODO
 * @Author liuxueying
 * @Date 2020/4/18 9:33 PM
 * @Version 1.0
 **/
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findFirstByNameAndPsw(username, password);
    }
}
