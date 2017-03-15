package twitter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import twitter.domain.TweetSearchResult;
import twitter.domain.TwitterService;

/**
 * Created by jayr .
 */
@Controller
@RequestMapping("/twitter")
public class TwitterController {

    @Autowired TwitterService twitterService;

    @GetMapping(path = "/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<TweetSearchResult> searchTweets(@PathVariable String keyword,
                                             @RequestParam(value = "count", required = false, defaultValue = "15") String count,
                                             @RequestParam(value = "maxId", required = false) String maxId) {

        TweetSearchResult result = twitterService.searchTweets(keyword, count, maxId);

        if(result == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping(path = "/saveSearch")
//    HttpEntity<String> saveSearch(HttpServletRequest request,
//            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
//
//        String attributeName = "keywords";
//
//        request.getSession().getAttribute()
//        request.getSession().setAttribute("keywords",
//
//        if(result == null)
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//        return new ResponseEntity<>("", HttpStatus.OK);
//    }
}
