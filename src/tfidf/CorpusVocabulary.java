package tfidf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CorpusVocabulary - Load Corpus
 */
public class CorpusVocabulary
{
    private Map<String, Integer> m_wordCounts;
	private Integer m_vocabSize = 0;
    private Integer m_numDocuments = 0;

	private CorpusVocabulary(List<List<String>> documents)
    {
        m_wordCounts = new HashMap<>();
        for (List<String> document : documents)
        {
            for(String word: document)
            {
                _add(word);
            }
            m_numDocuments += 1;
        }
	}

    public int getTotalDocuments()
    {
        return m_numDocuments;
    }

    public double getInverseDocumentFrequency(String term)
    {
        Integer termFreqInAllDocs = m_wordCounts.containsKey(term) ? m_wordCounts.get(term) : 0;
        // Add 1 to denominator to avoid division by zero errors
        return Math.log(m_numDocuments / (1 + termFreqInAllDocs.doubleValue()));
    }

	private void _add(String word)
    {
		Integer actualCount = m_wordCounts.get(word);
		if (actualCount == null)
        {
			actualCount = 1;
            m_vocabSize += 1;
		}
        else
        {
			actualCount += 1;
		}
		m_wordCounts.put(word, actualCount);
	}

    static class Builder
    {
        private List<List<String>> m_documents = new ArrayList<>();

        public Builder add(List<String> document)
        {
            m_documents.add(document);
            return this;
        }

        public Builder addAll(List<List<String>> documents)
        {
            for (List<String> document : documents)
            {
                m_documents.add(document);
            }
            return this;
        }

        public CorpusVocabulary build()
        {
            return new CorpusVocabulary(m_documents);
        }
    }
}
