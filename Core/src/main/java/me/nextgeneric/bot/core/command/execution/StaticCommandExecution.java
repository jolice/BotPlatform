package me.nextgeneric.bot.core.command.execution;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import me.nextgeneric.bot.core.MessageEvent;
import me.nextgeneric.bot.core.command.arguments.Arguments;

@Data
@AllArgsConstructor
@Setter(AccessLevel.NONE)
public class StaticCommandExecution implements CommandExecution {

    private String body;
    private MessageEvent message;
    private Arguments arguments;

    @Override
    public long getChatId() {
        return message.getIncomingMessage().getChatId();
    }
}
