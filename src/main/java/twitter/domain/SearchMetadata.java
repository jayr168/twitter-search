package twitter.domain;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by jayr .
 */
public class SearchMetadata {
    private String max_id_str;
    private String next_results;
    private String count;
    private String keyword;
    private String nextMaxId;

    public String getMax_id_str() {
        return max_id_str;
    }

    public void setMax_id_str(String max_id_str) {
        this.max_id_str = max_id_str;
    }

    public String getNext_results() {
        return next_results;
    }

    public void setNext_results(String next_results) {
        this.next_results = next_results;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getNextMaxId() {
        return nextMaxId;
    }

    public void setNextMaxId(String nextMaxId) {
        this.nextMaxId = nextMaxId;
    }

    public void parseValues(){

        if(next_results == null)
            return;

        List<NameValuePair> params;
        try {
            params = URLEncodedUtils.parse(new URI(next_results), "UTF-8");
            for (NameValuePair param : params) {
                if(param.getName().equals("max_id"))
                    this.nextMaxId = param.getValue();

                if(param.getName().equals("q"))
                    this.keyword = param.getValue();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
