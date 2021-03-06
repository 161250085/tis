package cn.edu.nju.tis.controller;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.model.InformationItem;
import cn.edu.nju.tis.model.User;
import cn.edu.nju.tis.service.*;
import cn.edu.nju.tis.utils.ResultMessageUtil;
import cn.edu.nju.tis.vo.COAandInfoItemVO;
import cn.edu.nju.tis.vo.CauseOfActionVO;
import cn.edu.nju.tis.vo.ModifiedCauseOfActionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CauseOfActionManageService causeOfActionManageService;

    @Autowired
    private COAService coaService;

    @Autowired
    private InfoItemService infoItemService;

    @Autowired
    private ElementExtractionService elementExtractionService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResultMessageBean<Object> login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        if(userService.findByUsernameAndPassword(username,password)!=null){
            User userLogin = userService.findByUsernameAndPassword(username,password);
            return ResultMessageUtil.success(userLogin);
        }else{
            return ResultMessageUtil.error(1,"用户名或密码错误,登录失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResultMessageBean<Object> register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        try{
            return userService.addUser(username,password);
        }catch (Exception e) {
            return ResultMessageUtil.error(-1, "注册失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCauseOfActionList", method = RequestMethod.GET)
    public ResultMessageBean getCauseOfActionList(@RequestParam(value = "userAccount") String userAccount){
        List<COAandInfoItemVO> causeOfActionList = causeOfActionManageService.findCOAByUserAccount(userAccount);
        return ResultMessageUtil.success(causeOfActionList);
    }

    @ResponseBody
    @RequestMapping(value = "/getRegisteredInfoItems", method = RequestMethod.GET)
    public ResultMessageBean getRegisteredInfoItems(){
        List<InformationItem> infoItemList = infoItemService.findRegisteredInformationItems();
        return ResultMessageUtil.success(infoItemList);
    }

    @ResponseBody
    @RequestMapping(value = "/addCauseOfAction", method = RequestMethod.POST)
    public ResultMessageBean addCauseOfAction(@RequestBody CauseOfActionVO causeOfActionVO){
        try{
            return coaService.addCOA(causeOfActionVO.getType(), causeOfActionVO.getCoaName(), causeOfActionVO.getUserAccount(), causeOfActionVO.getImportPackages(),causeOfActionVO.getItems(), causeOfActionVO.getExistedItem());
        }catch (Exception e) {
            return ResultMessageUtil.error(-1,"案由添加失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/modifyCauseOfAction", method = RequestMethod.POST)
    public ResultMessageBean modifyCauseOfAction(@RequestBody ModifiedCauseOfActionVO modifiedCauseOfActionVO) throws InterruptedException {
        return coaService.modifyCOA(modifiedCauseOfActionVO.getAccount(), modifiedCauseOfActionVO.getCoaId(), modifiedCauseOfActionVO.getType(), modifiedCauseOfActionVO.getCoaName(), modifiedCauseOfActionVO.getItems(), modifiedCauseOfActionVO.getExistedItems(), modifiedCauseOfActionVO.getImportPackages());
    }

    @ResponseBody
    @RequestMapping(value = "/getInformationItemDetail", method = RequestMethod.GET)
    public ResultMessageBean getInformationItemDetail(@RequestParam(value = "informationItemId") Integer informationItemId){
        InformationItem informationItem = causeOfActionManageService.findInfoItemByInfoItemId(informationItemId);
        return ResultMessageUtil.success(informationItem);
    }

    @ResponseBody
    @RequestMapping(value = "/getInfoItemListWithCode", method = RequestMethod.GET)
    public ResultMessageBean getInfoItemListWithCode(@RequestParam(value = "coaId") Integer coaId){
        List<InformationItem> infoItemListWithCode = infoItemService.findInfoItemsByCOAId(coaId);
        return ResultMessageUtil.success(infoItemListWithCode);
    }

    @ResponseBody
    @RequestMapping(value = "/extract", method = RequestMethod.POST)
    public ResultMessageBean extract(@RequestParam("files") MultipartFile[] files){
        try {
            return elementExtractionService.uploadXML(files);
        } catch(Exception e) {
            e.printStackTrace();
            return ResultMessageUtil.error(-1,"要素抽取失败");
        }
    }
}
