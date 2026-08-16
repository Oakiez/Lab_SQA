package com.sqa;

/*
 * Integration testing - Top-down Stubs
 * Name: นายวงศธร ธน.ยอด
 * SID:  673380425-2
 */

// Stub for DistanceConverter
class DistanceConverterStub extends DistanceConverter {
    @Override
    public double convert(double distanceValue, String fromUnit, String toUnit) {
        return 1000.0;
    }
}

// Stub for WeightConverter
class WeightConverterStub extends WeightConverter {
    @Override
    public double convert(double massValue, String fromUnit, String toUnit) {
        return 500.0;
    }
}

// Stub for TemperatureConverter
class TemperatureConverterStub extends TemperatureConverter {
    @Override
    public double convert(double tempValue, String fromUnit, String toUnit) {
        return 37.0;
    }
}

// Stub for getMultiplier() in DistanceConverter
class DistanceMultiplierStub extends DistanceConverter {
    @Override
    public double getMultiplier(String fromUnit, String toUnit) {
        return 1000.0; // Stub multiplier value
    }
}

// Stub for getMultiplier() in WeightConverter
class WeightMultiplierStub extends WeightConverter {
    @Override
    public double getMultiplier(String fromUnit, String toUnit) {
        return 1000.0;
    }
}