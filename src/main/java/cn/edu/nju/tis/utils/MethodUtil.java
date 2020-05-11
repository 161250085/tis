package cn.edu.nju.tis.utils;

import org.springframework.util.ClassUtils;

import java.io.*;
import java.util.Objects;

public class MethodUtil {
    //保存了
    private static final String CIVILSERVICE = "src/main/java/cn/edu/nju/tis/service/CivilExtractionService.java";
    private static final String CIVILSERVICEIMPL ="src/main/java/cn/edu/nju/tis/serviceImpl/CivilExtractionServiceImpl.java";
    private static final String ADMINISTRACTIVESERVICE ="src/main/java/cn/edu/nju/tis/service/AdministrativeExtractionService.java";
    private static final String ADMINISTRACTIVESERVICEIMPL = "src/main/java/cn/edu/nju/tis/serviceImpl/AdministrativeExtractionServiceImpl.java";
    private static final String CRIMINALSERVICE = "src/main/java/cn/edu/nju/tis/service/CriminalExtractionService.java";
    private static final String CRIMINALSERVICEIMPL = "src/main/java/cn/edu/nju/tis/serviceImpl/CriminalExtractionServiceImpl.java";
    /**
     * @Author cruck
     * @Description //修改方法，在service和serviceImpl里添加方法
     * @Date 09:56 2020/4/23
     * @Param [type, itemName, code]
     **/
    public  static void addMethod(String type, String code) throws Exception {
        String methodDeclaration = getMethodDeclaration(code);
        String localAddress = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath();
        int length = localAddress.length();
        String realAddress = localAddress.substring(0,length-15);

        switch (type) {
            case "CIVIL":
                insertMethod(methodDeclaration + ";\n}", realAddress+CIVILSERVICE);
                insertMethod("@Override\n"+code + "\n" + "}", realAddress+CIVILSERVICEIMPL);
                return;
            case "CRIMINAL":
                insertMethod(methodDeclaration + ";\n}", realAddress+CRIMINALSERVICE);
                insertMethod("@Override\n"+code + "\n" + "}", realAddress+CRIMINALSERVICEIMPL);
                return;
            case "ADMINISTRATIVE":
                insertMethod(methodDeclaration + ";\n}", realAddress+ADMINISTRACTIVESERVICE);
                insertMethod("@Override\n"+code + "\n" + "}", realAddress+ADMINISTRACTIVESERVICEIMPL);
        }
    }

    public static void addImports(String type, String importPackages) throws IOException {
        String localAddress = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath();
        int length = localAddress.length();
        String realAddress = localAddress.substring(0,length-15);

        switch (type) {
            case "CIVIL":
                insertImports(  realAddress+CIVILSERVICE, importPackages);
                insertImports( realAddress+CIVILSERVICEIMPL, importPackages);
                return;
            case "CRIMINAL":
                insertImports(realAddress+CRIMINALSERVICE ,importPackages);
                insertImports(realAddress+CRIMINALSERVICEIMPL, importPackages);
                return;
            case "ADMINISTRATIVE":
                insertImports(realAddress+ADMINISTRACTIVESERVICE, importPackages);
                insertImports(realAddress+ADMINISTRACTIVESERVICEIMPL, importPackages);
        }


}

    //插入方法，将最后一个大括号去掉，最后再换行补上大括号
    private static void insertMethod(String code, String path) throws Exception {
        if(path!=null){
            reWrite(path, code);
        }
    }

    //得到代码里的方法声明(去掉前后空格)
    private static String getMethodDeclaration(String code) {
        int index = code.indexOf('{');
        String str = code.substring(0,index);
        str = str.replace("public","");
        return str.trim();
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

    //从第一行后插入imports
    //机制：读到第一个";"后，先换行，然后插入
    private static void insertImports(String filepath,String imports) throws IOException {
        imports = "\n"+imports;
        File tmp = File.createTempFile("tmp", null);
        tmp.deleteOnExit();
        try {
            RandomAccessFile raf = new RandomAccessFile(filepath, "rw");
            long start = raf.getFilePointer();
            byte[] buf = new byte[1];
            raf.seek(start);
            raf.read(buf, 0, 1);
            while(buf[0] != ';'){
                start += 1;
                raf.seek(start);
                raf.read(buf,0,1);
            }
            start+=1;
            FileOutputStream tmpOut = new FileOutputStream(tmp);
            FileInputStream tmpIn = new FileInputStream(tmp);
            raf.seek(start);//首先的话是0
            byte[] buffer = new byte[64];
            int hasRead;
            while ((hasRead = raf.read(buffer)) > 0) {
                //把原有内容读入临时文件
                tmpOut.write(buffer, 0, hasRead);

            }
            raf.seek(start);
            raf.write(imports.getBytes());
            //追加临时文件的内容
            while ((hasRead = tmpIn.read(buffer)) > 0) {
                raf.write(buffer, 0, hasRead);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

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