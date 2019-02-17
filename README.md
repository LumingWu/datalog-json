# template-parser
A parser for translating templates from one template engine to another.

---
## Introduction
Template engines had been changing in Java web development. In school, I have learned the classic template engine JSP with JSTL for generating HTML. In my internship, I had to write a custom parser to translate Velocity templates to FreeMarker templates. My thought was somebody in somewhere is doing the exact same thing. Indeed, there is a translator USCavalry to do this specific translation. Like my custom parser, it handled most use cases. However, I think there is a need to create a set of tools to complete template translation.

---
## Input Validity
Template translation will begin with the assumption that the provided template is valid. Meaning there is no hack used to create unexpected behavior(s) in order to generate an expected view. Template translator will have unexpected handle to it.
## Semantic Assumption
In order to handle unsupported tokens. The translator has to assume that a token DIRECTIVE_TAG_CLOSE will close the most recent unsupported token that is not HTML token.

---
## Features
### Preprocess
Preprocess is a unique feature for template engines that supports preprocessing. An example is macro, other template engines might not support it. Preprocess will complete these operations before template translation.
### Normalize
A common problem in template translation is difference in convention.
* Syntactic Difference
  * Variable naming convention in one template engine is not acceptable in another template engine. Another problem while I am designing is semantic
* Semantic Difference
  * Tags could have start tag and end tag, or just a single tag. This is a challenge for parsing because writing parser generator will need to consider semantics that do not belong to the expected semantic.

Normalize is a feature to reformat the template to a form that is easier for translation.
### Tokenize
Tokenize the template is a common parsing technique done before evaluation. Each template engine will have its own Flex program that will tokenize all valid tokens.
### Evaluation
The final process of translation is to put tokens into a parse tree and evaluate / convert to another template engine. Each template will have its own Bison program to evaluate expected tokens. Unexpected tokens will be handle in an error / todo report in the end of the translation. Error report will include: Token name, line number, and character number.
