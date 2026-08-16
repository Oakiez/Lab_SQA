package com.example.junit6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class ShiftCipher_JUnit_Test {

    /**
     * Rigorous Test :-)
     */
	private final ShiftCipher cipher = new ShiftCipher();
	
    @Test
    public void TC01_UpperCaseMessage() {
        assertEquals("ZVMADHYL", cipher.shift("SOFTWARE", 3));
    }
    
    @Test
    public void TC02_LowerCaseMessage() {
        assertEquals("zvmadhyl", cipher.shift("software", 3));
    }
    
    @Test
    public void TC03_WrapAroundMessage() {
        assertEquals("ABC", cipher.shift("XYZ", 3));
    }
    
    @Test
    public void TC04_InvalidCharacterInMessage() {
        assertEquals("invalid", cipher.shift("HELLO123", 3));
    }
}
