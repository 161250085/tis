package cn.edu.nju.tis.controller;
import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.service.ElementExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElementExtractionController {
@Autowired
    private ElementExtractionService elementExtractionService;
//将粗力度的xml按要素表进行要素抽取，保存到对应位置

    @GetMapping("/manager/extraction")
    public ResultMessageBean element_extraction(@RequestParam String xml_url, @RequestParam String destination_xml_url){
        return elementExtractionService.element_extraction(xml_url, destination_xml_url);
    }


}
