package com.example.demo.Controller;

import com.example.demo.dto.DetailsDto;
import com.example.demo.model.Companies;
import com.example.demo.model.Test;
import com.example.demo.service.impl.TestServiceImpl;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestServiceImpl testService;

    String jsonArrayStr = "[{\"name\":\"John\",\"age\":30},{\"name\":\"Jane\",\"age\":25}]";







    @GetMapping("/test-data")
    public void getTestData(){
        testService.showAllTestItems();
    }

    @PostMapping("/save-data")
    public void saveTestData(@RequestParam Test test){
        testService.save(test);
    }

    @GetMapping("/companies-data")
    public ResponseEntity<List<DetailsDto>> getCompaniesData() {
        List<DetailsDto> detailsDtos = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayStr) ;

             detailsDtos = IntStream.range(0, jsonArray.length()).mapToObj(index ->{
                try {
                    return jsonArray.getJSONObject(index);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }).filter(jsonObject -> {
                try {
                    return "John".equals(jsonObject.getString("name"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }).map(jsonObject -> {
                DetailsDto detailsDto = new DetailsDto();
                try {
                    detailsDto.setName(jsonObject.getString("name"));

                    detailsDto.setAge(jsonObject.getInt("age"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                return detailsDto;
            }).collect(Collectors.toList());

        }catch (JSONException e) {
            throw new RuntimeException(e);
        }

        //testService.showAllCompaniesItem();
        return new ResponseEntity<>(detailsDtos, HttpStatus.ACCEPTED);
    }

    @GetMapping("/companies-name")
    public List<Companies> getCompaniesDataByName(){
       return testService.showCompaniesByName("Wetpaint");
    }

}
