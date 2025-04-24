package genericLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to read values from property files.
 * Author: Ajay A
 */
public class PropertyFileReader implements FrameworkConstants {

    private Properties properties;

    public PropertyFileReader() {
        try (FileInputStream file = new FileInputStream(PROPERTYFILE_PATH)) {
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + PROPERTYFILE_PATH);
        }
    }

    /**
     * This method reads the value based on the given key from the property file
     * @param key The key whose value needs to be fetched
     * @return Value mapped to the provided key
     */
    public static String getValueProperty(String key) {
        Properties properties = new Properties();
        try (FileInputStream file = new FileInputStream(PROPERTYFILE_PATH)) {
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + PROPERTYFILE_PATH);
        }
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key \"" + key + "\" not found in properties file.");
        }
        return value.trim();
    }
}