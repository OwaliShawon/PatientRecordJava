package org.example;

/**
 * Base class for patient records with common functionality
 */
public abstract class BasePatientRecord implements PatientRecord {
    protected int recordId;
    protected String patientName;
    protected String department;
    protected String tests;
    protected boolean isDetailedReport;

    public BasePatientRecord(String department) {
        this.department = department;
        this.isDetailedReport = false;
    }

    @Override
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    @Override
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public void setDetailedReport(boolean isDetailedReport) {
        this.isDetailedReport = isDetailedReport;
    }

    @Override
    public void display() {
        System.out.println("Record ID: " + recordId);
        System.out.println(department + " Record for " + patientName);
        System.out.println("Includes " + tests);

        if (isDetailedReport) {
            displayDetailedInfo();
        }
    }

    protected abstract void displayDetailedInfo();

    @Override
    public PatientRecord clone() {
        try {
            return (PatientRecord) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }
}

