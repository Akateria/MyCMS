package providers;

import context.ContentList;
import hibernate.dao.ContentDao;
import hibernate.daoImpl.ContentDaoImpl;
import hibernate.tables.Content;
import hibernate.tables.Tag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContentProvider {

    private static final String KEY_ID = "id";
    private static final String KEY_URL = "url";

    public Content getContentByID(Integer id){
        Content content = new Content();
        ContentDao articleDao = new ContentDaoImpl();
        try {
            List<Content> contents = articleDao.getContentsByProperty(KEY_ID, id);
            if(contents.size() > 0){
                content = contents.get(0);
            }
        }
        catch (SQLException e){
            content = null;
        }
        return content;
    }

    public Content getContentByURL(String url){
        Content content = new Content();
        ContentDao articleDao = new ContentDaoImpl();
        try {
            List<Content> contents = articleDao.getContentsByProperty(KEY_URL, url);
            if(contents.size() > 0){
                content = contents.get(0);
            }
        }
        catch (SQLException e){
            content = null;
        }
        return content;
    }

    public void addContent(Content content){
        ContentDao contentDao = new ContentDaoImpl();
        try {
            contentDao.addContent(content);

        }
        catch (SQLException e){

        }
    }

    public void updateContent(Content content){
        ContentDao contentDao = new ContentDaoImpl();
        try {
            contentDao.updateContent(content);

        }
        catch (SQLException e){

        }
    }

    public ArrayList<Content> getContentByString (String word, String contentsIdArray){
        ArrayList<Content> contents = new ArrayList<>();
        ContentDao contentDao = new ContentDaoImpl();
        try {
            contents = contentDao.getContentByString(word, contentsIdArray);
        }
        catch (SQLException e){

        }

        return contents;
    }

    public ArrayList<Content> getContentByTags (String tagsId, String contentsIdArray){
        ArrayList<Content> contents = new ArrayList<>();
        ContentDao contentDao = new ContentDaoImpl();
        try {
            contents = contentDao.getContentByTags(tagsId, contentsIdArray);
        }
        catch (SQLException e){

        }

        return contents;
    }

    public ArrayList<Content> getContentByTags (String tagsId){
        ArrayList<Content> contents = new ArrayList<>();
        ContentDao contentDao = new ContentDaoImpl();
        try {
            contents = contentDao.getContentByTags(tagsId);
        }
        catch (SQLException e){

        }

        return contents;
    }

    public int getAmountOfContent(){
        int size = 0;
        ContentDao contentDao = new ContentDaoImpl();
        try {
            List<Content> contentList = contentDao.getContents();
            size = contentList.size();

        }
        catch (SQLException e){

        }
        return size;
    }

}
