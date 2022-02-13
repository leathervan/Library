package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MappingProperties {

    private static final String propFile = "pages.properties";
    private static MappingProperties instance;
    private final Properties properties;

    private MappingProperties() {
        properties = new Properties();

        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(propFile);
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized MappingProperties getInstance() {
        if (instance == null) {
            instance = new MappingProperties();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
