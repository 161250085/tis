package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.service.CivilExtractionService;
import cn.edu.nju.tis.utils.TimeUtil;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CivilExtractionServiceImpl implements CivilExtractionService {
    public static String datePattern =  "(\\d{4}年(\\d{1,2}月)?(\\d{1,2}日)?(\\d{1,2}时)?(\\d{1,2}分)?)";
    static boolean is29 = true;
    /*
     *1 相识时间以及如何相识
     *  此方法把相识字符串里的所有时间都提取出来了，目前没有两个日期

     */
    public  void XSSJHRHXS(Document document,Element newroot){
        Element root = document.getRootElement();

        Element XSGCNode = newroot.addElement("XSSJYJRHXS").addAttribute("nameCN", "相识时间以及如何相识");
        Element XSSJNode = XSGCNode.addElement("XSSJ").addAttribute("nameCN", "相识时间");
        Element RHXSNode = XSGCNode.addElement("RHXS").addAttribute("nameCN", "如何相识");

        Pattern pattern = Pattern.compile(CivilExtractionServiceImpl.datePattern);
        String xsdate ="未提及";//相识时间
        String rhxs = "未提及";
        ArrayList<String> xsdateList=new ArrayList<>();
        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");
            String[] qwStrarray = qwStr.split("[。，；]");
            //相识时间
            for(String qw:qwStrarray){
                Matcher matcher = pattern.matcher(qw);
                boolean rs = matcher.find();
                if(rs){
                    if(qw.contains("相识")){
                        TimeUtil.getDate(qw, xsdateList, CivilExtractionServiceImpl.datePattern);
                        break;
                    }
                }
            }
            //如何相识
            for(String qw:qwStrarray){
                if(qw.contains("相识")){
                    rhxs = qw;
                    break;
                }
            }

        }
//        System.out.println(xsdateList);
//        System.out.println(rhxs);
        if(xsdateList.size()>0){
            xsdate = xsdateList.get(0);
        }
        XSSJNode.addAttribute("value",xsdate);
        RHXSNode.addAttribute("value",rhxs);

    }
    /*
     *2 确立恋爱关系时间
     * 此方法把相识字符串里的所有时间都提取出来了，目前没有两个日期
     */
    public  void QLLASJ(Document document,Element newroot){
        Element root = document.getRootElement();
        Element QDLAGXSJNode = newroot.addElement("QDLAGXSJ").addAttribute("nameCN", "确定恋爱关系时间");

        Pattern pattern = Pattern.compile(CivilExtractionServiceImpl.datePattern);
        String qdlagxsj = "未提及";
        ArrayList<String> qllagxsj = new ArrayList<>();
        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");
            String[] qwStrarray = qwStr.split("。|，|；");
            //建立恋爱时间
            for(String qw:qwStrarray){
                Matcher matcher = pattern.matcher(qw);
                boolean rs = matcher.find();
                if(rs){
                    if(qw.contains("恋爱")){
                        System.out.println(qw);
                        TimeUtil.getDate(qw, qllagxsj, CivilExtractionServiceImpl.datePattern);
                        break;
                    }

                }
            }
        }
//        System.out.println(qllagxsj);
        if(qllagxsj.size()>0){
            qdlagxsj = qllagxsj.get(0);
        }
        QDLAGXSJNode.addAttribute("value",qdlagxsj);
    }
    /*
     * 3结婚登记时间
     * 此方法把相识字符串里的所有时间都提取出来了，目前没有两个日期
     */
    public void JHDJSJ(Document document,Element newroot){
        Element root = document.getRootElement();
        Element JHDJSJNode = newroot.addElement("JHDJSJ").addAttribute("nameCN", "结婚登记时间");

        Pattern pattern = Pattern.compile(CivilExtractionServiceImpl.datePattern);
        String jhdjsj = "未提及";
        ArrayList<String> jhdjsjList = new ArrayList<>();
        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");
            String[] qwStrarray = qwStr.split("。|，|；");
            //结婚登记时间
            for(String qw:qwStrarray){
                Matcher matcher = pattern.matcher(qw);
                boolean rs = matcher.find();
                if(rs){
                    if(qw.contains("结婚")){
                        System.out.println(qw);
                        TimeUtil.getDate(qw, jhdjsjList, CivilExtractionServiceImpl.datePattern);
                        break;
                    }

                }
            }
        }
//        System.out.println(jhdjsjList);
        if(jhdjsjList.size()>0){
            jhdjsj = jhdjsjList.get(0);
        }
        JHDJSJNode.addAttribute("value",jhdjsj);
    }
    /*
     * 4举办结婚仪式时间
     */
    public void JBJHYSSJ(Document document,Element newroot){
        Element root = document.getRootElement();
        Element JBJHYSSJNode = newroot.addElement("JBJHYSSJ").addAttribute("nameCN", "举办结婚仪式时间");
        Pattern pattern = Pattern.compile(CivilExtractionServiceImpl.datePattern);
        String jbjhyssj = "未提及";
        ArrayList<String> jbjhyssjList = new ArrayList<>();
        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");
            String[] qwStrarray = qwStr.split("。|，|；");
            //结婚登记时间
            for(String qw:qwStrarray){
                Matcher matcher = pattern.matcher(qw);
                boolean rs = matcher.find();
                if(rs){
                    if((qw.contains("举行")||qw.contains("举办"))&&((qw.contains("结婚")&&(qw.contains("仪式")||qw.contains("典礼")))||qw.contains("婚礼"))){
                        System.out.println(qw);
                        TimeUtil.getDate(qw, jbjhyssjList, CivilExtractionServiceImpl.datePattern);
                        break;
                    }

                }
            }
        }
//        System.out.println(jbjhyssjList);
        if(jbjhyssjList.size()>0){
            jbjhyssj = (String) jbjhyssjList.get(0);
        }
        JBJHYSSJNode.addAttribute("value",jbjhyssj);
    }

    /*
     * 5.夫妻不和的开始时间及原因
     * 时间点很不准确，很多没有时间
     */
    public void FQBH(Document document,Element newroot){
        Element root = document.getRootElement();
        Element FQBHNode = newroot.addElement("FQBHDKSSJJYY").addAttribute("nameCN", "夫妻不和的开始时间及原因");
        Element FQBHKSSJNode = FQBHNode.addElement("FQBHKSSJ").addAttribute("nameCN", "夫妻不和开始时间");
        Element FQBHYYNode = FQBHNode.addElement("FQBHKSYY").addAttribute("nameCN", "夫妻不和原因");
        Pattern pattern = Pattern.compile(CivilExtractionServiceImpl.datePattern);

        String fqbhkssj ="未提及";
        String fqbhyyStr = "未提及";
        ArrayList<String> fqbhList = new ArrayList<>();  //夫妻不和开始时间
        ArrayList<String>  fqbhyy = new ArrayList<>(); //夫妻不和原因
        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");
            String[] qwStrarray = qwStr.split("。");
            //夫妻不和的开始原因以及时间
            for(String qw:qwStrarray){
                Matcher matcher = pattern.matcher(qw);
                boolean rs = matcher.find();
                if(rs){
                    if(qw.contains("生活琐事")||qw.contains("矛盾")||qw.contains("性格不合")||
                            qw.contains("吵架")||qw.contains("感情破裂")||qw.contains("感情已破裂")||
                            qw.contains("不睦")||qw.contains("不和")||
                            qw.contains("争吵")||qw.contains("殴打")||qw.contains("虐待")||
                            qw.contains("暴力")||qw.contains("吵打")||qw.contains("打骂")){
                        System.out.println("date:"+qw);
                        String[] keyWords = {"生活琐事","矛盾","性格不合","吵架","感情破裂","感情已破裂","不睦","不和",
                                "争吵","殴打","虐待","暴力","吵打","打骂"};
                        fqbhList.add(TimeUtil.getRightDate(qw, keyWords, 0, CivilExtractionServiceImpl.datePattern));
                        break;
                    }

                }
            }
            //
            for(String qw:qwStrarray){
                if(qw.contains("生活琐事")||qw.contains("矛盾")||
                        qw.contains("吵架")||(qw.contains("感情")&&qw.contains("破裂"))||
                        qw.contains("不睦")||qw.contains("不和")||
                        qw.contains("争吵")||qw.contains("殴打")||qw.contains("虐待")||
                        qw.contains("暴力")||qw.contains("吵打")||qw.contains("打骂")||
                        qw.contains("口角")||qw.contains("争执")){
                    //		 System.out.println(qw);
                    fqbhyy.add(qw) ;
                    break;

                }
            }


        }
