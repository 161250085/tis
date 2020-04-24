package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.model.CauseOfAction;
import cn.edu.nju.tis.model.InformationItem;
import cn.edu.nju.tis.model.ItemXML;
import cn.edu.nju.tis.repository.COARepository;
import cn.edu.nju.tis.repository.InformationItemRepository;
import cn.edu.nju.tis.service.ElementExtractionService;
import cn.edu.nju.tis.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElementExtractionServiceImpl implements ElementExtractionService {
    @Autowired
    InformationItemRepository informationItemRepository;
    @Autowired
    COARepository coaRepository;

    /**
     * @Author cruck
     * @Description //根据案由名称，通过反射调用信息项抽取方法，并且返回String类型的xml格式的数据
     * @Date 20:53 2020/4/24
     * @Param [coaName]
     * @return java.lang.String
     **/
    @Override
    public String itemsExtraction(String coaName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        CauseOfAction coa = coaRepository.findCauseOfActionByName(coaName);
        List<InformationItem> informationItems = informationItemRepository.findInformationItemByCOAId(coa.getId());
        List<ItemXML> itemXMLS = new ArrayList<>();
        switch (coa.getType()) {
            case "CIVIL":
                for (InformationItem item : informationItems) {
                    String methodName = ChineseCharToEn.getInstance().getAllFirstLetter(item.getName());
                    Class<?> clazz = Class.forName("cn.edu.nju.tis.serviceImpl.CivilExtractionServiceImpl");
                    Method method = clazz.getMethod(methodName, String.class);
                    String itemValue = (String) method.invoke(clazz.newInstance(), item.getName());
                    itemXMLS.add(new ItemXML(item.getId(), item.getName(), itemValue));
                }
                break;
            case "ADMINISTRATIVE":
                for (InformationItem item : informationItems) {
                    String methodName = ChineseCharToEn.getInstance().getAllFirstLetter(item.getName());
                    Class<?> clazz = Class.forName("cn.edu.nju.tis.serviceImpl.AdministrativeExtractionServiceImpl");
                    Method method = clazz.getMethod(methodName, String.class);
                    String itemValue = (String) method.invoke(clazz.newInstance(), item.getName());
                    itemXMLS.add(new ItemXML(item.getId(), item.getName(), itemValue));
                }
                break;
            case "CRIMINAL":
                for (InformationItem item : informationItems) {
                    String methodName = ChineseCharToEn.getInstance().getAllFirstLetter(item.getName());
                    Class<?> clazz = Class.forName("cn.edu.nju.tis.serviceImpl.CriminalExtractionServiceImpl");
                    Method method = clazz.getMethod(methodName, String.class);
                    String itemValue = (String) method.invoke(clazz.newInstance(), item.getName());
                    itemXMLS.add(new ItemXML(item.getId(), item.getName(), itemValue));
                }
                break;
        }
        return xmlUtil.createItemXml(itemXMLS);
    }
}
