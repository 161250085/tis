package cn.edu.nju.tis.controller;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.model.User;
import cn.edu.nju.tis.service.LoginService;
import cn.edu.nju.tis.utils.ResultMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private LoginService loginService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResultMessageBean login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        if(loginService.findByUsernameAndPassword(username,password)!=null){
            User userLogin = loginService.findByUsernameAndPassword(username,password);
            return ResultMessageUtil.success(userLogin);
        }else{
            return ResultMessageUtil.error(1,"用户名或密码错误,登录失败");
        }
    }
}
