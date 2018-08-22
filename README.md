# WACC

## Synopsis
This repository is about the WACC language compilers lab.  WACC (pronounced “whack”) is a simple variant on the While family of languages encountered in many program reasoning/verification courses (in particular in the Models of Computation course taught to our 2nd year undergraduates of Imperial College London).  It features all of the common language constructs you would expect of a While-like language, such as program variables, simple expressions, conditional branching, looping and no-ops. It also features a rich set of extra constructs, such as simple types, functions, arrays and basic tuple creation on the heap.  The core of the language should be simple enough to reason about and the extensions should pose some interesting challenges and design choices for anyone implementing it.

## Files/Directories explained
### antlr
The antlr directory contains an ANTLR lexer and parser specification for the WACC language along with a script that builds the corresponding Java class files using the ANTLR libraries (more details below).

### src

- antlr:contains the generated lexer and parser.

- arm: contains the code used for the assembly machine language.  Each class is used for a different operation to generate plain text containing the name of the instruction and the operands (if any) including the specific register that the instruction will be loaded or stored etc.

- errors: this directory contains the 3 different type of errors that can occur when programming in the WACC language.  These include Syntax and Semantic errors.

- expr_nodes: contains a different node for each different instance of expressions.  These include: Arguments, Arrays, Array Elements, Binary Operations, Booleans, Bytes, Calls to other functions(such as substitutions), Characters, Expressions into Expressions, Identifiers Integers, Literals, Pairs, Pair Elements, Parameters of any of the aforementioned types, Shorts, different Types, and Unary operations.

- extension: contains the work we have done to extend this project.  The main idea was to build some functionality that we would think it would be important, helpful or missing from WACC language.  My team implemented for loops, do while loops, optimisations of the assembly language, new types of data such as bytes and shorts, additional loop statements such as continue and break, nested pairs and side effecting expressions.

- node: contains Node abstract class and Translatable interface.  Both used as the highest peak of the hierarchy for all type of nodes such as expression nodes and machine code respectively.

- semantics: contains 3 classes that are the primary source of semantic errors. Firstly the function class is responsible for all functions, the parser visitor is responsible to observer that when parsing, everything is semantically correct and last but not least, the symbol table that keeps track of the scope of each function.

- stat_nodes: Holds all classes responsible to represent statements such as Assignment statement, break, continue, declarations, loop statements (while, do while, for), if statement, print and return.

- translator: this directory contains one class which acts like a brain, handling all possible cases of code translation.  What it does is to get WACC language code, recognise the type of statement or expressions, and send this to a corresponding function responsible to perform this specific translation.

- type: contains all possible language types that can be assigned into an expressions.  This includes the array type, boolean, bytes, characters, integers, ints, nulls, pairs, shorts, strings and the type object itself.

### tests
Contains all the tests of the extension we made to the language.  This happens because we were given the test
directory for the WACC language in order for all of us (including different groups of peoples in other teams
to test code in mutual testsuite.  For the extension, each team followed a different implementation therefore
we needed a tailor made test suite for the extension we followed.

## grun
The grun script allows us to run the ANTLR TestRig program that can assist in
the debugging you lexer and parser (more details below).

## compile
The compile script provides a frontend interface to our WACC compiler.

## Makefile
Makefile is running 'make' in the root directory to build the WACC compiler.
Starts from the antlrBuild script and the attempt to compile all .java files
within the src directory.

## License
1. The copyright of this project belongs to Imperial College London.
2. Part of my work is intentionally broken or misleading to avoid disrespectful people from copying and pasting.
