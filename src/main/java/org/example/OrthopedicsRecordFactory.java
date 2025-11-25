package org.example;

/**
 * Concrete factory for creating Orthopedics records
 */
public class OrthopedicsRecordFactory extends RecordFactory {

    @Override
    public PatientRecord createRecord() {
        return new OrthopedicsRecord();
    }
}

