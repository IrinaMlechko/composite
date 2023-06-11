package by.mlechka.composite.component;

import by.mlechka.composite.type.TextType;

public interface TextComponent {

    String toString();

    int count(TextType type);

    TextType getType();
}
