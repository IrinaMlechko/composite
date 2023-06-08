package by.mlechka.composite.main;

import by.mlechka.composite.exception.CustomException;
import by.mlechka.composite.reader.PropertiesStreamReader;
import by.mlechka.composite.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

public class Main {

    public static final String FILE_NAME = "text.txt";
    static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        PropertiesStreamReader propertiesStreamReader = new PropertiesStreamReader();
        TextReader textReader = new TextReader(propertiesStreamReader);
        try {
            String text = textReader.readTextFromFile(FILE_NAME);
            System.out.println("Text from file: " + text);
        } catch (CustomException e) {
            logger.error("Error reading text from file: " + e.getMessage());
        }

    }
}
