package controllers;

import builders.ContentJSONBuilder;
import hibernate.dao.ContentDao;
import hibernate.daoImpl.ContentDaoImpl;
import hibernate.tables.Content;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import providers.ContentListProvider;
import providers.ContentProvider;

import java.util.ArrayList;

@Controller
@RequestMapping("/website")
public class TagSearchController {

    @RequestMapping(value ="{pageId}/{wordID}/{tagsID}", method = RequestMethod.GET)
    public @ResponseBody
    String getTagsByID(@PathVariable String pageID, @PathVariable String wordID, @PathVariable String tagsID) {

        ContentListProvider contentListProvider = new ContentListProvider();
        ContentProvider contentProvider = new ContentProvider();
        ContentJSONBuilder contentJSONBuilder = new ContentJSONBuilder();

        Integer page = new Integer(pageID);
        String word = wordID;
        String tags = tagsID;

        ArrayList<Content> contents = contentListProvider.getContents(page);
        String contentsIdArray = contentListProvider.getContentIdInString(contents);

        ArrayList<Content> contentsWord = contentProvider.getContentByString(word, contentsIdArray);
        ArrayList<Content> contentsTag = contentProvider.getContentByTags(tags, contentsIdArray);

        ArrayList<Content> general = contentsWord;

        label: for (Content contentWord: contentsWord){
             for (Content contentTag: contentsTag){
                if(contentWord.equals(contentTag)){
                    break label;
                }
                else {
                    general.add(contentTag);
                }
            }
        }

        String contentGeneral = contentJSONBuilder.getJSONContentInString(general);

        return contentGeneral;

    }
}
