package calculator.expression;

public enum Operator {
    ADD,
    SUB,
    MULT,
    DIV,
    POW;


    public int getPrecedence() {
        switch (this) {
            case ADD:
            case SUB:
                return 1;
            case MULT:
            case DIV:
                return 2;
            case POW:
                return 3;
            default:
                throw new RuntimeException("Unknown operator: " + this);
        }
    }


    public String toString() {
        switch (this) {
            case ADD:
                return "+";
            case SUB:
                return "-";
            case MULT:
                return "*";
            case DIV:
                return "/";
            case POW:
                return "^";
            default:
                throw new RuntimeException("Unknown operator: " + this);
        }
    }
}