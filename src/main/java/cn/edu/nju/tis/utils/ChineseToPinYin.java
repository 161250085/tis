package cn.edu.nju.tis.utils;

import java.io.UnsupportedEncodingException;

public class ChineseToPinYin {
    private static class SingletonClassInstance {
        private static final ChineseToPinYin instance = new ChineseToPinYin();
    }
    public static ChineseToPinYin getInstance() {
        return SingletonClassInstance.instance;
    }

    private ChineseToPinYin() {

    }
    private final static int[] PinYin_location =
            {1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212,
                    3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925,
                    5249, 5590};

    private final static String[] FirstPinYin =
            {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "O",
                    "P", "Q", "R", "S", "T", "W", "X", "Y", "Z"};

    private final static String[] Encode ={"GB2312","UTF-8","ISO8859-1"};

    //得到单个汉字的拼音首字母
    public String getPinYinSingle(String word) {
        if(is_empty(word)) {
            return "";
        }
        //先进行编码转换
        word = encodeChange(word, Encode[0], Encode[2]);
        if(word.length()<=1){
            return encodeChange(word,Encode[2],Encode[0]);
        }
            int location_code = (word.charAt(0)-160) * 100 + (word.charAt(1)-160);
            if (location_code >= 1601 && location_code < 5591) {
                for (int j = 0; j<FirstPinYin.length; j++) {
                    if (location_code >= PinYin_location[j] && location_code < PinYin_location[j+1]) {
                        word = FirstPinYin[j];
                        break;
                    }
                }
            }
            return word;
    }
    //得到一个汉字字符串的拼音首字母
    public String getPinYin(String sentence) {
        //测试句子是否空
        if(is_empty(sentence)){
            return "";
        }
        StringBuffer result = new StringBuffer();
        for (int index = 0; index < sentence.length(); index++) {
            result.append(this.getPinYinSingle(sentence.substring(index,  index + 1)));
        }
        return result.toString();
    }
    //将汉字从GB2312（双字节编码）转换成ISO8859-1（单字节编码）
    private String encodeChange(String word, String input_encode, String output_enccode) {
        try {
            word = new String(word.getBytes(input_encode), output_enccode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return word;
    }
//验证字符串是否为空
    private boolean is_empty(String str){
        if(str==null){
            return true;
        }
        String real_str = str.trim();
        return real_str.equals("");
    }
}
