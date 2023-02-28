package com.example;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.utils.BotConfiguration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Demo1 extends JavaPlugin{
public static final Demo1 INSTANCE=new Demo1();

private Demo1(){
    super(new JvmPluginDescriptionBuilder("com.example.demo1","0.1.0")
        .name("Demo1")
        .author("rwk")
                .build());
}

@Override
public void onEnable(){
        getLogger().info("注册指令...");
        //CommandManager.INSTANCE.registerCommand();
        accountLogin();

        }
        public void accountLogin(){
            Properties accountinfo=new Properties();
            try {
                accountinfo.load(new FileInputStream("account.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            long qq = Long.parseLong(accountinfo.getProperty("id"));
            String password = accountinfo.getProperty("password");
            Bot bot = BotFactory.INSTANCE.newBot(qq,password,
                    new BotConfiguration(){{
                        setHeartbeatStrategy(BotConfiguration.HeartbeatStrategy.REGISTER);
                        setProtocol(MiraiProtocol.MACOS);
                        enableContactCache();
                        reconnectionRetryTimes(3);
                    }
                        private void reconnectionRetryTimes(int i) {
                        }

                    }
            );
            bot.login();
            getLogger().info("账号 "+qq+" 加载成功");

    }



        }