//        System.out.println(fqbhList);
//        System.out.println(fqbhyy);
        if(fqbhList.size()>0){
            fqbhkssj = fqbhList.get(0);
        }
        if(fqbhyy.size()>0){
            fqbhyyStr = fqbhyy.toString();
        }
        FQBHKSSJNode.addAttribute("value",fqbhkssj);
        FQBHYYNode.addAttribute("value",fqbhyyStr);


    }
    /*
     * 6.双方是否分居：           ，如分居，开始分居时间及原因：                 ，是否因感情不和分居：          ，原告现居住于            ，被告现居住于：         。
     * 这个找出来的居住地不是很准确
     */

    public void FJ(Document document,Element newroot){
        Element root = document.getRootElement();
        Element FJQKNode = newroot.addElement("FJQK").addAttribute("nameCN", "分居情况");
        Element SFFJNode = FJQKNode.addElement("SFFJ").addAttribute("nameCN","是否分居");
        Element KSFJSJNode = FJQKNode.addElement("KSFJSJ").addAttribute("nameCN", "开始分居时间");
        Element KSFJYYNode = FJQKNode.addElement("KSFJYY").addAttribute("nameCN", "开始分居原因");
        Element SFYWGQBHFJNode = FJQKNode.addElement("SFYWGQBHFJ").addAttribute("nameCN", "是否因为感情不和分居");
        Element YGXJZDNode = FJQKNode.addElement("YGXJZD").addAttribute("nameCN", "原告现居住地");
        Element BGXJZDNode = FJQKNode.addElement("BGXJZD").addAttribute("nameCN", "被告现居住地");

        String sffj = "未提及";   //是否分居
        String ksfjsjStr = "未提及";
        ArrayList<String> ksfjsj = new ArrayList<>(); //开始分居时间
        String ksfjyy = "未提及";//开始分居原因
        String sfywgqbhfj = "未提及";//是否因为感情不和分居
        String ygxjzd = "未提及";//原告现居住地
        String bgxjzd = "未提及";//被告现居住地
        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");


            //提取原被告现居住地
            //在诉讼参与人提取
            int flagy = 0;
            int flagb = 0;
            if(root.element("SSCYRQJ")!=null){
                Element SSCYRQJNode = root.element("SSCYRQJ");
                if(SSCYRQJNode.element("SSCYR")!=null){
                    ArrayList<Element> SSCYRNode = (ArrayList) SSCYRQJNode.elements("SSCYR");
                    for(Element e:SSCYRNode){
                        if(e.element("DSRDZ")!=null){
                            Element DSRDZNode = e.element("DSRDZ");
                            if(DSRDZNode.attribute("value")!=null){
                                String dsrdz = DSRDZNode.attributeValue("value");
                                if(e.attribute("value")!=null&&e.attributeValue("value").contains("原告")&&flagy==0){
                                    ygxjzd = dsrdz;
                                    flagy = 1;
                                }
                                else if(e.attribute("value")!=null&&e.attributeValue("value").contains("被告")&&flagb==0){
                                    bgxjzd = dsrdz;
                                    if(dsrdz.contains("同原告")||dsrdz.contains("同上")){
                                        bgxjzd = ygxjzd;
                                    }
                                    flagb = 1;
                                }
                            }
                        }
                        if(flagy*flagb==1){
                            break;
                        }



                    }
                }
            }
            //若文首枚提取到
            String[] qwStrarraysplit = qwStr.split("。|，|；");
            for(String qw:qwStrarraysplit){
                if(qw.contains("居住")||qw.contains("住在")||qw.contains("住")){
                    if(flagy==0){
                        if(qw.contains("原告")){
                            ygxjzd = qw;
                        }
                    }
                    if(flagb==0){
                        if(qw.contains("被告")){
                            bgxjzd = qw;
                        }
                    }
                    if(flagy*flagb==1){
                        break;
                    }
                }
            }


            //是否分居，分局原因，是否因为感情不和分居，原告、被告现居住地
            String[] qwStrarray = qwStr.split("。");
            for(String qw:qwStrarray){
                if(qw.contains("分居")&&(!qw.contains("从未分居")||!qw.contains("没有分居"))){
                    sffj = "是";
                    ksfjyy = qw;
                    if((!(qw.contains("并非")&&qw.contains("感情不和")))&&
                            (qw.contains("不睦")||qw.contains("不和")||
                                    qw.contains("矛盾")||qw.contains("虐待")||
                                    qw.contains("吵架")||qw.contains("打骂")||
                                    qw.contains("争吵")||qw.contains("殴打")||
                                    qw.contains("暴力")||qw.contains("吵打")||
                                    qw.contains("口角")||qw.contains("争执"))){
                        sfywgqbhfj = "是" ;
                    }
                    break;
                }
            }

            //开始分居时间提取
            qwStrarray = qwStr.split("。");
            Pattern r = Pattern.compile(CivilExtractionServiceImpl.datePattern);
            for(String qw:qwStrarray){
                if(qw.contains("分居")&&(!qw.contains("从未分居")||!qw.contains("没有分居"))){
                    Matcher matcher = r.matcher(qw);
                    boolean rs = matcher.find();
                    if(rs){
                        //	    System.out.println("date"+qw);
                        String[] keyWords={"分居"};
                        ksfjsj.add(TimeUtil.getRightDate(qw,keyWords ,1,CivilExtractionServiceImpl.datePattern));
                        break;
                    }
                }
            }






        }
//        System.out.println("开始分居时间"+ksfjsj);
//        System.out.println("开始分居原因"+ksfjyy);
//        System.out.println("原告现居住地"+ygxjzd);
//        System.out.println("被告现居住地"+ygxjzd);

        if(ksfjsj.size()>0){
            ksfjsjStr = (String) ksfjsj.get(0);
        }

        SFFJNode.addAttribute("value",sffj);
        KSFJSJNode.addAttribute("value",ksfjsjStr);
        KSFJYYNode.addAttribute("value",ksfjyy);
        SFYWGQBHFJNode.addAttribute("value",sfywgqbhfj);
        YGXJZDNode.addAttribute("value",ygxjzd);
        BGXJZDNode.addAttribute("value",ygxjzd);



    }

    /*
     * 7. 是否存在军婚情况及服役部队
     */
    public void JHQKJFYBD(Document document,Element newroot){
        Element root = document.getRootElement();
        Element SFCZJHQKJFYBDNode = newroot.addElement("SFCZJHQKJFYBD").addAttribute("nameCN", "是否存在军婚情况及服役部队");

        String ifJH = "否";
        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");
            if((qwStr.contains("军婚")||qwStr.contains("服役")||qwStr.contains("入伍")||
                    qwStr.contains("部队")||qwStr.contains("军队")||qwStr.contains("军官")||
                    qwStr.contains("中国人民解放军")||qwStr.contains("人民武装警察部队")||qwStr.contains("警察"))
                    &&(!qwStr.contains("退伍")&&!qwStr.contains("转业")&&!qwStr.contains("复员"))){
                ifJH = "是";
            }
        }
        SFCZJHQKJFYBDNode.addAttribute("value",ifJH);


    }

    /*
     * 8.信息项：婚前缺乏了解 简介：是否存在婚前缺乏了解，草率结婚，婚后未建立起夫妻感情，难以共同生活的情形
     */
    public static void HQQFLJ(Document document,Element newroot){
        Element root = document.getRootElement();
        Element HQQFLJNode = newroot.addElement("HQQFLJ").addAttribute("nameCN", "是否存在婚前缺乏了解，草率结婚，婚后未建立起夫妻感情，难以共同生活的情形");
        String JD ="否";
        int flagq = 0;
        int flagh = 0;
        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");
            String[] qwStrarray = qwStr.split("，|。");
            for(String qw:qwStrarray){
                if((qw.contains("婚前")&&(qw.contains("缺乏")||qw.contains("缺少"))&&qw.contains("了解"))||qw.contains("草率结婚")){
//                    System.out.println(qw);
                    flagq =1;
                }
                if(((qw.contains("未")||qw.contains("不能")||qw.contains("没有"))&&qw.contains("建立")&&qw.contains("夫妻感情"))||
                        ((qw.contains("难以")||qw.contains("无法")||qw.contains("不能"))&&(qw.contains("共同生活")||qw.contains("与被告生活")||qw.contains("与原告生活")))){
                    flagh =1;
//                    System.out.println(qw);
                }
            }
            if(flagq*flagh==1){
                JD = "是";
            }
        }
//        System.out.println(JD);
        if(flagq+flagh == 0){
            HQQFLJNode.addAttribute("value","未提及");
        }else{
            HQQFLJNode.addAttribute("value",JD);
        }
    }

    /*
     * 9.信息项：是否存在性格不和 简介：是否存在性格不合，经常发生矛盾，难以共同生活的情形
     */
    public static void SFCZXGBH(Document document,Element newroot){
        Element root = document.getRootElement();
        Element XGBHNode = newroot.addElement("SFCZXGBH").addAttribute("NameCN", "是否存在性格不合，经常发生矛盾，难以共同生活的情形");
        String JD ="否";
        int flagq = 0;
        int flagh = 0;

        if(root.attribute("value")!=null){
            String qwStr = root.attributeValue("value");
            String[] qwStrarray = qwStr.split("，|。");
            for(String qw:qwStrarray){
                if((qw.contains("性格")&&(qw.contains("不和")||qw.contains("不合")||
                        qw.contains("差异")||qw.contains("不同")||qw.contains("不投")))&&
                        (qw.contains("矛盾")||qw.contains("争执")||
                                qw.contains("不睦")||qw.contains("不和")||
                                qw.contains("争吵")||qw.contains("殴打")||qw.contains("虐待")||
                                qw.contains("暴力")||qw.contains("吵打")||qw.contains("打骂") )){
//                    System.out.println(qw);
                    flagq =1;
                }
                if(((qw.contains("难以")||qw.contains("无法")||qw.contains("不能"))&&(qw.contains("共同生活")||qw.contains("与被告生活")||qw.contains("与原告生活")))){
                    flagh =1;
//                    System.out.println(qw);
                }
            }
            if(flagq*flagh==1){
                JD = "是";
            }
        }
//        System.out.println(JD);
        if(flagq+flagh == 0){
            XGBHNode.addAttribute("value","未提及");
        }else{
            XGBHNode.addAttribute("value",JD);
        }
    }

    /*
     * 10.信息项：是否存在家庭暴力 简介：是否存在家庭暴力或虐待、遗弃家庭成员的情形
     * 使用CMSSD中的内容作为判断
     */
    public static void SFCZJTBL(Document document,Element newroot){
        Element SFCZJTBLNodeYQJTCYNode = newroot.addElement("SFCZJTBL").addAttribute("nameCN", "是否存在家庭暴力或虐待、遗弃家庭成员的情形");
        Element root = document.getRootElement();
        String JD ="否";
        int fnull = 0;
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                Element cmssdNode = ajjbqkNode.element("CMSSD");
                if(cmssdNode.attribute("value")!=null){

                    String qwStr = cmssdNode.attributeValue("value");
//                    System.out.println(qwStr);
                    String[] qwStrarray = qwStr.split("，|。");
                    for(String qw:qwStrarray){
                        final boolean isExisted = qw.contains("殴打") || qw.contains("虐待") || qw.contains("暴力") ||
                                qw.contains("抛弃") || qw.contains("遗弃") || qw.contains("动手");
                        if(isExisted){
                            fnull = 1;
                            if(!qw.contains("并未") || !qw.contains("没有") || !qw.contains("从未")){
                                JD = "是";
                            }
                        }

                    }

                }
            }
        }
