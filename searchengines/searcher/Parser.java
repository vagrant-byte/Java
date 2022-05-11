package com.example.searchengines.searcher;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parser {
    private static final String INPUT_PATH = "D:/doc_searcher_index/docs/api";
    private Index index=new Index();

    public static void main(String[] args) throws InterruptedException {
        Parser parser=new Parser();
        parser.runByThread();
    }
    public void runByThread() throws InterruptedException {
        System.out.println("开始解析");
        long beg=System.currentTimeMillis();
        ArrayList<File> result=new ArrayList<>();
        enumFile(INPUT_PATH,result);
        //判断线程是否执行完毕
        CountDownLatch latch=new CountDownLatch(result.size());
        //使用多线程进行文件的解析
        ExecutorService executorService= Executors.newFixedThreadPool(4);
        for (File file:result) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("解析:"+file.getAbsolutePath());
                    parseHTML(file);
                    latch.countDown();
                }
            });
        }
        latch.await();
        //所有任务结束后销毁线程池
        executorService.shutdown();
        //保存索引
        index.save();
        long end=System.currentTimeMillis();
        System.out.println("保存索引完成,所用时间为: "+(end-beg)+"ms");
    }
    //未优化的解析文件操作，比较消耗时间，因此使用多线程进行文件的解析
    private void run() {
        //1.根据指定路劲，枚举路劲中所有文件
        ArrayList<File> result=new ArrayList<>();
        enumFile(INPUT_PATH,result);
        for (File file:result) {
            System.out.println("解析 "+file.getAbsolutePath());
            //2.读取文件并解析html
            parseHTML(file);
        }
        System.out.println("解析完成");
        //保存到文件中
        index.save();
    }

    private void parseHTML(File file) {
        //1.解析HTML标题
        String title=parseTitle(file);
        //2.解析HTML对应的URL
        String url=parseUrl(file);
        //3.解析HTML对应的正文
        String content=parseContentByRegular(file);
        //4.将其添加到索引中
        index.addDoc(title,url,content);
    }

    private String parseContentByRegular(File file) {
        //1.将文件读到String中
        String content=readFile(file);
        //2.替换掉script标签
        content=content.replaceAll("<script.*?>(.*?)</script>"," ");
        //3.替换掉html标签
        content=content.replaceAll("<.*?>"," ");
        //4.替换掉多个空格
        content=content.replaceAll("\\s+"," ");
        return content;
    }

    private String readFile(File file) {
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder=new StringBuilder();
            while (true) {
                int ret=bufferedReader.read();
                if(ret==-1) {
                    break;
                }
                char c= (char) ret;
                if(c=='\n'||c=='\r') {
                    c=' ';
                }
                stringBuilder.append(c);
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String parseContent(File file) {
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(file))) {
            //读取文件按字符读取
            boolean isContent=true;
            StringBuilder stringBuilder=new StringBuilder();
            while (true) {
                int ret=bufferedReader.read();
                if(ret==-1) {
                    break;
                }
                char c= (char) ret;
                if(isContent) {
                    if(c=='<') {
                        isContent=false;
                        continue;
                    }
                    if(c=='\n'||c=='\r') {
                        c=' ';
                    }
                    stringBuilder.append(c);
                } else {
                    if(c=='>') {
                        isContent=true;
                    }
                }
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String parseUrl(File file) {
        String s1=file.getAbsolutePath().substring(INPUT_PATH.length());
        String s2="https://docs.oracle.com/javase/8/docs/api/";
        return s2+s1;
    }

    private String parseTitle(File file) {
        String title=file.getName().substring(0,file.getName().length()-".html".length());
        return title;
    }

    private void enumFile(String inputPath, ArrayList<File> result) {
        //将目录字符串转为File类
        File rootFile=new File(inputPath);
        File[] files=rootFile.listFiles();
        for (File f:files) {
            if(f.isDirectory()) {
                //当前是一个目录，递归往下继续读取
                enumFile(f.getAbsolutePath(),result);
            } else {
                if(f.getAbsolutePath().endsWith(".html")) {
                    result.add(f);
                }
            }
        }
    }
}
