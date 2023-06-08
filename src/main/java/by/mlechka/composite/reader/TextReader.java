package by.mlechka.composite.reader;

import by.mlechka.composite.exception.CustomException;

import java.nio.file.Files;
import java.nio.file.Path;

public class TextReader {
    private PropertiesStreamReader propertiesStreamReader;

    public TextReader(PropertiesStreamReader propertiesStreamReader) {
        this.propertiesStreamReader = propertiesStreamReader;
    }

    public String readTextFromFile(String fileName) throws CustomException {
        Path filePath = propertiesStreamReader.getFileFromResource(fileName);
        String text;
        try {
            text = Files.readString(filePath);
        } catch (Exception e) {
            throw new CustomException(e);
        }
        return text;
    }
}