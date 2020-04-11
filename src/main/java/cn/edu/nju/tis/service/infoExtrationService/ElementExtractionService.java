package cn.edu.nju.tis.service.infoExtrationService;

import cn.edu.nju.tis.bean.ResultMessageBean;

public interface ElementExtractionService {
    /**
     * 管理员修改用户密码
     *
     * @param xml_address
     * @param destination_address
     * @return ResultMessageBean.FAILURE, "要素提取失败!"
     * ResultMessageBean.SUCCESS,"要素提取成功!"
     */
    ResultMessageBean element_extraction(String xml_address, String destination_address);
}
