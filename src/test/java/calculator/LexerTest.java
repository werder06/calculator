package calculator;


import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class LexerTest {

    @Test
    public void testHappyPath() {
        check("(14.93 - 10) + 3 *  5.", Arrays.asList(
                new Token(TokenType.LEFT_BRACKET, "("),
                new Token(TokenType.NUMBER, "14.93"),
                new Token(TokenType.SUB, "-"),
                new Token(TokenType.NUMBER, "10"),
                new Token(TokenType.RIGHT_BRACKET, ")"),
                new Token(TokenType.ADD, "+"),
                new Token(TokenType.NUMBER, "3"),
                new Token(TokenType.MULT, "*"),
                new Token(TokenType.NUMBER, "5."),
                new Token(TokenType.EOF, "EOF")
        ));
    }

    @Test
    public void testError() {
        check("14.9d3 - 10", Arrays.asList(
                new Token(TokenType.NUMBER, "14.9"),
                new Token(TokenType.ERROR, "d"),
                new Token(TokenType.NUMBER, "3"),
                new Token(TokenType.SUB, "-"),
                new Token(TokenType.NUMBER, "10"),
                new Token(TokenType.EOF, "EOF")
        ));
    }

    private void check(String formula, List<Token> expected) {
        List<Token> actual = stringToTokens(formula);
        assertThat(expected, equalTo(actual));
    }

    private static List<Token> stringToTokens(String formula) {
        Lexer lexer = new Lexer(new StringReader(formula));
        List<Token> tokens = new ArrayList<>();
        Token token;
        while ((token = lexer.nextToken()) != Token.EOF) {
            tokens.add(token);
        }
        tokens.add(token);
        return tokens;
    }
}
