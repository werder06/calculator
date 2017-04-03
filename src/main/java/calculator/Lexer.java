package calculator;

import java.io.IOException;
import java.io.Reader;


public class Lexer {
    private final Reader reader;
    private int nextSymbol;
    private boolean hasNextSymbol = false;

    public Lexer(Reader reader) {
        this.reader = reader;
    }

    public Token nextToken() {
        int next = readNext();
        while (Character.isWhitespace(next)) {
            next = readNext();
        }
        if (numberPart(next)) {
            return literalNumber((char) next);
        }
        switch (next) {
            case -1:
                return Token.EOF;
            case '(':
                return new Token(TokenType.LEFT_BRACKET, "(");
            case ')':
                return new Token(TokenType.RIGHT_BRACKET, ")");
            case '*':
                return new Token(TokenType.MULT, "*");
            case '/':
                return new Token(TokenType.DIV, "/");
            case '+':
                return new Token(TokenType.ADD, "+");
            case '-': {
                return literalMinus();
            }
            case '^':
                return new Token(TokenType.POW, "^");
        }
        return new Token(TokenType.ERROR, ""+ (char) next);
    }


    private Token literalNumber(char... prefix) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : prefix) {
            stringBuilder.append(c);
        }
        do {
            int next = readNext();
            if (numberPart(next)) {
                stringBuilder.append((char) next);

            } else {
                hasNextSymbol = true;
                nextSymbol = next;
                return new Token(TokenType.NUMBER, stringBuilder.toString());
            }
        } while (true);
    }

    private Token literalMinus() {
        int next = readNext();
        if (next >= '0' && next <= '9') {
            return literalNumber('-', (char) next);
        } else {
            return new Token(TokenType.SUB, "-");
        }
    }

    private int readNext() {
        if (hasNextSymbol) {
            hasNextSymbol = false;
            return nextSymbol;
        }
        try {
            return reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static boolean numberPart(int symbol) {
        return symbol == '.' || (symbol >= '0' && symbol <= '9');
    }

}
