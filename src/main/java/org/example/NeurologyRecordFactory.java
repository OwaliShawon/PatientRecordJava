package org.example;

/**
 * Concrete factory for creating Neurology records
 */
public class NeurologyRecordFactory extends RecordFactory {

    @Override
    public PatientRecord createRecord() {
        return new NeurologyRecord();
    }
}

