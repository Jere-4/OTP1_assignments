package week4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExtraTest extends AbstractParent {

    private static Calculator calculator = new Calculator();
    private static final double DELTA = 0.001;

    @BeforeAll
    public static void testPowerOn() {
        System.out.println("@BeforeAll Power ON (before the first test)");
        calculator.powerOn();
    }

    @AfterAll
    public static void testPowerOff() {
        System.out.println("@AfterAll Power OFF (all tests executed).");
        calculator.powerOff();
        calculator = null;
    }

    @BeforeEach
    public void testReset() {
        System.out.println("  Reset calculator.");
        calculator.reset();
        assertEquals(0.0, calculator.getResult(), DELTA, "Reset failed");
    }

    @Test
    public void testSquare2() {
        calculator.square(2);
        assertEquals(4.0, calculator.getResult(), DELTA, "Squaring number 2 is incorrect");
    }

    @Test
    public void testSquare4() {
        calculator.square(4);
        assertEquals(16.0, calculator.getResult(), DELTA, "Squaring number 4 is incorrect");
    }

    @Test
    public void testSquare5() {
        calculator.square(5);
        assertEquals(25.0, calculator.getResult(), DELTA, "Squaring number 5 is incorrect");
    }

    @Test
    public void testSquareRoot2() {
        calculator.squareRoot(2);
        // Expected result is sqrt(2)
        assertEquals(Math.sqrt(2), calculator.getResult(), DELTA, "Square root of 2 is incorrect");
    }

    /*@Test
    @DisplayName("Test negative square root")
    public void testSquareRootNegative() {
        assertThrows(IllegalArgumentException.class, () -> calculator.squareRoot(-1),
                "Square root of a negative number should throw an exception");
    }*/
}

