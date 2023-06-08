package by.mlechka.composite.parser;

import by.mlechka.composite.base.TextComposite;

public abstract class AbstractTextParser {
    protected AbstractTextParser successor;
    public abstract void parse(String text, TextComposite composite);
}
