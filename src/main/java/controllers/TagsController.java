package controllers;

import builders.TagJSONBuilder;
import context.ListTags;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/website/alltags")
public class TagsController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    String getAllTags() {
        TagJSONBuilder tagJSONBuilder = new TagJSONBuilder();
        JSONArray jsonArray = tagJSONBuilder.getAllTagsInJSON();
        return jsonArray.toString();
    }
}
