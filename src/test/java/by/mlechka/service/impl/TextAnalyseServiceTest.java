package by.mlechka.service.impl;

import by.mlechka.composite.component.LetterCount;
import by.mlechka.composite.component.TextComponent;
import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.exception.CustomException;
import by.mlechka.composite.parser.TextParser;
import by.mlechka.composite.service.TextAnalyseService;
import by.mlechka.composite.service.impl.TextAnalyseServiceImpl;
import by.mlechka.composite.type.TextType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

class TextAnalyseServiceTest {

    static final String FILE_NAME = "src/test/java/resources/text.txt";
    private TextAnalyseService textAnalyseService;
    private TextComposite textComposite;

    @BeforeEach
    void setUp() throws IOException {
        textAnalyseService = new TextAnalyseServiceImpl();
        TextParser textParser = new TextParser();
        textComposite = new TextComposite(TextType.TEXT);
        String text = Files.readString(Path.of(FILE_NAME));
        textParser.parse(text, textComposite);
    }

    @Test
    public void testSortParagraphsBySentenceCount() throws CustomException, IOException {
        textAnalyseService.sortParagraphsBySentenceCount(textComposite);

        List<TextComponent> paragraphs = textComposite.getComponentsByType(TextType.PARAGRAPH);
        int previousCount = Integer.MIN_VALUE;
        for (TextComponent paragraph : paragraphs) {
            int currentCount = countSentences((TextComposite) paragraph);
            Assertions.assertTrue(currentCount >= previousCount, "Paragraphs are not sorted by sentence count");
            previousCount = currentCount;
        }
    }

    @Test
    public void testFindSentencesWithLongestWord() {
        List<String> sentences = textAnalyseService.findSentencesWithLongestWord(textComposite);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, sentences.size(), "Incorrect number of sentences with longest word"),
                () -> {
                    String expectedSentence = " Longest word Sommerschlussverkauf.";
                    Assertions.assertEquals(expectedSentence, sentences.get(0), "Incorrect sentence with longest word");
                }
        );
    }


    @Test
    public void testRemoveSentencesWithFewerWords() {
        int wordCount = 5;

        textAnalyseService.removeSentencesWithFewerWords(textComposite, wordCount);

        List<TextComponent> sentences = textComposite.getComponentsByType(TextType.SENTENCE);
        for (TextComponent sentence : sentences) {
            int sentenceWordCount = getWordCount((TextComposite) sentence);
            Assertions.assertTrue(sentenceWordCount >= wordCount, "Sentences with fewer words are not removed");
        }
    }

    @Test
    public void testCountSameWords() {
        Map<String, Integer> wordCountMap = textAnalyseService.countSameWords(textComposite);

        Assertions.assertAll(
                () -> Assertions.assertEquals(5, wordCountMap.size(), "Incorrect number of repeated words"),
                () -> Assertions.assertEquals(3, wordCountMap.get("sentence"), "Incorrect count for word 'sentence'"),
                () -> Assertions.assertEquals(2, wordCountMap.get("paragraph"), "Incorrect count for word 'paragraph'"),
                () -> Assertions.assertEquals(2, wordCountMap.get("second"), "Incorrect count for word 'second'"),
                () -> Assertions.assertEquals(2, wordCountMap.get("i"), "Incorrect count for word 'i'"),
                () -> Assertions.assertEquals(2, wordCountMap.get("third"), "Incorrect count for word 'third'")
        );
    }

    @Test
    public void testCountVowelsAndConsonants() {
        Map<Integer, LetterCount> countMap = textAnalyseService.countVowelsAndConsonants(textComposite);

        Assertions.assertAll(
                () -> {
                    LetterCount letterCount1 = countMap.get(0);
                    Assertions.assertEquals(4, letterCount1.getVowelsCount(), "Incorrect vowel count for sentence 1");
                    Assertions.assertEquals(9, letterCount1.getConsonantsCount(), "Incorrect consonant count for sentence 1");
                },
                () -> {
                    LetterCount letterCount2 = countMap.get(1);
                    Assertions.assertEquals(5, letterCount2.getVowelsCount(), "Incorrect vowel count for sentence 2");
                    Assertions.assertEquals(9, letterCount2.getConsonantsCount(), "Incorrect consonant count for sentence 2");
                },
                () -> {
                    LetterCount letterCount3 = countMap.get(2);
                    Assertions.assertEquals(1, letterCount3.getVowelsCount(), "Incorrect vowel count for sentence 3");
                    Assertions.assertEquals(4, letterCount3.getConsonantsCount(), "Incorrect consonant count for sentence 3");
                },
                () -> {
                    LetterCount letterCount4 = countMap.get(3);
                    Assertions.assertEquals(6, letterCount4.getVowelsCount(), "Incorrect vowel count for sentence 4");
                    Assertions.assertEquals(10, letterCount4.getConsonantsCount(), "Incorrect consonant count for sentence 4");
                },
                () -> {
                    LetterCount letterCount2 = countMap.get(4);
                    Assertions.assertEquals(11, letterCount2.getVowelsCount(), "Incorrect vowel count for sentence 2");
                    Assertions.assertEquals(15, letterCount2.getConsonantsCount(), "Incorrect consonant count for sentence 2");
                },
                () -> {
                    LetterCount letterCount3 = countMap.get(5);
                    Assertions.assertEquals(9, letterCount3.getVowelsCount(), "Incorrect vowel count for sentence 3");
                    Assertions.assertEquals(22, letterCount3.getConsonantsCount(), "Incorrect consonant count for sentence 3");
                },
                () -> {
                    LetterCount letterCount2 = countMap.get(6);
                    Assertions.assertEquals(5, letterCount2.getVowelsCount(), "Incorrect vowel count for sentence 2");
                    Assertions.assertEquals(10, letterCount2.getConsonantsCount(), "Incorrect consonant count for sentence 2");
                },
                () -> {
                    LetterCount letterCount3 = countMap.get(7);
                    Assertions.assertEquals(4, letterCount3.getVowelsCount(), "Incorrect vowel count for sentence 3");
                    Assertions.assertEquals(10, letterCount3.getConsonantsCount(), "Incorrect consonant count for sentence 3");
                },
                () -> {
                    LetterCount letterCount2 = countMap.get(8);
                    Assertions.assertEquals(2, letterCount2.getVowelsCount(), "Incorrect vowel count for sentence 2");
                    Assertions.assertEquals(1, letterCount2.getConsonantsCount(), "Incorrect consonant count for sentence 2");
                },
                () -> {
                    LetterCount letterCount3 = countMap.get(9);
                    Assertions.assertEquals(2, letterCount3.getVowelsCount(), "Incorrect vowel count for sentence 3");
                    Assertions.assertEquals(1, letterCount3.getConsonantsCount(), "Incorrect consonant count for sentence 3");
                }
        );
    }

    private int countSentences(TextComposite paragraph) {
        return paragraph.count(TextType.SENTENCE);
    }

    private int getWordCount(TextComposite sentence) {
        int count = 0;
        for (TextComponent component : sentence.getComponents()) {
            if (component.getType() == TextType.LEXEME) {
                count++;
            }
        }
        return count;
    }

}

