package cn.edu.nju.tis.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {

    //从一串字符串中得到所有的满足正则表达式的日期字符串，将它们存在list中
    public static void getDate(String str, List<String> list , String regx){
        String date = getSingleDate(str,regx);
        if(date!=null&&!date.equals("")){
            list.add(date);
            int index = str.indexOf(date)+date.length();
            String next = str.substring(index);
            getDate(next,list,regx);
        }
    }

    //从一串字符串中找出符合正则表达式的单个子串（时间）
    public static String getSingleDate(String str,String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(str);
        String datestr = "";
        if (m.find()) {
            datestr = m.group();
        }
        return datestr;
    }

    //除去列表中的重复元素，只保留第一个.通过set进行筛选
    public static void removeDuplicateWithOrder(List<String> list)   {
        Set<String> set = new HashSet<>();
        List<String> result = new  ArrayList<>();
        for (String element: list) {
            if (set.add(element))
                result.add(element);
        }
        list.clear();
        list.addAll(result);
    }

    //得到字符串里满足第一个正则符，但是不满足第二个的所有子串
    public static void getDaterTr(String str,ArrayList<String> list ,String regx1,String regx2){

        ArrayList<String> tmp_list  = new ArrayList<>();
        getDate(str,tmp_list,regx2);
        String tmp_str = "";
        if(tmp_list.size()>0){
            for (String value : tmp_list) {
                tmp_str = str.replace(value, "");
            }
        }
        getDate(tmp_str,list,regx1);
    }

    //得到对应的日期
    public static String getRightDate(String str,String[] keyWords,int fh,String regex){
        String datef = "";
        String dateh = "";
        int disf = 1000000;
        int dish = 1000000;
        for (String keyWord : keyWords) {
            if (str.contains(keyWord)) {
                ArrayList<String> a = new ArrayList<>();
                getDate(str, a, regex);
                for(String allDate: a) {
                    int start = str.indexOf(allDate);
                    int end = start + allDate.length() - 1;
                    if (end < str.indexOf(keyWord)) {
                        if (!(str.substring(end, str.indexOf(keyWord)).contains("，"))) {
                            return allDate;
                        }
                        if (disf > str.indexOf(keyWord) - end) {
                            disf = str.indexOf(keyWord) - end;
                            datef = allDate;
                        }
                    }
                    if (start > str.indexOf(keyWord)) {
                        if (!(str.substring(str.indexOf(keyWord), start).contains("，")) && fh != 0) {
                            return allDate;
                        }
                        if (dish > (start - str.indexOf(keyWord))) {
                            dish = start - str.indexOf(keyWord);
                            dateh = allDate;
                        }
                    }
                }
                break;
            }
        }
        if(fh == 0){
            return datef;
        }
        if(fh == 1){

            return dish>disf?datef:dateh;
        }
        return dateh;
    }

    //得到字符串中所有数字
    public static List<String> getNumbers(String content) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("[0-9]+([.][0-9]+)?");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            result.add(matcher.group(0));
        }
        return result;
    }

    //找到字符串中所有符合的关键词
    public static ArrayList<String> ifContain(String str,String[] keys){ //找到该字符串中包含哪些关键字
        ArrayList<String> re = new ArrayList<>();
        for(String key: keys){
            if (str.contains(key)){
                re.add(key);
            }
        }
        return re;
    }

    public static String dateFormat(String date){
        String re;
        re = new String(date.replace('年', '-'));
//	    	    	date = new String(date.replace('月', '-'));
//	    	    	date = new String(date.replace('日', '-'));
//	    	    	date = new String(date.replace('时', ':'));
//	    	    	date = new String(date.replace('分', ' '));
//	    	    	if(date.indexOf(date.length()-1)=='-'||date.indexOf(date.length()-1)==':'){
//	    	    		date = date.substring(0,date.length()-2);
//	    	    	}
        return re;
    }
}
