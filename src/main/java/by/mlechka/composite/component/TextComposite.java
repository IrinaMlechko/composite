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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TextComposite{");
        sb.append("components=").append(components);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public String action() {
        StringBuilder sb = new StringBuilder();
        sb.append(type).append(": ");
        for (TextComponent component : components) {
            sb.append(component.action());
        }
        return sb.toString();
    }

//
//    public int count() {
//        int count = 0;
//        for (TextComponent component : components) {
//            count += component.count();
//        }
//        return count;
//    }
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
