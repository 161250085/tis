package cn.edu.nju.tis.repository;

import cn.edu.nju.tis.model.InformationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InformationItemRepository extends JpaRepository<InformationItem, Integer> {
    InformationItem findInformationItemById(Integer id);

    InformationItem findByName(String name);

    List<InformationItem> findByAccount(String account);

    @Query("select new InformationItem(items.id, items.name) from InformationItem items")
    List<InformationItem> findAllInformationItems();

    @Query("select new InformationItem(items.id, items.name, items.account) from InformationItem items where items.id in (select item.infoId from COAInformationItem item where item.coaId =?1)" )
    List<InformationItem> findInformationItemsByCOAId(Integer coaId);

    @Query("select items from InformationItem items where items.id in (select item.infoId from COAInformationItem item where item.coaId =?1) and items.account = ?2" )
    List<InformationItem> findInformationItemsWithCodeByCOAIdAndAccount(Integer coaId, String userAccount);
}
