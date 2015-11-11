package controllers;

import builders.ContentJSONBuilder;
import hibernate.tables.Content;
import providers.FrontPageProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/website/mainpage")
public class MainPageController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    String getAllArticlesFromMainPage() {
        FrontPageProvider frontPageProvider = new FrontPageProvider();
        ContentJSONBuilder contentJSONBuilder = new ContentJSONBuilder();
        ArrayList<Content> contents = frontPageProvider.getFrontContent();
        String frontPageJSON = contentJSONBuilder.getJSONContentInString(contents);
        return frontPageJSON;
    }
}
