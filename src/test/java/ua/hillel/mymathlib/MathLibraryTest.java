package ua.hillel.mymathlib;

import org.junit.jupiter.api.*;


public class MathLibraryTest {

    private MathLibrary mathLibrary;

    @BeforeEach
    public void setUp(){
        mathLibrary = new MathLibrary();
    }

    @Test
    public void addTest_success(){
        double a = 21;
        double b = 11;

        double expected = 32;
        double actual = MathLibrary.add(a, b);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void subtractTest_success(){
        double a = 21;
        double b = 11;

        double expected = 10;
        double actual = MathLibrary.subtract(a, b);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void multiplyTest_success(){
        double a = 5;
        double b = 11;

        double expected = 55;
        double actual = MathLibrary.multiply(a, b);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void divideTest_success(){
        double a = 15;
        double b = 2;

        double expected = 7.5;
        double actual = MathLibrary.divide(a, b);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void divideTest_trowsException(){
        double a = 15;
        double b = 0;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MathLibrary.divide(a, b);
        });
    }

    @Test
    public void powerTest_success(){
        double a = 3;
        double b = 2;

        double expected = 9;
        double actual = MathLibrary.power(a, b);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void sqrRootTest_success(){
        double a = 9;

        double expected = 3;
        double actual = MathLibrary.sqrRoot(a);

        Assertions.assertEquals(actual, expected);
    }

    @AfterEach
    public void tearDown(){
        mathLibrary = null;
    }
}
