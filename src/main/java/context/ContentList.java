package context;


import hibernate.dao.ContentDao;
import hibernate.daoImpl.ContentDaoImpl;
import hibernate.tables.Content;
import hibernate.tables.Tag;
import hibernate.tables.User;
import hibernate.tables.userInfo.UserRole;
import providers.ContentTagLinkerProvider;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContentList {

    ArrayList<Content> contents;
    public int sizeList;
    UserRole userRole;
    User user;

    public ContentList() {
        user = UserSession.getUserSession().getUser();
        userRole = user.getRole();
        getAllContent();
        this.sizeList = contents.size();
    }

    public ContentList(ArticleStatus articleStatus) {
        user = UserSession.getUserSession().getUser();
        userRole = user.getRole();
        getAllContent(articleStatus);
        this.sizeList = contents.size();
    }

    private void getAllContent() {
        contents = new ArrayList<>();
        try {
            ContentDao contentDao = new ContentDaoImpl();
            List<Content> contentList = contentDao.getContents();
            for (Content content : contentList) {
                if (userRole == UserRole.AUTHOR) {
                    if(content.getAuthor().equals(user)) {
                        contents.add(content);
                    }
                }
                else {
                    contents.add(content);
                }

            }
        } catch (SQLException e) {

        }
    }

    private void getAllContent(ArticleStatus articleStatus) {
        contents = new ArrayList<>();
        try {
            ContentDao contentDao = new ContentDaoImpl();
            List<Content> contentList = contentDao.getContents();
            for (Content content : contentList) {
                if (userRole == UserRole.AUTHOR) {
                    if(content.getAuthor().equals(user)) {
                        contents.add(content);
                    }
                }
                else {
                    if(content.getArticleStatus() == articleStatus){
                        contents.add(content);
                    }

                }

            }
        } catch (SQLException e) {

        }
    }

    public ArrayList<Content> getContents(int amountOnPage, int pageNumber) {
        ArrayList<Content> contentPagination = new ArrayList<>();
        int lastNumber = amountOnPage*pageNumber;
        int firstNumber = lastNumber - amountOnPage + 1;
        int currentNumber = firstNumber;
        if(contents.size() < lastNumber){
            lastNumber = contents.size();
        }
        while (currentNumber <= lastNumber){
            Content content = contents.get(currentNumber-1);
            contentPagination.add(content);
            currentNumber++;
        }
        return contentPagination;

    }

    public ArrayList<Content> getContents(int amountOnPage, int pageNumber,ArticleStatus articleStatus) {
        ArrayList<Content> contentPagination = new ArrayList<>();
        int lastNumber = amountOnPage*pageNumber;
        int firstNumber = lastNumber - amountOnPage + 1;
        int currentNumber = firstNumber;
        if(contents.size() < lastNumber){
            lastNumber = contents.size();
        }
        while (currentNumber <= lastNumber){
            Content content = contents.get(currentNumber-1);
            contentPagination.add(content);
            currentNumber++;
        }
        return contentPagination;

    }

    public ArrayList<Content> getSimilarContent(Content content){
        int maxSize = 3;
        ContentTagLinkerProvider ContentTagLinkerProvider = new ContentTagLinkerProvider();
        ArrayList<Content> contentArrayList = ContentTagLinkerProvider.getSimilarContentByTag(content, maxSize);
        return contentArrayList;
    }

}
