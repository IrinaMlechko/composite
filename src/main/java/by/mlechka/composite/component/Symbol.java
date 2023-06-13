package by.mlechka.composite.component;

import by.mlechka.composite.type.TextType;

public class Symbol implements TextComponent {

    public static final String PUNCTUATION_MARC = "\\p{P}";

    private char symbol;

    private TextType type;

    public Symbol(char symbol, TextType type) {
        this.symbol = symbol;
        this.type = type;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public TextType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    @Override
    public int count(TextType type) {
        return (this.type == type) ? 1 : 0;
    }

    public static boolean isPunctuationMark(char symbol) {
        return String.valueOf(symbol).matches(PUNCTUATION_MARC);
    }

}
