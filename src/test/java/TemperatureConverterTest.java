import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    private TemperatureConverter converter;

    @BeforeEach
    void setUp() {
        converter = new TemperatureConverter();
    }

    @Test
    void testFahrenheitToCelsius() {
        assertEquals(0, converter.fahrenheitToCelsius(32), 0.001);
        assertEquals(100, converter.fahrenheitToCelsius(212), 0.001);
        assertEquals(-40, converter.fahrenheitToCelsius(-40), 0.001);
        assertEquals(37.777, converter.fahrenheitToCelsius(100), 0.001);
    }

    @Test
    void testCelsiusToFahrenheit() {
        assertEquals(32, converter.celsiusToFahrenheit(0), 0.001);
        assertEquals(212, converter.celsiusToFahrenheit(100), 0.001);
        assertEquals(-40, converter.celsiusToFahrenheit(-40), 0.001);
        assertEquals(98.6, converter.celsiusToFahrenheit(37), 0.1);
    }

    @Test
    void testIsExtremeTemperature() {
        assertTrue(converter.isExtremeTemperature(-41));
        assertTrue(converter.isExtremeTemperature(51));

        assertFalse(converter.isExtremeTemperature(-40));
        assertFalse(converter.isExtremeTemperature(50));
        assertFalse(converter.isExtremeTemperature(20));
    }

    @Test
    void testKelvinToCelsius_BasicExample() {
        // Example: 300 K → 26.85 °C
        assertEquals(26.85, converter.kelvinToCelsius(300.0), 0.001);
    }

    @Test
    void testKelvinToCelsius_AbsoluteZero() {
        // 0 K → -273.15 °C
        assertEquals(-273.15, converter.kelvinToCelsius(0.0), 0.001);
    }

    @Test
    void testKelvinToCelsius_FreezingPoint() {
        // 273.15 K → 0 °C
        assertEquals(0.0, converter.kelvinToCelsius(273.15), 0.001);
    }

    @Test
    void testKelvinToCelsius_RoomTemperature() {
        // 293.15 K → 20 °C
        assertEquals(20.0, converter.kelvinToCelsius(293.15), 0.001);
    }
}
