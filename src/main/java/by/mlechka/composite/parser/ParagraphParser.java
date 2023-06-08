package by.mlechka.composite.parser;

import by.mlechka.composite.base.TextComposite;

public class ParagraphParser extends AbstractTextParser{
    public ParagraphParser() {
        this.successor = new SentenceParser();
    }
    @Override
    public void parse(String paragraph, TextComposite composite) {
        
    }
}
