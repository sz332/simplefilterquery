package com.acme.filterquery.statemachine.impl;

import java.util.List;

import com.acme.filterquery.statemachine.Query;
import com.acme.filterquery.statemachine.State;
import com.acme.filterquery.statemachine.StateMachine;

public class OperatorState implements State {

    private static final char SEPARATOR = '=';

    private final StateMachine machine;
    private final StringBuilder sb;
    private final String field;

    public OperatorState(StateMachine machine, String field) {
        this.machine = machine;
        this.field = field;
        this.sb = new StringBuilder();
    }

    @Override
    public void read(char ch, List<Query> queryList) {
        if (ch != SEPARATOR) {
            sb.append(ch);
        } else {
            this.machine.setCurrentState(new StartingDoubleQuoteState(this.machine, this.field, this.sb.toString()));
        }
    }

}
