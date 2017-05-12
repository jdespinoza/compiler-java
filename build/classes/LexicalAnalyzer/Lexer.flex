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
KEYWORD_LEXER = "int"
OPERATOR_LEXER = ">"|"<"|"=="|"¡="|"&&"|"||"

%% 

{WHITESPACE_LEXER} {}

{ENTER_LEXER} { return new Symbol(sym.TK_ENTER); }

{KEYWORD_LEXER} {
                  return new Symbol(sym.TK_KEYWORD, yyline, yycolumn, yytext());  
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

.               {  }
