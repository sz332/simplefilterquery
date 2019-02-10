package com.acme.filterquery.statemachine;

import java.util.List;

public interface StateMachine {
    
    List<Query> parse(String input);
    
    void setCurrentState(State state);

}
