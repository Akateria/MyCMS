package providers;


import hibernate.dao.ContentTagLinkerDao;
import hibernate.daoImpl.ContentTagLinkerDaoImpl;
import hibernate.tables.Content;
import hibernate.tables.ContentTagLinker;
import hibernate.tables.Tag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContentTagLinkerProvider {

    private static final String KEY_CONTENT = "content";
    private static final String KEY_TAG = "tag";

    public void addLink(ContentTagLinker contentTagLinker){
        ContentTagLinkerDao contentTagLinkerDao = new ContentTagLinkerDaoImpl();
        try {
            contentTagLinkerDao.addLink(contentTagLinker);
        }
        catch (SQLException e){

        }
    }

    public ArrayList<Content> getSimilarContentByTag(Content content, int maxSize){

        ArrayList<Content> contents = new ArrayList<>();
        ArrayList<Tag> tags = new ArrayList<>();

        ContentTagLinkerDao contentTagLinkerDao = new ContentTagLinkerDaoImpl();
        try {
            List<ContentTagLinker> tagLinkers= contentTagLinkerDao.getContentTagLinkersByProperty(KEY_CONTENT, content);

            for (ContentTagLinker contentTagLinker: tagLinkers){
                if(contents.size() > maxSize){
                    break;
                }
                Tag tag = contentTagLinker.getTag();
                tags.add(tag);
            }
        }
        catch (SQLException e){

        }

        for (Tag tag: tags){
            if(contents.size() > maxSize){
                break;
            }
            try {
                List<ContentTagLinker> tagLinkers= contentTagLinkerDao.getContentTagLinkersByProperty(KEY_TAG, tag);

                for (ContentTagLinker contentTagLinker: tagLinkers){
                    Content similarContent = contentTagLinker.getContent();
                    if(similarContent.equals(content)){
                        continue;
                    }
                    contents.add(similarContent);
                }
            }
            catch (SQLException e){

            }
        }

        return contents;
    }

}
