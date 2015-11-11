package servlets.frontpage;


import hibernate.dao.ContentDao;
import hibernate.dao.FrontPageDao;
import hibernate.daoImpl.ContentDaoImpl;
import hibernate.daoImpl.FrontPageDaoImpl;
import hibernate.tables.Content;
import hibernate.tables.FrontPage;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/delete_from_front")
public class FrontPageRemoverServlet extends ServletProvider {

    private static final String pageName = "/front";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_CONTENT_LIST = "contentList";
    private ArrayList<Content> contents;
    String[] contentsId;

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
        getContentsList();
        removeContent();
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getParametersFromRequest(HttpServletRequest request){
        contentsId = request.getParameterValues(KEY_CONTENT_LIST);
    }

    private void getContentsList(){
        contents = new ArrayList<>();
        ContentDao contentDao = new ContentDaoImpl();
        try {
            for(String id:contentsId) {
                Integer idInt = new Integer(id);
                Content content = contentDao.getContent(idInt);
                contents.add(content);
            }
        }
        catch (SQLException e){

        }
    }

    private void removeContent(){
        FrontPageDao frontPageDao = new FrontPageDaoImpl();
        try {
            for(Content content: contents){
                List<FrontPage> frontPages = frontPageDao.getFrontPagesByProperty("contentId", content);
                if(frontPages != null){
                    if(frontPages.size() > 0){
                        FrontPage frontPage = frontPages.get(0);
                        frontPageDao.deleteFrontPage(frontPage);
                    }
                }
            }
        }
        catch (SQLException e){

        }

    }
}
