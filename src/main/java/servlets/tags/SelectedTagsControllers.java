package servlets.tags;

import context.ArticleStatus;
import context.ContentEditMode;
import context.ContentSession;
import context.UserSession;
import context.TagListSingleton;
import hibernate.tables.*;
import providers.ContentHistoryProvider;
import providers.ContentProvider;
import providers.ContentTagLinkerProvider;
import providers.TagProvider;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet("/selectTags")
public class SelectedTagsControllers extends ServletProvider {

    private static final String pageName = "/article_list";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_CHECKBOX = "selected";
    String[] tags;
    TagListSingleton tagList;
    Content content;
    Timestamp currentDate;
    User user;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response){
        getParametersFromRequest(request);
        getCurrentDate();
        getUser();
        getTagByID();
        createArticle();
        createTagsOfContent();
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getTagByID(){
        TagProvider tagProvider = new TagProvider();
        ArrayList<Tag> tagsList = new ArrayList<>();
        tagList = TagListSingleton.getTagList();

        for (String id: tags){
            Integer intId = new Integer(id);
            Tag tag = tagProvider.getTagByID(intId);
            tagsList.add(tag);
        }
        tagList.setTagsList(tagsList);
    }

    private void getParametersFromRequest(HttpServletRequest request){
        tags = request.getParameterValues(KEY_CHECKBOX);
    }

    private void createArticle(){
        ContentSession contentSession = ContentSession.getContentSession();
        ContentProvider contentProvider = new ContentProvider();

        if(contentSession.editMode == ContentEditMode.CREATE_MODE){
            content = new Content(contentSession.name, contentSession.image,
                    contentSession.text, currentDate, contentSession.link, ArticleStatus.AUTHOR, user, user);
            contentProvider.addContent(content);
            addToHistory();
        }
        else {
            content = new Content(contentSession.id, contentSession.name, contentSession.image,
                    contentSession.text, contentSession.link, ArticleStatus.AUTHOR, user, currentDate, contentSession.owner);
            contentProvider.updateContent(content);
        }
        ContentSession.getContentSession().removeInstance();
    }

    private void createTagsOfContent(){
        TagListSingleton tagListSingleton = TagListSingleton.getTagList();
        ArrayList<Tag> tags = tagListSingleton.getTags();
        ContentTagLinkerProvider contentTagLinkerProvider = new ContentTagLinkerProvider();

        for (Tag tag: tagList.getTags()){
            ContentTagLinker contentTagLinker = new ContentTagLinker(content, tag);
            contentTagLinkerProvider.addLink(contentTagLinker);
        }
    }

    private void addToHistory(){
        ContentHistoryProvider contentHistoryProvider = new ContentHistoryProvider();
        ContentPositionHistory contentPositionHistory = new ContentPositionHistory(content, user, ArticleStatus.AUTHOR, currentDate);
        contentHistoryProvider.addContentHistory(contentPositionHistory);
    }

    private void getCurrentDate(){
        currentDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    private User getUser(){
        user = UserSession.getUserSession().getUser();
        return user;
    }
}