//        System.out.println(JD);
        if(fnull == 0){
            SFCZJTBLNodeYQJTCYNode.addAttribute("value","未提及");
        }else{
            SFCZJTBLNodeYQJTCYNode.addAttribute("value",JD);
        }
    }

    /*
     * 11.信息项：是否存在婚外情 简介：是否存在一方或双方当事人有婚外情、与他人同居等情形
     */
    public static void SFCZHWQ(Document document,Element newroot){
        Element root = document.getRootElement();
        Element SFCZYFHSFDSRYHWQNode = newroot.addElement("SFCZHWQ").addAttribute("nameCN", "是否存在一方或双方当事人有婚外情、与他人同居等情形");
        String JD ="否";
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                Element cmssdNode = ajjbqkNode.element("CMSSD");
                if(cmssdNode.attribute("value")!=null){
                    String qwStr = cmssdNode.attributeValue("value");
//                    System.out.println(qwStr);
                    String[] qwStrarray = qwStr.split("，|。");
                    for(String qw:qwStrarray){
                        if(qw.contains("婚外情")||qw.contains("第三者")||
                                qw.contains("出轨")||qw.contains("同居")||
                                qw.contains("暧昧")||qw.contains("不正当关系")){
                            JD = "是";
                            break;
                        }
                    }

                }

            }
        }
        SFCZYFHSFDSRYHWQNode.addAttribute("value",JD);
    }


    /*
     * 12.信息项：是否赌博或吸毒 简介：是否存在一方或双方当事人有赌博、吸毒等恶习的情形
     * 这个恶习不知道有没有统计全部
     */
    public static void SFDBHXD(Document document,Element newroot){
        Element root = document.getRootElement();
        Element SFCZYFHSFDSRYHWQNode = newroot.addElement("SFDBHXD").addAttribute("nameCN", "是否存在一方或双方当事人有赌博、吸毒等恶习的情形");
        String JD ="否";
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                Element cmssdNode = ajjbqkNode.element("CMSSD");
                if(cmssdNode.attribute("value")!=null){
                    String qwStr = cmssdNode.attributeValue("value");

                    String[] qwStrarray = qwStr.split("，|。");
                    for(String qw:qwStrarray){
                        if(qw.contains("赌博")||qw.contains("吸毒")||qw.contains("酗酒")||qw.contains("恶习")
                        ){
                            JD = "是";
//                            System.out.println(qw);
                            break;
                        }
                    }

                }

            }
        }
        SFCZYFHSFDSRYHWQNode.addAttribute("value",JD);
    }
    /*
     * 13.信息项：是否一方违法或犯罪 简介：是否存在一方被依法判处有期徒刑以上刑罚，或其他违法、犯罪行为严重伤害夫妻感情的情形：                。刑期起止时间:    羁押地点
     */
    public static void SFYFWFHFZ(Document document,Element newroot){
        Element root = document.getRootElement();
        Element PXNode = newroot.addElement("SFYFWFHFZ").addAttribute("nameCN", "是否存在一方被依法判处有期徒刑以上刑罚，或其他违法、犯罪行为严重伤害夫妻感情的情形以及刑期起止时间、羁押地点");
        Element PXSHFQGQNode = PXNode.addElement("SFCZYFBYFPCYQTXSHFQGQ").addAttribute("nameCN", "是否存在一方被依法判处有期徒刑以上刑罚，或其他违法、犯罪行为严重伤害夫妻感情的情形");
        Element XQQZSJNode = PXNode.addElement("XQQZSJ").addAttribute("nameCN", "刑期起止时间");
        Element FXKSSJNode = XQQZSJNode.addElement("FXKSSJ").addAttribute("nameCN", "服刑开始时间");
        Element FXJSSJNode = XQQZSJNode.addElement("FXJSSJ").addAttribute("nameCN", "服刑结束时间");
        Element JYDDNode = PXNode.addElement("JYDD").addAttribute("nameCN","羁押地点");
        String JD ="否";


        int flagq = 0;
        int flagh = 0;
        String allStr="";
        Pattern r = Pattern.compile(CivilExtractionServiceImpl.datePattern);
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                Element cmssdNode = ajjbqkNode.element("CMSSD");
                if(cmssdNode.attribute("value")!=null){
                    allStr += cmssdNode.attributeValue("value");
                }
            }
        }
        if(root.element("CPFXGC")!=null){
            allStr += root.element("CPFXGC").attributeValue("value");

        }
        String[] qwStrarray = allStr.split("，|。");
        for(String qw:qwStrarray){
            if(qw.contains("判刑")||qw.contains("服刑")||qw.contains("判处")||qw.contains("有期徒刑")||
                    (qw.contains("犯")&&qw.contains("罪"))||qw.contains("违法")||qw.contains("刑")){
                flagq = 1;
            }
            if(qw.contains("感情")&&(qw.contains("破裂")||qw.contains("伤害")&&(!qw.contains("并未")||!qw.contains("没有")))){
                flagh =1;
            }

        }
        if(flagq*flagh==1){
            JD ="是";
        }


        //服刑开始时间、地点、期满
        if(root.attribute("value")!=null){
            qwStrarray = root.attributeValue("value").split("。");
            //服刑开始时间
            ArrayList<String> fxkssj = new ArrayList<>();//服刑开始时间
            ArrayList<String> fxjssj = new ArrayList<>();//服刑结束时间
            ArrayList<String> fxdd = new ArrayList<>();//服刑地点
            for(String qw:qwStrarray){
                Matcher m = r.matcher(qw);
                boolean rs = m.find();
                if(rs){
                    //服刑开始时间
                    if(qw.contains("被判刑")||qw.contains("被判处")||qw.contains("被判有期徒刑")||
                            qw.contains("判刑")||qw.contains("触犯法律")||qw.contains("劳动教养")||qw.contains("劳动改造")||
                            (qw.contains("因")&&qw.contains("罪")&&qw.indexOf("因")<qw.indexOf("罪"))
                    ){
                        String[] keyWords = {"被判刑","被判处","被判有期徒刑","判刑","触犯法律","因","罪"};
                        fxkssj.add(TimeUtil.getRightDate(qw, keyWords, 0, CivilExtractionServiceImpl.datePattern));
//                        System.out.println("服刑开始时间："+qw);
                    }
                    else if(qw.contains("服刑日期")){//从服刑日期中提取开始和结束时间
                        String[] splitarray = qw.split("，|；");
                        ArrayList<String> splitdate = new ArrayList<>();
                        for(String qwarray:splitarray){
                            if(qwarray.contains("服刑日期")){
                                Matcher splitm = r.matcher(qwarray);
                                if(splitm.find()){
                                    TimeUtil.getDate(qwarray, splitdate, CivilExtractionServiceImpl.datePattern);
                                    if(splitdate.size()>=1){
                                        fxkssj.add(splitdate.get(0));
//                                        System.out.println("服刑开始时间："+qw);
                                    }
                                    if(splitdate.size()>=2){
                                        fxjssj.add(splitdate.get(1));
                                        System.out.println("服刑结束时间："+qw);
                                    }
                                }
                            }
                        }
                    }
                    if(qw.contains("出狱")||qw.contains("刑满")||qw.contains("释放")||
                            ((qw.contains("刑期")||qw.contains("服刑"))&&qw.contains("期满"))){//服刑结束时间
                        String[] keyWords = {"出狱","刑满","释放","期满"};
                        fxjssj.add(TimeUtil.getRightDate(qw, keyWords, 0, CivilExtractionServiceImpl.datePattern));
//                        System.out.println("服刑结束时间："+qw);
                    }

                }


            }
            //羁押地点
            qwStrarray = root.attributeValue("value").split("。|，|；");
            for(String qw:qwStrarray){
                if((qw.contains("监狱")||qw.contains("所")||qw.contains("局"))&&(
                        qw.contains("服刑")||qw.contains("羁押")||qw.contains("押于")||qw.contains("押在")
                )){
                    fxdd.add(qw);
//                    System.out.println("服刑地点"+qw);
                }
            }


//            System.out.println(fxkssj);
//            System.out.println(fxjssj);
//            System.out.println(fxdd);
            Iterator it = fxkssj.iterator();
            if(fxkssj.size() == 0 ){
                FXKSSJNode.addAttribute("value","未提及");
            }else{
                while(it.hasNext()){
                    String str = (String) it.next();
                    if(!str.equals("")){
                        FXKSSJNode.addAttribute("value",str);
                    }
                }
            }

            if(fxjssj.size()==0){
                FXJSSJNode.addAttribute("value","未提及");
            }else{
                it = fxjssj.iterator();
                while(it.hasNext()){
                    String str = (String) it.next();
                    if(!str.equals("")){
                        FXJSSJNode.addAttribute("value",str);
                    }
                }
            }

            if(fxdd.size()==0){
                JYDDNode.addAttribute("value","未提及");
            }else{
                it = fxdd.iterator();
                while(it.hasNext()){
                    String str = (String) it.next();
                    if(!str.equals("")){
                        JYDDNode.addAttribute("value",str);
                    }
                }
            }


        }
        PXSHFQGQNode.addAttribute("value",JD);



    }
    /*
     *14 信息项：是否存在一方曾起诉离婚情况 简介：是否存在一方曾起诉离婚，经法院处理后夫妻感情未改善的情形
     */

    public static void row14(Document document,Element newroot){
        Element root = document.getRootElement();
        Element QSLHNode = newroot.addElement("SFCZYFCQSLHQK").addAttribute("nameCN", "是否存在一方曾起诉离婚，经法院处理后夫妻感情未改善的情形");
        String ifcz = "否";
        int flag = 0 ;
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNode){
                    if(e.attribute("value")!=null){
                        String[]  qwStrarray= e.attributeValue("value").split("。");
                        //是否曾起诉离婚
                        for(String qw:qwStrarray){
                            if(qw.contains("离婚")&&(qw.contains("起诉")||qw.contains("诉讼")||qw.contains("诉至"))&&!qw.contains("撤诉")){

                                if(qw.contains("曾")||qw.contains("次")||qw.contains("驳回")||qw.contains("判决")||qw.contains("调解")||qw.contains("不准")){
                                    flag =1;
                                    break;
                                }
                            }
                        }

                        //是否关系未改善
                        if(flag == 1 ){
                            qwStrarray= e.attributeValue("value").split("。|，|；");
                            for(String qw:qwStrarray){
                                if (((qw.contains("感情") || qw.contains("关系")) && (qw.contains("破裂") || (qw.contains("未") && qw.contains("改善")))) || (qw.contains("共同生活") && qw.contains("无法"))) {
                                    ifcz = "是";
                                    break;
                                }
                            }
                        }
                    }
                }
            }

        }
