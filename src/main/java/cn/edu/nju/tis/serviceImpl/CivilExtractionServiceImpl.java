package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.service.CivilExtractionService;
import cn.edu.nju.tis.utils.TimeUtil;
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
            System.out.println(ifcz+"VVVVVVVVVVVVVVVVVVVVVVVVVVv");
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



}