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
    public void enterDatalog(final DatalogParser.DatalogContext ctx) {
        buffer.addLast("[");
        capacity++;
    }

    @Override
    public void exitDatalog(final DatalogParser.DatalogContext ctx) {
        buffer.addLast("]");
        capacity++;
    }

    @Override
    public void enterClause(final DatalogParser.ClauseContext ctx) {
        if (!buffer.getLast().equals("[")) {
            buffer.addLast(",");
            capacity++;
        }
        buffer.addLast("[");
        capacity++;
    }

    @Override
    public void exitClause(final DatalogParser.ClauseContext ctx) {
        buffer.addLast("]");
        capacity++;
    }

    @Override
    public void enterLiteral(final DatalogParser.LiteralContext ctx) {
        if (((RuleContext) ctx.getChild(0)).getRuleIndex() != DatalogParser.RULE_predicate) {
            buffer.addLast(",");
            capacity++;
            String expression = ctx.getText();
            buffer.addLast(expression);
            capacity += expression.length();
        }
    }

    @Override
    public void enterPredicate(final DatalogParser.PredicateContext ctx) {
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
    public void exitPredicate(final DatalogParser.PredicateContext ctx) {
        if (ctx.getChildCount() > 1) {
            buffer.addLast("]");
            capacity++;
        }
    }

    @Override
    public void enterTerms(final DatalogParser.TermsContext ctx) {
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
