package com.acme.filterquery.statemachine;

import static org.hamcrest.CoreMatchers.hasItems;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.acme.filterquery.statemachine.impl.StateMachineImpl;

public class StateMachineTest {

    // age=lt="23"
    // age=lt="23";name=eq="John Doe"
    // name=eq="John \" Doe"

    @Test
    public void testSimpleQuery() {
        StateMachine machine = new StateMachineImpl();
        List<Query> query = machine.parse("age=lt=\"23\"");
        Assert.assertThat(query, hasItems(new Query("age", "lt", "23")));
    }

    @Test
    public void testMissingEndingDoubleQuoteQuery() {
        StateMachine machine = new StateMachineImpl();
        List<Query> query = machine.parse("age=lt=\"23");
        Assert.assertTrue(query.isEmpty());
    }
    
    @Test
    public void testCompoundQuery() {
        StateMachine machine = new StateMachineImpl();
        List<Query> query = machine.parse("age=lt=\"23\";name=eq=\"John Doe\"");
        Assert.assertThat(query, hasItems(new Query("age", "lt", "23")));
        Assert.assertThat(query, hasItems(new Query("name", "eq", "John Doe")));
    }
    
    @Test
    public void testCompoundWithRandomCharactersQuery() {
        StateMachine machine = new StateMachineImpl();
        List<Query> query = machine.parse("age=lt=\"23\"something else;name=eq=\"John Doe\"");
        Assert.assertThat(query, hasItems(new Query("age", "lt", "23")));
        Assert.assertThat(query, hasItems(new Query("name", "eq", "John Doe")));
    }

    @Test
    public void testEscapingDoubleQuote() {
        StateMachine machine = new StateMachineImpl();
        List<Query> query = machine.parse("name=eq=\"John \\\" Doe\"");
        Assert.assertThat(query, hasItems(new Query("name", "eq", "John \" Doe")));
    }

    @Test
    public void testEscapingEscapeCharacter() {
        StateMachine machine = new StateMachineImpl();
        List<Query> query = machine.parse("name=eq=\"John \\\\ Doe\"");
        Assert.assertThat(query, hasItems(new Query("name", "eq", "John \\ Doe")));
    }

    @Test
    public void testUnescapedDoubleQuote() {
        StateMachine machine = new StateMachineImpl();
        List<Query> query = machine.parse("name=eq=\"John \" Doe\"");
        Assert.assertThat(query, hasItems(new Query("name", "eq", "John ")));
    }
    

}

