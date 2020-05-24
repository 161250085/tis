package cn.edu.nju.tis.repository;

import cn.edu.nju.tis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

    List<User> findByType(String type);

    User findByName(String name);

    User findByNameAndType(String name, String type);

    //根据用户名密码查找用户
    User findFirstByNameAndPsw(String username, String password);

    @Modifying
    @Query("update User user set user.name = ?1, user.psw = ?2  where user.account = ?3")
    User modifyUser(String name, String psw, String id);
}
