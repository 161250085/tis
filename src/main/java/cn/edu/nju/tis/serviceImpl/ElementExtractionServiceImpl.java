package cn.edu.nju.tis.serviceImpl;

import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.service.infoExtrationService.ElementExtractionService;
import cn.edu.nju.tis.utils.ImportXMLUtil;
import org.springframework.stereotype.Service;

@Service
public class ElementExtractionServiceImpl implements ElementExtractionService {

    /**
     * @Author cruck
     * @Description //读取粗粒度的xml文书，并根据要素表筛选出要素，并打包成xml文件保存到对应的地址
     * @Date 14:03 2020/4/12
     * @Param [xml_address, destination_address]
     * @return cn.edu.nju.tis.bean.ResultMessageBean
     **/
    @Override
    public ResultMessageBean element_extraction(String xml_address, String destination_address) {
        if(!ImportXMLUtil.isValidXML(xml_address)){
            return new ResultMessageBean("-1", ResultMessageBean.ERROR);
        }
        else{
            return null;
        }

    }
}
