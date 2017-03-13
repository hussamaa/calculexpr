# CalculExpr (Calculette des Expressions) [![build status](https://api.travis-ci.org/hussamaa/calculexpr.svg?branch=master)](https://travis-ci.org/hussamaa/calculexpr)

CalculExpr is tool for solving mathematical expressions written in **Java**. In particular, CalculExpr employs **Shunting Yard algorithm** and **Reverse Polish Notation** to solve the mathematical expressions. This tool supports expressions involving numbers, identifiers, basic operators (*e.g.*, **+**, **-**, \*, **/**), and functions (*e.g.,* **sin**, **cos**, **log**, and **sqrt**).

An explanation about the Reverse Polish Notation, Shunting Yard algorithm and its pseudocode can be found at [Wikipedia](https://en.wikipedia.org/wiki/Shunting-yard_algorithm).

## Grammar

CalculExpr accepts the mathematical expressions described by the grammar hereunder.

  ```c
expr       ::= expr binop expr | '-' expr sd| '(' expr ')' | binding | term | function  [ok]
binop      ::= '+' | '-' | '*' | '/'                                                    [ok]
binding    ::= identifier '=' expr                                                      [ok]
term       ::= number | identifier                                                      [ok]
number     ::= integer | decimal                                                        [ok]
integer    ::= '0' | ('1' - '9') ('0' - '9')*                                           [ok]
decimal    ::= ( integer )? '.' ('0' - '9')*                                            [ok]
identifier ::= ('a' - 'z' | 'A' - 'Z') ('a' - 'z' | 'A' - 'Z' | '0' - '9')*             [ok]
function   ::= ('sqrt' | 'log' | 'sin' | 'cos') '(' expr ')'                            [partial]
  ```
## Execution and Usage

To use CalculExpr, it is necessary to have at least Java Runtime Environment
Version 8.0 (jre1.8), and the executable can be found at [v1.0](https://github.com/hussamaa/calculexpr/releases/download/v1.0/calculexpr-v1.0.jar).

> java -jar calculexpr-v1.0.jar

```  
*********************************************
* Calculette des Expressions           v1.0 *
*********************************************

Commands available:
 :vars            - shows all temporary variables
 :clear <varname> - erases temporary variables
 :quit (or :exit) - finishes the calculator

Please type your expressions:
1 + 3 * (4 + 2) / 2
10.0
:quit
```
## Notes and Known Issues

* This project uses the Google Java Style Guide. For more information, please visit the website [https://google.github.io/styleguide/javaguide.html](https://google.github.io/styleguide/javaguide.html)

* The functions call support only one number or identifier as parameter.

* The basic lexical analyzer and the Shunting Yard algorithm are not able to detect all syntax problems and sometimes it does not return the exact root cause for the invalid expression.
