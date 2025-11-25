# Design Pattern Architecture - UML Diagrams

## Class Diagram Overview

```
┌─────────────────────────────────────────────────────────────┐
│                    Design Patterns Used                      │
├─────────────────────────────────────────────────────────────┤
│ 1. Singleton Pattern - RecordIdGenerator                     │
│ 2. Factory Method Pattern - RecordFactory & Implementations  │
│ 3. Builder Pattern - PatientRecordBuilder                    │
│ 4. Prototype Pattern - PatientRecord (Cloneable)             │
└─────────────────────────────────────────────────────────────┘
```

## 1. Singleton Pattern - RecordIdGenerator

```
┌─────────────────────────────┐
│   RecordIdGenerator         │
│   <<Singleton>>             │
├─────────────────────────────┤
│ - instance: RecordIdGenerator│
│ - recordIdCounter: int      │
├─────────────────────────────┤
│ - RecordIdGenerator()       │
│ + getInstance(): RecordIdGenerator│
│ + generateRecordId(): int   │
└─────────────────────────────┘
```

## 2. Factory Method Pattern

```
                    ┌─────────────────────────┐
                    │    RecordFactory        │
                    │    <<abstract>>         │
                    ├─────────────────────────┤
                    │ + createRecord()        │
                    │ + createRecordForPatient()│
                    └───────────┬─────────────┘
                                │
                    ┌───────────┼───────────┬───────────┬───────────┐
                    │           │           │           │           │
         ┌──────────▼────┐ ┌───▼──────┐ ┌──▼───────┐ ┌▼──────────┐│
         │ Cardiology    │ │Neurology │ │Orthopedics││Dermatology││
         │ RecordFactory │ │RecordFactory││RecordFactory││RecordFactory│
         └───────────────┘ └──────────┘ └───────────┘ └───────────┘│
                    │           │           │           │           │
                    │ creates   │ creates   │ creates   │ creates   │
                    ▼           ▼           ▼           ▼           ▼
```

## 3. Prototype Pattern - PatientRecord Hierarchy

```
┌──────────────────────────────────┐
│      PatientRecord               │
│      <<interface>>               │
├──────────────────────────────────┤
│ + display(): void                │
│ + clone(): PatientRecord         │
│ + setPatientName(String): void   │
│ + setRecordId(int): void         │
└────────────────┬─────────────────┘
                 │
                 │ implements
                 │
┌────────────────▼──────────────────┐
│    BasePatientRecord              │
│    <<abstract>>                   │
├───────────────────────────────────┤
│ # recordId: int                   │
│ # patientName: String             │
│ # department: String              │
│ # tests: String                   │
│ # isDetailedReport: boolean       │
├───────────────────────────────────┤
│ + display(): void                 │
│ + clone(): PatientRecord          │
│ # displayDetailedInfo(): void     │
└────────┬──────────────────────────┘
         │
         │ extends
         │
    ┌────┼────────┬────────┬────────────┐
    │    │        │        │            │
┌───▼───┐│ ┌─────▼──┐ ┌───▼────┐ ┌────▼──────┐
│Cardio-││ │Neuro-  │ │Ortho-  │ │Dermato-   │
│logy   ││ │logy    │ │pedics  │ │logy       │
│Record ││ │Record  │ │Record  │ │Record     │
└───────┘│ └────────┘ └────────┘ └───────────┘
```

## 4. Builder Pattern

```
┌─────────────────────────────────┐
│   PatientRecordBuilder          │
│   <<Builder>>                   │
├─────────────────────────────────┤
│ - patientName: String           │
│ - factory: RecordFactory        │
│ - isDetailedReport: boolean     │
├─────────────────────────────────┤
│ + setPatientName(String): this  │
│ + setFactory(RecordFactory): this│
│ + withDetailedReport(): this    │
│ + withSummaryReport(): this     │
│ + build(): PatientRecord        │
│ + reset(): void                 │
└─────────────────────────────────┘
```

## 5. Complete System Architecture

