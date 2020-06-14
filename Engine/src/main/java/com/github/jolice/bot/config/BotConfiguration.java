package com.github.jolice.bot.config;

import com.github.jolice.bot.annotation.Warning;
import com.github.jolice.bot.application.ApplicationMessageHandler;
import com.github.jolice.bot.application.NoOpHandler;
import com.github.jolice.bot.command.Command;
import com.github.jolice.bot.command.repository.CommandRepository;
import com.github.jolice.bot.command.repository.VirtualCommandRepository;
import com.github.jolice.bot.engine.message.MultipleMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SuppressWarnings({Warning.AUTOWIRING, Warning.COMPONENT_SCAN})
@ComponentScan("${plugin.package}")
public class BotConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(BotConfiguration.class);

    @Bean(name = "DelegateHandler")
    public ApplicationMessageHandler applicationMessageHandler(@Autowired(required = false) List<ApplicationMessageHandler> handlers) {
        if (handlers != null) {
            logger.info("{} handler(s)", handlers.size());
            return handlers.size() > 1 ? new MultipleMessageHandler(handlers) : handlers.get(0);
        } else {
            return NoOpHandler.INSTANCE;
        }
    }

    @Bean
    public CommandRepository commandRepository(@Autowired(required = false) List<Command> commands) {
        if (commands != null) {
            logger.info("{} command(s)", commands.size());
            CommandRepository commandRepository = new VirtualCommandRepository();
            commands.forEach(commandRepository::registerCommand);
            return commandRepository;
        } else {
            logger.info("No commands registered");
            return CommandRepository.EMPTY;
        }
    }

}