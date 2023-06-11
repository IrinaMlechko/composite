package by.mlechka.composite.builder;

import by.mlechka.composite.component.TextComponent;
import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.type.TextType;

import java.util.List;

public class TextBuilder {
    public String buildText(TextComposite composite) {
        StringBuilder sb = new StringBuilder();
        List<TextComponent> components = composite.getComponents();

        for (TextComponent component : components) {
            if (component instanceof TextComposite) {
                TextComposite subComposite = (TextComposite) component;
                sb.append(buildText(subComposite));
                if (component.getType() == TextType.LEXEME){
                    sb.append(" ");
                }
                if (component.getType() == TextType.PARAGRAPH){
                    sb.append("\n");
                }
            } else {
                sb.append(component.toString());
            }
        }

        return sb.toString();
    }

}
