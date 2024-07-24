package com.blog.cron; import com.blog.service.MsmService;import com.blog.utils.JavaMain;import jakarta.annotation.Resource;import lombok.extern.slf4j.Slf4j;import net.mamoe.mirai.Bot;import org.springframework.scheduling.annotation.Scheduled;import org.springframework.stereotype.Component;/** * @Description * @ClassName Task1 * @Author User * @date 2020.06.07 12:24 */@Component@Slf4jpublic class Task {    @Resource    private Bot bot;    @Scheduled(cron = "0 0 15,16,17,18 * * ?",zone = "Asia/Shanghai")    public void sayWord() {        log.info("success send message!");    }    @Scheduled(cron = "0 0,30 0-2 * * ?",zone = "Asia/Shanghai")    public void scheduledTask() {        bot.getFriend(2249415510L).sendMessage("老子要就寝了，快睡觉！快睡觉！快睡觉！");    }    @Scheduled(cron = "0 30-59/3 11 * * ?",zone = "Asia/Shanghai")    public void scheduldTask() {        bot.getFriend(2249415510L).sendMessage("老子要就寝了，快睡觉！快睡觉！快睡觉！");    }}