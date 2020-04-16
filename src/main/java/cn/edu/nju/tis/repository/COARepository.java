package cn.edu.nju.tis.repository;

import cn.edu.nju.tis.model.CauseOfAction;
import cn.edu.nju.tis.model.COAType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface COARepository extends JpaRepository<CauseOfAction, Integer> {
    CauseOfAction findCauseOfActionById(Integer id);

    List<CauseOfAction> findCauseOfActionByType(COAType type);

    List<CauseOfAction> findCauseOfActionByName(String name);

    List<CauseOfAction> findCauseOfActionByAccount(String account);


}
