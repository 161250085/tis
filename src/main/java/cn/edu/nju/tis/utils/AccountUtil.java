package cn.edu.nju.tis.utils;

import java.util.Random;

public class AccountUtil {
    /**
     * @Author cruck
     * @Description 随机生成包含0-9和大小写字母的字符串,可以当成用户的唯一标志id
     * @Date 01:21 2020/4/17
     * @Param [length]
     * @return java.lang.String
     **/
    public static String randomAccount(int length) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            String charOrNum = (random.nextInt(6)+6) % 6 >=1 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                int temp = random.nextInt(2) % 2 == 0 ? 97 : 65;
                val.append((char) (random.nextInt(26) + temp));
            } else {
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }
}
