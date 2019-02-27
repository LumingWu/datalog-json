grammar Datalog;

@header {
package antlr4;
import antlr4.*;
}

datalog
    : clause* EOF
    ;

clause
    : predicate IF body CLAUSE_END
	| predicate CLAUSE_END
	;

body
    : literal (CONJUNCTION literal)*
	;

literal
    : predicate
    | term BIND term
	| term LARGER_THAN term
	| term LESS_THAN term
	| term NOT_EQUAL term
	| term LARGER_THAN_EQUAL term
	| term LESS_THAN_EQUAL
	;

predicate
    : predicate_symbol PREDICATE_OPEN terms PREDICATE_CLOSE
    | predicate_symbol PREDICATE_OPEN PREDICATE_CLOSE
    | predicate_symbol
    ;

predicate_symbol
    : IDENTIFIER
	| STRING
	;

terms
    : term (CONJUNCTION term)*
	;

term
    : VARIABLE
	| constant
	;

constant
    : IDENTIFIER
	| STRING
	;

CONJUNCTION : ',' ;

PREDICATE_OPEN : '(' ;

PREDICATE_CLOSE : ')' ;

CLAUSE_END : '.' ;

IF : ':-' ;

BIND : '=' ;

LARGER_THAN : '>' ;

LESS_THAN : '<' ;

NOT_EQUAL : '!=' ;

LARGER_THAN_EQUAL : '>=' ;

LESS_THAN_EQUAL : '<=' ;

IDENTIFIER : [!#$&*+\-/-9;<>@[-_a-}][!#$&*+\-/-9;<>@A-Z[-_a-}]* ;	// exception was [^ "%'\(\).:=\?A-Z`~][^ "%'\(\).:=\?`~]*, added ',' for fix.

VARIABLE : [A-Z][a-zA-Z0-9_]* ;

STRING : '"'('\\"'|'\\n'|'\\\\'|[^"\n\\])*'"' ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines




