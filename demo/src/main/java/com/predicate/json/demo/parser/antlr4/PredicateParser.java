package com.predicate.json.demo.parser.antlr4;

import antlr4.DatalogLexer;
import antlr4.DatalogParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PredicateParser {

    public PredicateParser() {

    }

    public String parse(String s) {
        DatalogLexer lexer = new DatalogLexer(CharStreams.fromString(s));
        CommonTokenStream token = new CommonTokenStream(lexer);
        DatalogParser parser = new DatalogParser(token);
        ParseTree tree = parser.datalog();
        JSONPredicateListenerImpl listener = new JSONPredicateListenerImpl();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        return listener.toString();
    }

}
