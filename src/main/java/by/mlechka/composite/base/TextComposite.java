package by.mlechka.composite.base;

import by.mlechka.composite.type.TextType;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private List<TextComponent> components = new ArrayList<>();
    private TextType type;

    public TextComposite() {
    }

    public void setType(TextType type) {
        this.type = type;
    }

    public boolean add (TextComponent component){
        return components.add(component);
    }
    public String action() {
        String text = null;
        text += " " + type + " ";
        for (TextComponent component:components) {
            text += component.action();
        }
        return text;
    }
    @Override
    public int count(){
        //переделать!
        return 10;
    }


}
