package com.sqa.lab5_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * CP353201 Software Quality Assurance (1/2569)
 * Lab#5.2 - Equivalence class testing (JUnit 5 Parameterized Tests)
 *
 * Test cases derived from Lab5.xlsx sheet "2_CompetitionScore".
 * Strategy: Strong Robust Equivalence Class Testing
 *
 * EC1-EC6:   valid score combinations / boundaries
 * EC7,EC9,EC11: score(1/2/3) > MAX_SCORE (500)
 * EC8,EC10,EC12: score(1/2/3) < MIN_SCORE (0)
 * EC13: array is null | EC14: array length > 3 | EC15: array length < 3
 */
class CompetitionScoreTest {

    private final CompetitionScore competition = new CompetitionScore();

    // =====================================================================
    // findMaxScore(int, int, int) - Valid Equivalence Classes (EC1-EC6)
    // =====================================================================
    @ParameterizedTest(name = "[{index}] findMaxScore({0}, {1}, {2}) = {3}")
    @DisplayName("findMaxScore(int,int,int) - Valid EC (EC1-EC6)")
    @CsvSource({
            // score1, score2, score3, expected             // TC id (EC)
            "350, 150, 50,  350",                           // TC001 (EC1) score1 highest
            "150, 400, 50,  400",                           // TC002 (EC2) score2 highest
            "50,  150, 450, 450",                           // TC003 (EC3) score3 highest
            "300, 300, 300, 300",                           // TC004 (EC4) all equal
            "500, 50,  10,  500",                           // TC005 (EC5) upper boundary
            "0,   0,   0,   0"                              // TC006 (EC6) lower boundary
    })
    void testFindMaxScoreThreeInts_valid(int score1, int score2, int score3, int expected) {
        assertEquals(expected, competition.findMaxScore(score1, score2, score3));
    }

    // =====================================================================
    // findMaxScore(int, int, int) - Invalid EC (EC7-EC12) and combinations
    // =====================================================================
    @ParameterizedTest(name = "[{index}] findMaxScore({0}, {1}, {2}) throws IllegalArgumentException")
    @DisplayName("findMaxScore(int,int,int) - Invalid EC (EC7-EC12) & combinations")
    @CsvSource({
            // score1, score2, score3                       // TC id (ECs covered)
            "502, 150, 50",                                 // TC007 (EC7)  score1 > MAX
            "-5,  150, 50",                                 // TC008 (EC8)  score1 < MIN
            "300, 505, 50",                                 // TC009 (EC9)  score2 > MAX
            "300, -10, 50",                                 // TC010 (EC10) score2 < MIN
            "300, 150, 510",                                // TC011 (EC11) score3 > MAX
            "300, 150, -20",                                // TC012 (EC12) score3 < MIN
            "502, 505, 50",                                 // TC016 (EC7, EC9)
            "502, -10, 50",                                 // TC017 (EC7, EC10)
            "-5,  505, 50",                                 // TC018 (EC8, EC9)
            "-5,  -10, 50",                                 // TC019 (EC8, EC10)
            "502, 150, 510",                                // TC020 (EC7, EC11)
            "502, 150, -20",                                // TC021 (EC7, EC12)
            "-5,  150, 510",                                // TC022 (EC8, EC11)
            "-5,  150, -20",                                // TC023 (EC8, EC12)
            "300, 505, 510",                                // TC024 (EC9, EC11)
            "300, 505, -20",                                // TC025 (EC9, EC12)
            "300, -10, 510",                                // TC026 (EC10, EC11)
            "300, -10, -20",                                // TC027 (EC10, EC12)
            "502, 505, 510",                                // TC028 (EC7, EC9, EC11)
            "502, 505, -20",                                // TC029 (EC7, EC9, EC12)
            "502, -10, 510",                                // TC030 (EC7, EC10, EC11)
            "502, -10, -20",                                // TC031 (EC7, EC10, EC12)
            "-5,  505, 510",                                // TC032 (EC8, EC9, EC11)
            "-5,  505, -20",                                // TC033 (EC8, EC9, EC12)
            "-5,  -10, 510",                                // TC034 (EC8, EC10, EC11)
            "-5,  -10, -20"                                 // TC035 (EC8, EC10, EC12)
    })
    void testFindMaxScoreThreeInts_invalid(int score1, int score2, int score3) {
        assertThrows(IllegalArgumentException.class,
                () -> competition.findMaxScore(score1, score2, score3));
    }

