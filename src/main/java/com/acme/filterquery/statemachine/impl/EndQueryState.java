package com.acme.filterquery.statemachine.impl;

import java.util.List;

import com.acme.filterquery.statemachine.Query;
import com.acme.filterquery.statemachine.State;
import com.acme.filterquery.statemachine.StateMachine;

public class EndQueryState implements State {

    private static final char SEPARATOR = ';';

    private final StateMachine machine;

    public EndQueryState(StateMachine machine) {
        this.machine = machine;
    }

    @Override
    public void read(char ch, List<Query> queryList) {
        if (ch == SEPARATOR) {
            this.machine.setCurrentState(new FieldState(machine));
        }
    }

}
