package org.example.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ParameterProvider {

    private static final String PARAMETERS_PATH = "configurations/config.properties";
    private final Map<String, String> parameters;
    private static ParameterProvider instance;

    public ParameterProvider() {
        try {
            parameters = new HashMap<String, String>();
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PARAMETERS_PATH);
            properties.load(inputStream);
            properties.stringPropertyNames()
                    .forEach(key -> parameters.put(key, properties.getProperty(key)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String parameterName) {
        if (instance == null) {
            instance = new ParameterProvider();
        }
        return instance.parameters.get(parameterName);
    }
}
