package com.example.searchengines.searcher;

import lombok.Data;

@Data
public class Result {
    private String title;
    private String url;
    //正文的摘要
    private String desc;
}
