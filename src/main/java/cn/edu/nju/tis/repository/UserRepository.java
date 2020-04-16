package cn.edu.nju.tis.repository;

import cn.edu.nju.tis.model.User;
import cn.edu.nju.tis.utils.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Author cruck
 * @Description
 * @Date 11:46 2020/4/16
 * @Param
 * @return
 **/
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByAccount(String account);

    User findByType(UserType type);

}
