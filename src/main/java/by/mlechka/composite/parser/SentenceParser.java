package by.mlechka.composite.parser;

import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.type.TextType;

public class SentenceParser extends AbstractTextParser {

    public static final String LEXEME_DELIMITER = "\\s+";

    public SentenceParser() {
        this.successor = new LexemeParser();
    }

    @Override
    public void parse(String sentence, TextComposite composite) {
        String[] lexemes = sentence.split(LEXEME_DELIMITER);

        for (String lexeme : lexemes) {
            TextComposite lexemeComposite = new TextComposite(TextType.LEXEME);
            successor.parse(lexeme, lexemeComposite);
            composite.add(lexemeComposite);
        }
    }
}
