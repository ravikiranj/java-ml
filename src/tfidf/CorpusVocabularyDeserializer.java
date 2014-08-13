package tfidf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * CorpusVocabularyDeSerializer
 *
 * @author rjanardhana
 * @since Aug 2014
 */
public class CorpusVocabularyDeserializer {
    private static final String DATASETS_DIR = "/home/rjanardhana/datasets/";
    private static final String SERIALIZED_CORPUS_VOCAB_FILEPATH = DATASETS_DIR + "corpus_vocab.ser";

    public CorpusVocabulary deserializeCorpusVocabulary()
    {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(SERIALIZED_CORPUS_VOCAB_FILEPATH);
            ObjectInputStream ois = new ObjectInputStream(fin);
            CorpusVocabulary corpusVocabulary = (CorpusVocabulary) ois.readObject();
            ois.close();
            return corpusVocabulary;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        CorpusVocabularyDeserializer corpusVocabularyDeserializer = new CorpusVocabularyDeserializer();
        System.out.println("Started deserializing");
        CorpusVocabulary corpusVocabulary = corpusVocabularyDeserializer.deserializeCorpusVocabulary();
        System.out.println("Done deserializing");
        System.out.println(corpusVocabulary);
    }
}
