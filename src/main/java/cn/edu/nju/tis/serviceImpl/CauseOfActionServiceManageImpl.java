package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.model.CauseOfAction;
import cn.edu.nju.tis.model.InformationItem;
import cn.edu.nju.tis.model.StateType;
import cn.edu.nju.tis.repository.COARepository;
import cn.edu.nju.tis.repository.InformationItemRepository;
import cn.edu.nju.tis.service.CauseOfActionManageService;
import cn.edu.nju.tis.vo.COAandInfoItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CauseOfActionServiceImpl
 * @Description TODO
 * @Author liuxueying
 * @Date 2020/4/21 3:26 PM
 * @Version 1.0
 **/
@Service
@Transactional
public class CauseOfActionServiceManageImpl implements CauseOfActionManageService {
    @Autowired
    private COARepository coaRepository;

    @Autowired
    private InformationItemRepository informationItemRepository;

    @Override
    public List<COAandInfoItemVO> findCOAByUserAccount(String userAccount) {
        List<CauseOfAction> coaList = coaRepository.findCauseOfActionByAccount(userAccount);
        List<COAandInfoItemVO> coaandInfoItemVOList = new ArrayList<>();
        for(CauseOfAction coa:coaList){
            List<InformationItem> informationItemList = informationItemRepository.findInformationItemsByCOAId(coa.getId());
            COAandInfoItemVO coaandInfoItemVO = new COAandInfoItemVO(coa.getId(), coa.getType(), coa.getName(), coa.getState(), coa.getAccount(), informationItemList);
            coaandInfoItemVOList.add(coaandInfoItemVO);
        }
        return coaandInfoItemVOList;
    }

    @Override
    public List<COAandInfoItemVO> findURCauseOfActionList() {
        List<CauseOfAction> coaList = coaRepository.findCauseOfActionByState(StateType.UNDER_REVIEWED.value);
        List<COAandInfoItemVO> coaandInfoItemVOList = new ArrayList<>();
        for(CauseOfAction coa:coaList){
            List<InformationItem> informationItemList = informationItemRepository.findInformationItemsByCOAId(coa.getId());
            COAandInfoItemVO coaandInfoItemVO = new COAandInfoItemVO(coa.getId(), coa.getType(), coa.getName(), coa.getState(), coa.getAccount(), informationItemList);
            coaandInfoItemVOList.add(coaandInfoItemVO);
        }
        return coaandInfoItemVOList;
    }

    @Override
    public InformationItem findInfoItemByInfoItemId(Integer informationItemId) {
        return informationItemRepository.findInformationItemById(informationItemId);
    }

    @Override
    public List<InformationItem> findInfoItemsByCOAIdAndAccount(Integer coaId, String userAccount) {
        return informationItemRepository.findInformationItemsWithCodeByCOAIdAndAccount(coaId, userAccount);
    }
}
