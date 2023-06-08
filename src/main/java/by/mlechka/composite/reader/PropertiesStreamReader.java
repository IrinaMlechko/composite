package by.mlechka.composite.reader;

import by.mlechka.composite.exception.CustomException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PropertiesStreamReader {

    public Path getFileFromResource(String fileName) throws CustomException {
        URL resource;
        Path path;
        try {
            resource = getClass().getClassLoader().getResource(fileName);
            path = Paths.get(resource.toURI());
        } catch (URISyntaxException ex) {
            throw new CustomException("Could to get path for " + fileName);
        }
        if (resource == null) {
            throw new IllegalArgumentException("File not found " + fileName);
        }
        return path;
    }
}
