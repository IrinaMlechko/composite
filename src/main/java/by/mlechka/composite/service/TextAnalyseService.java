package by.mlechka.composite.service;

import by.mlechka.composite.component.LetterCount;
import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.exception.CustomException;

import java.util.List;
import java.util.Map;

public interface TextAnalyseService {

    /**
     * Sorts the paragraphs in the given text composite by the number of sentences in each paragraph.
     *
     * @param textComposite the text composite to be sorted
     * @throws CustomException if the text composite is not of type TEXT
     */
    void sortParagraphsBySentenceCount(TextComposite textComposite) throws CustomException;

    /**
     * Finds all sentences in the given text composite that contain the longest word.
     *
     * @param textComposite the text composite to search for sentences
     * @return a list of sentences with the longest word
     */
    List<String> findSentencesWithLongestWord(TextComposite textComposite);

    /**
     * Removes sentences from the given text composite that have fewer words than the specified word count.
     *
     * @param textComposite the text composite to remove sentences from
     * @param wordCount     the minimum number of words a sentence should have to be retained
     */
    void removeSentencesWithFewerWords(TextComposite textComposite, int wordCount);

    Map<String, Integer> countSameWords(TextComposite textComposite);

    Map<Integer, LetterCount> countVowelsAndConsonants(TextComposite textComposite);
}
