package org.example;

/**
 * Prototype Pattern: Interface for cloneable patient records
 */
public interface PatientRecord extends Cloneable {
    void display();
    PatientRecord clone();
    void setPatientName(String patientName);
    void setRecordId(int recordId);
}

