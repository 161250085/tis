package cn.edu.nju.tis.repository;

import cn.edu.nju.tis.model.COAInformationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface COAInformationItemRepository extends JpaRepository<COAInformationItem, Integer> {
    List<COAInformationItem> findByCoaId(Integer coaId);

    List<COAInformationItem> findByInfoId(Integer infoId);

    COAInformationItem findByInfoIdAndCoaId(Integer infoId, Integer coaId);

}
