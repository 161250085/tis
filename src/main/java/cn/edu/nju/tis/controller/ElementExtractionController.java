package cn.edu.nju.tis.controller;
import cn.edu.nju.tis.bean.ResultMessageBean;
import cn.edu.nju.tis.service.infoExtrationService.ElementExtractionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ElementExtractionController {
    private ElementExtractionService elementExtractionService;
//将粗力度的xml按要素表进行要素抽取，保存到对应位置
    @PostMapping("/manager/extraction")
    public @ResponseBody
    ResultMessageBean element_extraction(@RequestParam String xml_address, @RequestParam String destination_address){
        return elementExtractionService.element_extraction(xml_address, destination_address);
    }


}
