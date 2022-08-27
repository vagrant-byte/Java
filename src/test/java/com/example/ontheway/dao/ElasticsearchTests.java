package com.example.ontheway.dao;

import com.example.ontheway.OnthewayApplication;
import com.example.ontheway.dao.elsaticsearch.DiscussPostRepository;
import com.example.ontheway.entity.DiscussPost;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OnthewayApplication.class)
public class ElasticsearchTests {
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private DiscussPostRepository discussPostRepository;
    @Autowired
    private ElasticsearchTemplate template;

    //往es中插入数据一次插入一条数据
    @Test
    public void testInsert() {
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(4));
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(5));
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(6));
    }
    //删除数据
    @Test
    public void delete() {
        discussPostRepository.deleteById(5);
    }
    //一次插入多条数据
    @Test
    public void testInsertList() {
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(1,0,100));
    }

    //修改
    @Test
    public void testUpdate() {
        DiscussPost post=discussPostMapper.selectDiscussPostById(7);
        post.setTitle("最适合穷游的8大城市");
        post.setContent("1.重庆   花费600-1000   时间2-6天   景点多数免费，蕴含许多特色小吃，魔幻8D的重庆有着历史的厚重感，也有现代的潮流感" +
                "推荐景点：解放碑，洪崖洞，李子坝轻轨站，长江索道等" +
                "2，大理  花费400-1200   时间5-8天   大理洱海一圈美景众多，花五十元租个小电驴，可以在洱海玩一天，小普陀，喜洲都是洱海一圈的范围内" +
                "推荐景点:大理古城 洱海公园 花语牧场 崇颂寺三塔 南诏风情岛" +
                "3.青岛  花费500-1000 时间6-8天  婀娜多姿的海滨城市，去石老人浴场看看日出，领略大海的壮丽风景，再去吃小吃，100快钱两个人可以吃到撑" +
                "推荐景点：北九水  第一海水浴场 极地海洋世界  崂山 栈桥  " +
                "4.西北  花费:500-1200  时间5-8天 去盐湖看日出，打卡茶卡盐湖的小火车拍照，一生必去一次大西北" +
                "推荐景点:茶卡盐湖  青海湖  七彩丹霞  敦煌莫高窟" +
                "5.成都  花费600-1200  时间：3-4天  是穷游的首选之地，美食众多，比如龙抄手，豆花，担担面，20块钱能在路边的小摊吃到撑" +
                "推荐景点：都江堰  青城山  杜甫草堂 望江楼" +
                "6.西双版纳 花费500-1000 时间5-6天  西双版纳汇集东南亚风情的建筑与夜市" +
                "推荐景点：玉龙雪山 丽江古城 曼听公园 野象谷 傣族园  中科院植物园" +
                "7.南京  花费400-1200  时间6-8天  南京是四大古城之一，也是民国时期的都城，有很多和民国有关的建筑，而且大多数景点免费" +
                "推荐景点：古鸡鸣寺 浦口火车站 先锋书屋  灵谷寺" +
                "8.西安  花费600-1400  时间4-7天  西安是十三朝古都，历史文化名城" +
                "推荐景点：钟楼 鼓楼 芙蓉园  兵马俑 大唐不夜城 大雁塔");
        discussPostRepository.save(post);
    }

    //查询
    @Test
    public void testSearchByRepository() {
        SearchQuery searchQuery=new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("大理","title","content"))
                .withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(0,10))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                       new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();

        // elasticTemplate.queryForPage(searchQuery, class, SearchResultMapper)
        // 底层获取得到了高亮显示的值, 但是没有返回.

        Page<DiscussPost> page=discussPostRepository.search(searchQuery);
        //一共多少数据
        System.out.println(page.getTotalElements());
        //多少页
        System.out.println(page.getTotalPages());
        //当前在第几页
        System.out.println(page.getNumber());
        //每一页最多多少数据
        System.out.println(page.getSize());
        for (DiscussPost post:page) {
            System.out.println(post);
        }

    }

    @Test
    public void testSearchByTemplate() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("旅游", "title", "content"))
                .withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(0, 10))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();

        Page<DiscussPost> page = template.queryForPage(searchQuery, DiscussPost.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass, Pageable pageable) {
                SearchHits hits = response.getHits();
                if (hits.getTotalHits() <= 0) {
                    return null;
                }

                List<DiscussPost> list = new ArrayList<>();
                for (SearchHit hit : hits) {
                    DiscussPost post = new DiscussPost();

                    String id = hit.getSourceAsMap().get("id").toString();
                    post.setId(Integer.valueOf(id));

                    String userId = hit.getSourceAsMap().get("userId").toString();
                    post.setUserId(Integer.valueOf(userId));

                    String title = hit.getSourceAsMap().get("title").toString();
                    post.setTitle(title);

                    String content = hit.getSourceAsMap().get("content").toString();
                    post.setContent(content);

                    String status = hit.getSourceAsMap().get("status").toString();
                    post.setStatus(Integer.valueOf(status));

                    String createTime = hit.getSourceAsMap().get("createTime").toString();
                    post.setCreateTime(new Date(Long.valueOf(createTime)));

                    String commentCount = hit.getSourceAsMap().get("commentCount").toString();
                    post.setCommentCount(Integer.valueOf(commentCount));

                    // 处理高亮显示的结果
                    HighlightField titleField = hit.getHighlightFields().get("title");
                    if (titleField != null) {
                        post.setTitle(titleField.getFragments()[0].toString());
                    }

                    HighlightField contentField = hit.getHighlightFields().get("content");
                    if (contentField != null) {
                        post.setContent(contentField.getFragments()[0].toString());
                    }

                    list.add(post);
                }

                return new AggregatedPageImpl(list, pageable,
                        hits.getTotalHits(), response.getAggregations(), response.getScrollId(), hits.getMaxScore());
            }
        });

        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumber());
        System.out.println(page.getSize());
        for (DiscussPost post : page) {
            System.out.println(post);
        }
    }
}
