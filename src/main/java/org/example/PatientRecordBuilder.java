package org.example;

/**
 * Builder Pattern: Constructs complex patient records step-by-step
 */
public class PatientRecordBuilder {
    private String patientName;
    private RecordFactory factory;
    private boolean isDetailedReport = false;

    public PatientRecordBuilder setPatientName(String patientName) {
        this.patientName = patientName;
        return this;
    }

    public PatientRecordBuilder setFactory(RecordFactory factory) {
        this.factory = factory;
        return this;
    }

    public PatientRecordBuilder withDetailedReport() {
        this.isDetailedReport = true;
        return this;
    }

    public PatientRecordBuilder withSummaryReport() {
        this.isDetailedReport = false;
        return this;
    }

    public PatientRecord build() {
        if (factory == null) {
            throw new IllegalStateException("Factory must be set before building");
        }
        if (patientName == null || patientName.isEmpty()) {
            throw new IllegalStateException("Patient name must be set before building");
        }

        return factory.createRecordForPatient(patientName, isDetailedReport);
    }

    public void reset() {
        this.patientName = null;
        this.factory = null;
        this.isDetailedReport = false;
    }
}

