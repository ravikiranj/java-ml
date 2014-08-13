package tfidf;

import com.google.common.base.Objects;
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
    private static final String HOME_DIR = "/Users/rjanardhana/";
    private static final String FILE_NAME = HOME_DIR + "git/java-ml/data/yelp_academic_dataset_review-sample.json";
    private final List<List<String>> m_reviews;

    public YelpReviewParser()
    {
        m_reviews = new ArrayList<>();
        _loadYelpReviewsData();
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this)
                    .add("reviews", m_reviews)
                    .toString();
    }

    private void _processLine(String line) throws JSONException {
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
        m_reviews.add(Arrays.asList(review.split("\\s+")));
    }

    private void _loadYelpReviewsData()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = br.readLine()) != null)
            {
                _processLine(line);
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
    }
}
