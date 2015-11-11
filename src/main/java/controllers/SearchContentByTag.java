package controllers;

import builders.ContentJSONBuilder;
import hibernate.tables.Content;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import providers.ContentProvider;

import java.util.ArrayList;

@Controller
@RequestMapping("/website/search")
public class SearchContentByTag {

    @RequestMapping(value = "{tagID}", method = RequestMethod.GET)
    public @ResponseBody
    String getContentByTag(@PathVariable String tagID) {

        ContentProvider contentProvider = new ContentProvider();
        ContentJSONBuilder contentJSONBuilder = new ContentJSONBuilder();

        ArrayList<Content> contentsTag = contentProvider.getContentByTags(tagID);
        String contentGeneral = contentJSONBuilder.getJSONContentInString(contentsTag);
        return contentGeneral;
    }


}
