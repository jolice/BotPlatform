package io.riguron.bot.api.command.execution;

import io.riguron.bot.api.MessageEvent;
import io.riguron.bot.api.command.arguments.Arguments;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter(AccessLevel.NONE)
public class StaticCommandExecution implements CommandExecution {

    private String body;
    private MessageEvent messageEvent;
    private Arguments arguments;

    @Override
    public long getChatId() {
        return messageEvent.getIncomingMessage().getChatId();
    }
}
