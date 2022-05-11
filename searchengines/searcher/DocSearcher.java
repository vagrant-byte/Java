package com.example.searchengines.searcher;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class DocSearcher {
    private static final String STOP_WORD="D:\\doc_searcher_index\\stop_word.txt";
    private HashSet<String> stopWordDict=new HashSet<>();
    private Index index=new Index();
    public DocSearcher() {
        index.load();
        loadStopWord();
    }

    private void loadStopWord() {
        try(InputStream inputStream=new FileInputStream(STOP_WORD)){
            //将停用词加入到hashset中 按行读取
            Scanner scanner=new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String line=scanner.nextLine();
                stopWordDict.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Result> search(String query) {
        //1.分词
        List<Term> oldTerm=ToAnalysis.parse(query).getTerms();
        List<Term> terms= new ArrayList<>();
        for (Term term:oldTerm) {
            if(stopWordDict.contains(term)) {
                continue;
            }
            terms.add(term);
        }
        //2.针对分词结果查倒排
        //存放所有分词的倒排
        ArrayList<ArrayList<Weight>> allResult=new ArrayList<>();
        for (Term term:terms) {
            String word=term.getName();
            ArrayList<Weight> list=index.getInverted(word);
            if(list==null) {
                continue;
            }
            allResult.add(list);
        }
        ArrayList<Weight> allTokenResult=mergeResult(allResult);
        //3.针对结果进行排序按权重降序排列
        allTokenResult.sort(new Comparator<Weight>() {
            @Override
            public int compare(Weight o1, Weight o2) {
                return o2.getWeight()-o1.getWeight();
            }
        });
        //4.返回结果
        List<Result> results=new ArrayList<>();
        for (Weight weight:allTokenResult) {
            DocInfo docInfo=index.getDocInfo(weight.getDocId());
            Result result=new Result();
            result.setTitle(docInfo.getTitle());
            result.setUrl(docInfo.getUrl());
            result.setDesc(GenDesc(docInfo.getContent(),terms));
            results.add(result);
        }
        return results;
    }
//    描述一个元素在二维数组中的位置
    static class Pos {
        public int row;
        public int col;
        public Pos(int row,int col) {
            this.row=row;
            this.col=col;
        }
    }
    private ArrayList<Weight> mergeResult(ArrayList<ArrayList<Weight>> allResult) {
        //1.针对每一行进行排序按照DocId进行升序排序
        for (ArrayList<Weight> list:allResult) {
            list.sort(new Comparator<Weight>() {
                @Override
                public int compare(Weight o1, Weight o2) {
                    return o1.getDocId()-o2.getDocId();
                }
            });
        }
        //2.创建优先级队列，进行合并
        //target存放合并后结果
        ArrayList<Weight> target=new ArrayList<>();
        //2.1创建优先级队列，并指定比价规则
        PriorityQueue<Pos> queue=new PriorityQueue<>(new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                //根据Pos值找到对应元素，按照DocId升序排序
                Weight w1=allResult.get(o1.row).get(o1.col);
                Weight w2=allResult.get(o2.row).get(o2.col);
                return w1.getDocId()-w2.getDocId();
            }
        });
        //2.2初始化队列将每一行的第一个元素放入队列中
        for (int i = 0; i <allResult.size() ; i++) {
            queue.offer(new Pos(i,0));
        }
        //2.3循环取队首元素
        while (!queue.isEmpty()) {
            Pos minPos=queue.poll();
            Weight curWeight=allResult.get(minPos.row).get(minPos.col);
            //看当前取到的Weight是否和前一个target中的docId是否相同
            if(target.size()>0) {
                Weight lastWeight=target.get(target.size()-1);
                if(lastWeight.getDocId()==curWeight.getDocId()) {
                    //相同文档合并权重
                    lastWeight.setWeight(lastWeight.getWeight()+curWeight.getWeight());
                } else {
                    //不相同 直接把curWeight插入到target末尾
                    target.add(curWeight);
                }
            } else {
                target.add(curWeight);
            }
            //2.5把当前元素处理完后，把对应元素光标往后移动取当前行的下一个元素
            Pos newPos=new Pos(minPos.row,minPos.col+1);
            if(newPos.col>=allResult.get(newPos.row).size()) {
                //光标到达末尾
                continue;
            }
            queue.offer(newPos);
        }
        return target;
    }

    private String GenDesc(String content, List<Term> terms) {
        //找出word在content中出现第一次的位置
        int firsPos=-1;
        for (Term term:terms) {
            String firsWord=term.getName();
            content=content.toLowerCase().replaceAll("\\b"+firsWord+"\\b"," "+firsWord+" ");
            firsPos=content.indexOf(" "+firsWord+" ");
            if(firsPos>0) {
                break;
            }
        }
        //分词不存在直接返回空
        if(firsPos==-1) {
            //所有发分词结果都不在正文中存在，直接返回正文前160个字符
            return content.substring(0,160)+"...";
        }
        //进行截断 从firsPos往前找90个字符作为描述的开始，然后从开始位置往后130个字符作为描述的结束
        String desc="";
        int descBeg=firsPos<90?0:firsPos-90;
        if(descBeg+130>content.length()) {
            desc=content.substring(descBeg,content.length());
        } else {
            desc=content.substring(descBeg,descBeg+130)+"...";
        }
//        实现标红逻辑，给关键词加上特殊标记
        for (Term term:terms) {
            String word=term.getName();
//            使用(?i)忽略大小写替换
            desc=desc.replaceAll("(?i) "+word+" ","<i>"+word+"</i>");
        }
        return desc;
    }
}
