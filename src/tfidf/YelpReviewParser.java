package tfidf;

import com.google.common.base.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rjanardhana on 8/13/14.
 */
public class YelpReviewParser {
    private static final String DATASETS_DIR = "/home/rjanardhana/datasets/";
    private static final String REVIEW_DATA_FILEPATH = DATASETS_DIR + "yelp_academic_dataset_review.json";
    private static final String REVIEW_SAMPLE_DATA_FILEPATH = DATASETS_DIR + "yelp_academic_dataset_review_sample.json";
    private static final String STOP_WORDS_FILEPATH = DATASETS_DIR + "stopwords.txt";

    private final List<List<String>> m_reviews;
    private final List<String> m_stopWords;

    public YelpReviewParser()
    {
        m_reviews = new ArrayList<>();
        m_stopWords = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        _loadStopWords();
        _loadYelpReviewsData();
        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken (seconds) = " + (endTime - startTime)/1000);
    }

    public List<List<String>> getReviews()
    {
        return m_reviews;
    }

    private void _loadStopWords()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(STOP_WORDS_FILEPATH));
            String line;
            while ((line = br.readLine()) != null)
            {
                m_stopWords.add(line.trim());
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this)
                    .add("reviews", m_reviews)
                    .toString();
    }

    private void _processReviewLine(String line) throws JSONException {
        if (StringUtils.isBlank(line))
        {
            return;
        }
        JSONObject jsonObject = new JSONObject(line);
        String review = jsonObject.getString("text");
        if (StringUtils.isBlank(review))
        {
            return;
        }
        // Needs ArrayList to filter than abstract List class
        ArrayList<String> words = new ArrayList<>();
        words.addAll(Arrays.asList(review
                                        .replaceAll("\\t\\n\\r", " ") // Replace newlines or tabs with space
                                        .replaceAll("\\p{P}", " ") // Replace punctuation with space
                                        .toLowerCase() // Normalize to lower case
                                        .split("\\s+"))); // Split on space
        CollectionUtils.filter(words, new Predicate()
        {
            public boolean evaluate(Object input)
            {
                String word = (String) input;
                return StringUtils.isNotBlank(word) && // not blank or null or empty
                       !m_stopWords.contains(word) && // not a stop word
                       word.matches("[a-zA-Z]+"); // alphabets only

            }
        });
        m_reviews.add(words);
    }

    private void _loadYelpReviewsData()
    {
        long index = 0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(REVIEW_DATA_FILEPATH));
            String line;
            while ((line = br.readLine()) != null)
            {
                _processReviewLine(line);
                index++;
                if (index % 100 == 0)
                {
                    System.out.print("Reviews processed = "+index+"\r");
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("Reviews processed = "+index);
    }
}
