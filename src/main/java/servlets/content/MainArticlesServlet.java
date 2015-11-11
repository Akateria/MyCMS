package servlets.content;

import hibernate.tables.Content;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/mainarticles")
public class MainArticlesServlet extends ServletProvider {

    private static final String pageName = "/contentList.jsp";
    private static final String CONTENT_TYPE = "text/html";
    public ArrayList<Content> contentList;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response){
        fillContentTable();
        request.getSession().setAttribute("article_list", contentList);
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void fillContentTable(){
        contentList = new ArrayList<>();
    }

}
