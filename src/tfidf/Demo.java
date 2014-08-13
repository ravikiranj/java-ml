package tfidf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demo - demo tf-idf
 */
public class Demo {

    public static List<List<String>> getDoc()
    {
        List<String> doc1 = Arrays.asList("alpha", "bravo", "charlie");
        List<String> doc2 = Arrays.asList("bravo", "charlie");
        List<String> doc3 = Arrays.asList("charlie", "bravo", "zeta");
        List<List<String>> docs = new ArrayList<List<String>>();
        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);
        return docs;
    }

    public static void main(String[] args)
    {
        List<List<String>> docs = getDoc();
        List<String> testStrings = Arrays.asList("alpha", "alpha", "charlie", "charlie");
        CorpusVocabulary corpusVocabulary = new CorpusVocabulary.Builder()
                .addAll(docs)
                .build();
        TFIDF t = new TFIDF(corpusVocabulary);
        List<TermScore> termScores = t.getTFIDFScoresForDocument(testStrings);
        System.out.println(termScores);
    }
}
