package calculator.expression;



public class BracketExpression implements Expression {
    private final Expression expression;

    public BracketExpression(Expression expression) {
        this.expression = expression;
    }


    @Override
    public double evaluate() {
        return expression.evaluate();
    }

    @Override
    public String toString() {
        return "(" + expression.toString() + " )";
    }
}
