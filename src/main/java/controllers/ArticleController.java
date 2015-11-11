package controllers;

import builders.ContentJSONBuilder;
import context.ModeJSON;
import hibernate.exception.ResourceNotFoundException;
import hibernate.tables.Content;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import providers.ContentProvider;

@Controller
@RequestMapping("/website/article")
public class ArticleController {

    Content content;

    @RequestMapping(value = "{url}", method = RequestMethod.GET)
    public @ResponseBody
    String getArticleDetails(@PathVariable String url) {

        getContent(url);
        ContentJSONBuilder contentJSONBuilder = new ContentJSONBuilder();
        String contentJSON = contentJSONBuilder.getStringJSON(content, ModeJSON.FULL_MODE);
        if(contentJSON != null){
            return contentJSON;
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    private void getContent(String url){
        ContentProvider contentProvider = new ContentProvider();
        content = contentProvider.getContentByURL(url);
    }

}