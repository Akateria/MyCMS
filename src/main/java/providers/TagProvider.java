package providers;

import hibernate.dao.ContentTagLinkerDao;
import hibernate.dao.TagDao;
import hibernate.daoImpl.ContentTagLinkerDaoImpl;
import hibernate.daoImpl.TagDaoImpl;
import hibernate.tables.Content;
import hibernate.tables.ContentTagLinker;
import hibernate.tables.Tag;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagProvider {

    private static final String KEY_CONTENT_ID = "content";

    public Tag getTagByID(Integer id) {
        Tag tag = null;
        TagDao tagDao = new TagDaoImpl();
        try {
            tag = tagDao.getTag(id);
        } catch (SQLException e) {

        }
        return tag;
    }

    public ArrayList<Tag> getTagsById(ArrayList<Integer> tagsId){
        ArrayList<Tag> tags = new ArrayList<>();
        for (Integer id: tagsId){
            Tag tag = getTagByID(id);
            tags.add(tag);
        }

        return tags;
    }

    public ArrayList<Tag> getTagsOfContent(Content content) {
        ArrayList<Tag> tags = new ArrayList<>();
        List<ContentTagLinker> contentTagLinkers = getRelatedLinkers(content);

        for (ContentTagLinker contentTagLinker : contentTagLinkers) {
            Tag tag = contentTagLinker.getTag();
            tags.add(tag);
        }
        return tags;
    }

    public ArrayList<Tag> getAllTags() {
        ArrayList<Tag> tags = new ArrayList<>();
        TagDao tagDao = new TagDaoImpl();
        try {
            List<Tag> tagList = tagDao.getTags();
            for (Tag tag : tagList) {
                tags.add(tag);
            }
        } catch (SQLException e) {

        }
        return tags;

    }

    private List<ContentTagLinker> getRelatedLinkers(Content content) {

        List<ContentTagLinker> contentTagLinkers = null;

        ContentTagLinkerDao contentTagLinkerDao = new ContentTagLinkerDaoImpl();
        try {
            contentTagLinkers =
                    contentTagLinkerDao.getContentTagLinkersByProperty(KEY_CONTENT_ID, content);
        } catch (SQLException e) {

        }
        return contentTagLinkers;
    }

    public void getContentTag() {
        TagDaoImpl tagDao = new TagDaoImpl();
        try {
            List<ContentTagLinker> contentTagLinkers = tagDao.getContentByTags();
        }

        catch(SQLException e){
        }
    }

}
