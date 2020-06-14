package com.github.jolice.bot.engine.message;

import com.github.jolice.bot.BotLauncher;
import com.github.jolice.bot.annotation.Warning;
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

