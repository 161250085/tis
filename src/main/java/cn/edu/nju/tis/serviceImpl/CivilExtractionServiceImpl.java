package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.service.CivilExtractionService;
import cn.edu.nju.tis.utils.TimeUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            String[] qwStrarray = qwStr.split("[。，；]");
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
            String[] qwStrarray = qwStr.split("[。，；]");
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
            String[] qwStrarray = qwStr.split("[。，；,]");
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
            String[] qwStrarraysplit = qwStr.split("[。，；]");
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

}