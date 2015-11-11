package servlets.content;

import context.*;
import hibernate.tables.Content;
import hibernate.tables.ContentPositionHistory;
import hibernate.tables.User;
import providers.ContentHistoryProvider;
import providers.ContentProvider;
import providers.UserProvider;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

@WebServlet("/createArticle")
public class ContentServlet extends ServletProvider {
    private static final String pageName = "/article_list";
    private static final String CONTENT_TYPE = "text/html";

    private static final String KEY_TEXT = "text";
    private static final String KEY_NAME = "name";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_KEYWORDS = "kwds";
    private static final String KEY_LINK = "link";
    private static final String KEY_IMAGE_NAME = "imageName";

    private String text;
    private String name;
    private String link;
    private String imageName;
    private User user;
    Timestamp currentTime;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response){
        getCurrentTime();
        getParameters(request);
        getUser();
        createContent();
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getCurrentTime(){
        currentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    private void getUser(){
        UserProvider userProvider = new UserProvider();
        user = userProvider.getCurrentUser();
    }

    private void getParameters(HttpServletRequest request){
        text = request.getParameter(KEY_TEXT);
        name = request.getParameter(KEY_NAME);
        link = request.getParameter(KEY_LINK);
        imageName = request.getParameter(KEY_IMAGE_NAME);
    }

    private void addToHistory(Content content, User user){
        ContentHistoryProvider contentHistoryProvider = new ContentHistoryProvider();

        ContentPositionHistory contentPositionHistory = new ContentPositionHistory(content, user, ArticleStatus.AUTHOR, currentTime);
        contentHistoryProvider.addContentHistory(contentPositionHistory);

   }

    private void createContent(){
        ContentProvider contentProvider = new ContentProvider();

        Content content = new Content(name, imageName,
                text, currentTime, link, ArticleStatus.AUTHOR, user, user);

        contentProvider.addContent(content);
        addToHistory(content, user);
    }

}
