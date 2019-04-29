/*-***
 *
 * This file defines a stand-alone lexical analyzer for a subset of the Pascal
 * programming language.  This is the same lexer that will later be integrated
 * with a CUP-based parser.  Here the lexer is driven by the simple Java test
 * program in ./PascalLexerTest.java, q.v.  See 330 Lecture Notes 2 and the
 * Assignment 2 writeup for further discussion.
 *
 */


import java_cup.runtime.*;


%%
/*-*
 * LEXICAL FUNCTIONS:
 */

%cup
%line
%column
%unicode
%class BaseGrammarLexer

%{

/**
 * Return a new Symbol with the given token id, and with the current line and
 * column numbers.
 */
Symbol newSym(int tokenId) {
    return new Symbol(tokenId, yyline, yycolumn);
}

/**
 * Return a new Symbol with the given token id, the current line and column
 * numbers, and the given token value.  The value is used for tokens such as
 * identifiers and numbers.
 */
Symbol newSym(int tokenId, Object value) {
    return new Symbol(tokenId, yyline, yycolumn, value);
}

%}


/*-*
 * PATTERN DEFINITIONS:
 */

tab				= \\t
newline			= \\n
slash			= \\
escapeapos		= {slash}'
escapequote		= {slash}\"
letter      	= [A-Za-z]
digit       	= [0-9]
id   			= {letter}({letter}|{digit})* 
intlit	    	= {digit}+
floatlit    	= {intlit}+\.{intlit}+
charchar		= [[^\\]&&[^']]|{newline}|{tab}|{escapeapos}|{slash}{slash}
charlit     	= '{charchar}'
stringchar		= [[[^\\]&&[^\"]]&&[[^\n]&&[^\t]]]|{newline}|{tab}|{escapequote}|{slash}{slash}
stringlit		= \"{stringchar}*\"
blockcomments   = {slash}\*
blockcommente   = \*{slash}
commentbody		= ([^\*]|(\*+[^\\]))
blockcomment    = {blockcomments}{commentbody}*?{blockcommente}
inlinecomment 	= {slash}{slash}.*\n
whitespace      = [ \n\t\r]



%%
/**
 * LEXICAL RULES:
 */
class		{ return newSym(sym.CLASS);}
"&&"            { return newSym(sym.AND); }
else            { return newSym(sym.ELSE); }
if              { return newSym(sym.IF); }
while		{ return newSym(sym.WHILE); }
read		{ return newSym(sym.READ); }
print		{ return newSym(sym.PRINT); }
printline	{ return newSym(sym.PRINTLN); }
return		{ return newSym(sym.RETURN); }
"||"            { return newSym(sym.OR); }
"*"             { return newSym(sym.TIMES); }
"+"             { return newSym(sym.PLUS); }
"+" 		{ return newSym(sym.PREFIXPLUS); }
"++"		{ return newSym(sym.PLUSPLUS); }
"-"             { return newSym(sym.MINUS); }
"-"			{ return newSym(sym.PREFIXMINUS); }
"--"		{ return newSym(sym.MINUSMINUS); }
"/"             { return newSym(sym.DIVIDE); }
";"             { return newSym(sym.SEMI); }
"("             { return newSym(sym.LEFT_PAREN); }
")"             { return newSym(sym.RT_PAREN); }
"{"		{ return newSym(sym.LEFT_BRACE); }
"}"		{ return newSym(sym.RT_BRACE); }
"["             { return newSym(sym.LEFT_BRKT); }
"]"             { return newSym(sym.RT_BRKT); }
"=="            { return newSym(sym.EQ); }
">"             { return newSym(sym.GTR); }
"<"             { return newSym(sym.LESS); }
"<="            { return newSym(sym.LESS_EQ); }
">="            { return newSym(sym.GTR_EQ); }
"<>"            { return newSym(sym.NOT_EQ); }
"~"		{ return newSym(sym.NOT); }
"?"		{ return newSym(sym.CONDITION); }
":"             { return newSym(sym.COLON); }
"="             { return newSym(sym.ASSMNT); }
","		{ return newSym(sym.COMMA); }
final 		{ return newSym(sym.FINAL); }
void		{ return newSym(sym.VOID); }
int		{ return newSym(sym.INT); }
float		{ return newSym(sym.FLOAT); }
bool		{ return newSym(sym.BOOL); }
char		{ return newSym(sym.CHAR); }
true		{ return newSym(sym.TRUE); }
false		{ return newSym(sym.FALSE); }
_tile		{ return newSym(sym.TILE); }
_display 	{ return newSym(sym.DISPLAY); }
{id}    	{ return newSym(sym.ID, yytext()); }
{intlit}        { return newSym(sym.INTLIT, new Integer(yytext())); }
{floatlit}      { return newSym(sym.FLOATLIT, new Double(yytext())); }
{charlit}       { return newSym(sym.CHARLIT, yytext()); }
{stringlit}	{ return newSym(sym.STRINGLIT, yytext()); }
{inlinecomment} { /* For this stand-alone lexer, print out comments. */}
{blockcomment}	{ /* For this stand-alone lexer, print out comments. */}
{whitespace}    { /* Ignore whitespace. */ }
.               { System.out.println("Illegal char, '" + yytext() +
                    "' line: " + yyline + ", column: " + yychar); } 
