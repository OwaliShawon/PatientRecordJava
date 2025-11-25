# Patient Record Service - Design Patterns Refactoring

## Overview
This project demonstrates a comprehensive refactoring of a Hospital Management System's Patient Record Generator using four key design patterns: **Factory Method**, **Builder**, **Prototype**, and **Singleton**.

## Design Patterns Implemented

### 1. Singleton Pattern
**Class:** `RecordIdGenerator`
- Ensures a single, shared instance for generating unique record IDs across the entire application
- Thread-safe implementation for concurrent access
- Maintains a sequential counter starting from 1000

```java
RecordIdGenerator.getInstance().generateRecordId()
```

### 2. Factory Method Pattern
**Classes:** `RecordFactory` (abstract), `CardiologyRecordFactory`, `NeurologyRecordFactory`, `OrthopedicsRecordFactory`, `DermatologyRecordFactory`
- Encapsulates the creation logic for department-specific records
- Allows easy extension by creating new factory classes
- Eliminates if-else chains and string comparisons

**Key Benefits:**
- New departments can be added without modifying existing code
- Each factory is responsible for creating its specific record type
- Follows Open/Closed Principle

### 3. Builder Pattern
**Class:** `PatientRecordBuilder`
- Constructs complex patient records step-by-step
- Supports optional features (summary vs. detailed reports)
- Provides a fluent API for readable code

```java
PatientRecord record = new PatientRecordBuilder()
    .setPatientName("John Doe")
    .setFactory(cardiologyFactory)
    .withDetailedReport()
    .build();
```

### 4. Prototype Pattern
**Interface:** `PatientRecord` (extends Cloneable)
- Allows cloning of existing records as templates
- Useful for creating multiple records with similar configurations
- Reduces object creation overhead

```java
PatientRecord clonedRecord = templateRecord.clone();
clonedRecord.setPatientName("New Patient");
```

## Architecture

### Core Components

1. **PatientRecord (Interface)**
   - Defines the contract for all patient records
   - Extends Cloneable for prototype support

2. **BasePatientRecord (Abstract Class)**
   - Provides common functionality for all records
   - Implements display logic with optional detailed reports

3. **Department-Specific Records**
   - `CardiologyRecord`: ECG, Blood Pressure, Cholesterol
   - `NeurologyRecord`: MRI, Cognitive Assessment
   - `OrthopedicsRecord`: X-ray, Bone Density Test
   - `DermatologyRecord`: Skin Biopsy, Allergy Test, UV Sensitivity

4. **DepartmentFactoryRegistry (Singleton)**
   - Central registry for managing department factories
   - Eliminates need for if-else chains
   - Supports dynamic factory registration

5. **PatientRecordService**
   - Main service class with NO if-else checks
   - Uses polymorphism through factory pattern
   - Clean, maintainable API

## Key Improvements

### Before Refactoring
```java
// ❌ Old code with tight coupling and if-else chains
if (department.equalsIgnoreCase("cardiology")) {
    System.out.println("Cardiology Record for " + patientName);
    System.out.println("Includes ECG, Blood Pressure, Cholesterol");
} else if (department.equalsIgnoreCase("neurology")) {
    System.out.println("Neurology Record for " + patientName);
    System.out.println("Includes MRI, Cognitive Assessment");
}
// ... more if-else branches
```

### After Refactoring
```java
// ✅ New code with clean separation and polymorphism
service.generateRecord("cardiology", "Rahim");
service.generateRecord("neurology", "Karim", true); // detailed report
```

## How to Add a New Department

Adding a new department is now incredibly simple and requires NO changes to existing code:

1. **Create a new Record class:**
```java
public class PediatricsRecord extends BasePatientRecord {
    public PediatricsRecord() {
        super("Pediatrics");
        this.tests = "Growth Chart, Vaccination Record, Developmental Assessment";
    }

    @Override
    protected void displayDetailedInfo() {
        System.out.println("--- Detailed Pediatrics Report ---");
        System.out.println("Growth: Normal for age");
        System.out.println("Vaccinations: Up to date");
        System.out.println("Development: On track");
    }
}
```

2. **Create a new Factory class:**
```java
public class PediatricsRecordFactory extends RecordFactory {
    @Override
    public PatientRecord createRecord() {
        return new PediatricsRecord();
    }
}
```

