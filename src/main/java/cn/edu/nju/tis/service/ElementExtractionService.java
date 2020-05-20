package cn.edu.nju.tis.service;

import cn.edu.nju.tis.bean.ResultMessageBean;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ElementExtractionService {
    ResultMessageBean<Object> uploadXML(MultipartFile[] files) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, DocumentException, InvocationTargetException, ClassNotFoundException;

//    ResultMessageBean<Object> downloadXML(String fileName, String filePath) throws  IOException;

    ResultMessageBean<Object> itemsExtraction(String filePath) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, DocumentException, IOException;

    }
