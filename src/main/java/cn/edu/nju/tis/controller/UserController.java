package cn.edu.nju.tis.controller;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.model.InformationItem;
import cn.edu.nju.tis.model.User;
import cn.edu.nju.tis.service.COAService;
import cn.edu.nju.tis.service.CauseOfActionManageService;
import cn.edu.nju.tis.service.InfoItemService;
import cn.edu.nju.tis.service.LoginService;
import cn.edu.nju.tis.utils.ResultMessageUtil;
import cn.edu.nju.tis.vo.COAandInfoItemVO;
import cn.edu.nju.tis.vo.CauseOfActionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private CauseOfActionManageService causeOfActionManageService;

    @Autowired
    private COAService coaService;

    @Autowired
    private InfoItemService infoItemService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResultMessageBean<Object> login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        if(loginService.findByUsernameAndPassword(username,password)!=null){
            User userLogin = loginService.findByUsernameAndPassword(username,password);
            return ResultMessageUtil.success(userLogin);
        }else{
            return ResultMessageUtil.error(1,"用户名或密码错误,登录失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCauseOfActionList", method = RequestMethod.GET)
    public ResultMessageBean getCauseOfActionList(@RequestParam(value = "userAccount") String userAccount){
        List<COAandInfoItemVO> causeOfActionList = causeOfActionManageService.findCOAByUserAccount(userAccount);
        return ResultMessageUtil.success(causeOfActionList);
    }

    @ResponseBody
    @RequestMapping(value = "/getExistedInfoItems", method = RequestMethod.GET)
    public ResultMessageBean getExistedInfoItems(){
        List<InformationItem> infoItemList = infoItemService.findAllInformationItems();
        return ResultMessageUtil.success(infoItemList);
    }

    @ResponseBody
    @RequestMapping(value = "/addCauseOfAction", method = RequestMethod.POST)
    public ResultMessageBean addCauseOfAction(@RequestBody CauseOfActionVO causeOfActionVO){
        try{
            System.out.println(causeOfActionVO.getType());
            System.out.println(causeOfActionVO.getCoaName());
            System.out.println(causeOfActionVO.getUserAccount());
            System.out.println(causeOfActionVO.getItemAndCode());
            System.out.println(causeOfActionVO.getExistedItem());
            return coaService.addCOA(causeOfActionVO.getType(), causeOfActionVO.getCoaName(), causeOfActionVO.getUserAccount(), causeOfActionVO.getItemAndCode(), causeOfActionVO.getExistedItem());
        }catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return ResultMessageUtil.error(-1,"案由添加失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getInformationItemDetail", method = RequestMethod.GET)
    public ResultMessageBean getInformationItemDetail(@RequestParam(value = "informationItemId") Integer informationItemId){
        InformationItem informationItem = causeOfActionManageService.findInfoItemByInfoItemId(informationItemId);
        return ResultMessageUtil.success(informationItem);
    }

    @ResponseBody
    @RequestMapping(value = "/getInfoItemListWithCode", method = RequestMethod.GET)
    public ResultMessageBean getInfoItemListWithCode(@RequestParam(value = "coaId") Integer coaId, @RequestParam(value = "userAccount") String userAccount){
        List<InformationItem> infoItemListWithCode = causeOfActionManageService.findInfoItemsByCOAIdAndAccount(coaId, userAccount);
        return ResultMessageUtil.success(infoItemListWithCode);
    }


}
