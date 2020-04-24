package cn.edu.nju.tis.utils;

import java.io.RandomAccessFile;

public class MethodUtil {
    /**
     * @Author cruck
     * @Description //修改方法，在service和serviceImpl里添加方法
     * @Date 09:56 2020/4/23
     * @Param [type, itemName, code]
     **/
    public  static void addMethod(String type, String itemName, String code) throws Exception {
        ChineseCharToEn chineseCharToEn = ChineseCharToEn.getInstance();
        //这边不考虑信息项首字母重复情况
        String methodDeclaration = getMethodDeclaration(code);
        String methodName = chineseCharToEn.getAllFirstLetter(itemName);
        switch (type) {
            case "CIVIL":
                insertMethod(methodDeclaration + ";\n", "src/main/java/cn/edu/nju/tis/service/CivilExtractionService.java");
                insertMethod(code + "\n" + "}", "src/main/java/cn/edu/nju/tis/serviceImpl/CivilExtractionServiceImpl.java");
                return;
            case "CRIMINAL":
                insertMethod(methodDeclaration + ";\n", "src/main/java/cn/edu/nju/tis/service/CriminalExtractionService.java");
                insertMethod(code + "\n" + "}", "src/main/java/cn/edu/nju/tis/serviceImpl/CriminalExtractionServiceImpl.java");
                return;
            case "ADMINISTRATIVE":
                insertMethod(methodDeclaration + ";\n", "src/main/java/cn/edu/nju/tis/service/AdministrativeExtractionService.java");
                insertMethod(code + "\n" + "}", "src/main/java/cn/edu/nju/tis/serviceImpl/AdministrativeExtractionServiceImpl.java");
        }
    }

    //插入方法，将最后一个大括号去掉，最后再换行补上大括号
    private static void insertMethod(String code, String path) throws Exception {
      if(path!=null){
          reWrite(path, code);
      }
    }

    //得到代码里的方法声明
    private static String getMethodDeclaration(String code) {
        int index = code.indexOf('{');
        return code.substring(0,index);
    }

    //换行之后再写
    private static void writeLine(String filepath, String string) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(filepath, "rw");
        long len = raf.length();
        long start = raf.getFilePointer();
        long end = start + len - 1;
        byte[] buf = new byte[1];
        raf.seek(end);
        raf.read(buf, 0, 1);
        //结尾如果是空行就直接写，不然换行写
        if (buf[0] == '\n')
            raf.writeBytes(string);
        else
            raf.writeBytes("\r\n"+string);
        raf.close();
    }

    //末尾的空格空行全都去掉，可以把大括号替换
    private static void reWrite(String filepath, String string)
            throws Exception {

        RandomAccessFile raf = new RandomAccessFile(filepath, "rw");
        long len = raf.length();
        long start = raf.getFilePointer();
        long tmpEnd = start + len-1;
        raf.seek(tmpEnd);
        byte[] buf = new byte[1];
        //有空格和换行不管，直接找到最后一个大括号
        while (tmpEnd > start) {
            raf.read(buf, 0, 1);
            if(Character.isSpaceChar(buf[0])||buf[0] == '\n' ){
                tmpEnd--;
                raf.seek(tmpEnd);
            }else{
                raf.setLength(tmpEnd - start);
                break;
            }

        }
        raf.close();
        writeLine(filepath, string);

    }
}