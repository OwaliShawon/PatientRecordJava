package org.example;

/**
 * Singleton Pattern: Provides a shared record ID generator across all records
 */
public class RecordIdGenerator {
    private static RecordIdGenerator instance;
    private int recordIdCounter = 1000;

    private RecordIdGenerator() {
        // Private constructor to prevent instantiation
    }

    public static RecordIdGenerator getInstance() {
        if (instance == null) {
            instance = new RecordIdGenerator();
        }
        return instance;
    }

    public synchronized int generateRecordId() {
        return recordIdCounter++;
    }
}

