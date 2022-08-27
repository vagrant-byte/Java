package com.example.ontheway.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
// 分为6个区域 3个备份
@Document(indexName = "discusspost",type = "_doc",shards = 6,replicas = 3)
@Data
public class DiscussPost {
    @Id
    private int id;

    @Field(type = FieldType.Integer)
    private int userId;

    //analyzer存储的解析器，searchAnalyzer搜素的解析器
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer ="ik_smart" )
    private String title;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer ="ik_smart" )
    private String content;

    @Field(type = FieldType.Integer)
    private int type;

    @Field(type = FieldType.Integer)
    private int status;

    @Field(type = FieldType.Date)
    private Date createTime;

    @Field(type = FieldType.Integer)
    private int commentCount;

    @Field(type = FieldType.Double)
    private double score;
}

