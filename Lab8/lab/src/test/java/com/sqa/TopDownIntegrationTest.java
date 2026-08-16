package com.sqa;

/*
 * Integration testing - Top-Down Test Cases (Depth-First)
 * Name: นายวงศธร ธน.ยอด
 * SID:  673380425-2
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TopDownIntegrationTest {

    // branch 1 (Depth 1): Distance Integration Branch
    @Test
    public void test01_Universal_with_DistanceStub() {
        // Test UniversalConverter with DistanceConverterStub
        DistanceConverterStub distanceStub = new DistanceConverterStub();
        double actual = distanceStub.convert(1.0, "kilometer", "meter");
        assertEquals(1000.0, actual, 0.001);
    }

    @Test
    public void test02_DistanceConverter_with_MultiplierStub() {
        // Test DistanceConverter with getMultiplierStub
        DistanceMultiplierStub stub = new DistanceMultiplierStub();
        double actual = stub.convert(2.0, "kilometer", "meter");
        assertEquals(2000.0, actual, 0.001);
    }

    @Test
    public void test03_DistanceConverter_getMultiplier_Leaf() {
        // Test Leaf Node (getMultiplier)
        DistanceConverter distanceConverter = new DistanceConverter();
        double actual = distanceConverter.getMultiplier("kilometer", "meter");
        assertEquals(1000.0, actual, 0.001);
    }

    // branch 2 (Depth 2): Weight Integration Branch
    @Test
    public void test04_Universal_with_WeightStub() {
        // Test Top Module with WeightConverterStub
        WeightConverterStub weightStub = new WeightConverterStub();
        double actual = weightStub.convert(1.0, "kilogram", "gram");
        assertEquals(500.0, actual, 0.001);
    }

    @Test
    public void test05_WeightConverter_with_MultiplierStub() {
        // Test WeightConverter with getMultiplierStub
        WeightMultiplierStub stub = new WeightMultiplierStub();
        double actual = stub.convert(2.0, "kilogram", "gram");
        assertEquals(2000.0, actual, 0.001);
    }

    @Test
    public void test06_WeightConverter_getMultiplier_Leaf() {
        // Test Leaf Node
        WeightConverter weightConverter = new WeightConverter();
        double actual = weightConverter.getMultiplier("kilogram", "gram");
        assertEquals(1000.0, actual, 0.001);
    }

    // branch 3 (Depth 3): Temperature Integration Branch
    @Test
    public void test07_Universal_with_TemperatureStub() {
        // Test Top Module with TemperatureConverterStub
        TemperatureConverterStub tempStub = new TemperatureConverterStub();
        double actual = tempStub.convert(100.0, "C", "F");
        assertEquals(37.0, actual, 0.001);
    }

    @Test
    public void test08_TemperatureConverter_Leaf() {
        // Tets Leaf Node
        TemperatureConverter tempConverter = new TemperatureConverter();
        double actual = tempConverter.convert(100.0, "C", "F");
        assertEquals(212.0, actual, 0.001);
    }
}