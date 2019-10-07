package io.riguron.bot.api.command.arguments;

import java.util.List;

public interface Arguments {

    List<String> getAll();

    String get(int index);

    int size();

    boolean isEmpty();

    String first();

    boolean isSingle();
}
