package cn.edu.nju.tis.controller;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.service.COAService;
import cn.edu.nju.tis.service.CauseOfActionManageService;
import cn.edu.nju.tis.utils.ResultMessageUtil;
import cn.edu.nju.tis.vo.COAandInfoItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/manager")
public class ManagerController {

    @Autowired
    private CauseOfActionManageService causeOfActionManageService;

    @Autowired
    private COAService coaService;

    @ResponseBody
    @RequestMapping(value = "/getURCauseOfActionList", method = RequestMethod.GET)
    public ResultMessageBean getURCauseOfActionList(){
        List<COAandInfoItemVO> urcauseOfActionList = causeOfActionManageService.findURCauseOfActionList();
        return ResultMessageUtil.success(urcauseOfActionList);
    }

    @ResponseBody
    @RequestMapping(value = "/passCauseOfAction", method = RequestMethod.GET)
    public ResultMessageBean passCauseOfAction(@RequestParam(value = "coaId") Integer coaId){
        try{
            return coaService.passCOA(coaId);
        }catch (Exception e) {
            return ResultMessageUtil.error(-1,"案由注册失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/notPassCauseOfAction", method = RequestMethod.GET)
    public ResultMessageBean notPassCauseOfAction(@RequestParam(value = "coaId") Integer coaId){
        return coaService.notPassCOA(coaId);
    }
}
