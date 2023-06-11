package by.mlechka.composite.component;

import by.mlechka.composite.type.TextType;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private List<TextComponent> components = new ArrayList<>();
    private TextType type;

    public TextComposite(TextType textType) {
        this.type = textType;
    }

    public TextComposite() {
    }

    @Override
    public TextType getType() {
        return type;
    }

    public List<TextComponent> getComponents() {
        return components;
    }

    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type).append(": ");
        for (TextComponent component : components) {
            sb.append(component.toString());
        }
        return sb.toString();
    }

    @Override
    public int count(TextType type) {
        int count = 0;
        if (this.type == type) {
            count++;
        }
        for (TextComponent component : components) {
                count += component.count(type);
        }
        return count;
    }

    public List<TextComponent> getComponentsByType(TextType type) {
        List<TextComponent> componentsByType = new ArrayList<>();
        for (TextComponent component : components) {
            if (component.getType() == type) {
                componentsByType.add(component);
            }
        }
        return componentsByType;
    }

}
