package org.example;

/**
 * Refactored Patient Record Service - NO if-else checks or string comparisons!
 * Uses Factory Method, Builder, Prototype, and Singleton patterns
 */
public class PatientRecordService {
    private final DepartmentFactoryRegistry registry;

    public PatientRecordService() {
        this.registry = DepartmentFactoryRegistry.getInstance();
    }

    /**
     * Simplified method - generates a summary report
     */
    public void generateRecord(String department, String patientName) {
        RecordFactory factory = registry.getFactory(department);

        if (factory == null) {
            System.out.println("Unknown department: " + department);
            return;
        }

        PatientRecordBuilder builder = new PatientRecordBuilder();
        PatientRecord record = builder
                .setPatientName(patientName)
                .setFactory(factory)
                .withSummaryReport()
                .build();

        record.display();
    }

    /**
     * Enhanced method - allows specifying detailed or summary report
     */
    public void generateRecord(String department, String patientName, boolean isDetailed) {
        RecordFactory factory = registry.getFactory(department);

        if (factory == null) {
            System.out.println("Unknown department: " + department);
            return;
        }

        PatientRecordBuilder builder = new PatientRecordBuilder();

        if (isDetailed) {
            builder.setPatientName(patientName)
                   .setFactory(factory)
                   .withDetailedReport();
        } else {
            builder.setPatientName(patientName)
                   .setFactory(factory)
                   .withSummaryReport();
        }

        PatientRecord record = builder.build();
        record.display();
    }

    /**
     * Demonstrates Prototype pattern - cloning a record template
     */
    public void generateRecordFromTemplate(PatientRecord templateRecord, String newPatientName) {
        PatientRecord clonedRecord = templateRecord.clone();
        clonedRecord.setPatientName(newPatientName);
        clonedRecord.setRecordId(RecordIdGenerator.getInstance().generateRecordId());
        clonedRecord.display();
    }

    /**
     * Register a new department dynamically
     */
    public void registerDepartment(String departmentName, RecordFactory factory) {
        registry.registerFactory(departmentName, factory);
    }
}

