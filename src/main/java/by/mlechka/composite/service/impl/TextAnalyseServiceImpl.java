package by.mlechka.composite.service.impl;

import by.mlechka.composite.component.TextComponent;
import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.exception.CustomException;
import by.mlechka.composite.service.TextAnalyseService;
import by.mlechka.composite.type.TextType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextAnalyseServiceImpl implements TextAnalyseService {

    @Override
    public void sortParagraphsBySentenceCount(TextComposite textComposite) throws CustomException {
        if (textComposite.getType() != TextType.TEXT) {
            throw new CustomException("Invalid text component" + textComposite.getType() + "Expected type: TEXT");
        }
        List<TextComponent> paragraphs = textComposite.getComponentsByType(TextType.PARAGRAPH);
        paragraphs.sort(Comparator.comparingInt(p -> countSentences((TextComposite) p)));
        textComposite.clearComponents();
        paragraphs.forEach(textComposite::add);
    }

    private int countSentences(TextComposite paragraph) {
        return paragraph.count(TextType.SENTENCE);
    }
    @Override
    public List<String> findSentencesWithLongestWord(TextComposite textComposite) {
        List<String> sentencesWithLongestWord = new ArrayList<>();

        int maxLength = 0;

        for (TextComponent component : textComposite.getComponents()) {
            if (component.getType() == TextType.SENTENCE) {
                String sentence = component.toString();
                String[] words = sentence.split("\\s+");

                for (String word : words) {
                    int wordLength = word.length();
                    if (wordLength > maxLength) {
                        maxLength = wordLength;
                    }
                }
            }
        }

        for (TextComponent component : textComposite.getComponents()) {
            if (component.getType() == TextType.SENTENCE) {
                String sentence = component.toString();
                String[] words = sentence.split("\\s+");

                for (String word : words) {
                    if (word.length() == maxLength) {
                        sentencesWithLongestWord.add(sentence);
                        break;
                    }
                }
            }
        }

        return sentencesWithLongestWord;
    }


}
