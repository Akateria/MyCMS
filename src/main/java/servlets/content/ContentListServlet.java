package servlets.content;

import context.ContentList;
import hibernate.dao.ContentDao;
import hibernate.daoImpl.ContentDaoImpl;
import hibernate.tables.Content;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/article_list")
public class ContentListServlet extends ServletProvider {

    private static final String pageName = "/content_list.jsp";
    private static final String CONTENT_TYPE = "text/html";
    private static final String PAGE_NUMBER = "page";
    private static final String KEY_CONTENT_LIST = "contentList";
    private static final String KEY_PAGE_AMOUNT = "pageAmount";
    private static final int AMOUNT_ON_PAGE = 5;
    private static final int NUMBER_FIRST_PAGE = 1;
    private ArrayList<Content> contents;
    public int pageNumber;
    public int amountPage;
    ContentList contentList;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        forwardRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        forwardRequest(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        forwardRequest(request, response);
    }

    private void forwardRequest(HttpServletRequest request, HttpServletResponse response){
        response.setContentType(CONTENT_TYPE);
        getParametersFromRequest(request);
        fillArticleTable();
        getAmountOnPage();
        setAttribute(request);
        super.forwardRequest(request, response, pageName);
    }

    private void fillArticleTable(){
        contentList = new ContentList();
        if(pageNumber > 1 ){
            contents = contentList.getContents(AMOUNT_ON_PAGE, pageNumber);
        }
        else {
            contents = contentList.getContents(AMOUNT_ON_PAGE, NUMBER_FIRST_PAGE);
        }
    }

    private void getParametersFromRequest(HttpServletRequest request){
        String pageString = request.getParameter(PAGE_NUMBER);
        if (pageString != null){
            pageNumber = new Integer(pageString);
        }
    }

    private void setAttribute(HttpServletRequest request){
        request.getSession().setAttribute(KEY_CONTENT_LIST, contents);
        request.getSession().setAttribute(KEY_PAGE_AMOUNT, amountPage);
    }

    private void getAmountOnPage(){
        int residue = contentList.sizeList%AMOUNT_ON_PAGE;
        if(residue == 0){
            amountPage = contentList.sizeList/AMOUNT_ON_PAGE;
        }
        else {
            amountPage = contentList.sizeList/AMOUNT_ON_PAGE +1;
        }
    }

}
