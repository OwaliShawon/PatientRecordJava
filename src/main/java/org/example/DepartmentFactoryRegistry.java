package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry to manage department factories
 * No more if-else or string comparisons in the service!
 */
public class DepartmentFactoryRegistry {
    private static DepartmentFactoryRegistry instance;
    private Map<String, RecordFactory> factoryMap;

    private DepartmentFactoryRegistry() {
        factoryMap = new HashMap<>();
        registerDefaultFactories();
    }

    public static DepartmentFactoryRegistry getInstance() {
        if (instance == null) {
            instance = new DepartmentFactoryRegistry();
        }
        return instance;
    }

    private void registerDefaultFactories() {
        registerFactory("cardiology", new CardiologyRecordFactory());
        registerFactory("neurology", new NeurologyRecordFactory());
        registerFactory("orthopedics", new OrthopedicsRecordFactory());
        registerFactory("dermatology", new DermatologyRecordFactory());
    }

    public void registerFactory(String departmentKey, RecordFactory factory) {
        factoryMap.put(departmentKey.toLowerCase(), factory);
    }

    public RecordFactory getFactory(String departmentKey) {
        return factoryMap.get(departmentKey.toLowerCase());
    }

    public boolean hasDepartment(String departmentKey) {
        return factoryMap.containsKey(departmentKey.toLowerCase());
    }
}

