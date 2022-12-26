package propertiesConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
    private final Properties properties;
    private final String propertyFilePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";
    public static PropertiesConfig configLoader;

    private PropertiesConfig() {
        this.properties = PropertiesConfig.propertyLoader(propertyFilePath);
    }

    private static Properties propertyLoader(String propertyFilePath) {
        Properties properties = new Properties();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(propertyFilePath));
            try {
                properties.load(bufferedReader);
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to laod properties file: " + propertyFilePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration not found at: " + propertyFilePath);
        }
        return properties;
    }

    public static PropertiesConfig getFileConfigReader(){
        if(configLoader ==null){
            configLoader = new PropertiesConfig();
        }
        return configLoader;
    }
    public long getLongTimeout(){
        return Long.parseLong(properties.getProperty("LongTimeout"));
    };

    public static void main(String[] args) {
        System.out.println(PropertiesConfig.getFileConfigReader().getLongTimeout());
    }
}
