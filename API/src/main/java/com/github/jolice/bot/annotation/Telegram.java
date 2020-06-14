package com.github.jolice.bot.annotation;

import com.github.jolice.bot.type.BotTypeMeta;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Profile(BotTypeMeta.TELEGRAM)
public @interface Telegram {
}
