package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.service.CivilExtractionService;
import cn.edu.nju.tis.utils.TimeUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CivilExtractionServiceImpl implements CivilExtractionService {
    public static String datePattern = "(\\d{4}年(\\d{1,2}月)?(\\d{1,2}日)?(\\d{1,2}时)?(\\d{1,2}分)?)";

    @Override
    public String XSSJ(Document document) {
        Pattern pattern = Pattern.compile(datePattern);
        Element root = document.getRootElement();
        String acquaintance_time = "未提及";
        List<String> acquaintance_timeList = new ArrayList<>();
        if (root.attribute("value") != null) {
            String qwStr = root.attributeValue("value");
            String[] qwArray = qwStr.split("[。，；]");
            //相识时间
            for (String qw : qwArray) {
                Matcher matcher = pattern.matcher(qw);
                if (matcher.find()) {
                    if (qw.contains("相识")) {
                        TimeUtil.getDate(qw, acquaintance_timeList, datePattern);
                        break;
                    }
                }
            }
        }
        if (acquaintance_timeList.size() > 0) {
            return acquaintance_timeList.get(0);
        }
        return acquaintance_time;
    }

    @Override
    public String RHXS(Document document) {
        Element root = document.getRootElement();
        String result = "未提及";
        if (root.attribute("value") != null) {
            String qwStr = root.attributeValue("value");
            String[] qwArray = qwStr.split("[。，；]]");
            //如何相识
            for (String qw : qwArray) {
                if (qw.contains("相识")) {
                    result = qw;
                    break;
                }
            }

        }
        return result;
    }

    @Override
    public String QLLASJ(Document document) {
        Element root = document.getRootElement();
        Pattern pattern = Pattern.compile(datePattern);
        String result = "未提及";
        List<String> list = new ArrayList<>();
        if (root.attribute("value") != null) {
            String qwStr = root.attributeValue("value");
            String[] qwArray = qwStr.split("[。，；]");
            //建立恋爱时间
            for (String qw : qwArray) {
                Matcher matcher = pattern.matcher(qw);
                if (matcher.find()) {
                    if (qw.contains("恋爱")) {
                        TimeUtil.getDate(qw, list, datePattern);
                        break;
                    }

                }
            }
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        return result;
    }

    @Override
    public String JHDJSJ(Document document) {
        Element root = document.getRootElement();
        Pattern pattern = Pattern.compile(datePattern);
        String result = "未提及";
        ArrayList<String> list = new ArrayList<>();
        if (root.attribute("value") != null) {
            String qwStr = root.attributeValue("value");
            String[] qwArray = qwStr.split("[。，；]");
            //结婚登记时间
            for (String qw : qwArray) {
                Matcher matcher = pattern.matcher(qw);
                if (matcher.find()) {
                    if (qw.contains("结婚")) {
                        TimeUtil.getDate(qw, list, datePattern);
                        break;
                    }

                }
            }
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        return result;
    }

    @Override
    public String JBJHYSSJ(Document document) {
        Element root = document.getRootElement();
        Pattern pattern = Pattern.compile(datePattern);
        String result = "未提及";
        ArrayList<String> list = new ArrayList<>();
        if (root.attribute("value") != null) {
            String qwStr = root.attributeValue("value");
            String[] qwArray = qwStr.split("[。，；,]");
            //结婚登记时间
            for (String qw : qwArray) {
                Matcher matcher = pattern.matcher(qw);
                if (matcher.find()) {
                    if ((qw.contains("举行") || qw.contains("举办")) && ((qw.contains("结婚") && (qw.contains("仪式") || qw.contains("典礼"))) || qw.contains("婚礼"))) {
                        TimeUtil.getDate(qw, list, datePattern);
                        break;
                    }

                }
            }
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        return result;
    }

    @Override
    public String FQBHKSSJ(Document document) {
        Element root = document.getRootElement();
        Pattern pattern = Pattern.compile(datePattern);

        String result = "未提及";
        ArrayList<String> list = new ArrayList<>();  //夫妻不和开始时间
        if (root.attribute("value") != null) {
            String qwStr = root.attributeValue("value");
            String[] qwArray = qwStr.split("。");
            for (String qw : qwArray) {
                Matcher matcher = pattern.matcher(qw);
                if (matcher.find()) {
                    if (qw.contains("生活琐事") || qw.contains("矛盾") || qw.contains("性格不合") ||
                            qw.contains("吵架") || qw.contains("感情破裂") || qw.contains("感情已破裂") ||
                            qw.contains("不睦") || qw.contains("不和") ||
                            qw.contains("争吵") || qw.contains("殴打") || qw.contains("虐待") ||
                            qw.contains("暴力") || qw.contains("吵打") || qw.contains("打骂")) {
                        String[] keyWords = {"生活琐事", "矛盾", "性格不合", "吵架", "感情破裂", "感情已破裂", "不睦", "不和",
                                "争吵", "殴打", "虐待", "暴力", "吵打", "打骂"};
                        list.add(TimeUtil.getRightDate(qw, keyWords, 0, datePattern));
                        break;
                    }

                }
            }
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        return result;
    }

    @Override
    public String FQBHYY(Document document) {
        Element root = document.getRootElement();
        Pattern pattern = Pattern.compile(datePattern);
        String result = "未提及";
        ArrayList<String> list = new ArrayList<>(); //夫妻不和原因
        if (root.attribute("value") != null) {
            String qwStr = root.attributeValue("value");
            String[] qwArray = qwStr.split("。");
            for (String qw : qwArray) {
                if (qw.contains("生活琐事") || qw.contains("矛盾") ||
                        qw.contains("吵架") || (qw.contains("感情") && qw.contains("破裂")) ||
                        qw.contains("不睦") || qw.contains("不和") ||
                        qw.contains("争吵") || qw.contains("殴打") || qw.contains("虐待") ||
                        qw.contains("暴力") || qw.contains("吵打") || qw.contains("打骂") ||
                        qw.contains("口角") || qw.contains("争执")) {
                    list.add(qw);
                    break;

                }
            }


        }

        if (list.size() > 0) {
            return list.toString();
        }
        return result;
    }

    @Override
    public String SFFJ(Document document) {
        Element root = document.getRootElement();
        String result = "未提及";   //是否分居
        String qwStr = root.attributeValue("value");
        //是否分居，分局原因，是否因为感情不和分居，原告、被告现居住地
        String[] qwList = qwStr.split("。");
        for (String qw : qwList) {
            if (qw.contains("分居") && (!qw.contains("从未分居") || !qw.contains("没有分居"))) {
                return "是";
            }
        }
        return result;
    }

    @Override
    public String KSFJSJ(Document document) {
        Element root = document.getRootElement();
        String result = "未提及";
        ArrayList<String> list = new ArrayList<>(); //开始分居时间
        String qwStr = root.attributeValue("value");
        //开始分居时间提取
        String[] qwList = qwStr.split("。");
        Pattern r = Pattern.compile(datePattern);
        for (String qw : qwList) {
            if (qw.contains("分居") && (!qw.contains("从未分居") || !qw.contains("没有分居"))) {
                Matcher matcher = r.matcher(qw);
                if (matcher.find()) {
                    String[] keyWords = {"分居"};
                    list.add(TimeUtil.getRightDate(qw, keyWords,1, datePattern));
                    break;
                }
            }
        }
        if(list.size()>0){
            return list.get(0);
        }
        return result;
    }

    @Override
    public String KSFJYY(Document document) {
        Element root = document.getRootElement();
        String result = "未提及";
        String qwStr = root.attributeValue("value");
        //分居原因
        String[] qwList = qwStr.split("。");
        for (String qw : qwList) {
            if (qw.contains("分居") && (!qw.contains("从未分居") || !qw.contains("没有分居"))) {
                return qw;
            }
        }
        return result;
    }

    @Override
    public String SFYWGQBHFJ(Document document) {
        Element root = document.getRootElement();
        String result = "未提及";
        String qwStr = root.attributeValue("value");
        //
        String[] qwList = qwStr.split("。");
        for (String qw : qwList) {
        if ((!(qw.contains("并非") && qw.contains("感情不和"))) &&
                (qw.contains("不睦") || qw.contains("不和") ||
                        qw.contains("矛盾") || qw.contains("虐待") ||
                        qw.contains("吵架") || qw.contains("打骂") ||
                        qw.contains("争吵") || qw.contains("殴打") ||
                        qw.contains("暴力") || qw.contains("吵打") ||
                        qw.contains("口角") || qw.contains("争执"))) {
            return "是";
        }
        }
        return result;
    }

    @Override
    public String YGXJZD(Document document) {
        Element root = document.getRootElement();
        String result = "未提及";
        String qwStr = root.attributeValue("value");
        //提取原告现居住地
        //在诉讼参与人提取
        if (root.element("SSCYRQJ") != null) {
            Element SSCYRQJNode = root.element("SSCYRQJ");
            if (SSCYRQJNode.element("SSCYR") != null) {
                ArrayList<Element> SSCYRNode = (ArrayList) SSCYRQJNode.elements("SSCYR");
                for (Element e : SSCYRNode) {
                    if (e.element("DSRDZ") != null) {
                        Element DSRDZNode = e.element("DSRDZ");
                        if (DSRDZNode.attribute("value") != null) {
                            String dsrdz = DSRDZNode.attributeValue("value");
                            if (e.attribute("value") != null && e.attributeValue("value").contains("原告")) {
                                return dsrdz;
                            }
                        }
                    }
                }
            }
        }
        //若文首枚提取到
        String[] qwList = qwStr.split("[。，；]");
        for (String qw : qwList) {
            if (qw.contains("居住") || qw.contains("住在") || qw.contains("住")) {
                    if (qw.contains("原告")) {
                        return qw;
                    }
                }
            }
            return result;
        }

    @Override
    public String BGXJZD(Document document) {
        Element root = document.getRootElement();
        String result = "未提及";
        String qwStr = root.attributeValue("value");
        //提取被告现居住地
        //在诉讼参与人提取
        if (root.element("SSCYRQJ") != null) {
            Element SSCYRQJNode = root.element("SSCYRQJ");
            if (SSCYRQJNode.element("SSCYR") != null) {
                ArrayList<Element> SSCYRNode = (ArrayList) SSCYRQJNode.elements("SSCYR");
                for (Element e : SSCYRNode) {
                    if (e.element("DSRDZ") != null) {
                        Element DSRDZNode = e.element("DSRDZ");
                        if (DSRDZNode.attribute("value") != null) {
                            String dsrdz = DSRDZNode.attributeValue("value");
                            if (e.attribute("value") != null && e.attributeValue("value").contains("被告")) {
                                if (dsrdz.contains("同原告") || dsrdz.contains("同上")) {
                                    return YGXJZD(document);
                                }else{
                                    return dsrdz;
                                }
                            }
                        }
                    }
                }
            }
        }
        //若文首枚提取到
        String[] qwList = qwStr.split("[。，；]");
        for (String qw : qwList) {
            if (qw.contains("居住") || qw.contains("住在") || qw.contains("住")) {
                    if (qw.contains("被告")) {
                        return qw;
                    }
            }
        }
        return result;
    }

    @Override
    public String JHQKJFYBD(Document document) {
        Element root = document.getRootElement();
        String result = "否";
        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");
            if((qwStr.contains("军婚")||qwStr.contains("服役")||qwStr.contains("入伍")||
                    qwStr.contains("部队")||qwStr.contains("军队")||qwStr.contains("军官")||
                    qwStr.contains("中国人民解放军")||qwStr.contains("人民武装警察部队")||qwStr.contains("警察"))
                    &&(!qwStr.contains("退伍")&&!qwStr.contains("转业")&&!qwStr.contains("复员"))){
                return "是";
            }
        }
        return result;
    }

    @Override
    public String CZKZYHBZYJCHYQK(Document document) {
        Element root = document.getRootElement();
        String result ="否";
        int flagq = 0;
        int flagh = 0;
        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");
            String[] qwList = qwStr.split("[，。]");
            for(String qw:qwList){
                if((qw.contains("婚前")&&(qw.contains("缺乏")||qw.contains("缺少"))&&qw.contains("了解"))||qw.contains("草率结婚")){
                    flagq =1;
                }
                if(((qw.contains("未")||qw.contains("不能")||qw.contains("没有"))&&qw.contains("建立")&&qw.contains("夫妻感情"))||
                        ((qw.contains("难以")||qw.contains("无法")||qw.contains("不能"))&&(qw.contains("共同生活")||qw.contains("与被告生活")||qw.contains("与原告生活")))){
                    flagh =1;
                }
            }
            if(flagq*flagh==1){
                result = "是";
            }
        }
        if(flagq+flagh == 0){
            return "未提及";
        }
        return result;
    }

    @Override
    public String XGBH(Document document) {
        Element root = document.getRootElement();
        String result ="否";
        int flagq = 0;
        int flagh = 0;
        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");
            String[] qwStrarray = qwStr.split("[，。]");
            for(String qw:qwStrarray){
                if((qw.contains("性格")&&(qw.contains("不和")||qw.contains("不合")||
                        qw.contains("差异")||qw.contains("不同")||qw.contains("不投")))&&
                        (qw.contains("矛盾")||qw.contains("争执")||
                                qw.contains("不睦")||qw.contains("不和")||
                                qw.contains("争吵")||qw.contains("殴打")||qw.contains("虐待")||
                                qw.contains("暴力")||qw.contains("吵打")||qw.contains("打骂") )){
                    flagq =1;
                }
                if(((qw.contains("难以")||qw.contains("无法")||qw.contains("不能"))&&(qw.contains("共同生活")||qw.contains("与被告生活")||qw.contains("与原告生活")))){
                    flagh =1;
                }
            }
            if(flagq*flagh==1){
                result = "是";
            }
        }
        if(flagq+flagh == 0){
            return "未提及";
        }
        return result;
    }

    @Override
    public String SFCZJTBLNDYQJTCY(Document document) {
        Element root = document.getRootElement();
        String result ="否";
        int fnull = 0;
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                Element cmssdNode = ajjbqkNode.element("CMSSD");
                if(cmssdNode.attribute("value")!=null){
                    String qwStr = cmssdNode.attributeValue("value");
                    String[] qwStrarray = qwStr.split("[，。]");
                    for(String qw:qwStrarray){
                        if(( qw.contains("殴打")||qw.contains("虐待")|| qw.contains("暴力")||
                                qw.contains("抛弃")||qw.contains("遗弃")||qw.contains("动手"))){
                            fnull = 1;
                            if(( qw.contains("殴打")||qw.contains("虐待")|| qw.contains("暴力")||
                                    qw.contains("抛弃")||qw.contains("遗弃")||qw.contains("动手"))&&
                                    (!qw.contains("并未")||!qw.contains("没有")||!qw.contains("从未"))){
                                result = "是";
                            }
                        }

                    }

                }
            }
        }
        if(fnull == 0){
            return "未提及";
        }
        return result;
    }

    @Override
    public String SFCZYFHSFDSRYHWQ(Document document) {
        Element root = document.getRootElement();
        String result ="否";
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                Element cmssdNode = ajjbqkNode.element("CMSSD");
                if(cmssdNode.attribute("value")!=null){
                    String qwStr = cmssdNode.attributeValue("value");
                    System.out.println(qwStr);
                    String[] qwStrarray = qwStr.split("[，。]");
                    for(String qw:qwStrarray){
                        if(qw.contains("婚外情")||qw.contains("第三者")||
                                qw.contains("出轨")||qw.contains("同居")||
                                qw.contains("暧昧")||qw.contains("不正当关系")){
                            result = "是";
                            break;
                        }
                    }

                }

            }
        }
        return result;
    }

    @Override
    public String SFCZYFHSFDSRYDBXDDEXDQX(Document document) {
        Element root = document.getRootElement();
        String result ="否";
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                Element cmssdNode = ajjbqkNode.element("CMSSD");
                if(cmssdNode.attribute("value")!=null){
                    String qwStr = cmssdNode.attributeValue("value");
                    String[] qwStrarray = qwStr.split("[，。]");
                    for(String qw:qwStrarray){
                        if(qw.contains("赌博")||qw.contains("吸毒")||qw.contains("酗酒")||qw.contains("恶习")
                        ){
                            result = "是";
                            break;
                        }
                    }

                }

            }
        }
        return result;
    }


}