package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.model.CauseOfAction;
import cn.edu.nju.tis.model.InformationItem;
import cn.edu.nju.tis.repository.COARepository;
import cn.edu.nju.tis.repository.InformationItemRepository;
import cn.edu.nju.tis.service.ElementExtractionService;
import cn.edu.nju.tis.utils.ChineseCharToEn;
import cn.edu.nju.tis.utils.ResultMessageUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public String itemsExtraction(String coaName,String filePath) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, DocumentException {
        CauseOfAction coa = coaRepository.findCauseOfActionByName(coaName);
        List<InformationItem> informationItems = informationItemRepository.findInformationItemsByCOAId(coa.getId());

        //先新建xml
        SAXReader reader = new SAXReader();
        File file = new File(filePath);
        Document document = reader.read(file);
        Element root = document.addElement(ChineseCharToEn.getInstance().getAllFirstLetter(coaName)+"YSTQ").addAttribute("nameCN", coaName+"要素提取");

        //要素抽取
        invokeMethod(coa, informationItems, document, root);
        return document.asXML();

    }

    @Override
    public ResultMessageBean<Object> itemsExtractionAndWrite(String coaName, String filePath, String desPath) throws DocumentException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        CauseOfAction coa = coaRepository.findCauseOfActionByName(coaName);
        List<InformationItem> informationItems = informationItemRepository.findInformationItemsByCOAId(coa.getId());

        //先新建xml
        SAXReader reader = new SAXReader();
        File file = new File(filePath);
        String fileName = file.getName();
        Document document = reader.read(file);
        Element root = DocumentHelper.createElement("write");
        Document new_document = DocumentHelper.createDocument(root);
        initXML(document,root);
        Element newRoot;
        newRoot = root.addElement(ChineseCharToEn.getInstance().getAllFirstLetter(coaName)+"YSTQ").addAttribute("nameCN", coaName+"要素提取");

        //要素抽取
        invokeMethod(coa, informationItems, document, newRoot);
        OutputFormat format = new OutputFormat("    ",true);
        format.setEncoding("UTF-8");
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(desPath+fileName+"要素提取.xml"),format);
        xmlWriter.write(new_document);
        xmlWriter.close();
        return ResultMessageUtil.success();
    }

    private void invokeMethod(CauseOfAction coa, List<InformationItem> informationItems, Document document, Element newRoot) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Element node = newRoot.addElement("ELEMENTS").addAttribute("nameCN", "信息项");
        switch (coa.getType()) {
            case "CIVIL":
                for (InformationItem item : informationItems) {
                    String methodName = ChineseCharToEn.getInstance().getAllFirstLetter(item.getName());
                    Class<?> clazz = Class.forName("cn.edu.nju.tis.serviceImpl.CivilExtractionServiceImpl");
                    Method method = clazz.getDeclaredMethod(methodName, Document.class, Element.class);
                    method.invoke(clazz.newInstance(),document, node);
                }
                break;
            case "ADMINISTRATIVE":
                for (InformationItem item : informationItems) {
                    String methodName = ChineseCharToEn.getInstance().getAllFirstLetter(item.getName());
                    Class<?> clazz = Class.forName("cn.edu.nju.tis.serviceImpl.AdministrativeExtractionServiceImpl");
                    Method method = clazz.getDeclaredMethod(methodName, Document.class, Element.class);
                    method.invoke(clazz.newInstance(),document, node);

                }
                break;
            case "CRIMINAL":
                for (InformationItem item : informationItems) {
                    String methodName = ChineseCharToEn.getInstance().getAllFirstLetter(item.getName());
                    Class<?> clazz = Class.forName("cn.edu.nju.tis.serviceImpl.CriminalExtractionServiceImpl");
                    Method method = clazz.getDeclaredMethod(methodName, Document.class, Element.class);
                    method.invoke(clazz.newInstance(),document, node);
                }
                break;
        }
    }

    public static void initXML(Document document,Element newroot){//将原文加进去
        Element root = document.getRootElement();
        newroot.add((Element)root.clone());
    }
}
