package cn.edu.nju.tis.controller;

import cn.edu.nju.tis.model.COAInformationItem;
import cn.edu.nju.tis.repository.COAInformationItemRepository;
import cn.edu.nju.tis.repository.COARepository;
import cn.edu.nju.tis.repository.InformationItemRepository;
import cn.edu.nju.tis.service.COAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")

public class test {
    @Autowired
    InformationItemRepository informationItemRepository;
    @Autowired
    COAInformationItemRepository coaInformationItemRepository;
    @Autowired
    COARepository coaRepository;
    @Autowired
    COAService coaService;
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1(@RequestParam(value = "coaId") Integer coaId){
        return coaInformationItemRepository.findOwnByCoaId(coaId).toString();
    }
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public void test2(@RequestParam(value = "itemId") Integer itemId){
        informationItemRepository.deleteById(itemId);
    }
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public void test3(@RequestParam(value = "coaId") Integer coaId){
        coaRepository.deleteById(coaId);
    }
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public void test4(){
        coaInformationItemRepository.save(new COAInformationItem(15,1));
    }
//    @RequestMapping(value = "/test5", method = RequestMethod.GET)
//    public ResultMessageBean<Object> test5() throws InterruptedException {
//        List<InformationItem> items = new ArrayList<>();
//        items.add(new InformationItem(15,"杀人干","shba","1234567890123456"));
//        items.add(new InformationItem(16,"谋杀犯","shba","1234567890123456"));
//        List<InformationItem> existedItems = new ArrayList<>();
//        existedItems.add(new InformationItem(3,"辣鸡嘴","sdas"));
//        coaService.modifyCOA("1234567890123456",1,"CIVIL","辣鸡嘴",items,existedItems);
//        return ResultMessageUtil.error(-1,"sad");
//    }
}
