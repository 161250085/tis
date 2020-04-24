package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.model.COAInformationItem;
import cn.edu.nju.tis.model.COAType;
import cn.edu.nju.tis.model.CauseOfAction;
import cn.edu.nju.tis.model.InformationItem;
import cn.edu.nju.tis.repository.COAInformationItemRepository;
import cn.edu.nju.tis.repository.COARepository;
import cn.edu.nju.tis.repository.InformationItemRepository;
import cn.edu.nju.tis.service.COAService;
import cn.edu.nju.tis.utils.MethodUtil;
import cn.edu.nju.tis.utils.ResultMessageUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class COAServiceImpl implements COAService {
    COARepository coaRepository;
    InformationItemRepository informationItemRepository;
    COAInformationItemRepository coaInformationItemRepository;
    @Override
    public ResultMessageBean<Object> addCOA(String type, String coaName, String userAccount, ConcurrentHashMap<String, String> itemAndCode, List<String> existedItem) throws Exception {
        //如果案由存在直接返回失败
        if(coaRepository.findCauseOfActionByName(coaName)!=null){
            return ResultMessageUtil.error(-1,"案由已存在");
        }
        //否则先进行数据库操作
        switch (type) {
            case "CIVIL":
                coaRepository.save(new CauseOfAction(COAType.CIVIL, coaName, userAccount));
                break;
            case "ADMINISTRATIVE":
                coaRepository.save(new CauseOfAction(COAType.ADMINISTRATIVE, coaName, userAccount));
                break;
            case "CRIMINAL":
                coaRepository.save(new CauseOfAction(COAType.CRIMINAL, coaName, userAccount));
                break;
            default:
                return ResultMessageUtil.error(-1,"案由类别错误");
        }
        CauseOfAction causeOfAction = coaRepository.findCauseOfActionByName(coaName);
        Integer coaId = causeOfAction.getId();
        //接下来存储item，同时存储item-coaId数据库,并且生成方法并进行插入
        for(Map.Entry<String, String> entry: itemAndCode.entrySet() ){
            informationItemRepository.save(new InformationItem(entry.getKey(),entry.getValue(),userAccount));
            coaInformationItemRepository.save(new COAInformationItem(informationItemRepository.findByName(entry.getKey()).getId(),coaId));
            //然后添加方法,根据种类进行添加
            MethodUtil.addMethod(type,entry.getKey(),entry.getValue());
        }
        //最后将已有的item和coa联系起来，加入数据库
        for(String item:existedItem){
            Integer infoId = informationItemRepository.findByName(item).getId();

            coaInformationItemRepository.save(new COAInformationItem(infoId, coaId));
        }
        return ResultMessageUtil.success();
    }
}
