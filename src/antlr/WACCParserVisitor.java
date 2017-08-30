// Generated from ./BasicParser.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link WACCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface WACCParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link WACCParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(@NotNull WACCParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#arrayLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiter(@NotNull WACCParser.ArrayLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#arrayElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElem(@NotNull WACCParser.ArrayElemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryOperator}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(@NotNull WACCParser.UnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#assignLhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignLhs(@NotNull WACCParser.AssignLhsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pTwo}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPTwo(@NotNull WACCParser.PTwoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseTypeLabel}
	 * labeled alternative in {@link WACCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseTypeLabel(@NotNull WACCParser.BaseTypeLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pFive}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPFive(@NotNull WACCParser.PFiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull WACCParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseTypeLabelLabel}
	 * labeled alternative in {@link WACCParser#pairElemType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseTypeLabelLabel(@NotNull WACCParser.BaseTypeLabelLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code while}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(@NotNull WACCParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charlit}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharlit(@NotNull WACCParser.CharlitContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#functionIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionIdentifier(@NotNull WACCParser.FunctionIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code strlit}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrlit(@NotNull WACCParser.StrlitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continue}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue(@NotNull WACCParser.ContinueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(@NotNull WACCParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(@NotNull WACCParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(@NotNull WACCParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code read}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(@NotNull WACCParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code signed}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSigned(@NotNull WACCParser.SignedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pairElemLabel}
	 * labeled alternative in {@link WACCParser#assignRhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElemLabel(@NotNull WACCParser.PairElemLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayTypeLabel}
	 * labeled alternative in {@link WACCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayTypeLabel(@NotNull WACCParser.ArrayTypeLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code brackets}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrackets(@NotNull WACCParser.BracketsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code secondPair}
	 * labeled alternative in {@link WACCParser#pairElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSecondPair(@NotNull WACCParser.SecondPairContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exit}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExit(@NotNull WACCParser.ExitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayElemLabel}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElemLabel(@NotNull WACCParser.ArrayElemLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doWhile}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhile(@NotNull WACCParser.DoWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boollit}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoollit(@NotNull WACCParser.BoollitContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(@NotNull WACCParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intlit}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntlit(@NotNull WACCParser.IntlitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionIdent}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionIdent(@NotNull WACCParser.ExpressionIdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignmentEq}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentEq(@NotNull WACCParser.AssignmentEqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code begin}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBegin(@NotNull WACCParser.BeginContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayLiterLabel}
	 * labeled alternative in {@link WACCParser#assignRhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiterLabel(@NotNull WACCParser.ArrayLiterLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pairlit}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairlit(@NotNull WACCParser.PairlitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code firstPair}
	 * labeled alternative in {@link WACCParser#pairElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFirstPair(@NotNull WACCParser.FirstPairContext ctx);
	/**
	 * Visit a parse tree produced by the {@code for}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(@NotNull WACCParser.ForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(@NotNull WACCParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayTypeLabelLabel}
	 * labeled alternative in {@link WACCParser#pairElemType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayTypeLabelLabel(@NotNull WACCParser.ArrayTypeLabelLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(@NotNull WACCParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code println}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintln(@NotNull WACCParser.PrintlnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLabel}
	 * labeled alternative in {@link WACCParser#assignRhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLabel(@NotNull WACCParser.ExprLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code composition}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComposition(@NotNull WACCParser.CompositionContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(@NotNull WACCParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#defineIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineIdentifier(@NotNull WACCParser.DefineIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pairTypeLabel}
	 * labeled alternative in {@link WACCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairTypeLabel(@NotNull WACCParser.PairTypeLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code free}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree(@NotNull WACCParser.FreeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryOperatorIntegers}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorIntegers(@NotNull WACCParser.BinaryOperatorIntegersContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pThree}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPThree(@NotNull WACCParser.PThreeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pOne}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPOne(@NotNull WACCParser.POneContext ctx);
	/**
	 * Visit a parse tree produced by the {@code break}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak(@NotNull WACCParser.BreakContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignment}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull WACCParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pSix}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPSix(@NotNull WACCParser.PSixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declaration}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(@NotNull WACCParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pair}
	 * labeled alternative in {@link WACCParser#pairElemType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(@NotNull WACCParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#pairType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairType(@NotNull WACCParser.PairTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code call}
	 * labeled alternative in {@link WACCParser#assignRhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(@NotNull WACCParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code print}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(@NotNull WACCParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newpair}
	 * labeled alternative in {@link WACCParser#assignRhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewpair(@NotNull WACCParser.NewpairContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(@NotNull WACCParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pFour}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPFour(@NotNull WACCParser.PFourContext ctx);
	/**
	 * Visit a parse tree produced by the {@code return}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(@NotNull WACCParser.ReturnContext ctx);
}