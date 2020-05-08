package cn.edu.nju.tis.repository;

import cn.edu.nju.tis.model.CauseOfAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface COARepository extends JpaRepository<CauseOfAction, Integer> {
    CauseOfAction findCauseOfActionById(Integer id);

    List<CauseOfAction> findCauseOfActionByType(String type);

   CauseOfAction findCauseOfActionByName(String name);

    List<CauseOfAction> findCauseOfActionByAccount(String account);

    @Query("update CauseOfAction coa set coa = ?1 where coa.id = ?2")
    void updateCauseOfActionById(CauseOfAction cause_of_action, Integer id);


}