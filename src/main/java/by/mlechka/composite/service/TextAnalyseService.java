package by.mlechka.composite.service;

import by.mlechka.composite.component.TextComposite;
import by.mlechka.composite.exception.CustomException;

import java.util.List;

public interface TextAnalyseService {


    void sortParagraphsBySentenceCount(TextComposite textComposite) throws CustomException;

    List<String> findSentencesWithLongestWord(TextComposite textComposite);

    void removeSentencesWithFewerWords(TextComposite textComposite, int wordCount);
}
