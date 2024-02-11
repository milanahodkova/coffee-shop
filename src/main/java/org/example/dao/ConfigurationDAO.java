package org.example.dao;

import org.example.models.Configuration;

import java.util.List;

public interface ConfigurationDAO {
    Configuration getConfigurationById(String id);
    List<Configuration> getAllConfigurations();
    void createConfiguration(Configuration configuration);
    void updateConfiguration(Configuration configuration);
    void deleteConfiguration(String id);
}
