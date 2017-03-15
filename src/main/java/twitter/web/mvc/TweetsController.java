package twitter.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import twitter.domain.Info;
import twitter.domain.TweetSearchResult;
import twitter.domain.TwitterService;

@Controller
@RequestMapping("/")
public class TweetsController {
    
    @Autowired
    TwitterService twitterService;

    @GetMapping("search")
    public ModelAndView search() {
        return new ModelAndView("tweets/search-form");
    }


    @GetMapping("tweets")
    public ModelAndView list(@RequestParam(value = "q") String qParam, @RequestParam(value = "count") String count, @RequestParam(value = "next", required = false) String nextParam) {
        TweetSearchResult searchResult = twitterService.searchTweets(qParam, count, nextParam);
        Info info = new Info();
        info.setSearch_text(qParam);
        info.setTweets(searchResult.getTweets());
        info.setPrev_id(searchResult.getSearchMetadata().getMax_id_str());
        info.setNext_id(searchResult.getSearchMetadata().getNextMaxId());
        info.setCount(count);
        return new ModelAndView("tweets/list", "info", info);
    }
}
