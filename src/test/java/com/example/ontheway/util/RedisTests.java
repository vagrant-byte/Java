package com.example.ontheway.util;

import com.example.ontheway.OnthewayApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OnthewayApplication.class)
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testStrings() {
        String redisKey="test:count";
        //往redis中存入数字
        redisTemplate.opsForValue().set(redisKey,1);
        //获取redis中的值
        System.out.println(redisTemplate.opsForValue().get(redisKey));
        //自增
        System.out.println(redisTemplate.opsForValue().increment(redisKey));
        //自减
        System.out.println(redisTemplate.opsForValue().decrement(redisKey));
    }
    //存储hash
    @Test
    public void testHashes() {
        String redisKey="test:user";
        redisTemplate.opsForHash().put(redisKey,"id",1);
        redisTemplate.opsForHash().put(redisKey,"username","zhangsan");
        System.out.println(redisTemplate.opsForHash().get(redisKey,"id"));
        System.out.println(redisTemplate.opsForHash().get(redisKey,"username"));
    }
    //存储列表
    @Test
    public void testLists() {
        String redisKey="test:ids";
        redisTemplate.opsForList().leftPush(redisKey,101);
        redisTemplate.opsForList().leftPush(redisKey,102);
        redisTemplate.opsForList().leftPush(redisKey,103);
        //获取列表中的个数
        System.out.println(redisTemplate.opsForList().size(redisKey));
        //获取下标为0的值
        System.out.println(redisTemplate.opsForList().index(redisKey,0));
        //获取从0-2下标的索引
        System.out.println(redisTemplate.opsForList().range(redisKey,0,2));
        //弹出数据
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
    }
    //集合
    @Test
    public void testSets() {
        String redisKey="test:teachers";
        redisTemplate.opsForSet().add(redisKey,"刘备","关羽","张飞","赵云");
        System.out.println(redisTemplate.opsForSet().size(redisKey));
        //随机弹出数据
        System.out.println(redisTemplate.opsForSet().pop(redisKey));
        //统计数据
        System.out.println(redisTemplate.opsForSet().members(redisKey));
    }
    //有序集合
    @Test
    public void testSortedSets() {
        String redisKey="test:students";
        redisTemplate.opsForZSet().add(redisKey,"唐僧",79);
        redisTemplate.opsForZSet().add(redisKey,"悟空",60);
        redisTemplate.opsForZSet().add(redisKey,"八戒",89);
        redisTemplate.opsForZSet().add(redisKey,"沙僧",59);
        redisTemplate.opsForZSet().add(redisKey,"白龙马",90);
        //统计一个多少个数据
        System.out.println(redisTemplate.opsForZSet().zCard(redisKey));
        //统计某个人分数
        System.out.println(redisTemplate.opsForZSet().score(redisKey,"八戒"));
        //统计排名降序 (返回索引)
        System.out.println(redisTemplate.opsForZSet().reverseRank(redisKey,"八戒"));
        //统计某个范围内排名
        System.out.println(redisTemplate.opsForZSet().reverseRange(redisKey,0,2));
    }

    @Test
    public void testKeys() {
        //删除key
        redisTemplate.delete("test:user");
        //判断key是否存在
        System.out.println(redisTemplate.hasKey("test:user"));
        //设置过期时间
        redisTemplate.expire("test:students",10, TimeUnit.SECONDS);
    }

    //多次访问用一个key
    //绑定对象
    @Test
    public void testBoundOperations() {
        String redisKey="test:count";
        BoundValueOperations operations=redisTemplate.boundValueOps(redisKey);
        operations.increment();
        System.out.println(operations.get());
    }

    //编程式事务 redis事务是将其存放在一个队列中，只要事务提交后才会返回内容，所以在事务内部不能进行查找操作
    @Test
    public void testTransactional() {
        Object obj=redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String redisKey="test:tx";
                //启动事务
                operations.multi();
                operations.opsForSet().add(redisKey,"zhangsan");
                operations.opsForSet().add(redisKey,"lisi");
                operations.opsForSet().add(redisKey,"wangwu");
                //提交事务
                return operations.exec();
            }
        });
        System.out.println(obj);
    }

}
