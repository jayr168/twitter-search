package twitter.domain;

import java.util.List;

public class Info {
    private String search_text;
    private List<Tweet> tweets;
    private String next_id;
    private String prev_id;
    private String count;

    public String getSearch_text() {
        return search_text;
    }

    public void setSearch_text(String search_text) {
        this.search_text = search_text;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public String getNext_id() {
        return next_id;
    }

    public void setNext_id(String next_id) {
        this.next_id = next_id;
    }

    public String getPrev_id() {
        return prev_id;
    }

    public void setPrev_id(String prev_id) {
        this.prev_id = prev_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
