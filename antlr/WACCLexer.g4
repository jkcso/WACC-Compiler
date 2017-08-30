lexer grammar BasicLexer;

/*---------------------------------------------------------------------------*/
// Program and Function Keywords

BEGIN: 'begin' ;
IS: 'is' ;
END: 'end' ;
RETURN: 'return' ;
CALL: 'call' ;

/*---------------------------------------------------------------------------*/
// Skip Keyword

SKP: 'skip' ;

/*---------------------------------------------------------------------------*/
// Skip Keyword

BREAK: 'break' ;

/*---------------------------------------------------------------------------*/
// Skip Keyword

CONTINUE: 'continue' ;

/*---------------------------------------------------------------------------*/
// Read Keyword

READ: 'read' ;

/*---------------------------------------------------------------------------*/
// Memory Free Keyword

FREE: 'free' ;

/*---------------------------------------------------------------------------*/
// Exit Keyword

EXIT: 'exit' ;

/*---------------------------------------------------------------------------*/
// Print Keywords

PRINT: 'print' ;
PRINTLN: 'println' ;

/*---------------------------------------------------------------------------*/
// Conditional Branch Keywords

IF: 'if' ;
THEN: 'then' ;
ELSE: 'else' ;
FI: 'fi' ;

/*---------------------------------------------------------------------------*/
// While Loop Keywords

WHILE: 'while' ;
DO: 'do' ;
DONE: 'done' ;

/*---------------------------------------------------------------------------*/
// For Loop Keywords

FOR: 'for' ;

/*---------------------------------------------------------------------------*/
// Pair Keywords

NEWPAIR: 'newpair' ;
PAIR: 'pair' ;
FST: 'fst' ;
SND: 'snd' ;

/*---------------------------------------------------------------------------*/
// type.Type Keywords

INT: 'int' ;
BOOL: 'bool' ;
CHAR: 'char' ;
STRING: 'string' ;
BYTE: 'byte' ; // This has been added for the extension of integers
SHORT: 'short' ; // This has been added for the extension of integers
LONG: 'long' ; // This has been added for the extension of integers

/*---------------------------------------------------------------------------*/
// Parentheses

LPAREN: '(' ;
RPAREN: ')' ;
SLPAREN: '[' ;
SRPAREN: ']' ;

/*---------------------------------------------------------------------------*/
// Unary Operators

NOT: '!' ;
//NEG: '-' ;
LEN: 'len' ;
ORD: 'ord' ;
CHR: 'chr' ;

/*---------------------------------------------------------------------------*/
// Binary Operators

MUL: '*' ;
DIV: '/' ;
MOD: '%' ;
PLUS: '+' ;
MINUS: '-' ;
GT: '>' ;
GTE: '>=' ;
LT: '<' ;
LTE: '<=' ;
EQ: '==' ;
NEQ: '!=' ;
AND: '&&' ;
OR: '||' ;

/*---------------------------------------------------------------------------*/
// Whitespace

WS: [ \t\n\r]+ -> skip ;

/*---------------------------------------------------------------------------*/
// Characters

fragment ESCCHAR: '0'
                | 'b'
                | 't'
                | 'n'
                | 'f'
                | 'r'
                | '\''
                | '\"'
                | '\\' ;

fragment CHARACTER: ~[\'"] | '\\' ESCCHAR ;
ASSIGN: '=' ;
ASSIGNEQ: '*='
        | '/='
        | '%='
        | '+='
        | '-=' 
 	| '++'
        | '--' ;
COMMA: ',' ;
SEMICOLON: ';' ;

/*---------------------------------------------------------------------------*/
// Numbers

fragment DIGIT: '0'..'9' ;
fragment SIGN: PLUS | MINUS ;

/*---------------------------------------------------------------------------*/
// NullType

fragment NULL: 'null' ;

/*---------------------------------------------------------------------------*/
// Comments

COMMENT: '#' ~[\r\n]* '\r'? '\n' -> skip ;

/*---------------------------------------------------------------------------*/
// Literals

SIGNEDINTLIT: SIGN DIGIT+ ;
INTLIT: DIGIT+ ;
BOOLLIT: 'true' | 'false' ;
CHARLIT: '\'' CHARACTER '\'' ;
STRLIT: '\"' (CHARACTER)* '\"' ;
PAIRLIT: NULL ;

/*---------------------------------------------------------------------------*/
//Identifier

IDENT: [_a-zA-Z] [_a-zA-Z0-9]* ;
