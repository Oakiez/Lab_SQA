package com.lab6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadraticEquationTest {

        private final QuadraticEquation quadraticEquation = new QuadraticEquation();

        @ParameterizedTest(name = "Rule#{index} => a={0}, b={1}, c={2}, expected={3}")
        @CsvSource({
                        "0,  10,  20,  NOT_QUADRATIC",
                        "2,  10,  3,  REAL_ROOTS",
                        "4,  4,  1,  EQUAL_ROOTS",
                        "3,  2,  5,  IMAGINARY_ROOTS"
        })
        void determineRootNature_limitedEntryDecisionTable(int a, int b, int c, RootNature expected) {
                RootNature actual = quadraticEquation.determineRootNature(a, b, c);
                assertEquals(expected, actual,
                                () -> "a=" + a + ", b=" + b + ", c=" + c
                                                + " expected " + expected + " but got " + actual);
        }
}