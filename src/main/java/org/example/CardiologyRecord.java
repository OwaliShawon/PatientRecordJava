package org.example;

/**
 * Concrete implementation for Cardiology department records
 */
public class CardiologyRecord extends BasePatientRecord {

    public CardiologyRecord() {
        super("Cardiology");
        this.tests = "ECG, Blood Pressure, Cholesterol";
    }

    @Override
    protected void displayDetailedInfo() {
        System.out.println("--- Detailed Cardiology Report ---");
        System.out.println("Heart Rate: Normal");
        System.out.println("Blood Pressure: 120/80 mmHg");
        System.out.println("Cholesterol Level: Within normal range");
    }
}

