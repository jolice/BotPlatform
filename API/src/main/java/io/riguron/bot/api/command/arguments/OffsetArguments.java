package io.riguron.bot.api.command.arguments;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class OffsetArguments implements Arguments {

    private static final int ONE_ARGUMENT_SIZE = 2;

    private static final int EMPTY_SIZE = 1;

    private List<String> arguments;

    public OffsetArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public List<String> getAll() {
        return this.arguments;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= arguments.size() - 1) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return arguments.get(index + 1);
    }

    @Override
    public int size() {
        return this.arguments.size() - 1;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == EMPTY_SIZE;
    }

    @Override
    public String first() {
        return this.get(0);
    }

    @Override
    public boolean isSingle() {
        return this.size() == ONE_ARGUMENT_SIZE;
    }


}
