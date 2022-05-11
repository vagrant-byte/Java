package com.example.searchengines.controllers;

import com.example.searchengines.searcher.DocSearcher;
import com.example.searchengines.searcher.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseBody
public class DescSearcherController {

    //查找类
    private static DocSearcher searcher=new DocSearcher();
    //构造Json格式
    private ObjectMapper objectMapper=new ObjectMapper();

    @RequestMapping("/searcher")
    public String search(@RequestParam("query") String query) throws JsonProcessingException {
       List<Result> results=searcher.search(query);
       return objectMapper.writeValueAsString(results);
    }
}
