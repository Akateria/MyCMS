package servlets.status;


import hibernate.dao.ContentDao;
import hibernate.dao.ContentPositionHistoryDao;
import hibernate.daoImpl.ContentDaoImpl;
import hibernate.daoImpl.ContentPositionHistoryDaoImpl;
import hibernate.tables.Content;
import hibernate.tables.ContentPositionHistory;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/contenthistory")
public class ContentHistoryServlet extends ServletProvider {
    private static final String pageName = "/contentHistory.jsp";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_CONTENT_HISTORY = "contentHistory";
    private static final String FIELD_CONTENT = "contentId";
    ArrayList<ContentPositionHistory> contentHistories;
    Content content;
    Integer contentID;

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
        getContent();
        getHistory();
        setParameters(request);
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getHistory(){
        contentHistories = new ArrayList<>();
        ContentPositionHistoryDao contentPositionHistory = new ContentPositionHistoryDaoImpl();
        try {
            List<ContentPositionHistory> history = contentPositionHistory.getContentPositionHistoryByProperty(FIELD_CONTENT, content);
            for(ContentPositionHistory contentPosition: history){
                contentHistories.add(contentPosition);
            }
        }
        catch (SQLException e){

        }

    }

    private void setParameters(HttpServletRequest request){
        request.getSession().setAttribute(KEY_CONTENT_HISTORY, contentHistories);
    }

    private void getParametersFromRequest(HttpServletRequest request){
        String IdString = request.getParameter(FIELD_CONTENT);
        contentID = new Integer(IdString);
    }

    private void getContent(){
        ContentDao contentDao = new ContentDaoImpl();
        try {
            content = contentDao.getContent(contentID);
        }
        catch (SQLException e){

        }
    }
}
