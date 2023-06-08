package by.mlechka.composite.component;

import by.mlechka.composite.type.TextType;

public interface TextComponent {

    String action();

    int count(TextType type);

    TextType getType();
}
