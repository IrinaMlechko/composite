package by.mlechka.composite.parser;

import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.type.TextType;

public class TextParser extends AbstractTextParser{

    public static final String PARAGRAPH_DELIMITER = "\\r?\\n";

    public TextParser() {
        this.successor = new ParagraphParser();
    }

    @Override
    public void parse(String text, TextComposite composite) {
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER);

        for (String paragraph : paragraphs) {
            TextComposite paragraphComposite = new TextComposite(TextType.PARAGRAPH);
            successor.parse(paragraph, paragraphComposite);
            composite.add(paragraphComposite);
        }
    }
}
