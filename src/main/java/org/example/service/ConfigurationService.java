package org.example.service;

import org.example.dao.ConfigurationDAO;
import org.example.models.Configuration;

public class ConfigurationService {
    private ConfigurationDAO configurationDAO;
    public ConfigurationService(ConfigurationDAO configurationDAO) {
        this.configurationDAO = configurationDAO;
    }
    public Configuration getConfigurationById(String id) {
        return configurationDAO.getConfigurationById(id);
    }

}
