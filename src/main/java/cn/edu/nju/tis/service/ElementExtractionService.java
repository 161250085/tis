package cn.edu.nju.tis.service;

import cn.edu.nju.tis.bean.ResultMessageBean;

public interface ElementExtractionService {
    /**
     *
     *
     * @param xml_url
     * @param destination_url
     * @return ResultMessageBean.FAILURE, "要素提取失败!"
     * ResultMessageBean.SUCCESS,"要素提取成功!"
     */
    ResultMessageBean element_extraction(String xml_url, String destination_url);
}
