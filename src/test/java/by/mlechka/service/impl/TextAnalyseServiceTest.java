package by.mlechka.service.impl;

import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.exception.CustomException;
import by.mlechka.composite.service.TextAnalyseService;
import by.mlechka.composite.service.impl.TextAnalyseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class TextAnalyseServiceTest {

    static final String FILE_PATH = "src/main/resources/text1.txt";
    private TextAnalyseService textAnalyseService;
    private TextComposite textComposite;

    @BeforeEach
    void setUp() throws IOException {
        textAnalyseService = new TextAnalyseServiceImpl();
        textComposite = new TextComposite();
        String text = Files.readString(Path.of(FILE_PATH));
    }

    @Test
    void sortParagraphsBySentenceCount_ValidTextComposite_SortsParagraphsBySentenceCount() {
        // Arrange

        // Act
        Assertions.assertDoesNotThrow(() -> textAnalyseService.sortParagraphsBySentenceCount(textComposite));

        // Assert
        // Add your assertions here
    }

    @Test
    void sortParagraphsBySentenceCount_InvalidTextComposite_ThrowsCustomException() {
        // Arrange
        TextComposite invalidTextComposite = new TextComposite();

        // Act & Assert
        Assertions.assertThrows(CustomException.class, () ->
                textAnalyseService.sortParagraphsBySentenceCount(invalidTextComposite));
    }

    @Test
    void findSentencesWithLongestWord_ValidTextComposite_ReturnsSentencesWithLongestWord() {
        // Arrange

        // Act
        List<String> sentencesWithLongestWord = textAnalyseService.findSentencesWithLongestWord(textComposite);

        // Assert
        // Add your assertions here
    }

    @Test
    void removeSentencesWithFewerWords_ValidTextComposite_RemovesSentencesWithFewerWords() {
        // Arrange
        int wordCount = 5;

        // Act
        Assertions.assertDoesNotThrow(() ->
                textAnalyseService.removeSentencesWithFewerWords(textComposite, wordCount));

        // Assert
        // Add your assertions here
    }
}

