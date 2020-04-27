package cn.edu.nju.tis.service;

import cn.edu.nju.tis.model.InformationItem;
import cn.edu.nju.tis.vo.COAandInfoItemVO;

import java.util.List;

public interface CauseOfActionManageService {
    List<COAandInfoItemVO> findCOAByUserAccount(String userAccount);
    InformationItem findInfoItemByInfoItemId(Integer informationItemId);
    List<InformationItem> findInfoItemsByCOAIdAndAccount(Integer coaId, String userAccount);
}
