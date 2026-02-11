package week4;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * Demonstrates the @BeforeEach, @AfterEach, @BeforeAll and @AfterAll annotations.
 */
public abstract class AbstractParent {

    @BeforeAll
    public static void startTestSystem() {
        System.out.println("@BeforeAll TESTING STARTS.");
    }

    @AfterAll
    public static void stopTestSystem() {
        System.out.println("@AfterAll TESTING FINISHED.");
    }

    @BeforeEach
    public void initTestSystem() {
        System.out.println("  @BeforeEach Start a new test method.");
    }

    @AfterEach
    public void cleanTestSystem() {
        System.out.println("  @AfterEach Test method executed.");
    }
}
