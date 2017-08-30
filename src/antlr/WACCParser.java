// Generated from ./BasicParser.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WACCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BEGIN=1, IS=2, END=3, RETURN=4, CALL=5, SKP=6, BREAK=7, CONTINUE=8, READ=9, 
		FREE=10, EXIT=11, PRINT=12, PRINTLN=13, IF=14, THEN=15, ELSE=16, FI=17, 
		WHILE=18, DO=19, DONE=20, FOR=21, NEWPAIR=22, PAIR=23, FST=24, SND=25, 
		INT=26, BOOL=27, CHAR=28, STRING=29, BYTE=30, SHORT=31, LONG=32, LPAREN=33, 
		RPAREN=34, SLPAREN=35, SRPAREN=36, NOT=37, LEN=38, ORD=39, CHR=40, MUL=41, 
		DIV=42, MOD=43, PLUS=44, MINUS=45, GT=46, GTE=47, LT=48, LTE=49, EQ=50, 
		NEQ=51, AND=52, OR=53, WS=54, ASSIGN=55, ASSIGNEQ=56, COMMA=57, SEMICOLON=58, 
		COMMENT=59, SIGNEDINTLIT=60, INTLIT=61, BOOLLIT=62, CHARLIT=63, STRLIT=64, 
		PAIRLIT=65, IDENT=66;
	public static final String[] tokenNames = {
		"<INVALID>", "'begin'", "'is'", "'end'", "'return'", "'call'", "'skip'", 
		"'break'", "'continue'", "'read'", "'free'", "'exit'", "'print'", "'println'", 
		"'if'", "'then'", "'else'", "'fi'", "'while'", "'do'", "'done'", "'for'", 
		"'newpair'", "'pair'", "'fst'", "'snd'", "'int'", "'bool'", "'char'", 
		"'string'", "'byte'", "'short'", "'long'", "'('", "')'", "'['", "']'", 
		"'!'", "'len'", "'ord'", "'chr'", "'*'", "'/'", "'%'", "'+'", "'-'", "'>'", 
		"'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", "WS", "'='", "ASSIGNEQ", 
		"','", "';'", "COMMENT", "SIGNEDINTLIT", "INTLIT", "BOOLLIT", "CHARLIT", 
		"STRLIT", "PAIRLIT", "IDENT"
	};
	public static final int
		RULE_program = 0, RULE_func = 1, RULE_stat = 2, RULE_assignLhs = 3, RULE_assignRhs = 4, 
		RULE_type = 5, RULE_baseType = 6, RULE_arrayType = 7, RULE_pairType = 8, 
		RULE_pairElemType = 9, RULE_expr = 10, RULE_arrayElem = 11, RULE_arrayLiter = 12, 
		RULE_paramList = 13, RULE_param = 14, RULE_pairElem = 15, RULE_argList = 16, 
		RULE_identifier = 17, RULE_defineIdentifier = 18, RULE_functionIdentifier = 19;
	public static final String[] ruleNames = {
		"program", "func", "stat", "assignLhs", "assignRhs", "type", "baseType", 
		"arrayType", "pairType", "pairElemType", "expr", "arrayElem", "arrayLiter", 
		"paramList", "param", "pairElem", "argList", "identifier", "defineIdentifier", 
		"functionIdentifier"
	};

	@Override
	public String getGrammarFileName() { return "WACCParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public WACCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(WACCParser.EOF, 0); }
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public TerminalNode BEGIN() { return getToken(WACCParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(WACCParser.END, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(40); match(BEGIN);
			setState(44);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(41); func();
					}
					} 
				}
				setState(46);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(47); stat(0);
			setState(48); match(END);
			setState(49); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(WACCParser.IS, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(WACCParser.LPAREN, 0); }
		public TerminalNode END() { return getToken(WACCParser.END, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(WACCParser.RPAREN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public FunctionIdentifierContext functionIdentifier() {
			return getRuleContext(FunctionIdentifierContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51); type(0);
			setState(52); functionIdentifier();
			setState(53); match(LPAREN);
			setState(55);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << BYTE) | (1L << SHORT) | (1L << LONG))) != 0)) {
				{
				setState(54); paramList();
				}
			}

			setState(57); match(RPAREN);
			setState(58); match(IS);
			setState(59); stat(0);
			setState(60); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReadContext extends StatContext {
		public AssignLhsContext assignLhs() {
			return getRuleContext(AssignLhsContext.class,0);
		}
		public TerminalNode READ() { return getToken(WACCParser.READ, 0); }
		public ReadContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitRead(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakContext extends StatContext {
		public TerminalNode BREAK() { return getToken(WACCParser.BREAK, 0); }
		public BreakContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitBreak(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignmentContext extends StatContext {
		public TerminalNode ASSIGN() { return getToken(WACCParser.ASSIGN, 0); }
		public AssignLhsContext assignLhs() {
			return getRuleContext(AssignLhsContext.class,0);
		}
		public AssignRhsContext assignRhs() {
			return getRuleContext(AssignRhsContext.class,0);
		}
		public AssignmentContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForContext extends StatContext {
		public TerminalNode DONE() { return getToken(WACCParser.DONE, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(WACCParser.SEMICOLON); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(WACCParser.LPAREN, 0); }
		public TerminalNode FOR() { return getToken(WACCParser.FOR, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode SEMICOLON(int i) {
			return getToken(WACCParser.SEMICOLON, i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public TerminalNode RPAREN() { return getToken(WACCParser.RPAREN, 0); }
		public ForContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitFor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SkipContext extends StatContext {
		public TerminalNode SKP() { return getToken(WACCParser.SKP, 0); }
		public SkipContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitSkip(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileContext extends StatContext {
		public TerminalNode DONE() { return getToken(WACCParser.DONE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(WACCParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(WACCParser.WHILE, 0); }
		public WhileContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclarationContext extends StatContext {
		public TerminalNode ASSIGN() { return getToken(WACCParser.ASSIGN, 0); }
		public AssignRhsContext assignRhs() {
			return getRuleContext(AssignRhsContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public DefineIdentifierContext defineIdentifier() {
			return getRuleContext(DefineIdentifierContext.class,0);
		}
		public DeclarationContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExitContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EXIT() { return getToken(WACCParser.EXIT, 0); }
		public ExitContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitExit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintContext extends StatContext {
		public TerminalNode PRINT() { return getToken(WACCParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintlnContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PRINTLN() { return getToken(WACCParser.PRINTLN, 0); }
		public PrintlnContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPrintln(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoWhileContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(WACCParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(WACCParser.WHILE, 0); }
		public DoWhileContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitDoWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompositionContext extends StatContext {
		public TerminalNode SEMICOLON() { return getToken(WACCParser.SEMICOLON, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public CompositionContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitComposition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueContext extends StatContext {
		public TerminalNode CONTINUE() { return getToken(WACCParser.CONTINUE, 0); }
		public ContinueContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitContinue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignmentEqContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNEQ() { return getToken(WACCParser.ASSIGNEQ, 0); }
		public AssignmentEqContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitAssignmentEq(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FreeContext extends StatContext {
		public TerminalNode FREE() { return getToken(WACCParser.FREE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FreeContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitFree(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends StatContext {
		public TerminalNode FI() { return getToken(WACCParser.FI, 0); }
		public TerminalNode ELSE() { return getToken(WACCParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(WACCParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(WACCParser.THEN, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public IfContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BeginContext extends StatContext {
		public TerminalNode BEGIN() { return getToken(WACCParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(WACCParser.END, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public BeginContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitBegin(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnContext extends StatContext {
		public TerminalNode RETURN() { return getToken(WACCParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		return stat(0);
	}

	private StatContext stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatContext _localctx = new StatContext(_ctx, _parentState);
		StatContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				_localctx = new SkipContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(63); match(SKP);
				}
				break;
			case 2:
				{
				_localctx = new BreakContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(64); match(BREAK);
				}
				break;
			case 3:
				{
				_localctx = new ContinueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(65); match(CONTINUE);
				}
				break;
			case 4:
				{
				_localctx = new DeclarationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(66); type(0);
				setState(67); defineIdentifier();
				setState(68); match(ASSIGN);
				setState(69); assignRhs();
				}
				break;
			case 5:
				{
				_localctx = new AssignmentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(71); assignLhs();
				setState(72); match(ASSIGN);
				setState(73); assignRhs();
				}
				break;
			case 6:
				{
				_localctx = new AssignmentEqContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(75); identifier();
				setState(76); match(ASSIGNEQ);
				setState(78);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(77); expr(0);
					}
					break;
				}
				}
				break;
			case 7:
				{
				_localctx = new ReadContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(80); match(READ);
				setState(81); assignLhs();
				}
				break;
			case 8:
				{
				_localctx = new FreeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82); match(FREE);
				setState(83); expr(0);
				}
				break;
			case 9:
				{
				_localctx = new ReturnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(84); match(RETURN);
				setState(85); expr(0);
				}
				break;
			case 10:
				{
				_localctx = new ExitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86); match(EXIT);
				setState(87); expr(0);
				}
				break;
			case 11:
				{
				_localctx = new PrintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88); match(PRINT);
				setState(89); expr(0);
				}
				break;
			case 12:
				{
				_localctx = new PrintlnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(90); match(PRINTLN);
				setState(91); expr(0);
				}
				break;
			case 13:
				{
				_localctx = new IfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92); match(IF);
				setState(93); expr(0);
				setState(94); match(THEN);
				setState(95); stat(0);
				setState(96); match(ELSE);
				setState(97); stat(0);
				setState(98); match(FI);
				}
				break;
			case 14:
				{
				_localctx = new WhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(100); match(WHILE);
				setState(101); expr(0);
				setState(102); match(DO);
				setState(103); stat(0);
				setState(104); match(DONE);
				}
				break;
			case 15:
				{
				_localctx = new DoWhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106); match(DO);
				setState(107); stat(0);
				setState(108); match(WHILE);
				setState(109); expr(0);
				}
				break;
			case 16:
				{
				_localctx = new ForContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(111); match(FOR);
				setState(112); match(LPAREN);
				setState(113); stat(0);
				setState(114); match(SEMICOLON);
				setState(115); expr(0);
				setState(116); match(SEMICOLON);
				setState(117); stat(0);
				setState(118); match(RPAREN);
				setState(119); stat(0);
				setState(120); match(DONE);
				}
				break;
			case 17:
				{
				_localctx = new BeginContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122); match(BEGIN);
				setState(123); stat(0);
				setState(124); match(END);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(133);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CompositionContext(new StatContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(128);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(129); match(SEMICOLON);
					setState(130); stat(2);
					}
					} 
				}
				setState(135);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignLhsContext extends ParserRuleContext {
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public AssignLhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignLhs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitAssignLhs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignLhsContext assignLhs() throws RecognitionException {
		AssignLhsContext _localctx = new AssignLhsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assignLhs);
		try {
			setState(139);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136); identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137); arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138); pairElem();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignRhsContext extends ParserRuleContext {
		public AssignRhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRhs; }
	 
		public AssignRhsContext() { }
		public void copyFrom(AssignRhsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayLiterLabelContext extends AssignRhsContext {
		public ArrayLiterContext arrayLiter() {
			return getRuleContext(ArrayLiterContext.class,0);
		}
		public ArrayLiterLabelContext(AssignRhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitArrayLiterLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallContext extends AssignRhsContext {
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(WACCParser.LPAREN, 0); }
		public TerminalNode CALL() { return getToken(WACCParser.CALL, 0); }
		public TerminalNode RPAREN() { return getToken(WACCParser.RPAREN, 0); }
		public FunctionIdentifierContext functionIdentifier() {
			return getRuleContext(FunctionIdentifierContext.class,0);
		}
		public CallContext(AssignRhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewpairContext extends AssignRhsContext {
		public TerminalNode NEWPAIR() { return getToken(WACCParser.NEWPAIR, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode LPAREN() { return getToken(WACCParser.LPAREN, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(WACCParser.COMMA, 0); }
		public TerminalNode RPAREN() { return getToken(WACCParser.RPAREN, 0); }
		public NewpairContext(AssignRhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitNewpair(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprLabelContext extends AssignRhsContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprLabelContext(AssignRhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitExprLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairElemLabelContext extends AssignRhsContext {
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public PairElemLabelContext(AssignRhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPairElemLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRhsContext assignRhs() throws RecognitionException {
		AssignRhsContext _localctx = new AssignRhsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignRhs);
		int _la;
		try {
			setState(159);
			switch (_input.LA(1)) {
			case LPAREN:
			case NOT:
			case LEN:
			case ORD:
			case CHR:
			case MINUS:
			case SIGNEDINTLIT:
			case INTLIT:
			case BOOLLIT:
			case CHARLIT:
			case STRLIT:
			case PAIRLIT:
			case IDENT:
				_localctx = new ExprLabelContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(141); expr(0);
				}
				break;
			case SLPAREN:
				_localctx = new ArrayLiterLabelContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(142); arrayLiter();
				}
				break;
			case NEWPAIR:
				_localctx = new NewpairContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(143); match(NEWPAIR);
				setState(144); match(LPAREN);
				setState(145); expr(0);
				setState(146); match(COMMA);
				setState(147); expr(0);
				setState(148); match(RPAREN);
				}
				break;
			case FST:
			case SND:
				_localctx = new PairElemLabelContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(150); pairElem();
				}
				break;
			case CALL:
				_localctx = new CallContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(151); match(CALL);
				setState(152); functionIdentifier();
				setState(153); match(LPAREN);
				setState(155);
				_la = _input.LA(1);
				if (((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (LPAREN - 33)) | (1L << (NOT - 33)) | (1L << (LEN - 33)) | (1L << (ORD - 33)) | (1L << (CHR - 33)) | (1L << (MINUS - 33)) | (1L << (SIGNEDINTLIT - 33)) | (1L << (INTLIT - 33)) | (1L << (BOOLLIT - 33)) | (1L << (CHARLIT - 33)) | (1L << (STRLIT - 33)) | (1L << (PAIRLIT - 33)) | (1L << (IDENT - 33)))) != 0)) {
					{
					setState(154); argList();
					}
				}

				setState(157); match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PairTypeLabelContext extends TypeContext {
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public PairTypeLabelContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPairTypeLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BaseTypeLabelContext extends TypeContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public BaseTypeLabelContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitBaseTypeLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayTypeLabelContext extends TypeContext {
		public TerminalNode SLPAREN() { return getToken(WACCParser.SLPAREN, 0); }
		public TerminalNode SRPAREN() { return getToken(WACCParser.SRPAREN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArrayTypeLabelContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitArrayTypeLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
			case BYTE:
			case SHORT:
			case LONG:
				{
				_localctx = new BaseTypeLabelContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(162); baseType();
				}
				break;
			case PAIR:
				{
				_localctx = new PairTypeLabelContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(163); pairType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(171);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayTypeLabelContext(new TypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(166);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(167); match(SLPAREN);
					setState(168); match(SRPAREN);
					}
					} 
				}
				setState(173);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode SHORT() { return getToken(WACCParser.SHORT, 0); }
		public TerminalNode BOOL() { return getToken(WACCParser.BOOL, 0); }
		public TerminalNode LONG() { return getToken(WACCParser.LONG, 0); }
		public TerminalNode STRING() { return getToken(WACCParser.STRING, 0); }
		public TerminalNode BYTE() { return getToken(WACCParser.BYTE, 0); }
		public TerminalNode CHAR() { return getToken(WACCParser.CHAR, 0); }
		public TerminalNode INT() { return getToken(WACCParser.INT, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << BYTE) | (1L << SHORT) | (1L << LONG))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public TerminalNode SLPAREN() { return getToken(WACCParser.SLPAREN, 0); }
		public TerminalNode SRPAREN() { return getToken(WACCParser.SRPAREN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); type(0);
			setState(177); match(SLPAREN);
			setState(178); match(SRPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairTypeContext extends ParserRuleContext {
		public PairElemTypeContext pairElemType(int i) {
			return getRuleContext(PairElemTypeContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(WACCParser.LPAREN, 0); }
		public List<PairElemTypeContext> pairElemType() {
			return getRuleContexts(PairElemTypeContext.class);
		}
		public TerminalNode PAIR() { return getToken(WACCParser.PAIR, 0); }
		public TerminalNode COMMA() { return getToken(WACCParser.COMMA, 0); }
		public TerminalNode RPAREN() { return getToken(WACCParser.RPAREN, 0); }
		public PairTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPairType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairTypeContext pairType() throws RecognitionException {
		PairTypeContext _localctx = new PairTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); match(PAIR);
			setState(181); match(LPAREN);
			setState(182); pairElemType();
			setState(183); match(COMMA);
			setState(184); pairElemType();
			setState(185); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemTypeContext extends ParserRuleContext {
		public PairElemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElemType; }
	 
		public PairElemTypeContext() { }
		public void copyFrom(PairElemTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BaseTypeLabelLabelContext extends PairElemTypeContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public BaseTypeLabelLabelContext(PairElemTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitBaseTypeLabelLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayTypeLabelLabelContext extends PairElemTypeContext {
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public ArrayTypeLabelLabelContext(PairElemTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitArrayTypeLabelLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairContext extends PairElemTypeContext {
		public TerminalNode PAIR() { return getToken(WACCParser.PAIR, 0); }
		public PairContext(PairElemTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemTypeContext pairElemType() throws RecognitionException {
		PairElemTypeContext _localctx = new PairElemTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pairElemType);
		try {
			setState(190);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new BaseTypeLabelLabelContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(187); baseType();
				}
				break;
			case 2:
				_localctx = new ArrayTypeLabelLabelContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(188); arrayType();
				}
				break;
			case 3:
				_localctx = new PairContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(189); match(PAIR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PairlitContext extends ExprContext {
		public TerminalNode PAIRLIT() { return getToken(WACCParser.PAIRLIT, 0); }
		public PairlitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPairlit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOperatorIntegersContext extends ExprContext {
		public TerminalNode INTLIT() { return getToken(WACCParser.INTLIT, 0); }
		public List<TerminalNode> SIGNEDINTLIT() { return getTokens(WACCParser.SIGNEDINTLIT); }
		public TerminalNode SIGNEDINTLIT(int i) {
			return getToken(WACCParser.SIGNEDINTLIT, i);
		}
		public BinaryOperatorIntegersContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitBinaryOperatorIntegers(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PThreeContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode GTE() { return getToken(WACCParser.GTE, 0); }
		public TerminalNode LT() { return getToken(WACCParser.LT, 0); }
		public TerminalNode GT() { return getToken(WACCParser.GT, 0); }
		public TerminalNode LTE() { return getToken(WACCParser.LTE, 0); }
		public PThreeContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPThree(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class POneContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MOD() { return getToken(WACCParser.MOD, 0); }
		public TerminalNode DIV() { return getToken(WACCParser.DIV, 0); }
		public TerminalNode MUL() { return getToken(WACCParser.MUL, 0); }
		public POneContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPOne(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryOperatorContext extends ExprContext {
		public TerminalNode NOT() { return getToken(WACCParser.NOT, 0); }
		public TerminalNode ORD() { return getToken(WACCParser.ORD, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LEN() { return getToken(WACCParser.LEN, 0); }
		public TerminalNode MINUS() { return getToken(WACCParser.MINUS, 0); }
		public TerminalNode CHR() { return getToken(WACCParser.CHR, 0); }
		public UnaryOperatorContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PSixContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(WACCParser.OR, 0); }
		public PSixContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPSix(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PTwoContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(WACCParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(WACCParser.MINUS, 0); }
		public PTwoContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPTwo(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SignedContext extends ExprContext {
		public TerminalNode SIGNEDINTLIT() { return getToken(WACCParser.SIGNEDINTLIT, 0); }
		public SignedContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitSigned(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PFiveContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(WACCParser.AND, 0); }
		public PFiveContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPFive(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharlitContext extends ExprContext {
		public TerminalNode CHARLIT() { return getToken(WACCParser.CHARLIT, 0); }
		public CharlitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitCharlit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BracketsContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(WACCParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(WACCParser.RPAREN, 0); }
		public BracketsContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitBrackets(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StrlitContext extends ExprContext {
		public TerminalNode STRLIT() { return getToken(WACCParser.STRLIT, 0); }
		public StrlitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitStrlit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayElemLabelContext extends ExprContext {
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public ArrayElemLabelContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitArrayElemLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoollitContext extends ExprContext {
		public TerminalNode BOOLLIT() { return getToken(WACCParser.BOOLLIT, 0); }
		public BoollitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitBoollit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntlitContext extends ExprContext {
		public TerminalNode INTLIT() { return getToken(WACCParser.INTLIT, 0); }
		public IntlitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitIntlit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionIdentContext extends ExprContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExpressionIdentContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitExpressionIdent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PFourContext extends ExprContext {
		public TerminalNode NEQ() { return getToken(WACCParser.NEQ, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQ() { return getToken(WACCParser.EQ, 0); }
		public PFourContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitPFour(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryOperatorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(193);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << MINUS))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(194); expr(9);
				}
				break;
			case 2:
				{
				_localctx = new IntlitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(195); match(INTLIT);
				}
				break;
			case 3:
				{
				_localctx = new SignedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(196); match(SIGNEDINTLIT);
				}
				break;
			case 4:
				{
				_localctx = new BoollitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(197); match(BOOLLIT);
				}
				break;
			case 5:
				{
				_localctx = new CharlitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(198); match(CHARLIT);
				}
				break;
			case 6:
				{
				_localctx = new StrlitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(199); match(STRLIT);
				}
				break;
			case 7:
				{
				_localctx = new PairlitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(200); match(PAIRLIT);
				}
				break;
			case 8:
				{
				_localctx = new ExpressionIdentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(201); identifier();
				}
				break;
			case 9:
				{
				_localctx = new ArrayElemLabelContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(202); arrayElem();
				}
				break;
			case 10:
				{
				_localctx = new BinaryOperatorIntegersContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(203);
				_la = _input.LA(1);
				if ( !(_la==SIGNEDINTLIT || _la==INTLIT) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(204); match(SIGNEDINTLIT);
				}
				break;
			case 11:
				{
				_localctx = new BracketsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(205); match(LPAREN);
				setState(206); expr(0);
				setState(207); match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(231);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(229);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new POneContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(211);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(212);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(213); expr(8);
						}
						break;
					case 2:
						{
						_localctx = new PTwoContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(214);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(215);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(216); expr(7);
						}
						break;
					case 3:
						{
						_localctx = new PThreeContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(217);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(218);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << GTE) | (1L << LT) | (1L << LTE))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(219); expr(6);
						}
						break;
					case 4:
						{
						_localctx = new PFourContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(220);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(221);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(222); expr(5);
						}
						break;
					case 5:
						{
						_localctx = new PFiveContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(223);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						{
						setState(224); match(AND);
						}
						setState(225); expr(4);
						}
						break;
					case 6:
						{
						_localctx = new PSixContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(226);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						{
						setState(227); match(OR);
						}
						setState(228); expr(3);
						}
						break;
					}
					} 
				}
				setState(233);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArrayElemContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> SLPAREN() { return getTokens(WACCParser.SLPAREN); }
		public List<TerminalNode> SRPAREN() { return getTokens(WACCParser.SRPAREN); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode SLPAREN(int i) {
			return getToken(WACCParser.SLPAREN, i);
		}
		public TerminalNode SRPAREN(int i) {
			return getToken(WACCParser.SRPAREN, i);
		}
		public ArrayElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitArrayElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElemContext arrayElem() throws RecognitionException {
		ArrayElemContext _localctx = new ArrayElemContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(234); identifier();
			setState(239); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(235); match(SLPAREN);
					setState(236); expr(0);
					setState(237); match(SRPAREN);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(241); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiterContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WACCParser.COMMA); }
		public TerminalNode SLPAREN() { return getToken(WACCParser.SLPAREN, 0); }
		public TerminalNode SRPAREN() { return getToken(WACCParser.SRPAREN, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(WACCParser.COMMA, i);
		}
		public ArrayLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitArrayLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiterContext arrayLiter() throws RecognitionException {
		ArrayLiterContext _localctx = new ArrayLiterContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243); match(SLPAREN);
			setState(252);
			_la = _input.LA(1);
			if (((((_la - 33)) & ~0x3f) == 0 && ((1L << (_la - 33)) & ((1L << (LPAREN - 33)) | (1L << (NOT - 33)) | (1L << (LEN - 33)) | (1L << (ORD - 33)) | (1L << (CHR - 33)) | (1L << (MINUS - 33)) | (1L << (SIGNEDINTLIT - 33)) | (1L << (INTLIT - 33)) | (1L << (BOOLLIT - 33)) | (1L << (CHARLIT - 33)) | (1L << (STRLIT - 33)) | (1L << (PAIRLIT - 33)) | (1L << (IDENT - 33)))) != 0)) {
				{
				setState(244); expr(0);
				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(245); match(COMMA);
					setState(246); expr(0);
					}
					}
					setState(251);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(254); match(SRPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(WACCParser.COMMA); }
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(WACCParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256); param();
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(257); match(COMMA);
				setState(258); param();
				}
				}
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public DefineIdentifierContext defineIdentifier() {
			return getRuleContext(DefineIdentifierContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264); type(0);
			setState(265); defineIdentifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemContext extends ParserRuleContext {
		public PairElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElem; }
	 
		public PairElemContext() { }
		public void copyFrom(PairElemContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FirstPairContext extends PairElemContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FST() { return getToken(WACCParser.FST, 0); }
		public FirstPairContext(PairElemContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitFirstPair(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SecondPairContext extends PairElemContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SND() { return getToken(WACCParser.SND, 0); }
		public SecondPairContext(PairElemContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitSecondPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemContext pairElem() throws RecognitionException {
		PairElemContext _localctx = new PairElemContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_pairElem);
		try {
			setState(271);
			switch (_input.LA(1)) {
			case FST:
				_localctx = new FirstPairContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(267); match(FST);
				setState(268); expr(0);
				}
				break;
			case SND:
				_localctx = new SecondPairContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(269); match(SND);
				setState(270); expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WACCParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WACCParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273); expr(0);
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(274); match(COMMA);
				setState(275); expr(0);
				}
				}
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(WACCParser.IDENT, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281); match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefineIdentifierContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(WACCParser.IDENT, 0); }
		public DefineIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineIdentifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitDefineIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineIdentifierContext defineIdentifier() throws RecognitionException {
		DefineIdentifierContext _localctx = new DefineIdentifierContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_defineIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283); match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionIdentifierContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(WACCParser.IDENT, 0); }
		public FunctionIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionIdentifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor) return ((WACCParserVisitor<? extends T>)visitor).visitFunctionIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionIdentifierContext functionIdentifier() throws RecognitionException {
		FunctionIdentifierContext _localctx = new FunctionIdentifierContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_functionIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285); match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2: return stat_sempred((StatContext)_localctx, predIndex);
		case 5: return type_sempred((TypeContext)_localctx, predIndex);
		case 10: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return precpred(_ctx, 7);
		case 3: return precpred(_ctx, 6);
		case 4: return precpred(_ctx, 5);
		case 5: return precpred(_ctx, 4);
		case 6: return precpred(_ctx, 3);
		case 7: return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3D\u0122\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\7\2-\n\2\f\2\16\2\60\13\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3:\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4Q\n\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0081\n\4\3\4\3\4\3\4\7\4\u0086"+
		"\n\4\f\4\16\4\u0089\13\4\3\5\3\5\3\5\5\5\u008e\n\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u009e\n\6\3\6\3\6\5\6\u00a2\n"+
		"\6\3\7\3\7\3\7\5\7\u00a7\n\7\3\7\3\7\3\7\7\7\u00ac\n\7\f\7\16\7\u00af"+
		"\13\7\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\5\13\u00c1\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\f\u00d4\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00e8\n\f\f\f\16\f\u00eb\13\f\3"+
		"\r\3\r\3\r\3\r\3\r\6\r\u00f2\n\r\r\r\16\r\u00f3\3\16\3\16\3\16\3\16\7"+
		"\16\u00fa\n\16\f\16\16\16\u00fd\13\16\5\16\u00ff\n\16\3\16\3\16\3\17\3"+
		"\17\3\17\7\17\u0106\n\17\f\17\16\17\u0109\13\17\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\5\21\u0112\n\21\3\22\3\22\3\22\7\22\u0117\n\22\f\22\16\22"+
		"\u011a\13\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\2\5\6\f\26\26\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\t\3\2\34\"\4\2\'*//\3\2>?\3\2"+
		"+-\3\2./\3\2\60\63\3\2\64\65\u0142\2*\3\2\2\2\4\65\3\2\2\2\6\u0080\3\2"+
		"\2\2\b\u008d\3\2\2\2\n\u00a1\3\2\2\2\f\u00a6\3\2\2\2\16\u00b0\3\2\2\2"+
		"\20\u00b2\3\2\2\2\22\u00b6\3\2\2\2\24\u00c0\3\2\2\2\26\u00d3\3\2\2\2\30"+
		"\u00ec\3\2\2\2\32\u00f5\3\2\2\2\34\u0102\3\2\2\2\36\u010a\3\2\2\2 \u0111"+
		"\3\2\2\2\"\u0113\3\2\2\2$\u011b\3\2\2\2&\u011d\3\2\2\2(\u011f\3\2\2\2"+
		"*.\7\3\2\2+-\5\4\3\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\61\3\2"+
		"\2\2\60.\3\2\2\2\61\62\5\6\4\2\62\63\7\5\2\2\63\64\7\2\2\3\64\3\3\2\2"+
		"\2\65\66\5\f\7\2\66\67\5(\25\2\679\7#\2\28:\5\34\17\298\3\2\2\29:\3\2"+
		"\2\2:;\3\2\2\2;<\7$\2\2<=\7\4\2\2=>\5\6\4\2>?\7\5\2\2?\5\3\2\2\2@A\b\4"+
		"\1\2A\u0081\7\b\2\2B\u0081\7\t\2\2C\u0081\7\n\2\2DE\5\f\7\2EF\5&\24\2"+
		"FG\79\2\2GH\5\n\6\2H\u0081\3\2\2\2IJ\5\b\5\2JK\79\2\2KL\5\n\6\2L\u0081"+
		"\3\2\2\2MN\5$\23\2NP\7:\2\2OQ\5\26\f\2PO\3\2\2\2PQ\3\2\2\2Q\u0081\3\2"+
		"\2\2RS\7\13\2\2S\u0081\5\b\5\2TU\7\f\2\2U\u0081\5\26\f\2VW\7\6\2\2W\u0081"+
		"\5\26\f\2XY\7\r\2\2Y\u0081\5\26\f\2Z[\7\16\2\2[\u0081\5\26\f\2\\]\7\17"+
		"\2\2]\u0081\5\26\f\2^_\7\20\2\2_`\5\26\f\2`a\7\21\2\2ab\5\6\4\2bc\7\22"+
		"\2\2cd\5\6\4\2de\7\23\2\2e\u0081\3\2\2\2fg\7\24\2\2gh\5\26\f\2hi\7\25"+
		"\2\2ij\5\6\4\2jk\7\26\2\2k\u0081\3\2\2\2lm\7\25\2\2mn\5\6\4\2no\7\24\2"+
		"\2op\5\26\f\2p\u0081\3\2\2\2qr\7\27\2\2rs\7#\2\2st\5\6\4\2tu\7<\2\2uv"+
		"\5\26\f\2vw\7<\2\2wx\5\6\4\2xy\7$\2\2yz\5\6\4\2z{\7\26\2\2{\u0081\3\2"+
		"\2\2|}\7\3\2\2}~\5\6\4\2~\177\7\5\2\2\177\u0081\3\2\2\2\u0080@\3\2\2\2"+
		"\u0080B\3\2\2\2\u0080C\3\2\2\2\u0080D\3\2\2\2\u0080I\3\2\2\2\u0080M\3"+
		"\2\2\2\u0080R\3\2\2\2\u0080T\3\2\2\2\u0080V\3\2\2\2\u0080X\3\2\2\2\u0080"+
		"Z\3\2\2\2\u0080\\\3\2\2\2\u0080^\3\2\2\2\u0080f\3\2\2\2\u0080l\3\2\2\2"+
		"\u0080q\3\2\2\2\u0080|\3\2\2\2\u0081\u0087\3\2\2\2\u0082\u0083\f\3\2\2"+
		"\u0083\u0084\7<\2\2\u0084\u0086\5\6\4\4\u0085\u0082\3\2\2\2\u0086\u0089"+
		"\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\7\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u008a\u008e\5$\23\2\u008b\u008e\5\30\r\2\u008c\u008e\5"+
		" \21\2\u008d\u008a\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008c\3\2\2\2\u008e"+
		"\t\3\2\2\2\u008f\u00a2\5\26\f\2\u0090\u00a2\5\32\16\2\u0091\u0092\7\30"+
		"\2\2\u0092\u0093\7#\2\2\u0093\u0094\5\26\f\2\u0094\u0095\7;\2\2\u0095"+
		"\u0096\5\26\f\2\u0096\u0097\7$\2\2\u0097\u00a2\3\2\2\2\u0098\u00a2\5 "+
		"\21\2\u0099\u009a\7\7\2\2\u009a\u009b\5(\25\2\u009b\u009d\7#\2\2\u009c"+
		"\u009e\5\"\22\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3"+
		"\2\2\2\u009f\u00a0\7$\2\2\u00a0\u00a2\3\2\2\2\u00a1\u008f\3\2\2\2\u00a1"+
		"\u0090\3\2\2\2\u00a1\u0091\3\2\2\2\u00a1\u0098\3\2\2\2\u00a1\u0099\3\2"+
		"\2\2\u00a2\13\3\2\2\2\u00a3\u00a4\b\7\1\2\u00a4\u00a7\5\16\b\2\u00a5\u00a7"+
		"\5\22\n\2\u00a6\u00a3\3\2\2\2\u00a6\u00a5\3\2\2\2\u00a7\u00ad\3\2\2\2"+
		"\u00a8\u00a9\f\4\2\2\u00a9\u00aa\7%\2\2\u00aa\u00ac\7&\2\2\u00ab\u00a8"+
		"\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\r\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1\t\2\2\2\u00b1\17\3\2\2\2\u00b2"+
		"\u00b3\5\f\7\2\u00b3\u00b4\7%\2\2\u00b4\u00b5\7&\2\2\u00b5\21\3\2\2\2"+
		"\u00b6\u00b7\7\31\2\2\u00b7\u00b8\7#\2\2\u00b8\u00b9\5\24\13\2\u00b9\u00ba"+
		"\7;\2\2\u00ba\u00bb\5\24\13\2\u00bb\u00bc\7$\2\2\u00bc\23\3\2\2\2\u00bd"+
		"\u00c1\5\16\b\2\u00be\u00c1\5\20\t\2\u00bf\u00c1\7\31\2\2\u00c0\u00bd"+
		"\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00bf\3\2\2\2\u00c1\25\3\2\2\2\u00c2"+
		"\u00c3\b\f\1\2\u00c3\u00c4\t\3\2\2\u00c4\u00d4\5\26\f\13\u00c5\u00d4\7"+
		"?\2\2\u00c6\u00d4\7>\2\2\u00c7\u00d4\7@\2\2\u00c8\u00d4\7A\2\2\u00c9\u00d4"+
		"\7B\2\2\u00ca\u00d4\7C\2\2\u00cb\u00d4\5$\23\2\u00cc\u00d4\5\30\r\2\u00cd"+
		"\u00ce\t\4\2\2\u00ce\u00d4\7>\2\2\u00cf\u00d0\7#\2\2\u00d0\u00d1\5\26"+
		"\f\2\u00d1\u00d2\7$\2\2\u00d2\u00d4\3\2\2\2\u00d3\u00c2\3\2\2\2\u00d3"+
		"\u00c5\3\2\2\2\u00d3\u00c6\3\2\2\2\u00d3\u00c7\3\2\2\2\u00d3\u00c8\3\2"+
		"\2\2\u00d3\u00c9\3\2\2\2\u00d3\u00ca\3\2\2\2\u00d3\u00cb\3\2\2\2\u00d3"+
		"\u00cc\3\2\2\2\u00d3\u00cd\3\2\2\2\u00d3\u00cf\3\2\2\2\u00d4\u00e9\3\2"+
		"\2\2\u00d5\u00d6\f\t\2\2\u00d6\u00d7\t\5\2\2\u00d7\u00e8\5\26\f\n\u00d8"+
		"\u00d9\f\b\2\2\u00d9\u00da\t\6\2\2\u00da\u00e8\5\26\f\t\u00db\u00dc\f"+
		"\7\2\2\u00dc\u00dd\t\7\2\2\u00dd\u00e8\5\26\f\b\u00de\u00df\f\6\2\2\u00df"+
		"\u00e0\t\b\2\2\u00e0\u00e8\5\26\f\7\u00e1\u00e2\f\5\2\2\u00e2\u00e3\7"+
		"\66\2\2\u00e3\u00e8\5\26\f\6\u00e4\u00e5\f\4\2\2\u00e5\u00e6\7\67\2\2"+
		"\u00e6\u00e8\5\26\f\5\u00e7\u00d5\3\2\2\2\u00e7\u00d8\3\2\2\2\u00e7\u00db"+
		"\3\2\2\2\u00e7\u00de\3\2\2\2\u00e7\u00e1\3\2\2\2\u00e7\u00e4\3\2\2\2\u00e8"+
		"\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\27\3\2\2"+
		"\2\u00eb\u00e9\3\2\2\2\u00ec\u00f1\5$\23\2\u00ed\u00ee\7%\2\2\u00ee\u00ef"+
		"\5\26\f\2\u00ef\u00f0\7&\2\2\u00f0\u00f2\3\2\2\2\u00f1\u00ed\3\2\2\2\u00f2"+
		"\u00f3\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\31\3\2\2"+
		"\2\u00f5\u00fe\7%\2\2\u00f6\u00fb\5\26\f\2\u00f7\u00f8\7;\2\2\u00f8\u00fa"+
		"\5\26\f\2\u00f9\u00f7\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2"+
		"\u00fb\u00fc\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00f6"+
		"\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\7&\2\2\u0101"+
		"\33\3\2\2\2\u0102\u0107\5\36\20\2\u0103\u0104\7;\2\2\u0104\u0106\5\36"+
		"\20\2\u0105\u0103\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\35\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010b\5\f\7"+
		"\2\u010b\u010c\5&\24\2\u010c\37\3\2\2\2\u010d\u010e\7\32\2\2\u010e\u0112"+
		"\5\26\f\2\u010f\u0110\7\33\2\2\u0110\u0112\5\26\f\2\u0111\u010d\3\2\2"+
		"\2\u0111\u010f\3\2\2\2\u0112!\3\2\2\2\u0113\u0118\5\26\f\2\u0114\u0115"+
		"\7;\2\2\u0115\u0117\5\26\f\2\u0116\u0114\3\2\2\2\u0117\u011a\3\2\2\2\u0118"+
		"\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119#\3\2\2\2\u011a\u0118\3\2\2\2"+
		"\u011b\u011c\7D\2\2\u011c%\3\2\2\2\u011d\u011e\7D\2\2\u011e\'\3\2\2\2"+
		"\u011f\u0120\7D\2\2\u0120)\3\2\2\2\26.9P\u0080\u0087\u008d\u009d\u00a1"+
		"\u00a6\u00ad\u00c0\u00d3\u00e7\u00e9\u00f3\u00fb\u00fe\u0107\u0111\u0118";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}