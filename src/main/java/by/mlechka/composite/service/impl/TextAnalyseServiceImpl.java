package by.mlechka.composite.service.impl;

import by.mlechka.composite.component.LetterCount;
import by.mlechka.composite.component.Symbol;
import by.mlechka.composite.component.TextComponent;
import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.exception.CustomException;
import by.mlechka.composite.service.TextAnalyseService;
import by.mlechka.composite.type.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyseServiceImpl implements TextAnalyseService {

    public static final String VOWEL_PATTERN = "[аеёиоуыэюяeuioay]";
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

    @Override
    public Map<String, Integer> countSameWords(TextComposite textComposite) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        Map<String, Integer> repeatedWordsMap = new HashMap<>();

        for (TextComponent paragraph : textComposite.getComponents()) {
            if (paragraph.getType() == TextType.PARAGRAPH) {
                for (TextComponent sentence : ((TextComposite) paragraph).getComponents()) {
                    if (sentence.getType() == TextType.SENTENCE) {
                        for (TextComponent lexeme : ((TextComposite) sentence).getComponents()) {
                            if (lexeme.getType() == TextType.LEXEME) {
                                StringBuilder wordBuilder = new StringBuilder();
                                for (TextComponent component : ((TextComposite) lexeme).getComponents()) {
                                    if (component.getType() == TextType.LETTER) {
                                        String letter = component.toString();
                                        wordBuilder.append(letter);
                                    } else if (component.getType() == TextType.PUNCTUATION_MARK) {
                                        String word = wordBuilder.toString();
                                        if (!word.isEmpty()) {
                                            String cleanedWord = word.toLowerCase();
                                            int count = wordCountMap.getOrDefault(cleanedWord, 0) + 1;
                                            wordCountMap.put(cleanedWord, count);
                                            if (count > 1) {
                                                repeatedWordsMap.put(cleanedWord, count);
                                            }
                                        }
                                        wordBuilder = new StringBuilder();
                                    }
                                }
                                String word = wordBuilder.toString();
                                if (!word.isEmpty()) {
                                    String cleanedWord = word.toLowerCase();
                                    int count = wordCountMap.getOrDefault(cleanedWord, 0) + 1;
                                    wordCountMap.put(cleanedWord, count);
                                    if (count > 1) {
                                        repeatedWordsMap.put(cleanedWord, count);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return repeatedWordsMap;
    }

    @Override
    public Map<Integer, LetterCount> countVowelsAndConsonants(TextComposite textComposite) {
        Map<Integer, LetterCount> countMap = new HashMap<>();

        int sentenceIndex = 0;
        for (TextComponent paragraph : textComposite.getComponents()) {
            if (paragraph.getType() == TextType.PARAGRAPH) {
                for (TextComponent sentence : ((TextComposite) paragraph).getComponents()) {
                    if (sentence.getType() == TextType.SENTENCE) {
                        int vowelsCount = 0;
                        int consonantsCount = 0;

                        for (TextComponent lexeme : ((TextComposite) sentence).getComponents()) {
                            if (lexeme.getType() == TextType.LEXEME) {
                                for (TextComponent component : ((TextComposite) lexeme).getComponents()) {
                                    if (component.getType() == TextType.LETTER) {
                                        Symbol letter = (Symbol) component;
                                        char symbol = letter.getSymbol();

                                        if (isVowel(symbol)) {
                                            vowelsCount++;
                                        } else if (isConsonant(symbol)) {
                                            consonantsCount++;
                                        }
                                    }
                                }
                            }
                        }

                        LetterCount letterCount = new LetterCount(vowelsCount, consonantsCount);
                        countMap.put(sentenceIndex++, letterCount);
                    }
                }
            }
        }

        return countMap;
    }

    private boolean isVowel(char symbol) {
        Pattern regex = Pattern.compile(VOWEL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = regex.matcher(Character.toString(symbol));
        return matcher.matches();
    }

    private boolean isConsonant(char symbol) {
        char lowerCaseSymbol = Character.toLowerCase(symbol);
        return Character.isLetter(lowerCaseSymbol) && !isVowel(lowerCaseSymbol);
    }


}
