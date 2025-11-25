package org.example;

/**
 * Factory Method Pattern: Abstract factory for creating patient records
 */
public abstract class RecordFactory {

    public abstract PatientRecord createRecord();

    public PatientRecord createRecordForPatient(String patientName, boolean isDetailedReport) {
        PatientRecord record = createRecord();
        record.setRecordId(RecordIdGenerator.getInstance().generateRecordId());
        record.setPatientName(patientName);

        if (record instanceof BasePatientRecord) {
            ((BasePatientRecord) record).setDetailedReport(isDetailedReport);
        }

        return record;
    }
}

