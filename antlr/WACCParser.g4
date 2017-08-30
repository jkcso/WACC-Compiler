parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

/*---------------------------------------------------------------------------*/
// Program/function rules

program: BEGIN func* stat END EOF ;
func: type functionIdentifier LPAREN paramList? RPAREN IS stat END ;

/*---------------------------------------------------------------------------*/
// Statement rules

stat: SKP                                                               # skip
    | BREAK                                                             # break
    | CONTINUE                                                          # continue
    | type defineIdentifier ASSIGN assignRhs                            # declaration
    | assignLhs ASSIGN assignRhs                                        # assignment
    | identifier ASSIGNEQ expr?                                         # assignmentEq
    | READ assignLhs                                                    # read
    | FREE expr                                                         # free
    | RETURN expr                                                       # return
    | EXIT expr                                                         # exit
    | PRINT expr                                                        # print
    | PRINTLN expr                                                      # println
    | IF expr THEN stat ELSE stat FI                                    # if
    | WHILE expr DO stat DONE                                           # while
    | DO stat WHILE expr                                                # doWhile
    | FOR LPAREN stat SEMICOLON expr SEMICOLON stat RPAREN stat DONE    # for
    | BEGIN stat END                                                    # begin
    | stat SEMICOLON stat                                               # composition
    ;

/*---------------------------------------------------------------------------*/
// Assignment rules

assignLhs: identifier
         | arrayElem
         | pairElem
         ;

assignRhs: expr                                                  # exprLabel
         | arrayLiter                                            # arrayLiterLabel // this.visit(ctx.arrayLiter())
         | NEWPAIR LPAREN expr COMMA expr RPAREN                 # newpair
         | pairElem                                              # pairElemLabel // this.visit(ctx.pairElem())
         | CALL functionIdentifier LPAREN argList? RPAREN        # call
         ;

/*---------------------------------------------------------------------------*/
// Typing rules

type: baseType              # baseTypeLabel // this.visit(ctx.baseType())
    | type SLPAREN SRPAREN  # arrayTypeLabel // this.visit(ctx.arrayType()) ?
    | pairType              # pairTypeLabel // this.visit(ctx.pairType())
    ;

baseType: INT
        | BOOL
        | CHAR
        | STRING
        | BYTE // This has been added for the extension of integers
        | SHORT // This has been added for the extension of integers
        | LONG // This has been added for the extension of integers
        ;

arrayType: type SLPAREN SRPAREN ;

pairType: PAIR LPAREN pairElemType COMMA pairElemType RPAREN ;

pairElemType: baseType      # baseTypeLabelLabel  // this.visit(ctx.baseType())
            | arrayType     # arrayTypeLabelLabel // this.visit(ctx.arrayType())
            | PAIR          # pair
            ;

/*---------------------------------------------------------------------------*/
// Expression

expr: INTLIT                                        # intlit
    | SIGNEDINTLIT                                  # signed
    | BOOLLIT                                       # boollit
    | CHARLIT                                       # charlit
    | STRLIT                                        # strlit
    | PAIRLIT                                       # pairlit
    | identifier                                    # expressionIdent
    | arrayElem                                     # arrayElemLabel
    | (NOT | MINUS | LEN | ORD | CHR) expr          # unaryOperator
    | (INTLIT | SIGNEDINTLIT) SIGNEDINTLIT          # binaryOperatorIntegers
    | expr (MUL | DIV | MOD) expr                   # pOne
    | expr (PLUS | MINUS) expr                      # pTwo
    | expr (GT | GTE | LT | LTE) expr               # pThree
    | expr (EQ | NEQ) expr                          # pFour
    | expr (AND) expr                               # pFive
    | expr (OR) expr                                # pSix
    | LPAREN expr RPAREN                            # brackets
    ;

/*---------------------------------------------------------------------------*/
// Array rules

arrayElem: identifier (SLPAREN expr SRPAREN)+ ;
arrayLiter: SLPAREN (expr (COMMA expr)* )? SRPAREN ;

/*---------------------------------------------------------------------------*/
// Parameter rules

paramList: param (COMMA param)* ;
param: type defineIdentifier ;

/*---------------------------------------------------------------------------*/
// Pair rules

pairElem: FST expr      # firstPair
        | SND expr      # secondPair
        ;

/*---------------------------------------------------------------------------*/
// List rule

argList: expr (COMMA expr)* ;

/*---------------------------------------------------------------------------*/

identifier: IDENT ;
defineIdentifier: IDENT ;
functionIdentifier: IDENT ;
