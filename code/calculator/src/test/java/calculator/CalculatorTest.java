package calculator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class CalculatorTest {

    @Test
    public void testSumShouldBeFiveWhenAddingTwoPlusThree() {
        Calculator calculator = new Calculator();
        int sum = calculator.add(2, 3);
        assertThat("Wrong sum", sum, is(5));
    }

    @Test
    public void testDifferenceShouldBe10WhenSubtractingTenFromTwenty() {
        Calculator calculator = new Calculator();
        int difference = calculator.subtract(20, 10);
        assertThat("Wrong difference", difference, is(10));
    }

    @Test
    public void testProductShouldBeHundredWhenMultiplyingTenAndTen() {
        Calculator calculator = new Calculator();
        int product = calculator.multiply(10, 10);
        assertThat("Wrong product", product, is(100));
    }

    @Test
    public void testQuotientShouldBeTwoWhenDividingTenAndFive() {
        Calculator calculator = new Calculator();
        int quotient = calculator.divide(10, 5);
        assertThat("Wrong quotient", quotient, is(2));
    }

    @Test
    public void testDividingByZeroIsIllegal() {
        Calculator calculator = new Calculator();
        try {
            calculator.divide(1, 0);
            fail("This line should never be reached!");
        } catch (Exception e) {
            assertThat("Wrong exceptionmessage", e.getMessage(), is("/ by zero"));
        }
    }
}
