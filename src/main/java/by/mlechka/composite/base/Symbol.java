package by.mlechka.composite.base;

import by.mlechka.composite.type.TextType;

public class Symbol implements TextComponent {

    private char symbol;

    private TextType type;

    public Symbol(char symbol, TextType type) {
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    public String action() {
        return symbol + " " + type;
    }

    @Override
    public int count() {
        return 1;
    }
}