//        System.out.println(ifcz);
        QSLHNode.addAttribute("value",ifcz);

    }
    /*
     * 15.信息项：精神病情况 简介：是否存在婚前隐瞒了精神病，婚后经治不愈，或者婚前知道对方患有精神病而与其结婚，或一方在夫妻共同生活期间患精神病，久治不愈的情形：
     */
    public static void JSBQK(Document document,Element newroot){
        Element root = document.getRootElement();
        Element JSBNode = newroot.addElement("JSBQK").addAttribute("nameCN", "是否存在婚前隐瞒了精神病，婚后经治不愈，或者婚前知道对方患有精神病而与其结婚，或一方在夫妻共同生活期间患精神病，久治不愈的情形");
        String ifcz = "否";


        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNode){
                    if(e.attribute("value")!=null){
                        String[]  qwStrarray= e.attributeValue("value").split("。");
                        //是否存在婚前隐瞒了精神病，婚后经治不愈
                        for(String qw:qwStrarray){
                            if(qw.contains("精神")&&(qw.contains("婚前")&&qw.contains("隐瞒")&&qw.contains("婚后")&&(qw.contains("不愈")||qw.contains("发病")||qw.contains("未愈")||qw.contains("未痊愈")||qw.contains("治疗")))&&!qw.contains("出院")){
//                                System.out.println(qw);
//                                System.out.println(document.getName());
                                ifcz = "是";
                            }

                            if(qw.contains("婚前")&&qw.contains("精神")&&(qw.contains("知晓")||qw.contains("知道")||qw.contains("了解")||qw.contains("知情"))){
//                                System.out.println(qw);
//                                System.out.println(document.getName());
                                ifcz = "是";

                            }
                            if((qw.contains("精神病")||qw.contains("精神残疾")||qw.contains("精神障碍")||qw.contains("精神分裂症"))&&(qw.contains("婚后")||qw.contains("婚初")||qw.contains("生活"))&&(qw.contains("不愈")||qw.contains("发病")||qw.contains("未愈")||qw.contains("未痊愈")||qw.contains("治疗"))&&!qw.contains("出院")){
//                                System.out.println(qw);
//                                System.out.println(document.getName());
                                ifcz = "是";
                            }
                            if(ifcz.equals("是")){

                                break;


                            }
                        }

                    }
                }
            }
        }
        JSBNode.addAttribute("value",ifcz);


    }
    /*
     * 16.信息项：分居情况 简介：是否存在因感情不和分居满二年，或经法院判决不准离婚后又分居满一年，互不履行夫妻义务的情形
     */

    public static void FJQK(Document document,Element newroot) throws ParseException {
        Element root = document.getRootElement();
        Element FJNode = newroot.addElement("FJQK").addAttribute("nameCN", "是否存在因感情不和分居满二年，或经法院判决不准离婚后又分居满一年，互不履行夫妻义务的情形");
        String ifcz = "否";
        String allStr = "";
        int flagfj2n = 0;
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNode){
                    if(e.attribute("value")!=null){
                        allStr += e.attributeValue("value");
                    }
                }
            }
        }
        if(root.element("CPFXGC")!=null){
            List<Element> cpfxgcNode = root.elements("CPFXGC");
            for(Element e:cpfxgcNode){
                if(e.attribute("value")!=null){
                    allStr += e.attributeValue("value");
                }
            }
        }

        String[] qwStr = allStr.split("。");


        //使用日期差值
        String fjkssj = "";
        String nowdate = "";
        String qslhsj = "";
        String[] keyWords = {"分居","离家出走"};
        int type=1;




        if(root.element("WW")!=null){//获取审判时间
            Element wwNode = root.element("WW");
            if(wwNode.element("CPSJ")!=null){
                Element cpsjNode = wwNode.element("CPSJ");
                if(cpsjNode.attribute("value")!=null){
                    nowdate = TimeUtil.getSingleDate(cpsjNode.attributeValue("value"), CivilExtractionServiceImpl.datePattern);
                }
            }else{
                String[] fileName = document.getName().split("/");

                nowdate = TimeUtil.getNumbers(fileName[fileName.length-1]).get(0);
                //	  System.out.println("name"+document.getName());
                //	  System.out.println("nowdate"+nowdate);
            }
        }

        //是否因感情不和分居两年
        for(String qw:qwStr){
            if(qw.contains("生活琐事")||qw.contains("矛盾")||qw.contains("性格不合")||
                    qw.contains("吵架")||qw.contains("感情破裂")||qw.contains("感情已破裂")||
                    qw.contains("不睦")||qw.contains("不和")||
                    qw.contains("争吵")||qw.contains("殴打")||qw.contains("虐待")||
                    qw.contains("暴力")||qw.contains("吵打")||qw.contains("打骂")){
                if(qw.contains("分居")||qw.contains("离家出走")){

                    fjkssj =  TimeUtil.getRightDate(qw, keyWords, 1, CivilExtractionServiceImpl.datePattern);

                    break;
                }
            }
        }

        //或经法院判决不准离婚后又分居满一年
        for(String qw:qwStr){
            if(qw.contains("离婚")&&(qw.contains("起诉")||qw.contains("诉讼")||qw.contains("诉至"))&&!qw.contains("撤诉")){
                if(qw.contains("曾")||qw.contains("次")||qw.contains("驳回")||qw.contains("判决")||qw.contains("调解")||qw.contains("不准")){
                    if(qw.contains("分居")&&qw.indexOf("离婚")<qw.indexOf("分居")){
                        qslhsj =  TimeUtil.getRightDate(qw, keyWords, 1, CivilExtractionServiceImpl.datePattern);
                        type=2;
                        break;
                    }
                }
            }

        }




        String startdate ="";
        String enddate ="";
        String qssj = "";

        startdate = TimeUtil.getNumbers(fjkssj).get(0);
        enddate = TimeUtil.getNumbers(nowdate).get(0);
        qssj = TimeUtil.getNumbers(qslhsj).get(0);


//        System.out.println("开始分居时间"+startdate);
//        System.out.println("曾起诉时间"+qssj);
//        System.out.println("现在时间"+enddate);
        //通过算年份差值
        if(!startdate.equals("")&&!enddate.equals("")){
            int dis  =Integer.parseInt(enddate)-Integer.parseInt(startdate);
            if(dis>=2){
                flagfj2n = 1;
            }
        }

        if(type == 2){
            if(!qssj.equals("")&&!enddate.equals("")){
                int dis  =Integer.parseInt(enddate)-Integer.parseInt(qssj);
                if(dis>=1){
                    flagfj2n = 1;
                }
            }
        }

        if(flagfj2n==1){
            ifcz = "是";
//            System.out.println(ifcz+"VVVVVVVVVVVVVVVVVVVVVVVVVVv");
        }


        FJNode.addAttribute("value",ifcz);

    }

    /*
     * 17.信息项：下落不明情况 简介：是否存在一方下落不明满二年，对方起诉离婚，经公告查找确无下落的情形
     */
    public static void XLBMQK(Document document,Element newroot){
        Element root = document.getRootElement();
        Element FJNode = newroot.addElement("XLBMQK").addAttribute("nameCN", "是否存在一方下落不明满二年，对方起诉离婚，经公告查找确无下落的情形");
        String ifcz = "否";
        String allStr = "";
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNode){
                    if(e.attribute("value")!=null){
                        allStr += e.attributeValue("value");
                    }
                }
            }
        }
        if(root.element("CPFXGC")!=null){
            List<Element> cpfxgcNode = root.elements("CPFXGC");
            for(Element e:cpfxgcNode){
                if(e.attribute("value")!=null){
                    allStr += e.attributeValue("value");
                }
            }
        }

        String[] qwStr = allStr.split("。");
        String czsj = "";//出走事件
        String cznf = "";//出走年份
        String nowdate  = "";//当前时间
        int flag1 = 0;
        int  flagifsz = 0;//公告查找
        int flagm2n = 0;
        String[] keyWords = {"离家","出走","下落不明","去向不明"};
        //获取出走时间
        for(String qw:qwStr){
            if((qw.contains("离家")||qw.contains("出走")&&(qw.contains("下落不明")||qw.contains("去向不明")))){
                czsj = TimeUtil.getRightDate(qw,keyWords , 1, CivilExtractionServiceImpl.datePattern);
                flag1 =1;
                break;
            }
        }
        if(!czsj.equals("")){
            cznf = TimeUtil.getNumbers(czsj).get(0);
        }
//        System.out.println("出走时间"+cznf);
        //是否进行公告查找
        if(flag1==1){
            for(String qw:qwStr){
                if (qw.contains("公告") && (qw.contains("确无下落") || qw.contains("未到"))) {
                    flagifsz = 1;
                    break;
                }
            }
        }

        //获取当前时间
        if(root.element("WW")!=null){//获取审判时间
            Element wwNode = root.element("WW");

            if(wwNode.element("CPSJ")!=null){
                Element cpsjNode = wwNode.element("CPSJ");
                if(cpsjNode.attribute("value")!=null){
                    nowdate  = TimeUtil.getSingleDate(cpsjNode.attributeValue("value"), CivilExtractionServiceImpl.datePattern);
                }
            }else{
                String[] fileName = document.getName().split("/");

                nowdate = TimeUtil.getNumbers(fileName[fileName.length-1]).get(0);
            }
        }
        String nowdate2 = TimeUtil.getNumbers(nowdate).get(0);
