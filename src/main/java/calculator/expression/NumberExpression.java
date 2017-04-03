package calculator.expression;


public class NumberExpression implements Expression {
    private double value;

    public NumberExpression(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
