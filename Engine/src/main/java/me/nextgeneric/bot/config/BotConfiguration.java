package me.nextgeneric.bot.config;

import me.nextgeneric.bot.core.annotation.Warning;
import me.nextgeneric.bot.core.application.ApplicationMessageHandler;
import me.nextgeneric.bot.core.command.Command;
import me.nextgeneric.bot.core.command.repository.CommandRepository;
import me.nextgeneric.bot.core.command.repository.VirtualCommandRepository;
import me.nextgeneric.bot.engine.message.MultipleMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

@Configuration
@ComponentScan("me.nextgeneric.bot.engine")
@DependsOn("PluginManager")
@SuppressWarnings(Warning.AUTOWIRING)
public class BotConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(BotConfiguration.class);

    @Bean(name = "DelegateHandler")
    public ApplicationMessageHandler applicationMessageHandler(@Autowired(required = false) List<ApplicationMessageHandler> handlers) {
        if (handlers != null) {
            logger.info("{} handler(s)", handlers.size());
            return handlers.size() > 1 ? new MultipleMessageHandler(handlers) : handlers.get(0);
        } else {
            logger.info("No handlers found");
            return ApplicationMessageHandler.NO_OP;
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