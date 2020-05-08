package cn.edu.nju.tis.repository;

import cn.edu.nju.tis.model.CauseOfAction;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface COARepository extends JpaRepository<CauseOfAction, Integer> {
    CauseOfAction findCauseOfActionById(Integer id);

    List<CauseOfAction> findCauseOfActionByType(String type);

    CauseOfAction findCauseOfActionByName(String name);

    List<CauseOfAction> findCauseOfActionByAccount(String account);

    List<CauseOfAction> findCauseOfActionByState(String state);

    @Query("update CauseOfAction coa set coa = ?1 where coa.id = ?2")
    void updateCauseOfActionById(CauseOfAction cause_of_action, Integer id);

    //根据案由id更新案由状态
    @Modifying
    @Transactional(timeout = 10)
    @Query("update CauseOfAction c set c.state = ?1 where c.id = ?2")
    void modifyCauseOfActionStateById(String state, Integer id);

}