package context;

import hibernate.tables.Tag;

import java.util.ArrayList;

public class TagListSingleton {

    public ArrayList<Tag> tags;
    private static TagListSingleton tagListSingleton;

    private TagListSingleton(){
        tags = new ArrayList<>();
    }

    public static synchronized TagListSingleton getTagList(){
        if (tagListSingleton == null){
            tagListSingleton = new TagListSingleton();
        }
        return tagListSingleton;
    }
    public void deleteInstance(){
        tagListSingleton = null;
    }

    public void setTagsList(ArrayList<Tag> tags){
        this.tags = tags;
    }

    public ArrayList<Tag> getTags(){
        return tags;
    }

}
