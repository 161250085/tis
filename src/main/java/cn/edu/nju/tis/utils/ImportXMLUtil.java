package cn.edu.nju.tis.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class ImportXMLUtil {
    //验证文件后缀是不是正确
    public static boolean is_xml(String filePath){
        return filePath.matches("^.+\\.(?i)(xml)$");
    }

    public static boolean isValidXML(String filePath){
        return filePath != null && is_xml(filePath);
    }

    //将xml文件读取成document
    public static Document load(String filename) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(new File(filename)); // 读取XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }

    //得到Document的根节点
    public static Element getRoot(Document dom){
        return dom.getRootElement();
    }

    //
}