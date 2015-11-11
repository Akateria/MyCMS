package servlets.frontpage;

import hibernate.dao.ContentDao;
import hibernate.dao.FrontPageDao;
import hibernate.daoImpl.ContentDaoImpl;
import hibernate.daoImpl.FrontPageDaoImpl;
import hibernate.tables.Content;
import hibernate.tables.FrontPage;
import providers.FrontPageProvider;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/add_to_front")
public class FrontPageController extends ServletProvider {

    private static final String pageName = "/article_list";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_CONTENT_LIST = "contentList";
    private static final int MAX_SIZE = 4;
    String[] contentList;
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
        getParametersFromRequest(request);
        getContents();
        //TODO fix bag
//        int currentAmount = getCurrentAmount();
//        if(contents.size() + currentAmount <= MAX_SIZE){
//            
//        }
        addToMainPage();
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getParametersFromRequest(HttpServletRequest request){
        contentList = request.getParameterValues(KEY_CONTENT_LIST);
    }

    private void getContents(){
        contents = new ArrayList<>();
        ContentDao contentDao = new ContentDaoImpl();
        try {
            for (String id: contentList) {
                Integer idInt = new Integer(id);
                Content content = contentDao.getContent(idInt);
                contents.add(content);
            }
        }
        catch (SQLException e){

        }
    }

    private int getCurrentAmount(){
        FrontPageProvider frontPageProvider = new FrontPageProvider();
        int currentSize = frontPageProvider.getFrontContent().size();
        return currentSize;
    }

    private void addToMainPage(){
        FrontPageDao frontPageDao = new FrontPageDaoImpl();
        for (Content content: contents) {
            FrontPage frontPage = new FrontPage(content, 1);
            try {
                frontPageDao.addFrontPageContent(frontPage);
            }
            catch (SQLException e){

            }
        }

    }
}
