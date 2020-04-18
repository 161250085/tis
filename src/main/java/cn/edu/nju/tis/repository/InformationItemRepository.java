package cn.edu.nju.tis.repository;

import cn.edu.nju.tis.model.InformationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformationItemRepository extends JpaRepository<InformationItem, Integer> {
    InformationItem findInformationItemById(Integer id);

    List<InformationItem> findByName(String name);

    List<InformationItem> findByAccount(String account);

}
