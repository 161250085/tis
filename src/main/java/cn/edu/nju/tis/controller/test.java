package cn.edu.nju.tis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")

public class test {
//    @Autowired
//    InformationItemRepository informationItemRepository;
//    @Autowired
//    COAInformationItemRepository coaInformationItemRepository;
//    @Autowired
//    COARepository coaRepository;
//    @Autowired
//    COAService coaService;
//    @Autowired
//    ElementExtractionService elementExtractionService;
//    @RequestMapping(value = "/test1", method = RequestMethod.GET)
//    public void test1(@RequestParam(value = "name") String name,@RequestParam(value = "filePath") String filePath) throws IOException {
//        elementExtractionService.downloadXML(name,filePath);
//    }
//    @RequestMapping(value = "/test2", method = RequestMethod.GET)
//    public void test2(@RequestParam(value = "itemId") Integer itemId){
//        informationItemRepository.deleteById(itemId);
//    }
//    @RequestMapping(value = "/test3", method = RequestMethod.GET)
//    public void test3(@RequestParam(value = "coaId") Integer coaId){
//        coaRepository.deleteById(coaId);
//    }
//    @RequestMapping(value = "/test4", method = RequestMethod.GET)
//    public void test4(){
//        coaInformationItemRepository.save(new COAInformationItem(15,1));
//    }

}
