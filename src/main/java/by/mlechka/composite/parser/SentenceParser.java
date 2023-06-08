package by.mlechka.composite.parser;

import by.mlechka.composite.base.TextComposite;

public class SentenceParser extends AbstractTextParser{
    public SentenceParser() {
        this.successor = new LexemeParser();
    }
    @Override
    public void parse(String sentence, TextComposite composite) {
        
    }
}
