package com.acme.filterquery.statemachine.impl;

import java.util.List;

import com.acme.filterquery.statemachine.Query;
import com.acme.filterquery.statemachine.State;
import com.acme.filterquery.statemachine.StateMachine;

public class ValueState implements State {

    private static final char ESCAPE = '\\';
    private static final char END_TOKEN = '"';

    private final StateMachine machine;
    private final String field;
    private final String operator;
    private final StringBuilder sb;

    public ValueState(StateMachine machine, String field, String operator, StringBuilder sb) {
        this.machine = machine;
        this.field = field;
        this.operator = operator;
        this.sb = sb;
    }

    public ValueState(StateMachine machine, String field, String operator) {
        this(machine, field, operator, new StringBuilder());
    }

    @Override
    public void read(char ch, List<Query> queryList) {

        if (ch == ESCAPE) {
            this.machine.setCurrentState(new EscapeState(machine, field, operator, sb));
        } else if (ch == END_TOKEN) {
            queryList.add(new Query(field, operator, this.sb.toString()));
            this.machine.setCurrentState(new EndQueryState(machine));
        } else {
            sb.append(ch);
        }

    }

}
