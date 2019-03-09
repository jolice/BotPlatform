package me.nextgeneric.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "me.nextgeneric.bot.config")
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Bootstrap.class);
        springApplication.setAdditionalProfiles(args);
        springApplication.run();
    }

}

