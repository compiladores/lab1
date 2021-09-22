package ar.uba.fi.compiladores.parte7;
import ar.uba.fi.compiladores.parte5.LexerException;
import ar.uba.fi.compiladores.parte3.Token;
import ar.uba.fi.compiladores.parte7.CompilispTokenTypes;

%%

%public
%class Compilisp
%type Token<CompilispTokenTypes>
%yylexthrow LexerException

%unicode

%%


[^] {throw new LexerException();}

