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

    //新的信息项得加上coa_id
    @Override
    public ResultMessageBean<Object> addCOA(String type, String coaName, String userAccount, String importPackages, List<InformationItem> items, List<String> existedItem){
        //如果案由存在直接返回失败
        if(coaRepository.findCauseOfActionByName(coaName)!=null){
            return ResultMessageUtil.error(-1,"同名案由已存在");
        }

        //测试一下新的信息项有没有重复的
        Set<String> set = new HashSet<>();
        for(InformationItem informationItem: items){
            if(!set.add(informationItem.getName())){
                return ResultMessageUtil.error(-1,"请不要添加同名信息项");
            }
        }

        //测一下新的信息项有没有已经存在的
        for(InformationItem informationItem:items){
            if(informationItemRepository.findByName(informationItem.getName())!=null) {
                return ResultMessageUtil.error(-1, "\"" + informationItem.getName() + "\"已存在");
            }
        }

        //否则先进行数据库操作
        switch (type) {
            case "CIVIL":
                coaRepository.save(new CauseOfAction(COAType.CIVIL, coaName, userAccount, importPackages));
                break;
            case "ADMINISTRATIVE":
                coaRepository.save(new CauseOfAction(COAType.ADMINISTRATIVE, coaName, userAccount, importPackages));
                break;
            case "CRIMINAL":
                coaRepository.save(new CauseOfAction(COAType.CRIMINAL, coaName, userAccount, importPackages));
                break;
            default:
                return ResultMessageUtil.error(-1,"案由类别错误");
        }
        CauseOfAction causeOfAction = coaRepository.findCauseOfActionByName(coaName);
        Integer coaId = causeOfAction.getId();
        //然后插入新建的信息项，同时创建案由信息项连接表
        for (InformationItem item : items) {
            //新建的信息项要有coa_id
            item.setCoaId(coaId);
            item.setAccount(userAccount);
            InformationItem in=informationItemRepository.save(item);
            coaInformationItemRepository.save(new COAInformationItem(in.getId(),coaId));
        }
        //最后将已有的item和coa联系起来，加入数据库
        for(String item:existedItem){
            coaInformationItemRepository.save(new COAInformationItem(Integer.parseInt(item), coaId));
        }
        return ResultMessageUtil.success();
    }

    @Override
    public ResultMessageBean<Object> modifyCOA(String userAccount,Integer coaId, String type, String coaName, List<InformationItem> items, List<InformationItem> existedItems, String importPackages){
        CauseOfAction coa = coaRepository.findCauseOfActionById(coaId);
        if(coaRepository.findCauseOfActionByName(coaName)!=null&&!coaRepository.findCauseOfActionByName(coaName).getId().equals(coaId)){
            return ResultMessageUtil.error(-1,"同名案由已存在");
        }
        if (coa.getState().equals(StateType.REGISTERED.value)) {
            return ResultMessageUtil.error(-1, "案由已注册，无法修改");
        }

        //测试一下新的信息项有没有重复的
        Set<String> set = new HashSet<>();
        for(InformationItem informationItem: items){
            if(!set.add(informationItem.getName())){
            return ResultMessageUtil.error(-1,"请不要添加同名信息项");
            }
        }

        //得到除了自己独有信息项以外的信息项
        List<InformationItem> informationItems = informationItemRepository.findAll();
        List<COAInformationItem> coaInformationItems = coaInformationItemRepository.findOwnByCoaId(coa.getId());
        for(COAInformationItem coaInformationItem:coaInformationItems){
            informationItems.removeIf(informationItem -> informationItem.getId().equals(coaInformationItem.getInfoId()));
        }

        //测一下新的信息项有没有已经存在的
        for(InformationItem informationItem:items){
            for(InformationItem informationItem1:informationItems){
                if(informationItem1.getName().equals(informationItem.getName())){
                    return ResultMessageUtil.error(-1,"\""+informationItem.getName()+"\"已存在");
                }
            }
        }

        //如果是待审批或者审批不通过的可以先删除后写入
        //首先找到案由自己单独的item,并且删掉信息对应信息项记录
        for (COAInformationItem coaInformationItem : coaInformationItems) {
            informationItemRepository.deleteById(coaInformationItem.getInfoId());
        }

        //之后再删掉对应的案由记录，因为用了强制外键，信息项-案由连接表对应记录也一并删除
        coaRepository.deleteById(coa.getId());
        //然后把修改好的依次再插进去
        switch (type) {
            case "CIVIL":
                coaRepository.save(new CauseOfAction(coaId, COAType.CIVIL, coaName, userAccount ,importPackages));
                break;
            case "ADMINISTRATIVE":
                coaRepository.save(new CauseOfAction(coaId, COAType.ADMINISTRATIVE, coaName, userAccount, importPackages));
                break;
            case "CRIMINAL":
                coaRepository.save(new CauseOfAction(coaId, COAType.CRIMINAL, coaName, userAccount, importPackages));
                break;
            default:
                return ResultMessageUtil.error(-1, "案由类别错误");

        }

        //最后再次建立已有信息项和案由的连接表
        for (InformationItem item : existedItems) {
            coaInformationItemRepository.save(new COAInformationItem(item.getId(), coaId));
        }


        //然后插入新建的信息项，同时创建案由信息项连接表
        for (InformationItem item : items) {
            //新建的信息项要有coa_id
            item.setCoaId(coaId);
            InformationItem in=informationItemRepository.save(item);
            coaInformationItemRepository.save(new COAInformationItem(in.getId(),coaId));
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
        //先将imports项插进去
        MethodUtil.addImports(type,coa.getImportPackages());
        //根据案由查找信息项，并且将代码插到项目里
        for(InformationItem informationItem: informationItemList){
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
