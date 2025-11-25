package org.example;

/**
 * Concrete implementation for Neurology department records
 */
public class NeurologyRecord extends BasePatientRecord {

    public NeurologyRecord() {
        super("Neurology");
        this.tests = "MRI, Cognitive Assessment";
    }

    @Override
    protected void displayDetailedInfo() {
        System.out.println("--- Detailed Neurology Report ---");
        System.out.println("MRI Scan: No abnormalities detected");
        System.out.println("Cognitive Score: 95/100");
        System.out.println("Neurological Reflexes: Normal");
    }
}

