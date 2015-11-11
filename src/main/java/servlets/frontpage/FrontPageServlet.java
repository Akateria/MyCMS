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

@WebServlet("/front")
public class FrontPageServlet extends ServletProvider {

    private static final String pageName = "/front_page.jsp";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_PAGE_LIST = "frontList";
    ArrayList<Content> contents;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response){
        getFrontPages();
        setParameters(request);
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getFrontPages(){
        contents = new ArrayList<>();
        FrontPageDao frontPageDao = new FrontPageDaoImpl();
        ContentDao contentDao = new ContentDaoImpl();
        try {
            List<FrontPage> frontPageList = frontPageDao.getFrontPages();
            for (FrontPage page: frontPageList){
                contents.add(page.getContentId());
            }
        }
        catch (SQLException e){

        }

    }

    private void setParameters(HttpServletRequest request){
        request.getSession().setAttribute(KEY_PAGE_LIST, contents);
    }
}
