package week4;

// java laskin

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * JUnit 5
 */
public class CalculatorTest { // The class name must end with Test

    // Fixture: all tests use the same calculator, reset before each test.
    private Calculator calculator;
    private static final double DELTA = 0.001;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
        calculator.reset();
    }

    // The test method name can be anything, as long as it has the @Test annotation
    @Test
    public void testAdd() {
        calculator.add(1);
        calculator.add(2);
        assertEquals(3.0, calculator.getResult(), DELTA, "The sum of numbers 1 and 2 is incorrect");
    }

    @Test
    public void testSubtract() {
        calculator.add(10);
        calculator.subtract(2);
        assertEquals(8.0, calculator.getResult(), DELTA, "The difference between 10 and 2 is incorrect");
    }

    @Test
    @DisplayName("Test division 8 / 2")
    public void testDivide() {
        calculator.add(8);
        calculator.divide(2);
        assertEquals(4.0, calculator.getResult(), DELTA, "Division 8/2 is incorrect");
    }

    // The correct result of this test is that division by zero throws an exception
    @Test
    @DisplayName("Test division by zero")
    public void testDivideByZero() {
        ArithmeticException exception =
                assertThrows(ArithmeticException.class, () -> calculator.divide(0));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    @DisplayName("Test multiplication 3 * 4")
    public void testMultiply() {
        calculator.add(3);       // result = 3
        calculator.multiply(4);  // result = 12
        assertEquals(12.0, calculator.getResult(), DELTA, "Multiplication 3*4 is incorrect");
    }

    @Test
    @DisplayName("Test square root of 9")
    public void testSquareRoot() {
        calculator.squareRoot(9);
        assertEquals(3, calculator.getResult(),
                "Square root of 9 should be 3");
    }
}
