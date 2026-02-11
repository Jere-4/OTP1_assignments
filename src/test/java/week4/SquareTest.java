package week4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareTest {

    private final Calculator calculator = new Calculator();

    @ParameterizedTest(name = "The square of {0} is {1}")
    @CsvSource({ "0, 0", "1, 1", "2, 4", "4, 16", "5, 25", "6, 36" })
    void testSquare(int number, int expected) {
        calculator.square(number);
        assertEquals(expected, calculator.getResult(), "Squaring does not seem to work correctly");
    }
}
