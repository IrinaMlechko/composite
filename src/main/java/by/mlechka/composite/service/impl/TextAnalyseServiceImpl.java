package by.mlechka.composite.service.impl;

import by.mlechka.composite.component.TextComponent;
import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.exception.CustomException;
import by.mlechka.composite.service.TextAnalyseService;
import by.mlechka.composite.type.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextAnalyseServiceImpl implements TextAnalyseService {

    static Logger logger = LogManager.getLogger();

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

        for (TextComponent paragraph : textComposite.getComponents()) {
            if (paragraph.getType() == TextType.PARAGRAPH) {
                for (TextComponent sentence : ((TextComposite) paragraph).getComponents()) {
                    if (sentence.getType() == TextType.SENTENCE) {
                        for (TextComponent lexeme : ((TextComposite) sentence).getComponents()) {
                            if (lexeme.getType() == TextType.LEXEME) {
                                int lexemeLength = getLetterCount((TextComposite) lexeme);
                                if (lexemeLength > maxLength) {
                                    maxLength = lexemeLength;
                                    logger.debug("Max length: " + maxLength);
                                    logger.debug(" Longest word: " + lexeme);
                                }
                            }
                        }
                    }
                }
            }
        }

        for (TextComponent paragraph : textComposite.getComponents()) {
            if (paragraph.getType() == TextType.PARAGRAPH) {
                for (TextComponent sentence : ((TextComposite) paragraph).getComponents()) {
                    if (sentence.getType() == TextType.SENTENCE) {
                        boolean hasLongestWord = false;
                        for (TextComponent lexeme : ((TextComposite) sentence).getComponents()) {
                            if (lexeme.getType() == TextType.LEXEME) {
                                int lexemeLength = getLetterCount((TextComposite) lexeme);
                                if (lexemeLength == maxLength) {
                                    hasLongestWord = true;
                                    break;
                                }
                            }
                        }
                        if (hasLongestWord) {
                            sentencesWithLongestWord.add(sentence.toString());
                        }
                    }
                }
            }
        }

        return sentencesWithLongestWord;
    }

    private int getLetterCount(TextComposite lexeme) {
        int count = 0;
        for (TextComponent component : lexeme.getComponents()) {
            if (component.getType() == TextType.LETTER) {
                count++;
            }
        }
        return count;
    }
    @Override
    public void removeSentencesWithFewerWords(TextComposite textComposite, int wordCount) {
        List<TextComponent> sentencesToRemove = new ArrayList<>();

        for (TextComponent paragraph : textComposite.getComponents()) {
            if (paragraph.getType() == TextType.PARAGRAPH) {
                for (TextComponent sentence : ((TextComposite) paragraph).getComponents()) {
                    if (sentence.getType() == TextType.SENTENCE) {
                        int sentenceWordCount = getWordCount((TextComposite) sentence);
                        logger.debug(sentence);
                        logger.debug("Amount of words in sentence " + sentenceWordCount);
                        if (sentenceWordCount < wordCount) {
                            sentencesToRemove.add(sentence);
                        }
                    }
                }
            }
        }

        for (TextComponent paragraph : textComposite.getComponents()) {
            if (paragraph.getType() == TextType.PARAGRAPH) {
                for (TextComponent sentence : sentencesToRemove) {
                    ((TextComposite) paragraph).getComponents().remove(sentence);
                }
            }
        }
    }

    private int getWordCount(TextComposite sentence) {
        int count = 0;
        for (TextComponent component : sentence.getComponents()) {
            if (component.getType() == TextType.LEXEME) {
                count++;
            }
        }
        return count;
    }


}
