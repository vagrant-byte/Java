package com.example.searchengines.searcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//构建索引数据结构
public class Index {
    private static final String INDEX_PATH="D:/doc_searcher_index/";
//    正排索引,下标对应docId
    private ArrayList<DocInfo> forwardIndex=new ArrayList<DocInfo>();
//    倒排索引 键为词，值为其权重
    private HashMap<String,ArrayList<Weight>> invertedIndex=new HashMap<>();
    private ObjectMapper objectMapper=new ObjectMapper();

    //1.在正排索引中查询文档信息
    public DocInfo getDocInfo(int docId) {
        return forwardIndex.get(docId);
    }
    //2.给定一个词，在倒排索引中查找与那些文档关联
    public ArrayList<Weight> getInverted(String term) {
        return invertedIndex.get(term);
    }
    //3.往索引中新增一个文档
    public void addDoc(String title,String url,String content) {
        //正排索引插入
        DocInfo docInfo=buildForward(title,url,content);
        //往倒排索引中插入文档
        buildIndex(docInfo);
    }
    private synchronized DocInfo buildForward(String title,String url,String content) {
        DocInfo docInfo=new DocInfo();
        docInfo.setDocId(forwardIndex.size());
        docInfo.setTitle(title);
        docInfo.setUrl(url);
        docInfo.setContent(content);
        //此处插入的DocId是正排数组的插入，其插入是在最后插入
        //往正排索引中插入文档
        forwardIndex.add(docInfo);
        return docInfo;
    }

    private void buildIndex(DocInfo docInfo) {
        class WordCnt {
            public int titleCount;
            public int contentCount;
            public WordCnt(int titleCount,int contentCount) {
                this.titleCount=titleCount;
                this.contentCount=contentCount;
            }
        }
        HashMap<String,WordCnt> wordCntMap=new HashMap<>();
        //1.对标题进行分词
        List<Term> terms= ToAnalysis.parse(docInfo.getTitle()).getTerms();
        //2.遍历分词结果，统计标题中每个词出现的次数
        for (Term term:terms) {
            //获取词
            String word=term.getName();
            //在表中查找是否存在其键
            WordCnt newWordCnt=wordCntMap.get(word);
            if(newWordCnt==null) {
                wordCntMap.put(word,new WordCnt(1,0));
            } else {
                newWordCnt.titleCount++;
            }
        }
        //3.对正文进行解析
        terms=ToAnalysis.parse(docInfo.getContent()).getTerms();
        //4.遍历分词结果，统计正文中每个词出的次数
        for (Term term:terms) {
            String word=term.getName();
            WordCnt wordCnt=wordCntMap.get(word);
            if(wordCnt==null) {
                wordCntMap.put(word,new WordCnt(0,1));
            } else {
                wordCnt.contentCount++;
            }
        }
        //5.把以上内容整理到一个Hash Map中
        for (Map.Entry<String,WordCnt> entry:wordCntMap.entrySet()) {
            synchronized (this) {
                List<Weight> invertedList=invertedIndex.get(entry.getKey());
                if(invertedList==null) {
                    //为空插入新的键值对
                    ArrayList<Weight> newInvertedList=new ArrayList<>();
                    Weight weight=new Weight();
                    weight.setDocId(docInfo.getDocId());
                    //计算权重
                    weight.setWeight(entry.getValue().titleCount*10+entry.getValue().contentCount);
                    newInvertedList.add(weight);
                    invertedIndex.put(entry.getKey(),newInvertedList);
                } else {
                    Weight weight=new Weight();
                    weight.setDocId(docInfo.getDocId());
                    weight.setWeight(entry.getValue().titleCount*10+entry.getValue().contentCount);
                    invertedList.add(weight);
                }
            }
        }
    }
    //4.保存索引到文件中
    public void save() {
        System.out.println("保存索引开始");
        //保存在当前目录下
        File indexPathFile=new File(INDEX_PATH);
        //目录为空进行创建
        if(!indexPathFile.exists()) {
            indexPathFile.mkdirs();
        }
        //保存正排索引 使用JSON格式进行保存
        File forwardIndexFile=new File(INDEX_PATH + "forward.dat");
        //保存倒排索引
        File invertedIndexFile=new File(INDEX_PATH + "inverted.dat");
        try {
            objectMapper.writeValue(forwardIndexFile,forwardIndex);
            objectMapper.writeValue(invertedIndexFile,invertedIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //5.加载索引到内存
    public void load() {
        long beg=System.currentTimeMillis();
        System.out.println("加载索引开始");
        File forwardIndexFile=new File(INDEX_PATH+"forward.dat");
        File invertedIndexFile=new File(INDEX_PATH+"inverted.dat");
        try {
            //读取对应文件中的内容并按照指定的格式进行解析
           forwardIndex=objectMapper.readValue(forwardIndexFile, new TypeReference<ArrayList<DocInfo>>() {
           });
           invertedIndex=objectMapper.readValue(invertedIndexFile, new TypeReference<HashMap<String, ArrayList<Weight>>>() {
           });
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end=System.currentTimeMillis();
        System.out.println("加载索引结束，消耗时间为: "+(end-beg)+"ms");
    }
}
