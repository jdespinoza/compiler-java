package LexicalAnalyzer;

import java_cup.runtime.*;
import java.io.IOException;
import Information.Code;

%%
%full
%unicode
%line
%column
%char
%public
%cup

%eofval{
    return new Symbol(sym.EOF);
%eofval} 
 
%{
    public Yylex(java.io.InputStream in) {
        this(new java.io.InputStreamReader(in));
    }
    String tok = "";
    private Symbol Token(int token, Object lexema) throws IOException {
	int linea = yyline + 1;
	tok = (String)lexema;
	if (token != sym.EOF)
            Code.saveInformation(linea,tok);
        return new Symbol(token, lexema);
    }

    private Symbol Token(int token) throws IOException {
        return Token(token, yytext());
    }
%}

NUMERO_LEXER = [0-9]+
IDENTIFICADOR_LEXER = [a-zA-Z][a-zA-Z0-9_]*
CadenaTexto = "\""[a-zA-Z0-9 _][a-zA-Z0-9 _]*"\""
%% 

"(" 			{ return new Symbol(sym.LPAREN); }
")" 			{ return new Symbol(sym.RPAREN); }
";"  			{ return new Symbol(sym.PTOCOMA); }
"+" 			{ return new Symbol(sym.SUMA); }
"-" 			{ return new Symbol(sym.RESTA); }
"*" 			{ return new Symbol(sym.PRODUCTO); }
"/" 			{ return new Symbol(sym.DIVISION); }
"<" 			{ return new Symbol(sym.MENOR); }
">" 			{ return new Symbol(sym.MAYOR); }
"==" 			{ return new Symbol(sym.IGUAL); }
"!=" 	 		{ return new Symbol(sym.DISTINTO); }  
"||" 			{ return new Symbol(sym.OR); }
"&&" 			{ return new Symbol(sym.AND); }
"=" 			{ return new Symbol(sym.ASIGNAR); }
"{" 			{ return new Symbol(sym.LLLAVE); }
"}" 			{ return new Symbol(sym.RLLAVE); }
"int"			{ return new Symbol(sym.INT, yyline, yycolumn, yytext()); }
"main" 			{ return new Symbol(sym.MAIN, yyline, yycolumn, yytext()); }
"if"			{ return new Symbol(sym.IF, yyline, yycolumn, yytext()); }
"else" 			{ return new Symbol(sym.ELSE, yyline, yycolumn, yytext()); }
"while" 		{ return new Symbol(sym.WHILE, yyline, yycolumn, yytext()); }
"puts" 			{ return new Symbol(sym.PUTS, yyline, yycolumn, yytext()); }
"putw"			{ return new Symbol(sym.PUTW, yyline, yycolumn, yytext()); }
"break"			{ return new Symbol(sym.BREAK, yyline, yycolumn, yytext()); }
{CadenaTexto}   	{ return new Symbol(sym.CADENATEXTO, yyline, yycolumn, yytext()); }
{IDENTIFICADOR_LEXER}	{ return new Symbol(sym.ID, yyline, yycolumn, yytext()); }
{NUMERO_LEXER}		{ return new Symbol(sym.ENTERO, yyline, yycolumn, yytext()); }
(" "|\n|\t|\r)+		{ }

. { System.err.println("Caracter no permitido: "+yytext()); }
