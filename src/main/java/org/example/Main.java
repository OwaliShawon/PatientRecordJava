package org.example;

/**
 * Main class demonstrating the refactored Patient Record System
 * Using Factory Method, Builder, Prototype, and Singleton patterns
 */
public class Main {
    public static void main(String[] args) {
        PatientRecordService service = new PatientRecordService();

        System.out.println("=== Testing Original Functionality (Summary Reports) ===\n");

        // Original test cases from the requirement
        service.generateRecord("cardiology", "Rahim");
        System.out.println();
        service.generateRecord("neurology", "Karim");
        System.out.println();

        System.out.println("=== Testing Orthopedics Department ===\n");
        service.generateRecord("orthopedics", "Ahmed");
        System.out.println();

        System.out.println("=== Testing Detailed Reports (Builder Pattern) ===\n");

        // Using Builder pattern to create detailed reports
        service.generateRecord("cardiology", "Fatima", true);
        System.out.println();

        service.generateRecord("neurology", "Salma", true);
        System.out.println();

        System.out.println("=== Testing New Department - Dermatology (Easy Extension) ===\n");

        // New department added without modifying core logic!
        service.generateRecord("dermatology", "Hassan");
        System.out.println();

        service.generateRecord("dermatology", "Nadia", true);
        System.out.println();

        System.out.println("=== Testing Prototype Pattern (Cloning Records) ===\n");

        // Create a template record
        PatientRecordBuilder builder = new PatientRecordBuilder();
        RecordFactory cardiologyFactory = DepartmentFactoryRegistry.getInstance().getFactory("cardiology");

        PatientRecord templateRecord = builder
                .setPatientName("Template Patient")
                .setFactory(cardiologyFactory)
                .withDetailedReport()
                .build();

        System.out.println("Original Template:");
        templateRecord.display();
        System.out.println();

        // Clone the template for multiple patients
        System.out.println("Cloned Record 1:");
        service.generateRecordFromTemplate(templateRecord, "Ali");
        System.out.println();

        System.out.println("Cloned Record 2:");
        service.generateRecordFromTemplate(templateRecord, "Zara");
        System.out.println();

        System.out.println("=== Testing Unknown Department Handling ===\n");
        service.generateRecord("unknown", "Test Patient");
        System.out.println();

        System.out.println("=== Demonstrating Singleton Pattern ===");
        System.out.println("RecordIdGenerator is a Singleton - all records share the same counter.");
        System.out.println("Notice the sequential record IDs: 1000, 1001, 1002, etc.");
    }
}