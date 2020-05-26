package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.model.CauseOfAction;
import cn.edu.nju.tis.model.InformationItem;
import cn.edu.nju.tis.repository.COARepository;
import cn.edu.nju.tis.repository.InformationItemRepository;
import cn.edu.nju.tis.service.ElementExtractionService;
import cn.edu.nju.tis.utils.ChineseCharToEn;
import cn.edu.nju.tis.utils.ResultMessageUtil;
import cn.edu.nju.tis.utils.XmlUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ElementExtractionServiceImpl implements ElementExtractionService {
    @Autowired
    InformationItemRepository informationItemRepository;
    @Autowired
    COARepository coaRepository;

    //将本地xml上传到upload文件夹中
    @Override
    public ResultMessageBean<Object> uploadXML(MultipartFile[] files) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, DocumentException, InvocationTargetException, ClassNotFoundException {
        MultipartFile[] newFiles = new MultipartFile[files.length];
        for(int i=0; i<files.length; i++){
            MultipartFile file = files[i];
            if(!XmlUtil.isValidXML(file.getOriginalFilename())){
                return ResultMessageUtil.error(-1,"非xml文件");
            }
            String newFileName = new Date().getTime() + "&" + file.getOriginalFilename();
            String path = "src/main/resources/uploadXML/" + newFileName;
            File newFile = new File(path);
            file.transferTo(newFile);
            itemsExtraction(newFileName);
            MultipartFile tmp_multi = getMultipartFile("src/main/resources/outputXML/"+newFileName);
            newFiles[i] = tmp_multi;
        }
        return ResultMessageUtil.success(newFiles);
    }

    //根据文件名和本地地址下载抽取完成的xml
//    @Override
//    public ResultMessageBean<Object> downloadXML(String fileName, String filePath) throws IOException {
//        filePath = filePath+"/"+fileName;
//        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/outputXML/"+fileName), StandardCharsets.UTF_8));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
//        String str;
//        while ((str = br.readLine()) != null) {
//            writer.write(str);
//            writer.write("\r\n");
//        }
//
//        br.close();
//        writer.close();
//        return ResultMessageUtil.success();
//    }


    /**
     * @Author cruck
     * @Description //根据案由名称，通过反射调用信息项抽取方法，并且将抽取的xml存放到outputXML上
     * @Date 20:53 2020/4/24
     * @Param [coaName]
     * @return java.lang.String
     **/
    @Override
    public ResultMessageBean<Object> itemsExtraction(String fileName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, DocumentException, IOException {
       String path = "src/main/resources/uploadXML/"+fileName;
       String coaName = getCOAName(path);
       if(coaName.equals("案由不存在")){
           return ResultMessageUtil.error(-1,"案由不存在");
       }

        CauseOfAction coa = coaRepository.findCauseOfActionByName(coaName);
        List<InformationItem> informationItems = informationItemRepository.findInformationItemsByCOAId(coa.getId());

        //先新建xml
        SAXReader reader = new SAXReader();
        File file = new File(path);
        Document document = reader.read(file);
        Element root = DocumentHelper.createElement("write");
        Document new_document = DocumentHelper.createDocument(root);
        //initXML(document,root);
        Element newRoot;
        newRoot = root.addElement(ChineseCharToEn.getInstance().getAllFirstLetter(coaName)+"YSTQ").addAttribute("nameCN", coaName+"要素提取");

        //要素抽取
        invokeMethod(coa, informationItems, document, newRoot);
        OutputFormat format = new OutputFormat("    ",true);
        format.setEncoding("UTF-8");
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/main/resources/outputXML/"+fileName),format);
        xmlWriter.write(new_document);
        xmlWriter.close();

        return ResultMessageUtil.success();
    }

    private void invokeMethod(CauseOfAction coa, List<InformationItem> informationItems, Document document, Element newRoot) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        switch (coa.getType()) {
            case "CIVIL":
                for (InformationItem item : informationItems) {
                    String methodName = ChineseCharToEn.getInstance().getAllFirstLetter(item.getName());
                    Class<?> clazz = Class.forName("cn.edu.nju.tis.serviceImpl.CivilExtractionServiceImpl");
                    Method method = clazz.getDeclaredMethod(methodName, Document.class, Element.class);
                    method.invoke(clazz.newInstance(),document, newRoot);
                }
                break;
            case "ADMINISTRATIVE":
                for (InformationItem item : informationItems) {
                    String methodName = ChineseCharToEn.getInstance().getAllFirstLetter(item.getName());
                    Class<?> clazz = Class.forName("cn.edu.nju.tis.serviceImpl.AdministrativeExtractionServiceImpl");
                    Method method = clazz.getDeclaredMethod(methodName, Document.class, Element.class);
                    method.invoke(clazz.newInstance(),document, newRoot);

                }
                break;
            case "CRIMINAL":
                for (InformationItem item : informationItems) {
                    String methodName = ChineseCharToEn.getInstance().getAllFirstLetter(item.getName());
                    Class<?> clazz = Class.forName("cn.edu.nju.tis.serviceImpl.CriminalExtractionServiceImpl");
                    Method method = clazz.getDeclaredMethod(methodName, Document.class, Element.class);
                    method.invoke(clazz.newInstance(),document, newRoot);
                }
                break;
        }
    }

//    public static void initXML(Document document,Element newroot){//将原文加进去
//        Element root = document.getRootElement();
//        newroot.add((Element)root.clone());
//    }

    public static Document load(String filename) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(new File(filename));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }

    //将文件地址转换成MultipartFile
    private MultipartFile getMultipartFile(String  filePath) throws IOException {
        File file = new File(filePath);

        FileInputStream input = new FileInputStream(file);

        return new MockMultipartFile("file", file.getName(), "text/plain", input);
    }

    private String getCOAName(String filePath){
        Document dom = load(filePath);
        Element root = dom.getRootElement();
        List<Element> skills = root.elements();
        for (Element skill : skills) {
            for(Iterator<Element> it = skill.elementIterator(); it.hasNext();){
                Element e = it.next();
                if(e.attribute("nameCN").getValue().equals("案由")) {
                    return e.attribute("value").getValue();
                }
            }
        }
        return "案由不存在";
    }
}
