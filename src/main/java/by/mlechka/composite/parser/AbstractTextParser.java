package by.mlechka.composite.parser;

import by.mlechka.composite.component.TextComposite;

public abstract class AbstractTextParser {
    protected AbstractTextParser successor;
    public abstract void parse(String text, TextComposite composite);
}
