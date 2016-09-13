package com.ligstd.homework.models;

import com.ligstd.homework.enums.CommandEnum;

import java.util.Map;

/**
 * Created by tt030 on 2016/9/11.
 */
public class Command {
    private CommandEnum type;
    private Map<String, Double> expressions;

    public CommandEnum getType() {
        return type;
    }

    private void setType(CommandEnum type) {
        this.type = type;
    }

    public Map<String, Double> getExpressions() {
        return expressions;
    }

    private void setExpressions(Map<String, Double> expressions) {
        this.expressions = expressions;
    }

    public Command(CommandEnum type, Map<String, Double> expressions){
        setType(type);
        setExpressions(expressions);
    }
}
