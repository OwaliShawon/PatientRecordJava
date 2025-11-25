package org.example;

/**
 * Concrete factory for creating Dermatology records
 */
public class DermatologyRecordFactory extends RecordFactory {

    @Override
    public PatientRecord createRecord() {
        return new DermatologyRecord();
    }
}

