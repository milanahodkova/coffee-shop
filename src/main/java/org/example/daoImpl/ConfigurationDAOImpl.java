package org.example.daoImpl;

import org.example.dao.ConfigurationDAO;
import org.example.models.Configuration;
import org.example.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationDAOImpl implements ConfigurationDAO {

    @Override
    public Configuration getConfigurationById(String id) {
        String query = "SELECT * FROM configuration WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int value = resultSet.getInt("value");
                    return new Configuration(id, value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Configuration> getAllConfigurations() {
        List<Configuration> configurations = new ArrayList<>();
        String query = "SELECT * FROM configuration";
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                int value = resultSet.getInt("value");
                Configuration configuration = new Configuration(id, value);
                configurations.add(configuration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return configurations;
    }

    @Override
    public void createConfiguration(Configuration configuration) {
        String query = "INSERT INTO configuration (id, value) VALUES (?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, configuration.getId());
            statement.setInt(2, configuration.getValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateConfiguration(Configuration configuration) {
        String query = "UPDATE configuration SET value = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, configuration.getValue());
            statement.setString(2, configuration.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteConfiguration(String id) {
        String query = "DELETE FROM configuration WHERE id = ?";
        try (Connection connection =  DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