```
┌──────────────────────────────────────────────────────────────────┐
│                          Main Application                         │
└───────────────────────┬──────────────────────────────────────────┘
                        │
                        │ uses
                        ▼
┌────────────────────────────────────────────────────────────────┐
│                   PatientRecordService                          │
├────────────────────────────────────────────────────────────────┤
│ - registry: DepartmentFactoryRegistry                          │
├────────────────────────────────────────────────────────────────┤
│ + generateRecord(String, String): void                         │
│ + generateRecord(String, String, boolean): void                │
│ + generateRecordFromTemplate(PatientRecord, String): void      │
│ + registerDepartment(String, RecordFactory): void              │
└────────────┬───────────────────────────┬───────────────────────┘
             │                           │
             │ uses                      │ uses
             ▼                           ▼
┌─────────────────────────┐    ┌──────────────────────────┐
│ DepartmentFactory       │    │ PatientRecordBuilder     │
│ Registry                │    │ <<Builder>>              │
│ <<Singleton>>           │    └──────────────────────────┘
├─────────────────────────┤              │
│ - instance: Registry    │              │ builds
│ - factoryMap: Map       │              ▼
├─────────────────────────┤    ┌──────────────────────────┐
│ + getInstance()         │    │   PatientRecord          │
│ + registerFactory()     │    │   <<interface>>          │
│ + getFactory()          │    └──────────────────────────┘
│ + hasDepartment()       │              ▲
└───────────┬─────────────┘              │
            │                            │
            │ manages                    │ implements
            ▼                            │
┌─────────────────────────┐              │
│   RecordFactory         │──creates────>│
│   <<abstract>>          │              │
└─────────────────────────┘              │
            ▲                            │
            │                            │
            │ extended by                │
    ┌───────┴────────┐                  │
    │                │                  │
    │  Department    │                  │
    │  Factories     │                  │
    └────────────────┘                  │
                                        │
                           ┌────────────┴──────────┐
                           │                       │
                   ┌───────▼────────┐     ┌───────▼────────┐
                   │ BasePatient    │     │ RecordId       │
                   │ Record         │◄────│ Generator      │
                   │ <<abstract>>   │uses │ <<Singleton>>  │
                   └────────────────┘     └────────────────┘
```

## Pattern Interaction Flow

### Creating a Simple Record (Summary Report)

```
Client
  │
  │ 1. generateRecord("cardiology", "Rahim")
  ▼
PatientRecordService
  │
  │ 2. getFactory("cardiology")
  ▼
DepartmentFactoryRegistry
  │
  │ 3. returns CardiologyRecordFactory
  ▼
PatientRecordBuilder
  │
  │ 4. setPatientName("Rahim")
  │ 5. setFactory(cardiologyFactory)
  │ 6. withSummaryReport()
  │ 7. build()
  ▼
CardiologyRecordFactory
  │
  │ 8. createRecord()
  ▼
CardiologyRecord (new instance)
  │
  │ 9. setRecordId(RecordIdGenerator.getInstance().generateRecordId())
  │ 10. display()
  ▼
Output: Record details printed
```

### Creating a Detailed Record (Builder Pattern)

```
Client
  │
  │ 1. generateRecord("neurology", "Karim", true)
  ▼
PatientRecordService
  │
  │ 2. getFactory("neurology")
  ▼
PatientRecordBuilder
  │
  │ 3. setPatientName("Karim")
  │ 4. setFactory(neurologyFactory)
  │ 5. withDetailedReport() ◄── Detailed flag set
  │ 6. build()
  ▼
NeurologyRecord
  │
  │ 7. display()
  │    └─► displayDetailedInfo() ◄── Additional details
  ▼
Output: Detailed record with extra information
```

### Cloning Records (Prototype Pattern)

```
Client
  │
  │ 1. Create template record
  ▼
PatientRecordBuilder
  │
  │ 2. Build template with detailed report
  ▼
Template PatientRecord (ID: 1007)
  │
  │ 3. clone()
  ▼
Cloned PatientRecord (shallow copy)
  │
  │ 4. setPatientName("Ali")
  │ 5. setRecordId(new ID: 1008)
  ▼
New PatientRecord with same configuration
```

## Design Pattern Benefits

### Factory Method Pattern
- ✅ Eliminates if-else chains
- ✅ Easy to add new departments
- ✅ Follows Open/Closed Principle

### Builder Pattern
- ✅ Constructs complex objects step-by-step
- ✅ Fluent API for readability
- ✅ Optional parameters (detailed/summary)

### Prototype Pattern
- ✅ Efficient object cloning
- ✅ Template-based record creation
- ✅ Reduces initialization overhead

### Singleton Pattern
- ✅ Single source of truth for IDs
- ✅ Thread-safe implementation
- ✅ Global access point

## Key Takeaways

1. **No if-else chains**: Factory pattern with registry eliminates conditional logic
2. **Polymorphism**: All records implement PatientRecord interface
3. **Extensibility**: Add new departments without modifying core code
4. **Flexibility**: Builder pattern supports various configurations
5. **Efficiency**: Prototype pattern enables quick record duplication
6. **Consistency**: Singleton ensures unique, sequential record IDs

