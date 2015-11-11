package builders;


import hibernate.tables.Content;
import hibernate.tables.Tag;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import providers.TagProvider;

import java.util.ArrayList;

public class TagJSONBuilder {

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public JSONArray getContentTagsJSON(Content content){
        TagProvider tagProvider = new TagProvider();
        ArrayList<Tag> tags = tagProvider.getTagsOfContent(content);
        JSONArray tagsArray = createJSONArray(tags);
        return tagsArray;
    }

    public JSONObject getTagInJSON(Tag tag){
        JSONObject tagJSON = new JSONObject();
        try {
            tagJSON.put(KEY_ID, tag.getId());
            tagJSON.put(KEY_NAME, tag.getName());
            return tagJSON;
        }
        catch (JSONException e){
            return tagJSON;
        }
    }

    public JSONArray getAllTagsInJSON() {
        TagProvider tagProvider = new TagProvider();
        ArrayList<Tag> tags = tagProvider.getAllTags();
        JSONArray tagsArray = createJSONArray(tags);
        return tagsArray;
    }

    private JSONArray createJSONArray(ArrayList<Tag> tags){
        JSONArray tagsArray = new JSONArray();

        for (Tag tag: tags){
            JSONObject tagJSON = getTagInJSON(tag);
            tagsArray.put(tagJSON);
        }
        return tagsArray;
    }
}
