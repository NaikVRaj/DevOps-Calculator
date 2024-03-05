// MT2023050 Vraj Jatin Naik
package com.example.miniproject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {

    @Test
    public void testPerformAddition() {
        double result = Calculator.performAddition(4, 3);
        assertEquals(7, result, 0.0001, "Testing addition of two numbers");
    }

    @Test
    public void testPerformSubtraction() {
        double result = Calculator.performSubtraction(8, 5);
        assertEquals(3, result, 0.0001, "Testing subtraction of two numbers");
    }

    @Test
    public void testPerformMultiplication() {
        double result = Calculator.performMultiplication(2, 6);
        assertEquals(12, result, 0.0001, "Testing multiplication of two numbers");
    }

    @Test
    public void testPerformDivision() {
        double result = Calculator.performDivision(9, 3);
        assertEquals(3, result, 0.0001, "Testing division of two numbers");
    }

    @Test
    public void testPerformDivisionByZero() {
        double result = Calculator.performDivision(9, 0);
        assertEquals(Double.POSITIVE_INFINITY, result, "Testing divide by zero result");
    }


    @Test
    public void testPerformSine() {
        double result = Calculator.performSine(Math.PI / 2);
        assertEquals(1, result, 0.0001, "Testing sine function");
    }

    @Test
    public void testPerformCosine() {
        double result = Calculator.performCosine(0);
        assertEquals(1, result, 0.0001, "Testing cosine function");
    }

    @Test
    public void testPerformTangent() {
        double result = Calculator.performTangent(0);
        assertEquals(0, result, 0.0001, "Testing tangent function");
    }

    @Test
    public void testPerformPower() {
        double result = Calculator.performPower(2, 3);
        assertEquals(8, result, 0.0001, "Testing power function");
    }



    @Test
    public void testPerformSquareRoot() {
        double result = Calculator.performSquareRoot(25);
        assertEquals(5, result, 0.0001, "Testing square root function");
    }

    @Test
    public void testPerformFactorial() {
        double result = Calculator.performFactorial(5);
        assertEquals(120, result, "Testing factorial function");
    }

    @Test
    public void testPerformFactorialZero() {
        double result = Calculator.performFactorial(0);
        assertEquals(1, result, "Testing factorial of 0");
    }

    @Test
    public void testPerformFactorialOne() {
        double result = Calculator.performFactorial(1);
        assertEquals(1, result, "Testing factorial of 1");
    }

    @Test
    public void testPerformLogarithm_PositiveInput() {
        double result = Calculator.performLogarithm(10); // Logarithm of 10
        assertEquals(2.3026, result, 0.0001, "Testing logarithm function with a positive input");
    }

    @Test
    public void testPerformLogarithm_ZeroInput() {
        double result = Calculator.performLogarithm(0); // Logarithm of 0
        assertEquals(Double.NaN, result, "Testing logarithm function with zero input");
    }

    @Test
    public void testPerformLogarithm_NegativeInput() {
        double result = Calculator.performLogarithm(-5); // Logarithm of -5
        assertEquals(Double.NaN, result, "Testing logarithm function with a negative input");
    }
}
