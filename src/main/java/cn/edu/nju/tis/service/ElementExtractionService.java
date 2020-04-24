package cn.edu.nju.tis.service;

import java.lang.reflect.InvocationTargetException;

public interface ElementExtractionService {
    String itemsExtraction(String coaName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException;
}
