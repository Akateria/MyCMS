package builders;


import context.ContentList;
import context.ModeJSON;
import hibernate.tables.Content;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import providers.ContentProvider;

import java.util.ArrayList;

public class ContentJSONBuilder {

    private static final String KEY_NAME            = "name";
    private static final String KEY_ID              = "id";
    private static final String KEY_CREATED         = "created";
    private static final String KEY_URL             = "url";
    private static final String KEY_TAGS            = "tags";
    private static final String KEY_AUTHOR          = "author";
    private static final String KEY_TEXT            = "text";
    private static final String KEY_REVIEW          = "review";
    private static final String KEY_IMG             = "img";
    private static final String KEY_SIMILAR         = "similar";

    public String getStringJSON(Content content, ModeJSON mode) {
        JSONObject articleInJSON;

        if(mode.equals(ModeJSON.FULL_MODE)){
            articleInJSON = createFullJSON(content);
        }
        else {
            articleInJSON = createAbbreviatedJSON(content);
        }

        String articleInString = articleInJSON.toString();
        return articleInString;
    }


    public JSONObject createFullJSON(Content content) {
        try {
            JSONObject articleData = new JSONObject();
            articleData.put(KEY_ID,            content.getId());
            articleData.put(KEY_NAME,          content.getName());
            articleData.put(KEY_IMG,           content.getImagePath());
            articleData.put(KEY_CREATED,       content.getDateInString());
            articleData.put(KEY_URL,           content.getUrl());
            articleData.put(KEY_REVIEW,        content.getReviewCount());
            articleData.put(KEY_TAGS,          getTagsOfContent(content));
            articleData.put(KEY_AUTHOR,        content.getAuthor().createJSON());
            articleData.put(KEY_TEXT,          content.getText());
            articleData.put(KEY_SIMILAR,       getSimilarContextInJSON(content));


            return articleData;
        }
        catch (JSONException e){
            return null;
        }
    }

    private JSONArray getTagsOfContent(Content content){
        TagJSONBuilder tagJSONBuilder = new TagJSONBuilder();
        JSONArray tags = tagJSONBuilder.getContentTagsJSON(content);

        return tags;
    }

    public JSONObject createJSONForSimilarContent(Content content){
        try {
            JSONObject articleData = new JSONObject();
            articleData.put(KEY_ID,            content.getId());
            articleData.put(KEY_NAME,          content.getName());
            articleData.put(KEY_CREATED,       content.getDateInString());
            articleData.put(KEY_IMG,           content.getImagePath());
            articleData.put(KEY_URL,           content.getUrl());

            return articleData;
        }
        catch (JSONException e){
            return null;
        }

    }

    public JSONObject createAbbreviatedJSON(Content content){
        try {
            JSONObject articleData = new JSONObject();
            articleData.put(KEY_ID,            content.getId());
            articleData.put(KEY_NAME,          content.getName());
            articleData.put(KEY_CREATED,       content.getDateInString());
            articleData.put(KEY_URL,           content.getUrl());
            articleData.put(KEY_IMG,           content.getImagePath());
            articleData.put(KEY_REVIEW,        content.getReviewCount());
            articleData.put(KEY_TAGS,          getTagsOfContent(content));
            articleData.put(KEY_AUTHOR,        content.getAuthor().createJSON());

            return articleData;
        }
        catch (JSONException e){
            return null;
        }
    }

    private JSONArray getSimilarContextInJSON(Content content){
        JSONArray jsonContentArray = new JSONArray();
        ContentList contentList = new ContentList();
        ArrayList<Content> contents = contentList.getSimilarContent(content);
        for (Content item: contents){
            JSONObject jsonContent = createJSONForSimilarContent(item);
            jsonContentArray.put(jsonContent);
        }
        return jsonContentArray;
    }

    public String getJSONContentInString(ArrayList<Content> contents){
        ContentProvider contentProvider = new ContentProvider();
        JSONArray jsonArray = new JSONArray();
        for (Content content: contents){
            JSONObject contentJSON = createAbbreviatedJSON(content);
            jsonArray.put(contentJSON);
        }
        int size = contentProvider.getAmountOfContent();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("count", size);
            jsonObject.put("posts", jsonArray);
        }
        catch (JSONException e){

        }

        return jsonObject.toString();
    }

}
