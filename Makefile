# uses JFlex >= 1.3.2, and CUP >= 0.10j
#
# targets:
#
# make all                                                            
#    generates lexer, and parser, and compiles all *.java files
#                                                                     
# make run (or just: make)                                           
#    starts the program on a test example
#                                                                     

JAVA=java
JAVAC=javac
# Root of the project
JFLEX=$(JAVA) -jar jflex-full-1.7.0.jar
CUPJAR=./java-cup-11b.jar
CUP=$(JAVA) -jar $(CUPJAR)
CP=.:$(CUPJAR)

default: test

.SUFFIXES: $(SUFFIXES) .class .java

.java.class:
	$(JAVAC) -cp $(CP) $*.java

FILE=	BaseGrammarLexer.java      parser.java    sym.java \
		ArgDecl.java ArgDeclArray.java ArgDecls.java ArgDeclSingle.java \
		ArgFuncStmt.java Args.java AssmntStmt.java BG.java BinaryExpr.java \
		BoolLit.java CharLit.java Expr.java FieldDecl.java \
		FieldDeclArray.java FieldDecls.java FieldDeclSingle.java FloatLit.java \
		FuncStmt.java IfBackend.java IfBackendStmt.java IfBase.java IfStmt.java \
		IntLit.java MatchedFinalIf.java MatchedIf.java MatchedIfBase.java \
		MatchedInnerIf.java MemberDecls.java MethodDecl.java MethodDecls.java \
		Name.java NameArray.java NestedIf.java NonIfStmt.java OptionalExpr.java \
		OptionalFinal.java OptionalSemi.java ParenExpr.java PrintList.java \
		PrintLnList.java PrintLnStmt.java PrintStmt.java Program.java ReadList.java \
		ReadStmt.java ReturnType.java ScopeStmt.java Stmt.java Stmts.java \
		StrLit.java TernaryExpr.java Type.java TypeCastExpr.java TypeLit.java \
		UnaryExpr.java UnaryStmt.java ValueReturn.java VoidFuncStmt.java VoidReturn.java \
		WhileStmt.java UnmatchedIf.java FuncExpr.java VoidFuncExpr.java \
		ArgFuncExpr.java FieldStart.java FieldsNMethods.java VoidType.java \
		WhileBase.java WhileNonWhile.java WhileIf.java NestedWhile.java \
		WhileMatchedIf.java WhileMatchedIfEnd.java NestedIfWhiles.java \
		NestedWhile.java NestedWhileEnd.java NestedWhileIf.java NestedWhileEndIf.java \
		NestedMatchedWhileIf.java BaseGrammarTop.java IntType.java FloatType.java \
		CharType.java BoolType.java NonTypeCastExpr.java ActionExpr.java \
		Data.java DataList.java DataHash.java FullType.java VarData.java FunctionData.java\
		StrType.java BaseGrammarException.java NameSingle.java Display.java Tile.java\
		ControllerInput.java ControllerNum.java ButtonInput.java InputMethod.java\
	BaseGrammarCompiler.java

dump: parserD.java $(FILE:java=class)

run: testCodeGen.txt

testCodeGen.txt: all
	$(JAVA) -cp $(CP) BaseGrammarCompiler < testCodeGen.as > testCodeGen.txt
	cat -n testCodeGen.txt

testBasic.txt: all
	$(JAVA) -cp $(CP) BaseGrammarCompiler < testBasic.as > testBasic.txt
	cat -n testBasic.txt

all: BaseGrammarLexer.java parser.java $(FILE:java=class)


clean:
	rm -f *.class *~ *.bak BaseGramamrLexer.java parser.java sym.java

BaseGrammarLexer.java: BaseGrammar.jflex
	$(JFLEX) BaseGrammar.jflex

parser.java: BaseGrammar-tokens.cup
	$(CUP) -interface < BaseGrammar-tokens.cup 

parserD.java: BaseGrammar-tokens.cup
	$(CUP) -interface -dump < BaseGrammar-tokens.cup
