package cn.edu.nju.tis.repository;

import cn.edu.nju.tis.model.User;
import cn.edu.nju.tis.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author cruck
 * @Description
 * @Date 11:46 2020/4/16
 * @Param
 * @return
 **/
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByAccount(String account);

    List<User> findByType(UserType type);

    User findByName(String name);

    User findByNameAndType(String name, UserType type);

}
