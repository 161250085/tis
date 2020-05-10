package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.model.*;
import cn.edu.nju.tis.repository.COAInformationItemRepository;
import cn.edu.nju.tis.repository.COARepository;
import cn.edu.nju.tis.repository.InformationItemRepository;
import cn.edu.nju.tis.service.COAService;
import cn.edu.nju.tis.utils.MethodUtil;
import cn.edu.nju.tis.utils.ResultMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class COAServiceImpl implements COAService {
    @Autowired
    COARepository coaRepository;
    @Autowired
    InformationItemRepository informationItemRepository;
    @Autowired
    COAInformationItemRepository coaInformationItemRepository;
    //进行两个操作：1、新建案由记录和新增信息项记录（记录状态默认是未审核）
    //2、然后将已有的信息项和新建的案由连接起来
    @Override
    public ResultMessageBean<Object> addCOA(String type, String coaName, String userAccount, ConcurrentHashMap<String, String> itemAndCode, List<String> existedItem) throws Exception {
        //如果案由存在直接返回失败
        if(coaRepository.findCauseOfActionByName(coaName)!=null){
            return ResultMessageUtil.error(-1,"同名案由已存在");
        }
        //如果信息项存在同名，也直接返回失败；信息项列表本身不能有重名
        Set<String> set = new HashSet<>();
        for(Map.Entry<String, String> entry: itemAndCode.entrySet() ){
            if(!set.add(entry.getKey())){
                return ResultMessageUtil.error(-1, "同名信息项已存在");
            }
            if(informationItemRepository.findByName(entry.getKey())!=null){
                return ResultMessageUtil.error(-1,"\""+entry.getKey()+"\""+"已存在");
            }
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
        //接下来存储item，同时存储item-coaId数据库
        for(Map.Entry<String, String> entry: itemAndCode.entrySet() ){
            informationItemRepository.save(new InformationItem(entry.getKey(),entry.getValue(),userAccount));
            coaInformationItemRepository.save(new COAInformationItem(informationItemRepository.findByName(entry.getKey()).getId(),coaId));
        }
        //最后将已有的item和coa联系起来，加入数据库
        for(String item:existedItem){
            Integer infoId = informationItemRepository.findByName(item).getId();
            coaInformationItemRepository.save(new COAInformationItem(infoId, coaId));
        }
        return ResultMessageUtil.success();
    }

    @Override
    public ResultMessageBean<Object> modifyCOA(String userAccount,Integer coaId, String type, String coaName, List<InformationItem> items, List<InformationItem> existedItems) {
        CauseOfAction coa = coaRepository.findCauseOfActionById(coaId);
        if(coaRepository.findCauseOfActionByName(coaName)!=null){
            return ResultMessageUtil.error(-1,"同名案由已存在");
        }
        if (coa.getState().equals(StateType.REGISTERED.value)) {
            return ResultMessageUtil.error(-1, "案由已注册，无法修改");
        }

        //再测试一下信息项是否已经已经存在，如果已经存在直接返回错误
        //同时测试一下新的信息项有没有重复的
        Set<String> set = new HashSet<>();
        for(InformationItem informationItem: items){
            if(!set.add(informationItem.getName())){
            return ResultMessageUtil.error(-1,"请不要添加同名信息项");
            }
            if(informationItemRepository.findByName(informationItem.getName())!=null){
                return ResultMessageUtil.error(-1,"\""+informationItem.getName()+"\""+"已存在");
            }
        }

        //如果是待审批或者审批不通过的可以先删除后写入
        //首先找到案由自己单独的item,并且删掉信息对应信息项记录
        List<COAInformationItem> coaInformationItems = coaInformationItemRepository.findOwnByCoaId(coa.getId());
        for (COAInformationItem coaInformationItem : coaInformationItems) {
            informationItemRepository.deleteById(coaInformationItem.getInfoId());
        }

        //之后再删掉对应的案由记录，因为用了强制外键，信息项-案由连接表对应记录也一并删除
        coaRepository.deleteById(coa.getId());
        //然后把修改好的依次再插进去
        switch (type) {
            case "CIVIL":
                coaRepository.save(new CauseOfAction(coaId, COAType.CIVIL, coaName, userAccount));
                break;
            case "ADMINISTRATIVE":
                coaRepository.save(new CauseOfAction(coaId, COAType.ADMINISTRATIVE, coaName, userAccount));
                break;
            case "CRIMINAL":
                coaRepository.save(new CauseOfAction(coaId, COAType.CRIMINAL, coaName, userAccount));
                break;
            default:
                return ResultMessageUtil.error(-1, "案由类别错误");

        }
        Integer coa_id = coaRepository.findCauseOfActionByName(coaName).getId();
        //然后插入新建的信息项，同时创建案由信息项连接表
        for (InformationItem item : items) {
            informationItemRepository.save(item);
            coaInformationItemRepository.save(new COAInformationItem(informationItemRepository.findByName(item.getName()).getId(), coa_id));
        }

        //最后再次建立已有信息项和案由的连接表
        for (InformationItem item : existedItems) {
            coaInformationItemRepository.save(new COAInformationItem(item.getId(), coa_id));
        }

        return ResultMessageUtil.success();
    }

    //插入代码
    @Override
    public ResultMessageBean<Object> passCOA(Integer coaId) throws Exception {
        //如果案由不存在直接返回失败
        if(coaRepository.findCauseOfActionById(coaId)==null){
            return ResultMessageUtil.error(-1,"案由不存在");
        }
        CauseOfAction coa = coaRepository.findCauseOfActionById(coaId);
        if(coa.getState().equals("REGISTERED")){
            return ResultMessageUtil.error(-1, "案由已注册");
        }
        coaRepository.modifyCauseOfActionStateById("REGISTERED", coaId);
        List<InformationItem> informationItemList = informationItemRepository.findInformationItemsByCOAId(coaId);
        for(InformationItem ii:informationItemList){
            informationItemRepository.modifyInformationItemStateById("REGISTERED", ii.getId());
        }

        String type = coa.getType();
        //根据案由查找信息项，并且将代码插到项目里
        List<COAInformationItem> coaInformationItems = coaInformationItemRepository.findByCoaId(coaId);
        for(COAInformationItem coaInformationItem: coaInformationItems){
            InformationItem informationItem = informationItemRepository.findInformationItemById(coaInformationItem.getInfoId());
            MethodUtil.addMethod(type,informationItem.getCode());
        }

        return ResultMessageUtil.success();
    }

    @Override
    public ResultMessageBean<Object> notPassCOA(Integer coaId) {
        //如果案由不存在直接返回失败
        if(coaRepository.findCauseOfActionById(coaId)==null){
            return ResultMessageUtil.error(-1,"案由不存在");
        }else{
            coaRepository.modifyCauseOfActionStateById("NOT_PASSED", coaId);
            List<InformationItem> informationItemList = informationItemRepository.findInformationItemsByCOAId(coaId);
            for(InformationItem ii:informationItemList){
                informationItemRepository.modifyInformationItemStateById("NOT_PASSED", ii.getId());
            }
            return ResultMessageUtil.success();
        }

    }
}
