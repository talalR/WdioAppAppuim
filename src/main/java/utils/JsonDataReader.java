package utils;

// MobileAutomationFramework/src/main/java/com/example/utils/JsonDataReader.java

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonDataReader {

    private static final Logger logger = LogManager.getLogger(JsonDataReader.class);
    private static final String TEST_DATA_FILE = "data/test_data.json";
    private static Map<String, List<Map<String, String>>> jsonData;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(TEST_DATA_FILE);
            if (inputStream != null) {
                jsonData = mapper.readValue(inputStream, new TypeReference<Map<String, List<Map<String, String>>>>() {});
                logger.info("JSON test data file loaded successfully.");
            } else {
                logger.error("Could not find the JSON test data file: {}", TEST_DATA_FILE);
                throw new RuntimeException("Failed to load test data from JSON file."); // Added RuntimeException
            }
        } catch (IOException e) {
            logger.error("Error loading JSON test data file: {}", e.getMessage());
            throw new RuntimeException("Error loading test data from JSON file.", e); // Added RuntimeException
        }
    }

    public static Map<String, String> getTestData(String dataSetName, String testCaseName) {
        if (jsonData != null && jsonData.containsKey(dataSetName)) {
            List<Map<String, String>> testCases = jsonData.get(dataSetName);
            for (Map<String, String> testCase : testCases) {
                if (testCase.containsKey("TestCase") && testCase.get("TestCase").equalsIgnoreCase(testCaseName)) {
                    return testCase;
                }
            }
            logger.warn("Test case '{}' not found in data set '{}'.", testCaseName, dataSetName);
        } else {
            logger.warn("Data set '{}' not found in the JSON data file.", dataSetName);
        }
        return null;
    }
}