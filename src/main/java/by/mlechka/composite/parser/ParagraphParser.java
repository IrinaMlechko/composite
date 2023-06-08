package by.mlechka.composite.parser;

import by.mlechka.composite.component.TextComposite;

import static by.mlechka.composite.type.TextType.SENTENCE;

public class ParagraphParser extends AbstractTextParser{

    public static final String SENTENCE_DELIMITER = "(?<=[.!?])\\s+";

    public ParagraphParser() {
        this.successor = new SentenceParser();
    }
    @Override
    public void parse(String paragraph, TextComposite composite) {
        String[] sentences = paragraph.split(SENTENCE_DELIMITER);

        for (String sentence : sentences) {
            TextComposite sentenceComposite = new TextComposite(SENTENCE);
            successor.parse(sentence, sentenceComposite);
            composite.add(sentenceComposite);
        }
    }
}