//        System.out.println("现在时间"+nowdate);
        if(!cznf.equals("")&&!nowdate2.equals("")){
            int dis  =Integer.parseInt(nowdate2)-Integer.parseInt(cznf);
            if(dis>=2){
                flagm2n  = 1;
            }
        }
        if(flagm2n*flagifsz==1){
            ifcz = "是";
//            System.out.println(ifcz+"VVVVVVVVVVVVVVVVVVVv");
        }
        FJNode.addAttribute("value",ifcz);


    }

    //18.信息项：是否患有是疾病缺陷 简介：否存在一方患有法定禁止结婚疾病的，或一方有生理缺陷，或其它原因不能发生性行为，且难以治愈的情形
    public static void sfhyjbqx(Document document,Element newroot){
        Element sfhyjbqxNode=newroot.addElement("SFHYJBQX");
        sfhyjbqxNode.addAttribute("nameCN", "是否存在一方患有法定禁止结婚疾病的，或一方有生理缺陷，或其它原因不能发生性行为，且难以治愈的情形");
        String sf="否";
        int fnull = 0 ;
        Element root=document.getRootElement();
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode=root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                List<Element> cmssdNode=ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNode){
                    if(e.attribute("value")!=null){
                        String[] qwStr=e.attributeValue("value").split("。");
							/*for(String qw:qwStr){
								if(((qw.contains("遗传")||qw.contains("传染")||qw.contains("精神")||qw.contains("性")
										||qw.contains("艾滋")||qw.contains("淋"))&&qw.contains("病"))
										||qw.contains("生理缺陷")
										||((qw.contains("无法")||qw.contains("不能"))&&qw.contains("性行为"))
											&&((qw.contains("治愈")||qw.contains("恢复"))&&(qw.contains("无法")||qw.contains("不能")))){
									sf="存在";
								}
							}*/
                        for(String qw:qwStr){
                            if(((qw.contains("遗传")||qw.contains("传染")||qw.contains("精神")||qw.contains("性")
                                    ||qw.contains("艾滋")||qw.contains("淋"))&&qw.contains("病"))){
                                fnull = 1;
                                sf="是";
                            }else if(qw.contains("生理缺陷")){
                                sf="是";
                                fnull = 1;
                            }else if((qw.contains("无法")||qw.contains("不能"))&&qw.contains("性行为")){
                                fnull = 1;
                                if((qw.contains("治愈")||qw.contains("恢复"))&&(qw.contains("无法")||qw.contains("不能"))){
                                    sf="是";

                                }
                            }
                        }
                    }
                }
            }
        }
        if(fnull == 0){
            sfhyjbqxNode.addAttribute("value","未提及");
        }else{
            sfhyjbqxNode.addAttribute("value",sf);
        }
    }


    //19.信息项：是否欺骗作假 简介：是否一方欺骗对方，或者在结婚登记时弄虚作假，骗取《结婚证》的情形
    public static void sfqpzj(Document document,Element newroot){
        Element sfqpzjNode=newroot.addElement("SFQPZJ");
        sfqpzjNode.addAttribute("nameCN", "是否一方欺骗对方，或者在结婚登记时弄虚作假，骗取《结婚证》的情形");
        String sf="否";
        int fnull = 0;
        Element root=document.getRootElement();
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode=root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                List<Element> cmssdNode=ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNode){
                    if(e.attribute("value")!=null){
                        String[] qwStr=e.attributeValue("value").split("。");
                        for(String qw:qwStr){
                            if((qw.contains("结婚登记")||qw.contains("信息")||qw.contains("身份"))){
                                fnull = 1 ;
                                if((qw.contains("欺骗")||qw.contains("哄骗")||qw.contains("诱骗"))
                                        ||
                                        (qw.contains("作假")||qw.contains("造假"))&&(qw.contains("结婚登记")||qw.contains("信息")||qw.contains("身份"))){
                                    sf="是";

                                }
                            }
                        }
                    }
                }
            }
        }
        if(fnull == 0){
            sfqpzjNode.addAttribute("value","未提及");
        }else{
            sfqpzjNode.addAttribute("value",sf);
        }
    }
    //20.信息项：是否未同居无和好 简介：是否存在双方办理结婚登记后，未同居生活，无和好可能的情形
    public static void sfwtjwhh(Document document,Element newroot){
        Element sfwtjwhhNode=newroot.addElement("SFWTJWHH");
        sfwtjwhhNode.addAttribute("nameCN", "是否存在双方办理结婚登记后，未同居生活，无和好可能的情形");
        String sf="否";
        int fnull = 0;
        Element root=document.getRootElement();
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode=root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                List<Element> cmssdNode=ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNode){
                    if(e.attribute("value")!=null){
                        String[] qwStr=e.attributeValue("value").split("。");
                        for(String qw:qwStr){

                            if((qw.contains("同居")||qw.contains("居住")||((qw.contains("共同")
                                    ||qw.contains("一起"))&&qw.contains("生活")))&&(qw.contains("未")||qw.contains("没"))
                                    ||((qw.contains("和好")||qw.contains("复合"))&&(qw.contains("无")||qw.contains("没"))&&qw.contains("可能"))){
                                fnull = 1;
                                if(qw.contains("办理结婚登记后")){
                                    sf="存在";
                                }
                            }
                        }
                    }
                }
            }
        }
        if(fnull == 0){
            sfwtjwhhNode.addAttribute("value","未提及");
        }else{
            sfwtjwhhNode.addAttribute("value",sf);
        }
    }
    //21.信息项：是否包办买办婚姻 简介：是否存在包办、买卖婚姻、婚后一方随即提出离婚，或者虽共同生活多年，但确未建立起夫妻感情的情形
    public static void sfbbmbhy(Document document,Element newroot){
        Element sfbbmbhyNode=newroot.addElement("SFBBMBHY");
        sfbbmbhyNode.addAttribute("nameCN", "是否存在包办、买卖婚姻、婚后一方随即提出离婚，或者虽共同生活多年，但确未建立起夫妻感情的情形");
        String ifbbmb="否";
        int fnull = 0;
        Element root=document.getRootElement();
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode=root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                List<Element> cmssdNode=ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNode){
                    if(e.attribute("value")!=null){
                        String[]  qwStrarray= e.attributeValue("value").split("。");
                        for(String qw:qwStrarray){
                            if(qw.contains("婚姻")&&(qw.contains("包办")||qw.contains("买办"))){
                                fnull=1;
                                if(qw.contains("婚姻")&&(qw.contains("包办")||qw.contains("买办"))
                                        ||qw.contains("离婚")&&(qw.contains("立刻")||qw.contains("马上")||qw.contains("随即"))
                                        ||((qw.contains("感情")||qw.contains("关系"))&&qw.contains("未确立"))){
                                    ifbbmb="存在";
                                }
                            }
                        }
                    }
                }
            }
        }
        if(fnull == 0){
            sfbbmbhyNode.addAttribute("value","未提及");
        }else{
            sfbbmbhyNode.addAttribute("value",ifbbmb);
        }
    }
    //22.简介：是否怀孕哺乳期 简介：起诉时女方是否怀孕或者哺乳期
    public static void sfhybrq(Document document,Element newroot){
        Element sfhybrqNode=newroot.addElement("SFHYBRQ");
        sfhybrqNode.addAttribute("nameCN", "起诉时女方是否怀孕或者哺乳期");
        String ifhy="否";
        int fnull = 0;
        Element root=document.getRootElement();
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){//查明事实段
                List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNode){
                    if(e.attribute("value")!=null){
                        String[]  qwStrarray= e.attributeValue("value").split("。");
                        //起诉时是否怀孕或者哺乳期
                        for(String qw:qwStrarray){
                            if(qw.contains("处")||qw.contains("正")&&(qw.contains("怀孕")||qw.contains("哺乳期"))){
                                fnull = 1;
                                if(qw.contains("诉讼")&&(qw.contains("期")||qw.contains("间"))){
                                    ifhy="是";
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(fnull == 0){
            sfhybrqNode.addAttribute("value","未提及");
        }else{
            sfhybrqNode.addAttribute("value",ifhy);
        }
    }

    //23.信息项：生育子女情况
    public static void syznqk(Document document,Element newroot){
        String znqk="";
        Element syznqkNode=newroot.addElement("SYZNQK");
        syznqkNode.addAttribute("nameCN", "生育子女情况");
        int fnull=0;
        Element root=document.getRootElement();
        String text=root.getName();
        System.out.println(text);
        if(text.equals("QW")){
            List<Attribute> qwAttr=root.attributes();
            for(Attribute attr:qwAttr){
                if(attr.getName().equals("value")){
                    ArrayList<String> li=new ArrayList<>();
                    String qwValue=attr.getValue();
                    String[] qwValues=qwValue.split("，|。|；");
                    //for(int i=0;i<qwValues.length;i++)
                    //System.out.println(qwValues[i]);
                    for (String value : qwValues) {
                        if ((value.contains("生") || value.contains("育") || value.contains("原、被告")
                                || value.contains("婚生") || value.contains("双方")) && (value.contains("子") || value.contains("女") || value.contains("男") || value.contains("女孩"))) {
                            String[] keywords = {"生", "育", "双方"};
                            fnull = 1;
                            //System.out.println("有");
                            for (String keyword : keywords) {
                                //System.out.println("有");
                                int start = value.indexOf(keyword);
                                //System.out.println(start);
                                znqk = value.substring(start + 1);
                                li.add(znqk);

                            }
                        } else if (value.contains("未生育") || value.contains("未育有")) {
                            li.add("无子女");
                            fnull = 1;
                        }
                    }

                    TimeUtil.removeDuplicateWithOrder(li);
                    String syznqk=li.toString();
                    syznqkNode.addAttribute("value",syznqk);
                }
            }
        }
        if(fnull == 0){
            syznqkNode.addAttribute("value","未提及");
        }
    }

    //24.信息项：子女特殊情况 简介：子女有无重大疾病或其他特殊情况
    public static void zntsqk(Document document,Element newroot)
    {
        Element zntsqkNode=newroot.addElement("ZNTSQK");
        zntsqkNode.addAttribute("nameCN", "子女有无重大疾病或其他特殊情况");
        String ifhb="否";
        int fnull=0;
        Element root=document.getRootElement();
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){//查明事实段
                List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNode){
                    if(e.attribute("value")!=null){
                        String[]  qwStrarray= e.attributeValue("value").split("。");
                        //子女有无重大疾病或其他特殊情况
                        for(String qw:qwStrarray){
                            if(qw.contains("患有")||qw.contains("患病")||qw.contains("事故")
                                    ||qw.contains("病情")||qw.contains("遭遇")||qw.contains("遇到")){
                                fnull = 1;
                                if(qw.contains("子")||qw.contains("女")){
                                    ifhb=qw.toString();
                                }
                            }
                        }
                    }
                }
            }
        }
        if(fnull == 0){
            zntsqkNode.addAttribute("value","未提及");
        }else{
            zntsqkNode.addAttribute("value",ifhb);
        }
    }

    /*
     * 25.信息项：子女意见 简介：父母双方对十周岁以上的未成年子女随父或随母生活发生争执的，该子女的意见
     */

    public static void ZNYJ(Document document,Element newroot){
        Element root = document.getRootElement();
        Element ZVYJNode = newroot.addElement("SZSYSZNYJ").addAttribute("nameCN", "父母双方对十周岁以上的未成年子女随父或随母生活发生争执的，该子女的意见");
        String zvyj = "未提及";

        if(root.element("CPFXGC")!=null){
            Element cpfxgcNode = root.element("CPFXGC");
            if(cpfxgcNode.attribute("value")!=null){
                String qwStr = cpfxgcNode.attributeValue("value");
                String[] qwStrarray = qwStr.split("。");
                for(String qw:qwStrarray){
                    if((qw.contains("婚生")||qw.contains("原、被告")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))){
                        if((qw.contains("未超过")||qw.contains("未满")||qw.contains("不满")||qw.contains("不足")||qw.contains("未足")||qw.contains("以下"))&&qw.contains("十周岁")){
                            zvyj = "未超过十周岁";
                        }
                        else if(qw.contains("意见")||qw.contains("意愿")||qw.contains("愿意随")||qw.contains("愿随")){
                            {
                                String[] qwarrayList = qw.split("，");
                                for(String qwarray:qwarrayList){
                                    int start=0;
                                    int end=0;
                                    if(qwarray.contains("随")&&qwarray.contains("生活")){
                                        start = qwarray.indexOf("随");
                                        end = qwarray.indexOf("生活");

                                    }
                                    else if(qwarray.contains("由")&&qwarray.contains("抚养")){
                                        start = qwarray.indexOf("由");
                                        end = qwarray.indexOf("抚养");
                                    }
                                    if(start<end){
                                        zvyj = qwarray.substring(start,end+2);

                                    }

                                }

                            }
                        }

                    }

                }
            }
//            System.out.println(zvyj);
        }
        ZVYJNode.addAttribute("value",zvyj);
    }


    /*
     * 26.信息项：两周岁以下子女是否存在可随父方生活 简介：两周岁以下的子女，是否存在可随父方生活的情形
     */
    public static void LZSYXZNSFCZKSFFSH(Document document,Element newroot){
        Element root = document.getRootElement();
        Element LZSYXZNNode = newroot.addElement("LZSYXZNSFCZKSFFSH").addAttribute("nameCN", "两周岁以下的子女，是否存在可随父方生活的情形");
        String ifcz = "否";
        String bgxb = "";//被告性别
        String ygxb = "";//原告性别
        int flag;
        int flagy = 0;
        int flagb = 0;

        if(root.element("SSCYRQJ")!=null){//获取原被告性别
            Element SSCYRQJNode = root.element("SSCYRQJ");
            if(SSCYRQJNode.attribute("value")!=null){
                String[] sccyr = SSCYRQJNode.attributeValue("value").split("。");
                //System.out.println(SSCYRQJNode.attributeValue("value"));
                for(String cyrall:sccyr){
                    String cyr = cyrall.trim();
                    if(cyr.startsWith("原告")||cyr.startsWith("被告")){
                        flag = 0;
                        if(cyr.startsWith("原告")){
                            flag=1;
                        }
                        if(cyr.startsWith("被告")){
                            flag=-1;
                        }

                        String[] cyrsplit = cyr.split("，");
                        for(int i = 1;i<cyrsplit.length;i++){
                            if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==1&&flagy!=1){
                                ygxb = cyrsplit[i];
                                flagy =1;
                                break;
                            }else if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==-1&&flagb!=1){
                                bgxb = cyrsplit[i];
                                flagb=1;
                                break;
                            }
                        }
                    }
                    //	System.out.println("y"+ygxb);
                    //    System.out.println("b"+bgxb);
                }
            }
            LZSYXZNNode.addAttribute("value",ifcz);
        }
        if(root.element("CPFXGC")!=null){
            Element cpfxgcNode = root.element("CPFXGC");
            if(cpfxgcNode.attribute("value")!=null){
                String qwStr = cpfxgcNode.attributeValue("value");
                String[] qwStrarray = qwStr.split("。");
                for(String qw:qwStrarray){
                    if((qw.contains("婚生")||qw.contains("原、被告")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))){
                        //	 System.out.println("all"+qw);
                        if((qw.contains("两周岁")||qw.contains("两岁"))&&(qw.contains("未满")&&qw.contains("不满")||qw.contains("不足")||qw.contains("未足")||qw.contains("以下"))){
                            int start=0;
                            int end=0;
                            String sub = "";
                            if(qw.contains("随")&&qw.contains("生活")){
                                start = qw.indexOf("随");
                                end = qw.indexOf("生活");

                            }
                            else if(qw.contains("由")&&qw.contains("抚养")){
                                start = qw.indexOf("由");
                                end = qw.indexOf("抚养");
                            }
                            if(start<end){
                                sub = qw.substring(start,end+2);

                            }else{
                                sub =qw;
                            }
                            if(sub.contains("父")){
                                ifcz = "是";
                                System.out.println("row28"+ifcz);

                            }
                            else if(sub.contains("原告")&&ygxb.contains("男")){
                                ifcz = "是";
                                System.out.println("row28"+ifcz);
                            }
                            else if(sub.contains("被告")&&bgxb.contains("男")){
                                ifcz = "是";
                                System.out.println("row28"+ifcz);
                            }
                            //	  System.out.println(document.getName());
                            break;

                        }

                    }

                }
            }

        }

    }
    /*
     * 27-1.信息项：母方严重疾病 简介：母方患有久治不愈的传染性疾病或其他严重疾病，子女不宜与其共同生活的情形：
     */
    public static void MFYZJB(Document document,Element newroot){
        Element root = document.getRootElement();
        Element YZJBNode = newroot.addElement("MFYZJB").addAttribute("nameCN", "母方患有久治不愈的传染性疾病或其他严重疾病，子女不宜与其共同生活的情形");
        String bgxb = "";//被告性别
        String ygxb = "";//原告性别
        int flag = 0 ;
        int flagy = 0;
        int flagb = 0;
        int flagzvnl = 0;
        String ifcc = "否";
        //是否子女两周岁以下
        if(root.element("CPFXGC")!=null){
            Element cpfxgcNode = root.element("CPFXGC");
            if(cpfxgcNode.attribute("value")!=null){
                String qwStr = cpfxgcNode.attributeValue("value");
                String[] qwStrarray = qwStr.split("。");
                for(String qw:qwStrarray){
                    if((qw.contains("婚生")||qw.contains("原、被告")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))){
                        //	 System.out.println("all"+qw);
                        if((qw.contains("两周岁")||qw.contains("两岁"))&&(qw.contains("未满")&&qw.contains("不满")||qw.contains("不足")||qw.contains("未足")||qw.contains("以下"))){
                            flagzvnl =1;
                        }
                    }
                }
            }
        }

        if(flagzvnl == 1){

            if(root.element("SSCYRQJ")!=null){//获取原被告性别
                Element SSCYRQJNode = root.element("SSCYRQJ");
                if(SSCYRQJNode.attribute("value")!=null){
                    String[] sccyr = SSCYRQJNode.attributeValue("value").split("。");
                    //System.out.println(SSCYRQJNode.attributeValue("value"));
                    for(String cyrall:sccyr){
                        String cyr = cyrall.trim();
                        if(cyr.startsWith("原告")||cyr.startsWith("被告")){
                            flag = 0;
                            if(cyr.startsWith("原告")){
                                flag=1;
                            }
                            if(cyr.startsWith("被告")){
                                flag=-1;
                            }

                            String[] cyrsplit = cyr.split("，");
                            for(int i = 1;i<cyrsplit.length;i++){
                                if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==1&&flagy!=1){
                                    ygxb = cyrsplit[i];
                                    flagy =1;
                                    break;
                                }else if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==-1&&flagb!=1){
                                    bgxb = cyrsplit[i];
                                    flagb=1;
                                    break;
                                }
                            }
                        }
                        //	System.out.println("y"+ygxb);
                        //    System.out.println("b"+bgxb);
                    }
                }
            }
            String flagsex ="XXX";
            if(bgxb.contains("女")){
                flagsex = "被告";
            }else if(ygxb.contains("女")){
                flagsex = "原告";
            }
            //	System.out.println("flag"+flagsex);
            // System.out.println(flagsex);
            //	 System.out.println(ygxb);
            //	 System.out.println(bgxb);
            //	 System.out.println(document.getName());
            String alljd = "";
            //首先判断母方是否患病
            if(root.element("CPFXGC")!=null){
                Element cpfxgcNode = root.element("CPFXGC");
                if(cpfxgcNode.attribute("value")!=null){
                    String qwStr = cpfxgcNode.attributeValue("value");
                    alljd +=qwStr;

                }
            }
            if(root.element("AJJBQK")!=null&&flag!=2){
                Element ajjbqkNode = root.element("AJJBQK");
                if(ajjbqkNode.element("CMSSD")!=null){
                    List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
                    for(Element e:cmssdNode){
                        if(e.attribute("value")!=null){
                            String qwStr = e.attributeValue("value");
                            alljd += qwStr;
                        }
                    }
                }
            }
            String[] qwStrarray = alljd.split("，|。|；|、");
            for(String qw:qwStrarray){ //判断母方是否患病
                if((qw.contains("病")||qw.contains("症"))&&qw.contains(flagsex)){
                    flag =2;
                    break;
                }
            }
            qwStrarray = alljd.split("。");
            if(flag ==2){
                // System.out.println(flagsex);
                // System.out.println(ygxb);
                // System.out.println(bgxb);
                // System.out.println(document.getName());
                for(String qw:qwStrarray){
                    if((qw.contains("孩子")||qw.contains("子")||qw.contains("女"))&&
                            (qw.contains("抚育")||qw.contains("抚养"))&&(qw.contains("病")||qw.contains("症"))&&(
                            qw.contains("不宜")||qw.contains("不利"))){
                        ifcc = "是";

                        System.out.println("row28-1"+ifcc);

                        break;

                    }
                }
            }
        }
        YZJBNode.addAttribute("value",ifcc);


    }
    /*
     * 27-2.信息项：母方有抚养条件不尽抚养义务 简介：母方有抚养条件不尽抚养义务，而父方要求子女随其生活的情形
     */

    public static void MFYFYTJBJFYYW(Document document,Element newroot){
        Element root = document.getRootElement();
        Element MFBJFYYWNode = newroot.addElement("MFYFYTJBJFYYW").addAttribute("nameCN", "母方有抚养条件不尽抚养义务，而父方要求子女随其生活的情形");
        String bgxb = "";//被告性别
        String ygxb = "";//原告性别
        int flag = 0 ;
        int flagy = 0;
        int flagb = 0;
        int flagzvnl = 0;
        String ifcc = "否";
        //是否子女两周岁以下
        if(root.element("CPFXGC")!=null){
            Element cpfxgcNode = root.element("CPFXGC");
            if(cpfxgcNode.attribute("value")!=null){
                String qwStr = cpfxgcNode.attributeValue("value");
                String[] qwStrarray = qwStr.split("。");
                for(String qw:qwStrarray){
                    if((qw.contains("婚生")||qw.contains("原、被告")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))){
                        //	 System.out.println("all"+qw);
                        if((qw.contains("两周岁")||qw.contains("两岁"))&&(qw.contains("未满")&&qw.contains("不满")||qw.contains("不足")||qw.contains("未足")||qw.contains("以下"))){
                            flagzvnl =1;
                        }
                    }
                }
            }
        }

        if(flagzvnl == 1){
            if(root.element("SSCYRQJ")!=null){//获取原被告性别
                Element SSCYRQJNode = root.element("SSCYRQJ");
                if(SSCYRQJNode.attribute("value")!=null){
                    String[] sccyr = SSCYRQJNode.attributeValue("value").split("。");
                    //System.out.println(SSCYRQJNode.attributeValue("value"));
                    for(String cyrall:sccyr){
                        String cyr = cyrall.trim();
                        if(cyr.startsWith("原告")||cyr.startsWith("被告")){
                            flag = 0;
                            if(cyr.startsWith("原告")){
                                flag=1;
                            }
                            if(cyr.startsWith("被告")){
                                flag=-1;
                            }

                            String[] cyrsplit = cyr.split("，");
                            for(int i = 1;i<cyrsplit.length;i++){
                                if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==1&&flagy!=1){
                                    ygxb = cyrsplit[i];
                                    flagy =1;
                                    break;
                                }else if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==-1&&flagb!=1){
                                    bgxb = cyrsplit[i];
                                    flagb=1;
                                    break;
                                }
                            }
                        }
                        //	System.out.println("y"+ygxb);
                        //    System.out.println("b"+bgxb);
                    }
                }
            }
            String flagsex ="";
            String uflagsex = "";
            if(bgxb.contains("女")){
                flagsex = "被告";
                uflagsex = "原告";
            }else if(ygxb.contains("女")){
                flagsex = "原告";
                uflagsex = "被告";
            }

            int flagq =0;
            //首先看母亲有没有尽到抚养义务
            if(root.element("CPFXGC")!=null){
                Element cpfxgcNode = root.element("CPFXGC");
                if(cpfxgcNode.attribute("value")!=null){
                    String qwStr = cpfxgcNode.attributeValue("value");
                    String[] qwStrarray = qwStr.split("。|，");
                    for(String qw:qwStrarray){
                        if((qw.contains(flagsex)||qw.contains("母"))&&qw.contains("抚养义务")
                                &&(qw.contains("不履行")||qw.contains("未尽")||qw.contains("未履行"))){
                            int d1 = 10000;
                            int d2 = 10000;
                            int d3 = 10000;
                            int dt = 10000;
                            int d = qw.indexOf("抚养");

                            if(qw.contains("原告")){
                                d1 = Math.abs(qw.indexOf("原告")-d);
                            }
                            if(qw.contains("被告")){
                                d2 =  Math.abs(qw.indexOf("被告")-d);
                            }
                            if(qw.contains(flagsex)){
                                dt =  Math.abs(qw.indexOf(flagsex)-d);
                            }
                            if(qw.contains("母亲")){
                                d3 =  Math.abs(qw.indexOf("母亲")-d);
                            }
                            if(dt<=Math.min(d1, d2)||d3<=Math.min(d1, d2)){


                                flagq =1 ;
                            }
                        }
                    }

                }
            }
            if(flagq ==1){
                String nodeStr = "";
                if(uflagsex.equals("原告")){
                    nodeStr = "YGSCD";
                }else{
                    nodeStr = "BGBCD";
                }
                if(root.element("AJJBQK")!=null){
                    Element ajNode = root.element("AJJBQK");
                    if(ajNode.element(nodeStr)!=null){
                        Element node = ajNode.element(nodeStr);
                        if(node.attribute("value")!=null){
                            String[] nodeStrall = node.attributeValue("value").split("，|。");
                            for(String str:nodeStrall){
                                if((str.contains("婚生")||str.contains("原、被告")|| str.contains("双方"))&&
                                        (str.contains("子")||str.contains("女"))&&(str.contains("抚养")||str.contains("生活"))){
                                    if(str.contains("本人")||str.contains("自己")||str.contains("我")||str.contains(uflagsex)||str.contains("父")){
                                        ifcc ="是";
                                        System.out.println(document.getName());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        MFBJFYYWNode.addAttribute("value",ifcc);
    }
    /*
     * 27-3.信息项：其他原因子女无法随母生活 简介：因其他原因，子女确无法随母方生活的情形
     */
    public static void QTYYZNWFSMSH(Document document,Element newroot){
        Element root = document.getRootElement();
        Element QTYYNode = newroot.addElement("QTYYZNWFSMSH").addAttribute("nameCN", "因其他原因，子女确无法随母方生活的情形");
        String bgxb = "";//被告性别
        String ygxb = "";//原告性别
        int flag = 0 ;
        int flagy = 0;
        int flagb = 0;
        int flagzvnl = 0;
        String ifcc = "否";
        if(root.element("CPFXGC")!=null){
            Element cpfxgcNode = root.element("CPFXGC");
            if(cpfxgcNode.attribute("value")!=null){
                String qwStr = cpfxgcNode.attributeValue("value");
                String[] qwStrarray = qwStr.split("。");
                for(String qw:qwStrarray){
                    if((qw.contains("婚生")||qw.contains("原、被告")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))){
                        //	 System.out.println("all"+qw);
                        if((qw.contains("两周岁")||qw.contains("两岁"))&&(qw.contains("未满")&&qw.contains("不满")||qw.contains("不足")||qw.contains("未足")||qw.contains("以下"))){
                            flagzvnl =1;
                        }
                    }
                }
            }
        }

        if(flagzvnl == 1){
            if(root.element("SSCYRQJ")!=null){//获取原被告性别
                Element SSCYRQJNode = root.element("SSCYRQJ");
                if(SSCYRQJNode.attribute("value")!=null){
                    String[] sccyr = SSCYRQJNode.attributeValue("value").split("。");
                    //System.out.println(SSCYRQJNode.attributeValue("value"));
                    for(String cyrall:sccyr){
                        String cyr = cyrall.trim();
                        if(cyr.startsWith("原告")||cyr.startsWith("被告")){
                            flag = 0;
                            if(cyr.startsWith("原告")){
                                flag=1;
                            }
                            if(cyr.startsWith("被告")){
                                flag=-1;
                            }
                            String[] cyrsplit = cyr.split("，");
                            for(int i = 1;i<cyrsplit.length;i++){
                                if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==1&&flagy!=1){
                                    ygxb = cyrsplit[i];
                                    flagy =1;
                                    break;
                                }else if((cyrsplit[i].contains("男")||cyrsplit[i].contains("女"))&&flag==-1&&flagb!=1){
                                    bgxb = cyrsplit[i];
                                    flagb=1;
                                    break;
                                }
                            }
                        }
                        //	System.out.println("y"+ygxb);
                        //    System.out.println("b"+bgxb);
                    }
                }
            }
            String flagsex ="";
            if(bgxb.contains("女")){
                flagsex = "被告";
            }else if(ygxb.contains("女")){
                flagsex = "原告";
            }

            if(root.element("CPFXGC")!=null){
                Element cpfxgcNode = root.element("CPFXGC");
                if(cpfxgcNode.attribute("value")!=null){
                    String qwStr = cpfxgcNode.attributeValue("value");
                    String[] qwStrarray = qwStr.split("。|，");
                    for(String qw:qwStrarray){
                        if((qw.contains("婚生")||qw.contains("原、被告")|| qw.contains("双方"))&&
                                (qw.contains("子")||qw.contains("女"))
                                &&(qw.contains("不随")||qw.contains("不由")||qw.contains("无法随"))&&
                                (qw.contains("母")||qw.contains(flagsex))){
                            ifcc = "是";
//                            System.out.println(qw);
                        }
                    }
                }
            }

        }
        QTYYNode.addAttribute("value",ifcc);
    }
    /*
     * 28.简介：起诉时子女生活、学习现状
     */

    public static void QSSZNSHXXXZ(Document document,Element newroot){
        Element root = document.getRootElement();
        Element ZNZKNode = newroot.addElement("QSSZNSHXXXZ").addAttribute("nameCN", "起诉时子女生活、学习现状");
        StringBuilder allStr = new StringBuilder();
        if(root.element("AJJBQK")!=null){
            Element ajjbqkNode = root.element("AJJBQK");
            if(ajjbqkNode.element("CMSSD")!=null){
                List<Element> cmssdNode = ajjbqkNode.elements("CMSSD");
                for(Element e:cmssdNode){
                    if(e.attribute("value")!=null){
                        String qwStr = e.attributeValue("value");
                        String[] qwStrarray = qwStr.split("。");


                        for(String qw:qwStrarray){
                            int flag = 0;
                            String[] qwlist = qw.split("，");
                            for(String qwlistsp:qwlist){
                                if((qwlistsp.contains("婚生")||qwlistsp.contains("原、被告")||qwlistsp.contains("原告之")||qwlistsp.contains("被告之")||qwlistsp.contains("双方")||qwlistsp.contains("生"))&&(qwlistsp.contains("子")||qwlistsp.contains("女"))){
                                    flag  = 1;
                                    allStr.append(qwlistsp).append("。");

                                }else if(qwlistsp.contains("现")){
                                    if(flag == 1){
                                        allStr.append(qwlistsp);
                                        flag = 0;
                                    }
                                }
                            }

                        }
//                        System.out.println(allStr);

                    }
                }
            }

        }
        if(allStr.toString().equals("")){
            ZNZKNode.addAttribute("value","未提及");
        }else{
            ZNZKNode.addAttribute("value", allStr.toString());
        }
    }

    //信息项：优先跟随一方 简介：对两周岁以上未成年的子女，是否存在可予优先考虑与一方生活的情形
    public static void YXGSYF(Document document,Element newroot){
        Element YXGSYF = newroot.addElement("YXGSYF").addAttribute("nameCN", "两周岁以上子女优先考虑抚养权的因素");
        if(JYYS(document,YXGSYF)||SHHJYS(document,YXGSYF)||YFYQTZN(document,YXGSYF)||SFCZYFBLYS(document,YXGSYF)){
            YXGSYF.addAttribute("value","是");
        }else{
            YXGSYF.addAttribute("value","否");
        }
    }

    // 29-4. 信息项：是否存在一方不利因素 简介：是否存在子女随其生活，对子女成长有利，而另一方患有久治不愈的传染性疾病或其他严重疾病，
    //     *  或者有其他不利于子女身心健康的情形，不宜与子女共同生活的情形：

    public static boolean SFCZYFBLYS(Document document,Element newroot){
        boolean result = false;
        Element root = document.getRootElement();
        Element row29_4Node = newroot.addElement("YFBLYS").addAttribute("nameCN", "一方不利因素");
        String yfblys = "未提及或不考虑";//一方不利因素
        Iterator it = root.elementIterator();
        int fnull = 0;
        while(it.hasNext()){
            Element Node = (Element) it.next();
            if(Node.getName().equals("CPFXGC")) {
                if(Node.attribute("value")!=null) {
                    String qwStr = Node.attributeValue("value");
                    String[] qwStrarray = qwStr.split("。|；");
                    for(String qw:qwStrarray) {
                        if((qw.contains("成长有利")||qw.contains("成长不利")
                                ||qw.contains("有利于孩子")||qw.contains("不利于孩子")
                                ||qw.contains("有利于子女")||qw.contains("有利于小孩")
                                ||qw.contains("由原告抚养为宜")||qw.contains("由被告抚养为宜")
                                ||(((qw.contains("有利于")||qw.contains("不利于"))&&qw.contains("身心健康"))))
                                ||((qw.contains("病")||qw.contains("患"))
                                &&(((qw.contains("婚生")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女")))
                                &&(qw.contains("由")||qw.contains("跟")||qw.contains("随"))
                                &&(qw.contains("被告")||qw.contains("原告")||qw.contains("父亲")||qw.contains("母亲"))
                                &&(qw.contains("生活")||qw.contains("抚养")||qw.contains("抚育"))))){
                            fnull = 1;
//                            System.out.println(document.getName());
                            yfblys = qw;
//                            System.out.println(qw);
                            if(!yfblys.equals("未提及或不考虑")) {
                                result = true;
//                                System.out.println("29-4");
                                break;
                            }
                        }

                    }
                }
            }
        }

        if(fnull == 0){
            row29_4Node.addAttribute("value","未提及");
        }else{
            if(!(yfblys.equals("未提及或不考虑"))) {
                row29_4Node.addAttribute("value","是");
            }
            else {
                row29_4Node.addAttribute("value","否");
            }
        }
    return result;
    }

    //29-3. 信息项：一方有其他子女 简介：无其他子女，而另一方有其他子女的情形
    public static boolean YFYQTZN(Document document,Element newroot){
        boolean result = false;
        Element root = document.getRootElement();
        Element row29_3Node = newroot.addElement("YFYQTZN").addAttribute("nameCN", "婚前有其他子女的因素");
        String yfyqtzn = "未提及或不考虑";//一方有其他子女
        Iterator it = root.elementIterator();
        int fnull = 0;
        while(it.hasNext()){
            Element Node = (Element) it.next();
            if(Node.getName().equals("CPFXGC")) {
                if(Node.attribute("value")!=null) {
                    String qwStr = Node.attributeValue("value");
                    String[] qwStrarray = qwStr.split("。|；");
                    for(String qw:qwStrarray) {
                        if((qw.contains("婚前子女")||qw.contains("婚前所生"))
                                &&(!qw.contains("离婚前子女"))&&(!qw.contains("离婚前所生"))){
                            fnull=1;
//                            System.out.println(document.getName());
                            yfyqtzn = qw;
//                            System.out.println(qw);
                            if(!yfyqtzn.equals("未提及或不考虑")) {
                                result = true;
//                                System.out.println("29-3");
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(fnull == 0){
            row29_3Node.addAttribute("value","未提及");
        }else{
            if(!(yfyqtzn.equals("未提及或不考虑"))) {
                row29_3Node.addAttribute("value","是");
            }
            else {
                row29_3Node.addAttribute("value","否");
            }
        }
        return result;
    }

    //29-2. 信息项：生活环境因素 简介：子女随其生活时间较长，改变生活环境对子女健康成长明显不利的情形

    public static boolean SHHJYS(Document document,Element newroot){
        boolean result = false;
        Element root = document.getRootElement();
        String shhj = "未提及或不考虑";//生活环境因素
        Iterator it = root.elementIterator();
        int fnull = 0;
        while(it.hasNext()){
            Element Node = (Element) it.next();
            if(Node.getName().equals("CPFXGC")) {
                if(Node.attribute("value")!=null) {
                    String qwStr = Node.attributeValue("value");
                    String[] qwStrarray = qwStr.split("。|；");
                    for(String qw:qwStrarray) {
                        if(qw.contains("改变")&&qw.contains("生活环境")
                                &&(qw.contains("有利")||qw.contains("不利"))){
                            fnull =1;
//                            System.out.println(document.getName());
                            shhj = qw;
//                            System.out.println(qw);
                            if(!shhj.equals("未提及或不考虑")) {
                                result = true;
//                                System.out.println("29-2");
                                break;
                            }
                        }
                    }
                }
            }
        }

        Element row29_2Node = newroot.addElement("row29_2").addAttribute("nameCN", "生活环境因素");
        if(fnull == 0){
            row29_2Node.addAttribute("value","未提及");
        }else{
            if(!(shhj.equals("未提及或不考虑"))) {
                row29_2Node.addAttribute("value","是");
            }else {
                row29_2Node.addAttribute("value","否");
            }
        }
    return result;
    }

    //29-1. 信息项：绝育因素 简介：已做绝育手术或因其他原因丧失生育能力的情形
    public static boolean JYYS(Document document,Element newroot){
        boolean result = false;
        Element root = document.getRootElement();
        String jyys = "未提及或不考虑";//绝育因素
        Iterator it = root.elementIterator();
        int fnull = 0;
        while(it.hasNext()){
            Element Node = (Element) it.next();
            if(Node.getName().equals("CPFXGC")) {
                if(Node.attribute("value")!=null) {
                    String qwStr = Node.attributeValue("value");
                    String[] qwStrarray = qwStr.split("。|；");
                    for(String qw:qwStrarray) {
                        if((qw.contains("生育能力")||(qw.contains("生育")&&qw.contains("疾病"))||qw.contains("无法生育")||qw.contains("绝育"))
                                &&((qw.contains("婚生")||qw.contains("双方"))&&(qw.contains("子")||qw.contains("女"))
                                &&(qw.contains("由")||qw.contains("跟")||qw.contains("随"))
                                &&(qw.contains("被告")||qw.contains("原告")||qw.contains("父亲")||qw.contains("母亲"))
                                &&(qw.contains("生活")||qw.contains("抚养")||qw.contains("抚育")))
                        ){
                            fnull = 1;
//                            System.out.println(document.getName());
                            jyys = qw;
//                            System.out.println(jyys);
                            if(!jyys.equals("未提及或不考虑")) {
                                result = true;
//                                System.out.println("29-1");
                                break;
                            }
                        }
                    }
                }
            }
        }

        Element row29_1Node = newroot.addElement("JYYS").addAttribute("nameCN", "绝育因素");
        if(fnull == 0){
            row29_1Node.addAttribute("value","未提及");
        }else{
            if(!(jyys.equals("未提及或不考虑"))) row29_1Node.addAttribute("value","是");
            else row29_1Node.addAttribute("value","否");
        }
        return result;
    }

//30.信息项：祖父母因素 简介：是否存在父、母抚养未成年子女的条件基本相同，双方均要求子女与其共同生活，但子女单独随祖父母或外祖父母共同生活多年，
    //且祖父母或外祖父母要求并有能力帮助子女照顾孙子女或外孙子女的，可作为子女随父或母生活的优先条件予以考虑的情形

    public static void  ZFMYS(Document document,Element new_root){
        Element root = document.getRootElement();
        String Grandpa = "未考虑或不存在";//祖父母因素
        Element GRANDPA = new_root.addElement("GRANDPA").addAttribute("nameCN", "祖父母因素");
        Iterator it = root.elementIterator();
        boolean is_null = true;
        while(it.hasNext()){
            Element Node = (Element) it.next();
            if(Node.getName().equals("CPFXGC")) {
                if(Node.attribute("value")!=null){
                    String key_sentence = Node.attributeValue("value");
                    String[] key_words = key_sentence.split("。|；");
                    for(String key:key_words){
                        if((key.contains("祖母")||key.contains("祖父")||key.contains("爷爷")||
                                key.contains("奶奶")||key.contains("外公")||key.contains("外婆"))&&(key.contains("生活")||key.contains("居住")||key.contains("抚养")||
                                key.contains("成长"))&&(!(key.contains("房屋")||key.contains("财产")))) {
                            is_null = false ;
                            Grandpa = key;
                        }
                    }
                }
            }
        }
        if(is_null){
            GRANDPA.addAttribute("value", "未提及");
        }else{
            if(!(Grandpa.equals("未考虑或不存在"))) {
                GRANDPA.addAttribute("value", "是");
            }
            else {
                GRANDPA.addAttribute("value", "否");
            }
        }
    }

}