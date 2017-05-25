package LexicalAnalyzer;

import java_cup.runtime.*;

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

 public Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
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

%% 

{WHITESPACE_LEXER} {}

{ENTER_LEXER} { return new Symbol(sym.TK_ENTER); }

{MAIN_LEXER} {
                  return new Symbol(sym.TK_MAIN, yyline, yycolumn, yytext());  
                }

{KEYWORD_A_LEXER} {
                  return new Symbol(sym.TK_KEYWORD_A, yyline, yycolumn, yytext());  
                }
{KEYWORD_B_LEXER} {
                  return new Symbol(sym.TK_KEYWORD_B, yyline, yycolumn, yytext());  
                }

{FUNCTION_LEXER} {
                  return new Symbol(sym.TK_FUNCTION, yyline, yycolumn, yytext());  
                }

{TYPE_LEXER}    {
                  return new Symbol(sym.TK_TYPE, yyline, yycolumn, yytext());  
                }

{IDENTIFIER_LEXER} {
                    return new Symbol(sym.TK_ID, yyline, yycolumn, yytext());
                }

{NUMBER_LEXER} {    
                  return new Symbol(sym.TK_NUM, yyline, yycolumn, yytext());
               }

"+"             {    
                  return new Symbol(sym.TK_MAS);
                }

"-"		{   
                  return new Symbol(sym.TK_MENOS);
                }

"*"		{                
                  return new Symbol(sym.TK_POR);                  
                }

"/"		{   
                  return new Symbol(sym.TK_DIV);                  
                }

"("		{  
                  return new Symbol(sym.TK_LPAREN);                  
                }

")"             { 
                  return new Symbol(sym.TK_RPAREN);                  
                }

"="             {
                    return new Symbol(sym.TK_ASSIGN);
                }

">"             {
                    return new Symbol(sym.TK_GREATER);
                }

"<"             {
                    return new Symbol(sym.TK_LESS);
                }

"=="            {
                    return new Symbol(sym.TK_EQUAL);
                }

"¡="            {
                    return new Symbol(sym.TK_DIFFERENT);
                }

"&&"            {
                    return new Symbol(sym.TK_AND);
                }

"||"            {
                    return new Symbol(sym.TK_OR);
                }

";"             {
                    return new Symbol(sym.TK_PYC);
                }

"{"             {
                    return new Symbol(sym.TK_LKEY);
                }

"}"             {
                    return new Symbol(sym.TK_RKEY);
                }

.               {  }
