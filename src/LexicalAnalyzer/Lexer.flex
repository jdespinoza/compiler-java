package LexicalAnalyzer;

import java_cup.runtime.*;
import java.io.IOException;
import Information.Code;
import static LexicalAnalyzer.sym.terminalNames;
import java.util.ArrayList;

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
    private ArrayList<String> listLexer = new ArrayList<>();
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
    
    public ArrayList<String> getListLexer(){
        return listLexer;
    }
%}

NUMERO_LEXER = [0-9]+
IDENTIFICADOR_LEXER = [a-zA-Z][a-zA-Z0-9_]*
CadenaTexto = \"([\x20-\x21\x23-\xFE])*\"
%% 

"(" 			{ 
                            Symbol n = new Symbol(sym.LPAREN);
                            listLexer.add("<"+terminalNames[sym.LPAREN]+">");
                            return n; 
                        }
")" 			{ 
                            Symbol n = new Symbol(sym.RPAREN);
                            listLexer.add("<"+terminalNames[sym.RPAREN]+">");
                            return n; 
                        }
";"  			{ 
                            Symbol n = new Symbol(sym.PTOCOMA);
                            listLexer.add("<"+terminalNames[sym.PTOCOMA]+">");
                            return n; 
                        }
"+" 			{
                            Symbol n = new Symbol(sym.SUMA);
                            listLexer.add("<"+terminalNames[sym.SUMA]+">");
                            return n; 
                        }
"-" 			{ 
                            Symbol n = new Symbol(sym.RESTA);
                            listLexer.add("<"+terminalNames[sym.RESTA]+">");
                            return n; 
                        }
"*" 			{ 
                            Symbol n = new Symbol(sym.PRODUCTO);
                            listLexer.add("<"+terminalNames[sym.PRODUCTO]+">");
                            return n; 
                        }
"/" 			{   Symbol n = new Symbol(sym.DIVISION);
                            listLexer.add("<"+terminalNames[sym.DIVISION]+">");
                            return n;  
                        }
"<" 			{   Symbol n = new Symbol(sym.MENOR);
                            listLexer.add("<"+terminalNames[sym.MENOR]+">"); 
                            return n; 
                        }
">" 			{   Symbol n = new Symbol(sym.MAYOR);
                            listLexer.add("<"+terminalNames[sym.MAYOR]+">");
                            return n;  
                        }
"==" 			{   Symbol n = new Symbol(sym.IGUAL);
                            listLexer.add("<"+terminalNames[sym.IGUAL]+">");
                            return n; 
                        }
"!=" 	 		{   Symbol n = new Symbol(sym.DISTINTO);
                            listLexer.add("<"+terminalNames[sym.DISTINTO]+">");
                            return n; 
                        }  
"||" 			{   Symbol n = new Symbol(sym.OR);
                            listLexer.add("<"+terminalNames[sym.OR]+">");
                            return n; 
                        }
"&&" 			{   Symbol n = new Symbol(sym.AND);
                            listLexer.add("<"+terminalNames[sym.AND]+">");
                            return n; 
                        }
"=" 			{   Symbol n = new Symbol(sym.ASIGNAR);
                            listLexer.add("<"+terminalNames[sym.ASIGNAR]+">");
                            return n; 
                        }
"{" 			{   Symbol n = new Symbol(sym.LLLAVE);
                            listLexer.add("<"+terminalNames[sym.LLLAVE]+">");
                            return n; 
                        }
"}" 			{   Symbol n = new Symbol(sym.RLLAVE);
                            listLexer.add("<"+terminalNames[sym.RLLAVE]+">");
                            return n; 
                        }
"int"			{   Symbol n = new Symbol(sym.INT, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.RLLAVE]+", "+yytext()+">");
                            return n; 
                        }
"END"                   {   Symbol n = new Symbol(sym.END, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.END]+", "+yytext()+">");
                            return n; 
                        }
"function"              {   Symbol n = new Symbol(sym.FUNCTION, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.FUNCTION]+", "+yytext()+">");
                            return n; 
                        }
"main" 			{   Symbol n = new Symbol(sym.MAIN, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.MAIN]+", "+yytext()+">");
                            return new Symbol(sym.MAIN, yyline, yycolumn, yytext()); 
                        }
"empty" 		{   Symbol n = new Symbol(sym.EMPTY, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.EMPTY]+", "+yytext()+">");
                            return n; 
                        }
"if"			{   Symbol n = new Symbol(sym.IF, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.IF]+", "+yytext()+">");
                            return n; 
                        }
"else" 			{   Symbol n = new Symbol(sym.ELSE, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.ELSE]+", "+yytext()+">");
                            return n; 
                        }
"while" 		{   Symbol n = new Symbol(sym.WHILE, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.WHILE]+", "+yytext()+">");
                            return n; 
                        }
"puts" 			{   Symbol n = new Symbol(sym.PUTS, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.PUTS]+", "+yytext()+">");
                            return n; 
                        }
"putw"			{   Symbol n = new Symbol(sym.PUTW, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.PUTW]+", "+yytext()+">");
                            return n; 
                        }
"break"			{   Symbol n = new Symbol(sym.BREAK, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.BREAK]+", "+yytext()+">");
                            return n; 
                        }
{CadenaTexto}   	{   Symbol n = new Symbol(sym.CADENATEXTO, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.CADENATEXTO]+", "+yytext()+">");
                            return n; 
                        }
{IDENTIFICADOR_LEXER}	{   Symbol n = new Symbol(sym.ID, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.ID]+", "+yytext()+">");
                            return n; 
                        }
{NUMERO_LEXER}		{   Symbol n = new Symbol(sym.ENTERO, yyline, yycolumn, yytext());
                            listLexer.add("<"+terminalNames[sym.ENTERO]+", "+yytext()+">");
                            return n; 
                        }
(" "|\n|\t|\r)+		{ }

. { System.err.println("Caracter no permitido: "+yytext()); }
