package com.blog.config;

import net.mamoe.mirai.Bot;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import top.mrxiaom.overflow.BotBuilder;

/**
 * @program: action
 * @author: rhwu
 * @create: 2024-07-23 22:27
 */
@Component
public class QQRobotConfig {
    @Bean
    public Bot initBot(){
        return  BotBuilder.positive("ws://139.9.220.169:3001")
                .token("114514")
                .connect();
    }
}