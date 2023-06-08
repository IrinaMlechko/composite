//package by.mlechka.composite.base;
//
//public class TextLeaf implements TextComponent {
//
//    private int id;
//
//    public TextLeaf(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void operation() {
//        System.out.println("Leaf -> Performing operation");
//    }
//
//    public void add(TextComponent c) {
//        System.out.println("Leaf -> add. Doing nothing");
//        // генерация исключения и return false (если метод не void)
//    }
//
//    public void remove(TextComponent c) {
//        System.out.println("Leaf -> remove. Doing nothing");
//        // генерация исключения и return false (если метод не void)
//    }
//
//    public Object getChild(int index) {
//        throw new UnsupportedOperationException();
//    }
//}
