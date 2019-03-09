package me.nextgeneric.bot.config;

import org.pf4j.spring.SpringPluginManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class PluginConfiguration {

    @Bean(name = "PluginManager")
    public SpringPluginManager pluginManager(ApplicationContext applicationContext) {
        SpringPluginManager springPluginManager = new SpringPluginManager(Paths.get("plugins"));
        springPluginManager.setApplicationContext(applicationContext);
        return springPluginManager;
    }

}