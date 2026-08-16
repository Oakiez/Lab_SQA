package com.sqa.lab5_1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * CP353201 Software Quality Assurance (1/2569)
 * Lab#5.1 - Equivalence class testing (JUnit 5 Parameterized Tests)
 *
 * Test cases derived from Lab5.xlsx sheets "1_Encrypt" and "1_Decrypt".
 * Strategy: Weak Robust Equivalence Class Testing
 */
class ShiftCipherTest {

    private final ShiftCipher cipher = new ShiftCipher();

    // =====================================================================
    // Encryption - Valid Equivalence Classes (EC1-EC5)
    // EC1: plainText A-Z only | EC2: plainText a-z only
    // EC3: 0 <= key <= 25     | EC4: key >= 26 | EC5: key < 0
    // =====================================================================
    @ParameterizedTest(name = "[{index}] encrypt(\"{0}\", {1}) = \"{2}\"")
    @DisplayName("Encrypt - Valid EC (EC1-EC5)")
    @CsvSource({
            // plainText, key, expectedCipherText          // TC id (ECs covered)
            "LAPTOP, 7,  SHWAVW",                           // TC001 (EC1, EC3)
            "laptop, 29, ODSWRS",                           // TC002 (EC2, EC4)
            "LAPTOP, -7, ETIMHI",                           // TC003 (EC1, EC5)
            "LAPTOP, 0,  LAPTOP",                           // TC009 (EC1, EC3) key lower bound
            "LAPTOP, 25, KZOSNO"                            // TC010 (EC1, EC3) key upper bound
    })
    void testEncrypt_validEquivalenceClasses(String plainText, int key, String expected) {
        assertEquals(expected, cipher.encrypt(plainText, key));
    }

    // =====================================================================
    // Encryption - Invalid Equivalence Classes (EC6-EC10)
    // EC6: plainText null | EC7: plainText empty | EC8: contains space
    // EC9: contains digit | EC10: contains special character
    // =====================================================================
    @ParameterizedTest(name = "[{index}] encrypt(\"{0}\", {1}) throws IllegalArgumentException")
    @DisplayName("Encrypt - Invalid EC (EC6-EC10)")
    @CsvSource(value = {
            // plainText, key                              // TC id (ECs covered)
            "NULL_TOKEN, 7",                                // TC004 (EC6) null
            "'',         7",                                // TC005 (EC7) empty string
            "LAP TOP,    7",                                // TC006 (EC8) contains space
            "LAPTOP67,   7",                                // TC007 (EC9) contains digit
            "LAPTOP!,    7"                                 // TC008 (EC10) contains special char
    }, nullValues = "NULL_TOKEN")
    void testEncrypt_invalidEquivalenceClasses(String plainText, int key) {
        assertThrows(IllegalArgumentException.class, () -> cipher.encrypt(plainText, key));
    }

    // =====================================================================
    // Decryption - Valid Equivalence Classes (EC1-EC5)
    // EC1: cipherText A-Z only | EC2: cipherText a-z only
    // EC3: 0 <= key <= 25      | EC4: key >= 26 | EC5: key < 0
    // =====================================================================
    @ParameterizedTest(name = "[{index}] decrypt(\"{0}\", {1}) = \"{2}\"")
    @DisplayName("Decrypt - Valid EC (EC1-EC5)")
    @CsvSource({
            // cipherText, key, expectedPlainText           // TC id (ECs covered)
            "QEVOIX, 4,  MARKET",                           // TC001 (EC1, EC3)
            "qevoix, 30, MARKET",                           // TC002 (EC2, EC4)
            "IWNGAP, -4, MARKET",                           // TC003 (EC1, EC5)
            "MARKET, 0,  MARKET",                           // TC009 (EC1, EC3) key lower bound
            "LZQJDS, 25, MARKET"                            // TC010 (EC1, EC3) key upper bound
    })
    void testDecrypt_validEquivalenceClasses(String cipherText, int key, String expected) {
        assertEquals(expected, cipher.decrypt(cipherText, key));
    }

    // =====================================================================
    // Decryption - Invalid Equivalence Classes (EC6-EC10)
    // EC6: cipherText null | EC7: cipherText empty | EC8: contains space
    // EC9: contains digit  | EC10: contains special character
    // =====================================================================
    @ParameterizedTest(name = "[{index}] decrypt(\"{0}\", {1}) throws IllegalArgumentException")
    @DisplayName("Decrypt - Invalid EC (EC6-EC10)")
    @CsvSource(value = {
            // cipherText, key                              // TC id (ECs covered)
            "NULL_TOKEN, 4",                                // TC004 (EC6) null
            "'',         4",                                // TC005 (EC7) empty string
            "MAR KET,    4",                                // TC006 (EC8) contains space
            "MARKET67,   4",                                // TC007 (EC9) contains digit
            "MARKET!,    4"                                 // TC008 (EC10) contains special char
    }, nullValues = "NULL_TOKEN")
    void testDecrypt_invalidEquivalenceClasses(String cipherText, int key) {
        assertThrows(IllegalArgumentException.class, () -> cipher.decrypt(cipherText, key));
    }
}