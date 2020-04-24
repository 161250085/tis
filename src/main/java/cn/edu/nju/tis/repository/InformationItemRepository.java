package cn.edu.nju.tis.repository;

import cn.edu.nju.tis.model.InformationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InformationItemRepository extends JpaRepository<InformationItem, Integer> {
    InformationItem findInformationItemById(Integer id);

    InformationItem findByName(String name);

    List<InformationItem> findByAccount(String account);

    @Query("select items from InformationItem items where items.id in (select item.infoId from COAInformationItem item where item.coaId =?1)" )
    List<InformationItem> findInformationItemByCOAId(Integer coaId);

}
