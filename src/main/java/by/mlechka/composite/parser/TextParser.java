package by.mlechka.composite.parser;

import by.mlechka.composite.base.TextComposite;

import java.util.ArrayList;
import java.util.List;

public class TextParser extends AbstractTextParser{

    public TextParser() {
        this.successor = new ParagraphParser();
    }

    @Override
    public void parse(String text, TextComposite composite) {
        List<String> paragraphs = new ArrayList<>();

        for (int i = 0; i < paragraphs.size(); i++) {
            successor.parse(paragraphs.get(i), composite);
        }
    }
}