    // =====================================================================
    // findMaxScore(int[]) - Valid Equivalence Classes (EC1-EC6)
    // =====================================================================
    @ParameterizedTest(name = "[{index}] findMaxScore({0}) = {1}")
    @DisplayName("findMaxScore(int[]) - Valid EC (EC1-EC6)")
    @MethodSource("validArrayProvider")
    void testFindMaxScoreArray_valid(int[] scores, int expected) {
        assertEquals(expected, competition.findMaxScore(scores));
    }

    static Stream<Arguments> validArrayProvider() {
        return Stream.of(
                Arguments.of(new int[]{350, 150, 50}, 350),   // TC001 (EC1)
                Arguments.of(new int[]{150, 400, 50}, 400),   // TC002 (EC2)
                Arguments.of(new int[]{50, 150, 450}, 450),   // TC003 (EC3)
                Arguments.of(new int[]{300, 300, 300}, 300),  // TC004 (EC4)
                Arguments.of(new int[]{500, 50, 10}, 500),    // TC005 (EC5) upper boundary
                Arguments.of(new int[]{0, 0, 0}, 0)           // TC006 (EC6) lower boundary
        );
    }

    // =====================================================================
    // findMaxScore(int[]) - Invalid EC (EC7-EC15) and combinations
    // EC13: array null | EC14: length > 3 | EC15: length < 3
    // =====================================================================
    @ParameterizedTest(name = "[{index}] findMaxScore(array) throws IllegalArgumentException")
    @DisplayName("findMaxScore(int[]) - Invalid EC (EC7-EC15) & combinations")
    @MethodSource("invalidArrayProvider")
    void testFindMaxScoreArray_invalid(int[] scores) {
        assertThrows(IllegalArgumentException.class, () -> competition.findMaxScore(scores));
    }

    static Stream<Arguments> invalidArrayProvider() {
        return Stream.of(
                Arguments.of((Object) new int[]{502, 150, 50}),        // TC007 (EC7)
                Arguments.of((Object) new int[]{-5, 150, 50}),         // TC008 (EC8)
                Arguments.of((Object) new int[]{300, 505, 50}),        // TC009 (EC9)
                Arguments.of((Object) new int[]{300, -10, 50}),        // TC010 (EC10)
                Arguments.of((Object) new int[]{300, 150, 510}),       // TC011 (EC11)
                Arguments.of((Object) new int[]{300, 150, -20}),       // TC012 (EC12)
                Arguments.of((Object) null),                           // TC013 (EC13) null array
                Arguments.of((Object) new int[]{110, 220, 330, 440}),  // TC014 (EC14) length 4
                Arguments.of((Object) new int[]{150, 250}),            // TC015 (EC15) length 2
                Arguments.of((Object) new int[]{502, 505, 50}),        // TC016 (EC7, EC9)
                Arguments.of((Object) new int[]{502, -10, 50}),        // TC017 (EC7, EC10)
                Arguments.of((Object) new int[]{-5, 505, 50}),         // TC018 (EC8, EC9)
                Arguments.of((Object) new int[]{-5, -10, 50}),         // TC019 (EC8, EC10)
                Arguments.of((Object) new int[]{502, 150, 510}),       // TC020 (EC7, EC11)
                Arguments.of((Object) new int[]{502, 150, -20}),       // TC021 (EC7, EC12)
                Arguments.of((Object) new int[]{-5, 150, 510}),        // TC022 (EC8, EC11)
                Arguments.of((Object) new int[]{-5, 150, -20}),        // TC023 (EC8, EC12)
                Arguments.of((Object) new int[]{300, 505, 510}),       // TC024 (EC9, EC11)
                Arguments.of((Object) new int[]{300, 505, -20}),       // TC025 (EC9, EC12)
                Arguments.of((Object) new int[]{300, -10, 510}),       // TC026 (EC10, EC11)
                Arguments.of((Object) new int[]{300, -10, -20}),       // TC027 (EC10, EC12)
                Arguments.of((Object) new int[]{502, 505, 510}),       // TC028 (EC7, EC9, EC11)
                Arguments.of((Object) new int[]{502, 505, -20}),       // TC029 (EC7, EC9, EC12)
                Arguments.of((Object) new int[]{502, -10, 510}),       // TC030 (EC7, EC10, EC11)
                Arguments.of((Object) new int[]{502, -10, -20}),       // TC031 (EC7, EC10, EC12)
                Arguments.of((Object) new int[]{-5, 505, 510}),        // TC032 (EC8, EC9, EC11)
                Arguments.of((Object) new int[]{-5, 505, -20}),        // TC033 (EC8, EC9, EC12)
                Arguments.of((Object) new int[]{-5, -10, 510}),        // TC034 (EC8, EC10, EC11)
                Arguments.of((Object) new int[]{-5, -10, -20})         // TC035 (EC8, EC10, EC12)
        );
    }
}