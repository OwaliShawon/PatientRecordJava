package org.example;

/**
 * Example of a new department (Dermatology) - easily extensible
 */
public class DermatologyRecord extends BasePatientRecord {

    public DermatologyRecord() {
        super("Dermatology");
        this.tests = "Skin Biopsy, Allergy Test, UV Sensitivity Test";
    }

    @Override
    protected void displayDetailedInfo() {
        System.out.println("--- Detailed Dermatology Report ---");
        System.out.println("Skin Condition: Normal");
        System.out.println("Allergy Test: No allergies detected");
        System.out.println("UV Sensitivity: Low risk");
    }
}

