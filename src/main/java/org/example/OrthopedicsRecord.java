package org.example;

/**
 * Concrete implementation for Orthopedics department records
 */
public class OrthopedicsRecord extends BasePatientRecord {

    public OrthopedicsRecord() {
        super("Orthopedics");
        this.tests = "X-ray, Bone Density Test";
    }

    @Override
    protected void displayDetailedInfo() {
        System.out.println("--- Detailed Orthopedics Report ---");
        System.out.println("X-ray Results: No fractures detected");
        System.out.println("Bone Density: Normal for age group");
        System.out.println("Joint Mobility: Full range of motion");
    }
}

