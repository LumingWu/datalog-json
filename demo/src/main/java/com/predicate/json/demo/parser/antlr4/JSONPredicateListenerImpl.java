package com.predicate.json.demo.parser.antlr4;

import antlr4.DatalogBaseListener;
import antlr4.DatalogParser;
import org.antlr.v4.runtime.RuleContext;

import java.util.LinkedList;

public class JSONPredicateListenerImpl extends DatalogBaseListener {

    private final LinkedList<String> buffer = new LinkedList<String>();
    private String toString;
    private int capacity = 0;

    public JSONPredicateListenerImpl() {
    }

    @Override
    public void enterDatalog(DatalogParser.DatalogContext ctx) {
        buffer.addLast("[");
        capacity++;
    }

    @Override
    public void exitDatalog(DatalogParser.DatalogContext ctx) {
        buffer.addLast("]");
        capacity++;
    }

    @Override
    public void enterClause(DatalogParser.ClauseContext ctx) {
        if (!buffer.getLast().equals("[")) {
            buffer.addLast(",");
            capacity++;
        }
        buffer.addLast("[");
        capacity++;
    }

    @Override
    public void exitClause(DatalogParser.ClauseContext ctx) {
        buffer.addLast("]");
        capacity++;
    }

    @Override
    public void enterLiteral(DatalogParser.LiteralContext ctx) {
        if (((RuleContext) ctx.getChild(0)).getRuleIndex() != DatalogParser.RULE_predicate) {
            buffer.addLast(",");
            capacity++;
            String expression = ctx.getText();
            buffer.addLast(expression);
            capacity += expression.length();
        }
    }

    @Override
    public void enterPredicate(DatalogParser.PredicateContext ctx) {
        if (!buffer.getLast().equals("[")) {
            buffer.addLast(",");
            capacity++;
        }
        if (ctx.getChildCount() == 1) {
            String groundInstance = ctx.getText();
            buffer.addLast(groundInstance);
            capacity += groundInstance.length();
        } else {
            buffer.addLast("[");
            capacity++;
            String predicateSymbol = ctx.getChild(0).getText();
            buffer.addLast(predicateSymbol);
            capacity += predicateSymbol.length();
        }
    }

    @Override
    public void exitPredicate(DatalogParser.PredicateContext ctx) {
        if (ctx.getChildCount() > 1) {
            buffer.addLast("]");
            capacity++;
        }
    }

    @Override
    public void enterTerms(DatalogParser.TermsContext ctx) {
        buffer.addLast(",");
        capacity++;
        String terms = ctx.getText();
        buffer.addLast(terms);
        capacity += terms.length();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(capacity);
        while (!buffer.isEmpty()) {
            sb.append(buffer.removeFirst());
        }
        toString = sb.toString();
        return toString;
    }

}
