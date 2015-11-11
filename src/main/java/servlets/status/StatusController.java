package servlets.status;

import context.UserSession;
import hibernate.tables.Content;
import context.ArticleStatus;
import hibernate.tables.ContentPositionHistory;
import hibernate.tables.User;
import providers.ContentHistoryProvider;
import providers.ContentProvider;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

@WebServlet("/changeStatus")
public class StatusController extends ServletProvider {
    private static final String pageName = "/article_list";
    private static final String CONTENT_TYPE = "text/html";

    private static final String KEY_ID = "id";
    private static final String KEY_STATUS = "status";

    private String id;
    Content content;
    ArticleStatus articleStatus;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response){
        getRequestParam(request);
        content = getContent();
        content.setArticleStatus(articleStatus);
        commitChanges();
        addToHistory();
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getRequestParam(HttpServletRequest request){
        id = request.getParameter(KEY_ID);
        articleStatus = ArticleStatus.valueOf(request.getParameter(KEY_STATUS));
    }

    private Content getContent(){
        Integer idInteger = new Integer(id);
        ContentProvider contentProvider = new ContentProvider();
        content = contentProvider.getContentByID(idInteger);
        return content;
    }

    private void commitChanges(){
        ContentProvider contentProvider = new ContentProvider();
        contentProvider.updateContent(content);
    }

    private void addToHistory(){
        if (articleStatus == ArticleStatus.ARCHIVE){
            return;
        }
        User user = UserSession.getUserSession().getUser();
        Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ContentHistoryProvider contentHistoryProvider = new ContentHistoryProvider();
        ContentPositionHistory contentPositionHistory = new ContentPositionHistory(content, user, articleStatus, date);
        contentHistoryProvider.addContentHistory(contentPositionHistory);
    }

}

