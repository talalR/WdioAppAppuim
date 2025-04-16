package utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonDataReader {

    private static final Logger logger = LogManager.getLogger(JsonDataReader.class); // Initializes a logger for this class.
    private static final String TEST_DATA_FILE = "data/test_data.json"; // Defines the path to the JSON test data file.
    private static Map<String, List<Map<String, String>>> jsonData; // Declares a map to store JSON data, keyed by dataset name.

    static { // Static initializer block executed when the class is loaded.
        ObjectMapper mapper = new ObjectMapper(); // Creates an ObjectMapper instance.
        try {
            InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(TEST_DATA_FILE); // Reads the JSON file from the classpath.
            if (inputStream != null) { // Checks if the file was found.
                jsonData = mapper.readValue(inputStream, new TypeReference<Map<String, List<Map<String, String>>>>() {}); // Parses the JSON data into the jsonData map.
                logger.info("JSON test data file loaded successfully."); // Logs a success message.
            } else { // If the file was not found.
                logger.error("Could not find the JSON test data file: {}", TEST_DATA_FILE); // Logs an error.
                throw new RuntimeException("Failed to load test data from JSON file."); // Throws a RuntimeException to halt execution.
            }
        } catch (IOException e) { // Catches IOException during file reading or parsing.
            logger.error("Error loading JSON test data file: {}", e.getMessage()); // Logs the error message.
            throw new RuntimeException("Error loading test data from JSON file.", e); // Throws a RuntimeException.
        }
    }

    public static Map<String, String> getTestData(String dataSetName, String testCaseName) { // Method to retrieve test data for a specific test case.
        if (jsonData != null && jsonData.containsKey(dataSetName)) { // Checks if data is loaded and the dataset exists.
            List<Map<String, String>> testCases = jsonData.get(dataSetName); // Gets the list of test cases for the dataset.
            for (Map<String, String> testCase : testCases) { // Iterates through the test cases.
                if (testCase.containsKey("TestCase") && testCase.get("TestCase").equalsIgnoreCase(testCaseName)) { // Finds the test case by name.
                    return testCase; // Returns the test data for the matching test case.
                }
            }
            logger.warn("Test case '{}' not found in data set '{}'.", testCaseName, dataSetName); // Logs a warning if the test case is not found.
        } else { // If the dataset is not found.
            logger.warn("Data set '{}' not found in the JSON data file.", dataSetName); // Logs a warning.
        }
        return null; // Returns null if no matching data is found.
    }
}