package com.acme.filterquery.statemachine.impl;

import java.util.List;

import com.acme.filterquery.statemachine.Query;
import com.acme.filterquery.statemachine.State;
import com.acme.filterquery.statemachine.StateMachine;

public class EscapeState implements State {

    private final StateMachine machine;
    private final String field;
    private final String operator;
    private final StringBuilder sb;

    public EscapeState(StateMachine machine, String field, String operator, StringBuilder sb) {
        this.machine = machine;
        this.field = field;
        this.operator = operator;
        this.sb = sb;
    }

    @Override
    public void read(char ch, List<Query> queryList) {
        sb.append(ch);
        this.machine.setCurrentState(new ValueState(machine, field, operator, sb));
    }

}
