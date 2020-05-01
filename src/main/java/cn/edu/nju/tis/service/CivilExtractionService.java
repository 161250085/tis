package cn.edu.nju.tis.service;

import org.dom4j.Document;

public interface CivilExtractionService {
    /**
     * @Author cruck
     * @Description //测试方法，抽取离婚纠纷的要素表信息项
     * @Date 16:17 2020/4/28
     * @Param
     * @return
     **/
    //1.相识时间
    String XSSJ(Document document);

    //2.如何相识
    String RHXS(Document document);

    //3.确立恋爱关系时间
    String QLLASJ(Document document);

    //4.结婚登记时间
    String JHDJSJ(Document document);

    //5.举办结婚仪式时间
    String JBJHYSSJ(Document document);

    //6.夫妻不和开始时间
    String FQBHKSSJ(Document document);

    //7.夫妻不和原因
    String FQBHYY(Document document);

    //8.是否分居
    String SFFJ(Document document);

    //9.开始分居时间
    String KSFJSJ(Document document);

    //10.开始分居原因
    String KSFJYY(Document document);

    //11.是否因为感情不和分居
    String SFYWGQBHFJ(Document document);

    //12.原告现居住地
    String YGXJZD(Document document);

    //13.被告现居住地
    String BGXJZD(Document document);

    //14.是否存在军婚情况及服役部队
    String JHQKJFYBD(Document document);

    //15.是否存在婚前缺乏了解，草率结婚，婚后未建立起夫妻感情，难以共同生活的情形
    String CZKZYHBZYJCHYQK(Document document);
}