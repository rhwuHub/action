package com.blog.utils;import net.mamoe.mirai.Bot;import net.mamoe.mirai.BotFactory;import net.mamoe.mirai.event.events.FriendMessageEvent;import net.mamoe.mirai.message.data.MessageChainBuilder;import net.mamoe.mirai.message.data.QuoteReply;import net.mamoe.mirai.utils.BotConfiguration;import top.mrxiaom.qsign.QSignService;import java.io.File;public class JavaMain {    public static void main(String[] args) {        QSignService.Factory.init(new File("D:\\work\\gitCode\\action\\Blog-server\\src\\main\\resources\\txlib\\8.9.63"));        // 加载签名服务所需协议信息，如果你的协议信息存在非 以上的工作目录 中的文件夹，请将参数 null 改为协议信息所在目录        // 该方法将会扫描目录下以协议信息命名的文件进行加载，如 android_phone.json        // 如果你想单独加载协议信息文件，详见 loadProtocolExample() 中的例子        QSignService.Factory.loadProtocols(null);        // 注册签名服务        QSignService.Factory.register();        Bot bot = BotFactory.INSTANCE.newBot(2943334252L, "Wuruihao5");        // Bot bot = BotFactory.INSTANCE.newBot(2943334252L, BotAuthorization.byQRCode(), configuration -> {        //     configuration.setProtocol(BotConfiguration.MiraiProtocol.MACOS);        //     configuration.setWorkingDir(new File("C:/mirai"));        // });        bot.login();        JavaMain.afterLogin(bot);    }    public static void afterLogin(Bot bot) {        long yourQQNumber = 2249415510L;        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {            if (event.getSender().getId() == yourQQNumber) {                event.getSubject().sendMessage(                        new MessageChainBuilder()                        .append(new QuoteReply(event.getMessage()))                        .append("白徐吊，赶紧睡！！！")                        .build()                );            }        });    }}class WithConfiguration1 {    public static void main(String[] args) {        QSignService.Factory.init(new File("D:\\work\\gitCode\\action\\Blog-server\\src\\main\\resources\\txlib\\8.9.63"));        // 加载签名服务所需协议信息，如果你的协议信息存在非 以上的工作目录 中的文件夹，请将参数 null 改为协议信息所在目录        // 该方法将会扫描目录下以协议信息命名的文件进行加载，如 android_phone.json        // 如果你想单独加载协议信息文件，详见 loadProtocolExample() 中的例子        QSignService.Factory.loadProtocols(null);        // 注册签名服务        QSignService.Factory.register();        // 使用自定义配置        Bot bot = BotFactory.INSTANCE.newBot(2943334252L, "Wuruihao5", new BotConfiguration() {{            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息            setProtocol(MiraiProtocol.ANDROID_PHONE); // 切换协议        }});        bot.login();        JavaMain.afterLogin(bot);    }}