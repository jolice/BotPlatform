package me.nextgeneric.bot.core.annotation;

import me.nextgeneric.bot.core.type.BotTypeMeta;
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
