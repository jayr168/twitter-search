package twitter.domain;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jayr .
 */
public class Tweet {
    private String text;
    private String id;
    private Date created_at;
    private User user;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Calendar getCreated_at() {
        Calendar calendar = null;

        if(this.created_at != null){
            calendar = Calendar.getInstance();
            calendar.setTime(this.created_at);
        }
        return calendar;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

}
