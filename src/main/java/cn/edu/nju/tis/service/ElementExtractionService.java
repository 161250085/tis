package cn.edu.nju.tis.service;

import cn.edu.nju.tis.bean.ResultMessageBean;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ElementExtractionService {
    ResultMessageBean<Object> uploadXML(String filePath) throws IOException;

    ResultMessageBean<Object> downloadXML(String fileName, String filePath) throws  IOException;
    String itemsExtraction(String filePath) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, DocumentException;

    ResultMessageBean<Object> itemsExtractionAndWrite(String coaName, String filePath, String desPath) throws DocumentException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, IOException;
}
