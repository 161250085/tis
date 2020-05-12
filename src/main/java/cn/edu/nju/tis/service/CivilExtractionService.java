package cn.edu.nju.tis.service;

import org.dom4j.Document;
import org.dom4j.Element;

public interface CivilExtractionService {
    /**
     * @return
     * @Author cruck
     * @Description //测试方法，抽取离婚纠纷的要素表信息项
     * @Date 16:17 2020/4/28
     * @Param
     **/
    void XSSJHRHXS(Document document, Element newroot);

    void QLLASJ(Document document,Element newroot);

    void JHDJSJ(Document document,Element newroot);

    void JBJHYSSJ(Document document,Element newroot);

    void FQBH(Document document,Element newroot);

    void FJ(Document document,Element newroot);

    void JHQKJFYBD(Document document,Element newroot);


}