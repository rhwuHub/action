package com.blog.cron; import com.blog.service.MsmService;import com.blog.utils.JavaMain;import jakarta.annotation.Resource;import lombok.extern.slf4j.Slf4j;import net.mamoe.mirai.Bot;import org.springframework.scheduling.annotation.Scheduled;import org.springframework.stereotype.Component;/** * @Description * @ClassName Task1 * @Author User * @date 2020.06.07 12:24 */@Component@Slf4jpublic class Task {    @Resource    private MsmService msmService;    @Resource    private Bot bot;    @Scheduled(cron = "0 0 15,16,17,18 * * ?")    public void sayWord() {//        msmService.sendEmail("2249415510@qq.com","记得收拾狗窝；记得烧水；记得洗衣服、晒衣服");        log.info("success send message!");    }    @Scheduled(cron = "0 30-59/3 11 * * ?")    public void scheduledTask() {        bot.getFriend(2249415510L).sendMessage("老子要就寝了，快睡觉！快睡觉！快睡觉！");        JavaMain.afterLogin(bot);//        msmService.sendEmail("2249415510@qq.com","老子要就寝了，快睡觉！快睡觉！快睡觉！");    }//    @Scheduled(cron = "0 * * * * ?")//    public void testTask() {//        bot.getFriend(2300889886L).sendMessage("test");//    }}