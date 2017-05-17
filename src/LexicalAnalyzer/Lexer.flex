package LexicalAnalyzer;

import java_cup.runtime.*;
import java.util.ArrayList;
import static LexicalAnalyzer.sym.terminalNames;
%%

%unicode
%line
%column
%public
%cup

%eofval{
    return new Symbol(sym.EOF);
%eofval}

%{
public ArrayList<Symbol> listLexer = new ArrayList<>();

 public Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

/* Pasa a String los valores de los objetos Symbol
   creando una lista de tokens con su valor en formato:
    < Token, Valor > .
*/
 public String printListLexer(){
     String out = "";
     Symbol n;
     int len = listLexer.size();
     for(int i = 0; i < len ;i++){
         n = listLexer.get(i);
         out+="< "+terminalNames[n.sym];
         if(n.value!= null){
             out+=", "+n.value;
         }
         out+=" >\n";
     }
     return out;
 }

 /*Muestra la lista de lexers*/
 public ArrayList<Symbol> getListLexer(){
    return listLexer;
 }

%} 

NUMBER_LEXER = [0-9]+
WHITESPACE_LEXER = [ \t]
ENTER_LEXER = [\r\n]+
IDENTIFIER_LEXER = [a-zA-Z][a-zA-Z0-9]*
KEYWORD_A_LEXER = "if"|"while"
KEYWORD_B_LEXER = "else"
MAIN_LEXER = "main"
FUNCTION_LEXER = "puts"|"putw"
TYPE_LEXER = "int"
STRING_LEXER = "\""[a-zA-Z0-9][a-zA-Z0-9 ]*"\""
%% 
{WHITESPACE_LEXER} {}

{ENTER_LEXER}       { return new Symbol(sym.TK_ENTER); }
{MAIN_LEXER}        {
                      Symbol n = new Symbol(sym.TK_MAIN, yyline, yycolumn, yytext());
                      listLexer.add(n);
                      return n;  
                    }
{KEYWORD_A_LEXER}   {
                      Symbol n = new Symbol(sym.TK_KEYWORD_A, yyline, yycolumn, yytext());
                      listLexer.add(n);
                      return n;  
                    }

{KEYWORD_B_LEXER}     {
                        Symbol n = new Symbol(sym.TK_KEYWORD_B, yyline, yycolumn, yytext());
                        listLexer.add(n);
                        return n;  
                      }
{FUNCTION_LEXER}      {
                        Symbol n = new Symbol(sym.TK_FUNCTION, yyline, yycolumn, yytext());
                        listLexer.add(n);
                        return n;
                      }
{TYPE_LEXER}          {
                        Symbol n = new Symbol(sym.TK_TYPE, yyline, yycolumn, yytext());
                        listLexer.add(n);
                        return n;  
                      }
{IDENTIFIER_LEXER}    {
                        Symbol n = new Symbol(sym.TK_ID, yyline, yycolumn, yytext());
                        listLexer.add(n);
                        return n;
                      }

{NUMBER_LEXER}        {    
                        Symbol n = new Symbol(sym.TK_NUM, yyline, yycolumn, yytext());
                        listLexer.add(n);
                        return n;
                      }
{STRING_LEXER}        {
                        Symbol n = new Symbol(sym.TK_STRING, yyline, yycolumn, yytext());
                        listLexer.add(n);
                        return n;
                      }
"+"                   {    
                        Symbol n = new Symbol(sym.TK_MAS);
                        listLexer.add(n);
                        return n;
                      }
"-"		      { 
                        Symbol n = new Symbol(sym.TK_MENOS);
                        listLexer.add(n);
                        return n;
                      }
"*"                   {          
                        Symbol n = new Symbol(sym.TK_POR);
                        listLexer.add(n);
                        return n;
                      }
"/"                   {   
                        Symbol n = new Symbol(sym.TK_DIV);
                        listLexer.add(n);
                        return n;
                      }
"("		      {  
                        Symbol n = new Symbol(sym.TK_LPAREN);
                        listLexer.add(n);
                        return n;                  
                      }
")"                   { 
                        Symbol n = new Symbol(sym.TK_RPAREN);
                        listLexer.add(n);
                        return n;
                      }
"="                   {
                        Symbol n = new Symbol(sym.TK_ASSIGN);
                        listLexer.add(n);
                        return n;
                      }
">"                   {
                        Symbol n = new Symbol(sym.TK_GREATER);
                        listLexer.add(n);
                        return n;
                      }
"<"                   {
                        Symbol n = new Symbol(sym.TK_LESS);
                        listLexer.add(n);
                        return n;
                      }
"=="                  {
                        Symbol n = new Symbol(sym.TK_EQUAL);
                        listLexer.add(n);
                        return n;
                      }
"¡="                  {
                        Symbol n = new Symbol(sym.TK_DIFFERENT);
                        listLexer.add(n);
                        return n;
                      }
"&&"                  {
                        Symbol n = new Symbol(sym.TK_AND);
                        listLexer.add(n);
                        return n;
                      }
"||"                  {
                        Symbol n = new Symbol(sym.TK_OR);
                        listLexer.add(n);
                        return n;
                      }
";"                   {
                        Symbol n = new Symbol(sym.TK_PYC);
                        listLexer.add(n);
                        return n;
                      }
"{"                   {
                        Symbol n = new Symbol(sym.TK_LKEY);
                        listLexer.add(n);
                        return n;
                      }
"}"                   {
                        Symbol n = new Symbol(sym.TK_RKEY);
                        listLexer.add(n);
                        return n;
                      }
.                     { /*Ignora*/ }
