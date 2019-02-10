package com.acme.filterquery.statemachine;

import java.util.List;

public interface State {

    public void read(char ch, List<Query> queryList);
    
}
