package com.github.jolice.bot.command.stateful;

import com.github.jolice.bot.command.arguments.Arguments;
import com.github.jolice.bot.command.execution.CommandExecution;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatefulCommandTest {

    private State state = new State();

    static class State {

        int execution;
        int prompt;
    }

    @Test
    void whenPassedStateProperlyThenProceeded() {

        CommandState third = state(x -> true, 3, NullState.INSTANCE);

        CommandState second = mock(CommandState.class);

        when(second.execute(any())).thenReturn(false);
        when(second.nextState()).thenReturn(third);

        CommandState first = state(x -> true, 1, second);

        StatefulCommand statefulCommand = statefulCommand(first);
        CommandExecution commandExecution = mock(CommandExecution.class);
        Arguments arguments = mock(Arguments.class);
        when(commandExecution.getChatId()).thenReturn(1L);
        when(commandExecution.getArguments()).thenReturn(arguments);


        statefulCommand.execute(commandExecution);
        assertState(0, 1);
        statefulCommand.execute(commandExecution);
        // Executing mock command
        assertState(1, 1);


        for (int i = 0; i < 5; i++) {
            statefulCommand.execute(commandExecution);
            assertState(1, 1);
        }

        // Emulating valid input provided and proceed
        when(second.execute(any())).thenAnswer(invocationOnMock -> {
            state.execution = 2;
            return true;
        });

        statefulCommand.execute(commandExecution);
        assertState(2, 3);

    }

    @Test
    void whenResetThenResetToInitialState() {

        CommandState third = state(x -> true, 3, NullState.INSTANCE);
        CommandState second = state(x -> true, 2, third);
        CommandState first = state(x -> true, 1, second);

        StatefulCommand statefulCommand = statefulCommand(first);
        CommandExecution commandExecution = mock(CommandExecution.class);
        Arguments arguments = mock(Arguments.class);
        when(commandExecution.getChatId()).thenReturn(1L);
        when(commandExecution.getArguments()).thenReturn(arguments);

        statefulCommand.execute(commandExecution);
        assertState(0, 1);
        statefulCommand.execute(commandExecution);
        assertState(1, 2);
        statefulCommand.execute(commandExecution);
        assertState(2, 3);

        when(arguments.isEmpty()).thenReturn(true);
        statefulCommand.execute(commandExecution);
        assertState(2, 1);
        when(arguments.isEmpty()).thenReturn(false);
        statefulCommand.execute(commandExecution);
        assertState(1, 2);
    }

    @Test
    public void whenStateFailedThenNotPromoted() {

        CommandState second = state(x -> true, 2, NullState.INSTANCE);
        CommandState first = state(x -> false, 1, second);

        StatefulCommand statefulCommand = statefulCommand(first);
        CommandExecution commandExecution = mock(CommandExecution.class);
        Arguments arguments = mock(Arguments.class);
        when(commandExecution.getChatId()).thenReturn(1L);
        when(commandExecution.getArguments()).thenReturn(arguments);

        assertState(0, 0);
        statefulCommand.execute(commandExecution);
        assertState(0, 1);
        statefulCommand.execute(commandExecution);
        assertState(0, 1);
        statefulCommand.execute(commandExecution);
        assertState(0, 1);
    }

    @Test
    public void whenAllStatesPassedThenPromotedToFinal() {

        CommandState third = state(x -> true, 3, NullState.INSTANCE);
        CommandState second = state(x -> true, 2, third);
        CommandState first = state(x -> true, 1, second);

        StatefulCommand statefulCommand = statefulCommand(first);
        CommandExecution commandExecution = mock(CommandExecution.class);
        Arguments arguments = mock(Arguments.class);
        when(commandExecution.getChatId()).thenReturn(1L);
        when(commandExecution.getArguments()).thenReturn(arguments);

        statefulCommand.execute(commandExecution);
        assertState(0, 1);
        statefulCommand.execute(commandExecution);
        assertState(1, 2);
        statefulCommand.execute(commandExecution);
        assertState(2, 3);
        statefulCommand.execute(commandExecution);
        assertState(3, 3);
        statefulCommand.execute(commandExecution);
        assertState(3, 1);
        statefulCommand.execute(commandExecution);
        assertState(1, 2);

    }

    private void assertState(int execution, int prompt) {
        assertEquals(state.execution, execution);
        assertEquals(state.prompt, prompt);
    }

    private StatefulCommand statefulCommand(CommandState first) {
        return new StatefulCommand(first) {
            @Override
            public List<String> aliases() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private CommandState state(Predicate<CommandExecution> predicate, int val, CommandState nextState) {
        return new CommandState() {
            @Override
            public boolean execute(CommandExecution execution) {
                boolean passed = predicate.test(execution);
                if (passed) {
                    state.execution = val;
                }
                return passed;
            }

            @Override
            public void prompt(CommandExecution commandExecution) {
                state.prompt = val;
            }

            @Override
            public CommandState nextState() {
                return nextState;
            }
        };
    }

}