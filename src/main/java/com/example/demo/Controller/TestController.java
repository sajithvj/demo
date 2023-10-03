package com.example.demo.Controller;

import com.example.demo.model.Companies;
import com.example.demo.model.Test;
import com.example.demo.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestServiceImpl testService;


    @GetMapping("/test-data")
    public void getTestData(){
        testService.showAllTestItems();
    }

    @PostMapping("/save-data")
    public void saveTestData(@RequestParam Test test){
        testService.save(test);
    }

    @GetMapping("/companies-data")
    public void getCompaniesData(){
        testService.showAllCompaniesItem();
    }

    @GetMapping("/companies-name")
    public List<Companies> getCompaniesDataByName(){
       return testService.showCompaniesByName("Wetpaint");
    }

}
