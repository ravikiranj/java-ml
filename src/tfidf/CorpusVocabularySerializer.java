package tfidf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * CorpusVocabularySerializer
 *
 * @author rjanardhana
 * @since Aug 2014
 */
public class CorpusVocabularySerializer {
    private static final String DATASETS_DIR = "/home/rjanardhana/datasets/";
    private static final String SERIALIZED_CORPUS_VOCAB_FILEPATH = DATASETS_DIR + "corpus_vocab.ser";

    public void serializeCorpusVocabulary(CorpusVocabulary corpusVocabulary)
    {
        FileOutputStream fout = null;
        try {
            System.out.println("Started writing to file");
            fout = new FileOutputStream(SERIALIZED_CORPUS_VOCAB_FILEPATH);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(corpusVocabulary);
            oos.close();
            System.out.println("Done writing to file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        YelpReviewParser yelpReviewParser = new YelpReviewParser();
        CorpusVocabulary corpusVocabulary = new CorpusVocabulary(yelpReviewParser.getReviews());
        CorpusVocabularySerializer corpusVocabularySerializer = new CorpusVocabularySerializer();
        //corpusVocabularySerializer.serializeCorpusVocabulary(corpusVocabulary);
    }
}
