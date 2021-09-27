package ar.uba.fi.compiladores.parte4;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.uba.fi.compiladores.parte3.BadTokenException;
import ar.uba.fi.compiladores.parte3.ManualLexer;
import ar.uba.fi.compiladores.parte3.Token;
import ar.uba.fi.compiladores.parte4.Numbersoup.Automata;
import ar.uba.fi.compiladores.parte4.Numbersoup.State;
import ar.uba.fi.compiladores.parte4.Numbersoup.TokenTypes;

public class NumbersoupTest {
    Automata language = new Automata();
    ManualLexer<State,TokenTypes> lexer = new ManualLexer<State,TokenTypes>(language);

    @Test 
    public void testOtherTokensAsPrefixes() throws BadTokenException{
        List<Token<TokenTypes>> expected = Arrays.asList(
            new Token<>(TokenTypes.BIN,"0110"),
            new Token<>(TokenTypes.DEC,"102"),
            new Token<>(TokenTypes.HEX,"018F"),
            new Token<>(TokenTypes.BINHEX,"0AFx010"),
            new Token<>(TokenTypes.EOF,null)
        );
        assertEquals(expected, lexer.lex(" 0110 102 018F 0AFx010"));
    }
    @Test 
    public void testOtherTokensAsPostfixes() throws BadTokenException{
        List<Token<TokenTypes>> expected = Arrays.asList(
            new Token<>(TokenTypes.DEC,"210"),
            new Token<>(TokenTypes.HEX,"F801"),
            new Token<>(TokenTypes.EOF,null)
        );
        assertEquals(expected, lexer.lex(" 210 F801"));
    }
    @Test
    public void testBadNumber() throws BadTokenException{
        assertThrows(BadTokenException.class, ()->lexer.lex(" 0AFx0102"));
    }
    @Test void testBadCharacters() throws BadTokenException{
        assertThrows(BadTokenException.class, ()->lexer.lex("ho1a"));
    }
    
}
