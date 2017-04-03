package calculator;


import calculator.expression.BinaryOperationExpression;
import calculator.expression.BracketExpression;
import calculator.expression.Expression;
import calculator.expression.NumberExpression;
import calculator.expression.Operator;

import java.io.StringReader;

import static calculator.TokenType.*;

public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(String text) {
        this.lexer = new Lexer(new StringReader(text));
    }

    public Expression parse() {
        return parseImpl(0);
    }

    private Expression parseImpl(int precedenceHigherThan) {
        Expression expression = null;
        while (true) {
            Token nextToken = peek();
            if (nextToken == Token.EOF) {
                break;
            }
            if (expression == null) {
                expression = parseBaseExpression(nextToken);
            } else {
                if (nextToken.isOperator()) {
                    Operator operator = getOperatorForToken(nextToken);
                    if (operator.getPrecedence() <= precedenceHigherThan) {
                        break;
                    }
                    pop();
                    Expression right = parseImpl(operator.getPrecedence());
                    expression = new BinaryOperationExpression(expression, right, operator);
                } else {
                    break;
                }
            }
        }
        return expression;
    }

    private Expression parseBaseExpression(Token token) {
        if (token.getTokenType() == LEFT_BRACKET) {
            pop();
            Expression expression = parseImpl(0);
            Token lastToken = pop();
            if (lastToken.getTokenType() == RIGHT_BRACKET) {
                return new BracketExpression(expression);
            }
        } else if (token.getTokenType() == NUMBER) {
            pop();
            double value = Double.parseDouble(token.getValue());
            return new NumberExpression(value);
        }
        return null;
    }

    private Token pop() {
        if (currentToken != null) {
            Token next = currentToken;
            currentToken = null;
            return next;
        }
        return lexer.nextToken();
    }

    private Token peek() {
        if (currentToken != null) {
            return currentToken;
        } else {
            currentToken = lexer.nextToken();
            return currentToken;
        }
    }

    private static Operator getOperatorForToken(Token nextToken) {
        if (nextToken.getTokenType() == SUB) {
            return Operator.SUB;
        } else if (nextToken.getTokenType() == MULT) {
            return Operator.MULT;
        } else if (nextToken.getTokenType() == ADD) {
            return Operator.ADD;
        } else if (nextToken.getTokenType() == DIV) {
            return Operator.DIV;
        } else if (nextToken.getTokenType() == POW) {
            return Operator.POW;
        }
        throw new RuntimeException("Can't get operation for " + nextToken);
    }

}
