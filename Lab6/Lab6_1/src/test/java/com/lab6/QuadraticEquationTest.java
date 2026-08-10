package com.lab6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadraticEquationTest {

        private final QuadraticEquation quadraticEquation = new QuadraticEquation();

        @ParameterizedTest(name = "Rule#{index} => a={0}, b={1}, c={2}, expected={3}")
        @CsvSource({
                        "0,  5,  3,  NOT_QUADRATIC",
                        "1,  5,  1,  REAL_ROOTS",
                        "1,  2,  1,  EQUAL_ROOTS",
                        "1,  1,  1,  IMAGINARY_ROOTS"
        })
        void determineRootNature_limitedEntryDecisionTable(int a, int b, int c, RootNature expected) {
                RootNature actual = quadraticEquation.determineRootNature(a, b, c);
                assertEquals(expected, actual,
                                () -> "a=" + a + ", b=" + b + ", c=" + c
                                                + " expected " + expected + " but got " + actual);
        }
}