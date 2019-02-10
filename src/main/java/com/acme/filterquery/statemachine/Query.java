package com.acme.filterquery.statemachine;

import java.util.Objects;

public class Query {
    
    String field;
    String operator;
    String value;
    
    public Query(String field, String operator, String value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }
    
    public String field() {
        return field;
    }
    
    public String operator() {
        return operator;
    }
    
    public String value() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, operator, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Query other = (Query) obj;
        return Objects.equals(field, other.field) && Objects.equals(operator, other.operator) && Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Query [field=" + field + ", operator=" + operator + ", value=" + value + "]";
    }
    
}
