package by.mlechka.composite.component;

import by.mlechka.composite.type.TextType;

public class Symbol implements TextComponent {

    public static final String PUNCTUATION_MARC = ".,!?;:";

    private char symbol;

    private TextType type;

    public Symbol(char symbol, TextType type) {
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    public TextType getType() {
        return type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Symbol{");
        sb.append("symbol=").append(symbol);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String action() {
        return symbol + " " + type;
    }

    @Override
    public int count(TextType type) {
        return (this.type == type) ? 1 : 0;
    }

    public static boolean isPunctuationMark(char symbol) {
        return PUNCTUATION_MARC.contains(String.valueOf(symbol));
    }

}
