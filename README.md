# Datalog in JSON
A converter that serializes a Datalog program into JSON.

---
## Introduction
Datalog was an interesting database in my exploration of logic programming. Datalog uses predicate(s) to construct and represent its fact(s) and rule(s). However, that is not the case for web development. JSON is the most trending format and many frameworks / libraries were built to work with it. I will offer my idea that was used in my school project to represent Datalog program in JSON arrays and literals.

---
## Use Cases
For front-end, if we wanted to use D3.js to visualize a Datalog program, we need to represent the program in JSON so that it can be parsed.

For document database like MongoDB. However, the loss of interpretation in JSON will very likely require the users to process it to fit into the schema.

---
## Structure
Datalog Program : [Clause*]

Clause: [Predicate] | [Predicate, Literal (, Literal)\*]

Literal: Predicate | EXPRESSION

Predicate: [PREDICATE_SYMBOL (, ATOM)\*]

EXPRESSION: Datalog expression like X = 1.

PREDICATE_SYMBOL: Predicate name like PREDICATE_SYMBOL(a, b).

ATOM: Variable or actual atom in Datalog, meaning strings, numbers, and identifiers.

The names are not perfect description of standard Datalog because even Datalog databases called each of them differently. I hope this can provide a good overview.

Key Facts:

- JSON will always start with an array, the length is the number of clauses.
- A clause is a fact if its length is 1, otherwise, it is a rule with the body following the first predicate.
- A predicate could be an array or string. String is used for the case of ground instance being a fact. Like "bob." can be a valid Datalog clause, it is a proposition.
- An arity 0 predicate may be represented by [PREDICATE_SYMBOL], equivalent predicate is "PREDICATE_SYMBOL()".
- If string "g g" was in the Datalog program, it will look like ""g g"" in JSON.

---
## Demo
A demo is created and deployed to Google App Engine. Please don't spam, my project is running on free quota.
