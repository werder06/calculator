package calculator;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


public class ParserText {

    @Test
    public void test() {
        check("1 + 2 * 3 * 4", 25);
        check("1 * 2 + 3 * 4", 14);
        check("1 * (2 + 3) * 4", 20);
        check("14 * 5  + 3 / 3 - 5", 66.0);
        check("(2^3 + (35 - 5) * (19 +1))", 608.0);

    }

    @Test
    public void testMinus() {
        check("(-1^2) + 1", 2);
        check("(-1^3) + 1", 0);
    }

    private static void check(String value, double expected) {
        Parser p = new Parser(value);
        assertThat(p.parse().evaluate(), equalTo(expected));
    }
}
