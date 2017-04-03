package calculator.expression;

public class BinaryOperationExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final Operator operator;

    public BinaryOperationExpression(Expression left, Expression right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public double evaluate() {
        switch (operator) {
            case MULT:
                return left.evaluate() * right.evaluate();
            case DIV:
                return left.evaluate() / right.evaluate();
            case ADD:
                return left.evaluate() + right.evaluate();
            case SUB:
                return left.evaluate() - right.evaluate();
            case POW:
                return Math.pow(left.evaluate(), right.evaluate());
        }
        throw new RuntimeException("Unsupported operator " + operator);
    }

    public String toString() {
        return left.toString() + " " + operator.toString() + " " + right.toString();
    }
}
