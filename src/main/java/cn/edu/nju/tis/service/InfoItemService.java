package cn.edu.nju.tis.service;

import cn.edu.nju.tis.model.InformationItem;

import java.util.List;

public interface InfoItemService {
    List<InformationItem> findRegisteredInformationItems();
}
