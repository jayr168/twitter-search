package twitter.domain;

import java.util.List;

/**
 * Created by jayr .
 */
public class TweetSearchResult {
    private List<Tweet> tweets;
    private SearchMetadata searchMetadata;

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public SearchMetadata getSearchMetadata() {
        return searchMetadata;
    }

    public void setSearchMetadata(SearchMetadata searchMetadata) {
        this.searchMetadata = searchMetadata;
    }
}
