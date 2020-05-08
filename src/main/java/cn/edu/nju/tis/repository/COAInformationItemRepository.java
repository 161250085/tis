package cn.edu.nju.tis.repository;

import cn.edu.nju.tis.model.COAInformationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface COAInformationItemRepository extends JpaRepository<COAInformationItem, Integer> {
    List<COAInformationItem> findByCoaId(Integer coaId);

    List<COAInformationItem> findByInfoId(Integer infoId);

    COAInformationItem findByInfoIdAndCoaId(Integer infoId, Integer coaId);

    //根据案由id得到案由自己单独的coa_item
    @Query("select coa_item from COAInformationItem coa_item where coa_item.infoId in (select  ci.infoId from COAInformationItem ci group by ci.infoId having count(ci) = 1 ) and coa_item.coaId =?1 ")
    List<COAInformationItem> findOwnByCoaId(Integer coaId);
}
