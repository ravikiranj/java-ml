package tfidf;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.sun.javafx.beans.annotations.NonNull;

import java.util.Comparator;

/**
 * Created by rjanardhana on 8/13/14.
 */
public class TermScore {
    private String m_term;
    private double m_score;

    public TermScore(@NonNull String term, double score)
    {
        m_term = term;
        m_score = score;
    }

    public String getTerm()
    {
        return m_term;
    }

    public double getScore()
    {
        return m_score;
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this)
                    .add("term", m_term)
                    .add("score", m_score)
                    .toString();
    }

    static class TermScoreCompartor implements Comparator<TermScore>
    {

        @Override
        public int compare(TermScore o1, TermScore o2) {
            return ComparisonChain.start()
                    .compare(o2.getScore(), o1.getScore())
                    .result();
        }
    }

}
