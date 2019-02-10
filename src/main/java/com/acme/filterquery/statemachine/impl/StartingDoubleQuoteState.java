package com.acme.filterquery.statemachine.impl;

import java.util.List;

import com.acme.filterquery.statemachine.Query;
import com.acme.filterquery.statemachine.State;
import com.acme.filterquery.statemachine.StateMachine;

public class StartingDoubleQuoteState implements State{
    
    private static final char SEPARATOR = '"';

    private final StateMachine machine;
    private final String field;
    private final String operator;

    public StartingDoubleQuoteState(StateMachine machine, String field, String operator) {
        this.machine = machine;
        this.field = field;
        this.operator = operator;
    }

    @Override
    public void read(char ch, List<Query> queryList) {
        if (ch == SEPARATOR) {
            this.machine.setCurrentState(new ValueState(machine, field, operator));
        }
    }

}
