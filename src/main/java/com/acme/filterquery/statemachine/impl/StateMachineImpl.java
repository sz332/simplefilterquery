package com.acme.filterquery.statemachine.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.acme.filterquery.statemachine.Query;
import com.acme.filterquery.statemachine.State;
import com.acme.filterquery.statemachine.StateMachine;

public class StateMachineImpl implements StateMachine {

    StringReader reader;
    State currentState;

    public StateMachineImpl() {
        this.currentState = new FieldState(this);
    }

    public List<Query> parse(String input) {
        
        List<Query> queryList = new ArrayList<>();

        for (char ch : input.toCharArray()) {
            this.currentState.read(ch, queryList);
        }

        return queryList;
    }

    public void setCurrentState(State state) {
        this.currentState = state;
    }

}
