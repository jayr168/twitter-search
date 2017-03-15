package twitter.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;

/**
 * Created by jayr .
 */
@Service
public class TwitterService {

    private static final String TWITTER_AUTH_URL    = "https://api.twitter.com/oauth2/token";
    private static final String TWITTER_API_URL     = "https://api.twitter.com/1.1/search/tweets.json";

    @Value("${consumerkey}")
    private String consumerKey;
    @Value("${consumersecret}")
    private String consumerSecret;

    //prepare the key and secret and encode to base64
    public String getBase64Auth(){

        String base64EncodedAuth = "";

        String concatenatedKeyAndSecret = consumerKey + ":" + consumerSecret;

        base64EncodedAuth = Base64.getEncoder().encodeToString(concatenatedKeyAndSecret.getBytes());

        return base64EncodedAuth;
    }

    //obtain the OAuth Token for use to access the API
    public String obtainBearerToken(){

        String token = "";
        HttpResponse<JsonNode> jsonResponse = null;

        try {
            jsonResponse = Unirest.post(TWITTER_AUTH_URL)
                    .header("Authorization", "Basic " + getBase64Auth())
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8.")
                    .body("grant_type=client_credentials")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if(jsonResponse != null){
            token = jsonResponse.getBody().getObject().getString("access_token");
        }

        return "Bearer " + token;
    }

    /**
     *
     * Returns TweetSearchResult objects which contains the result from the twitter API
     *
     * @param searchParam the keyword to search for in tweets
     * @param count The number of tweets to return per page, up to a maximum of 100. Defaults to 15
     * @param maxId Returns results with an ID less than (that is, older than) or equal to the specified ID.
     * @return the TweetSearchResult object which contains the matching tweets and search metadata
     */
    public TweetSearchResult searchTweets(String searchParam, String count, String maxId){
        String result = "";
        String jsonStr = "";
        String jsonSearchMetadata ="";

        HttpRequest request = Unirest.get(TWITTER_API_URL);
        request.header("Authorization", obtainBearerToken())
                .queryString("q", searchParam)
                .queryString("count", count);

        if(maxId != null && !maxId.isEmpty()){
            request.queryString("max_id", maxId);
        }

        if(count != null && !count.isEmpty()){
            request.queryString("count", count);
        }

        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = request.asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        try {
            jsonStr = jsonResponse.getBody().getObject().getJSONArray("statuses").toString();
            jsonSearchMetadata = jsonResponse.getBody().getObject().getJSONObject("search_metadata").toString();

            jsonStr = jsonStr.replaceAll("(?i)" + searchParam, "<strong>" + searchParam + "</strong>");
        }catch (Exception e){
            return null;
        }

        Gson gson = new GsonBuilder().setDateFormat("EEE MMM dd HH:mm:ss ZZZZ yyyy").create();
        Tweet[] tweets = gson.fromJson(jsonStr, Tweet[].class);
        SearchMetadata metadata = gson.fromJson(jsonSearchMetadata, SearchMetadata.class);
        metadata.parseValues();

        TweetSearchResult tweetSearchResult = new TweetSearchResult();
        tweetSearchResult.setTweets(Arrays.asList(tweets));
        tweetSearchResult.setSearchMetadata(metadata);

        return tweetSearchResult;
    }

}
