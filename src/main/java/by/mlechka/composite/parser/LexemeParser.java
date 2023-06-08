package by.mlechka.composite.parser;

import by.mlechka.composite.component.Symbol;
import by.mlechka.composite.component.TextComponent;
import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.type.TextType;

import static by.mlechka.composite.component.Symbol.isPunctuationMark;

public class LexemeParser extends AbstractTextParser{

    @Override
    public void parse(String lexeme, TextComposite composite) {
        char[] symbols = lexeme.toCharArray();
        for (char symbol : symbols) {
            TextComponent component;
            if (isPunctuationMark(symbol)) {
                component = new Symbol(symbol, TextType.PUNCTUATION_MARK);
            } else {
                component = new Symbol(symbol, TextType.LETTER);
            }
            composite.add(component);
        }
    }

}
