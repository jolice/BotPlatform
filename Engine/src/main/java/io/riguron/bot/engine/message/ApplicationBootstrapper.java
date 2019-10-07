package io.riguron.bot.engine.message;

import io.riguron.bot.api.BotLauncher;
import io.riguron.bot.api.annotation.Warning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings(Warning.AUTOWIRING)
public class ApplicationBootstrapper {

    private BotLauncher botLauncher;

    @Autowired
    public ApplicationBootstrapper(BotLauncher botLauncher) {
        this.botLauncher = botLauncher;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void launchBot() {
        botLauncher.launch();
    }
}

