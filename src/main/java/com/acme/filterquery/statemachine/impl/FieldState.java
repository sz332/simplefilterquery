package com.acme.filterquery.statemachine.impl;

import java.util.List;

import com.acme.filterquery.statemachine.Query;
import com.acme.filterquery.statemachine.State;
import com.acme.filterquery.statemachine.StateMachine;

public class FieldState implements State {

    private static final char SEPARATOR = '=';

    private final StateMachine machine;
    private final StringBuilder sb;

    public FieldState(StateMachine machine) {
        this.machine = machine;
        this.sb = new StringBuilder();
    }

    @Override
    public void read(char ch, List<Query> queryList) {
        if (ch != SEPARATOR) {
            sb.append(ch);
        } else {
            this.machine.setCurrentState(new OperatorState(this.machine, this.sb.toString()));
        }
    }

}