3. **Register the factory:**
```java
DepartmentFactoryRegistry.getInstance()
    .registerFactory("pediatrics", new PediatricsRecordFactory());
```

That's it! No modifications to `PatientRecordService` or any other existing classes.

## Usage Examples

### Generate Summary Report
```java
PatientRecordService service = new PatientRecordService();
service.generateRecord("cardiology", "Rahim");
```

### Generate Detailed Report
```java
service.generateRecord("neurology", "Karim", true);
```

### Use Builder Pattern Directly
```java
PatientRecordBuilder builder = new PatientRecordBuilder();
RecordFactory factory = DepartmentFactoryRegistry.getInstance()
    .getFactory("cardiology");

PatientRecord record = builder
    .setPatientName("Fatima")
    .setFactory(factory)
    .withDetailedReport()
    .build();

record.display();
```

### Clone Records (Prototype Pattern)
```java
PatientRecord template = builder
    .setPatientName("Template Patient")
    .setFactory(cardiologyFactory)
    .withDetailedReport()
    .build();

// Clone for multiple patients
PatientRecord cloned = template.clone();
cloned.setPatientName("Ali");
cloned.setRecordId(RecordIdGenerator.getInstance().generateRecordId());
cloned.display();
```

## Building and Running

### Build the project:
```bash
./gradlew build
```

### Run the application:
```bash
./gradlew run
```

## Output Sample

```
=== Testing Original Functionality (Summary Reports) ===

Record ID: 1000
Cardiology Record for Rahim
Includes ECG, Blood Pressure, Cholesterol

Record ID: 1001
Neurology Record for Karim
Includes MRI, Cognitive Assessment

=== Testing Detailed Reports (Builder Pattern) ===

Record ID: 1003
Cardiology Record for Fatima
Includes ECG, Blood Pressure, Cholesterol
--- Detailed Cardiology Report ---
Heart Rate: Normal
Blood Pressure: 120/80 mmHg
Cholesterol Level: Within normal range
```

## SOLID Principles Demonstrated

1. **Single Responsibility Principle (SRP)**
   - Each class has one clear responsibility
   - RecordIdGenerator only handles ID generation
   - Factories only handle object creation

2. **Open/Closed Principle (OCP)**
   - System is open for extension (new departments)
   - Closed for modification (no changes to core logic)

3. **Liskov Substitution Principle (LSP)**
   - All department records can be used interchangeably through the PatientRecord interface

4. **Interface Segregation Principle (ISP)**
   - Clean, focused interfaces (PatientRecord, RecordFactory)

5. **Dependency Inversion Principle (DIP)**
   - PatientRecordService depends on abstractions (RecordFactory, PatientRecord)
   - Not on concrete implementations

## Benefits of This Refactoring

✅ **No if-else chains or string comparisons in core logic**  
✅ **Easy to add new departments without modifying existing code**  
✅ **Decoupled logic for formatting, building, and cloning records**  
✅ **Factory-based system for dynamic polymorphism**  
✅ **Flexible report generation (summary vs. detailed)**  
✅ **Thread-safe record ID generation**  
✅ **Prototype support for efficient record cloning**  
✅ **Clean, maintainable, and testable code**

## Project Structure

```
src/main/java/org/example/
├── Main.java                        # Demo application
├── PatientRecordService.java        # Main service (NO if-else!)
├── PatientRecord.java               # Core interface (Prototype)
├── BasePatientRecord.java           # Abstract base class
├── RecordIdGenerator.java           # Singleton for ID generation
├── RecordFactory.java               # Factory Method (abstract)
├── PatientRecordBuilder.java        # Builder pattern
├── DepartmentFactoryRegistry.java   # Factory registry (Singleton)
│
├── CardiologyRecord.java            # Department-specific record
├── CardiologyRecordFactory.java     # Department-specific factory
├── NeurologyRecord.java
├── NeurologyRecordFactory.java
├── OrthopedicsRecord.java
├── OrthopedicsRecordFactory.java
├── DermatologyRecord.java
└── DermatologyRecordFactory.java
```

## Conclusion

This refactoring demonstrates how design patterns can transform tightly coupled, maintenance-heavy code into a flexible, extensible, and maintainable system. The elimination of if-else chains, the use of polymorphism, and the application of SOLID principles make this system ready for production use and future enhancements.

