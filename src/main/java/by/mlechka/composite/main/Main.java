package by.mlechka.composite.main;

import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.exception.CustomException;
import by.mlechka.composite.parser.TextParser;
import by.mlechka.composite.reader.PropertiesStreamReader;
import by.mlechka.composite.reader.TextReader;
import by.mlechka.composite.type.TextType;

public class Main {

    public static final String FILE_NAME = "text.txt";

    public static void main(String[] args) throws CustomException {

        PropertiesStreamReader propertiesStreamReader = new PropertiesStreamReader();
        TextReader textReader = new TextReader(propertiesStreamReader);
        TextParser textParser = new TextParser();
        TextComposite textComposite = new TextComposite(TextType.TEXT);
        textParser.parse(textReader.readTextFromFile(FILE_NAME), textComposite);
        int paragraphCount = textComposite.count(TextType.PARAGRAPH);
        System.out.println("Amount of paragraphs: " + paragraphCount);
        int sentenceCount = textComposite.count(TextType.SENTENCE);
        System.out.println("Amount of sentences: " + sentenceCount);
        int lexemeCount = textComposite.count(TextType.LEXEME);
        System.out.println("Amount of lexemes: " + lexemeCount);
        int letterCount = textComposite.count(TextType.LETTER);
        System.out.println("Amount of letters: " + letterCount);
        int punctuationCount = textComposite.count(TextType.PUNCTUATION_MARK);
        System.out.println("Amount of punctuation marks: " + punctuationCount);
    }
}
