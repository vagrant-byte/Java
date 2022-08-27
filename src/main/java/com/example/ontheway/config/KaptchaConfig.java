package com.example.ontheway.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

//配置类  生成验证码
@Configuration
public class KaptchaConfig {

    @Bean
    public Producer kaptchaProdicer() {
        //实例化Properties
        Properties properties=new Properties();
        properties.setProperty("kaptcha.image.width","100");
        properties.setProperty("kaptcha.image.height","40");
        //设置验证码字体大小
        properties.setProperty("kaptcha.textproducer.font.size","32");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color","blue");
        //验证码随机字符范围
        properties.setProperty("kaptcha.textproducer.char.string","0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        //随机字符的长度
        properties.setProperty("kaptcha.textproducer.char.length","4");
        //设置验证码上的干扰类 此处设置为没有干扰
        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");

        DefaultKaptcha kaptcha=new DefaultKaptcha();
        //将对象放在Config中,参数为Properties
        Config config=new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
