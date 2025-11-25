package org.example;

/**
 * Concrete factory for creating Cardiology records
 */
public class CardiologyRecordFactory extends RecordFactory {

    @Override
    public PatientRecord createRecord() {
        return new CardiologyRecord();
    }
}

