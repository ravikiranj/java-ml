package tfidf;

import com.sun.javafx.beans.annotations.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TFIDF
 */
public class TFIDF
{
    private CorpusVocabulary m_corpusVocabulary;

	public TFIDF(@NonNull CorpusVocabulary corpusVocabulary)
    {
        m_corpusVocabulary = corpusVocabulary;
	}

    public double getTFIDFScore(List<String> document, String term)
    {
        double termFrequency = _getTermFrequency(document, term);
        double inverseDocumentFrequency = m_corpusVocabulary.getInverseDocumentFrequency(term);
        return termFrequency * inverseDocumentFrequency;
    }

    public List<TermScore> getTFIDFScoresForDocument(List<String> document)
    {
        List<TermScore> result = new ArrayList<>();
        // Use set to grab uniques
        Set<String> uniqDocument = new HashSet<>(document);
        for (String term : uniqDocument)
        {
            // Use the real list and not the uniques to compute TFIDF score
            TermScore termScore = new TermScore(term, getTFIDFScore(document, term));
            result.add(termScore);
        }
        return result;
    }

    private double _getTermFrequency(List<String> document, String term)
    {
       double termFreq = 0.0;
       for (String word : document)
       {
           if (word.equals(term))
           {
               termFreq += 1;
           }
       }
        return termFreq;
    }

}