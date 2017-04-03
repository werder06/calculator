package calculator;

public class Token {
    public static Token EOF = new Token(TokenType.EOF, "EOF");

    private TokenType tokenType;
    private String value;

    public Token(TokenType tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        Token token = (Token) o;

        if (tokenType != token.tokenType) return false;
        if (!value.equals(token.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tokenType.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + tokenType +
                ", value='" + value + '\'' +
                '}';
    }

    public boolean isOperator() {
        return tokenType == TokenType.SUB || tokenType == TokenType.ADD
                || tokenType == TokenType.DIV || tokenType == TokenType.MULT
                || tokenType == TokenType.POW;
    }
}
