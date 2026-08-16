package com.sqa;

/*
 * Integration testing - Bottom-up Integration Test
 * Name: นายวงศธร ธน.ยอด
 * SID:  673380425-2
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BottomUpIntegrationTest {

    // Test Atomic Units / Leaf Nodes
    @Test
    public void testLevel1_Distance_getMultiplier() {
        // Driver call Leaf Node directly
        DistanceConverter distanceConverter = new DistanceConverter();
        double actual = distanceConverter.getMultiplier("kilometer", "meter");
        assertEquals(1000.0, actual, 0.001);
    }

    @Test
    public void testLevel1_Weight_getMultiplier() {
        // Driver call Leaf Node directly
        WeightConverter weightConverter = new WeightConverter();
        double actual = weightConverter.getMultiplier("kilogram", "gram");
        assertEquals(1000.0, actual, 0.001);
    }

    @Test
    public void testLevel1_Temperature_convert() {
        // Driver call Leaf Node directly
        TemperatureConverter tempConverter = new TemperatureConverter();
        double actual = tempConverter.convert(100.0, "C", "F");
        assertEquals(212.0, actual, 0.001);
    }

    // Integration with Leaf Nodes that have tested
    @Test
    public void testLevel2_DistanceConverter_convert() {
        // Driver call DistanceConverter with getMultiplier()
        DistanceConverter distanceConverter = new DistanceConverter();
        double actual = distanceConverter.convert(2.5, "kilometer", "meter");
        assertEquals(2500.0, actual, 0.001);
    }

    @Test
    public void testLevel2_WeightConverter_convert() {
        // Driver call WeightConverter with getMultiplier()
        WeightConverter weightConverter = new WeightConverter();
        double actual = weightConverter.convert(5.0, "kilogram", "gram");
        assertEquals(5000.0, actual, 0.001);
    }

    // Integration all module together
    @Test
    public void testLevel3_UniversalConverter_Distance() {
        UniversalConverter universal = new UniversalConverter();
        double actual = universal.convert(1.0, "distance", "kilometer", "meter");
        assertEquals(1000.0, actual, 0.001);
    }

    @Test
    public void testLevel3_UniversalConverter_Weight() {
        UniversalConverter universal = new UniversalConverter();
        double actual = universal.convert(1.0, "weight", "kilogram", "gram");
        assertEquals(1000.0, actual, 0.001);
    }

    @Test
    public void testLevel3_UniversalConverter_Temperature() {
        UniversalConverter universal = new UniversalConverter();
        double actual = universal.convert(0.0, "temperature", "C", "F");
        assertEquals(32.0, actual, 0.001);
    }
}