package servlets.content;

import hibernate.tables.Content;
import providers.ContentProvider;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/article")
public class ArticleDetails extends ServletProvider {

    private static final String pageName = "/ArticleDetails.jsp";
    private static final String KEY_NAME = "name";
    private static final String KEY_TEXT = "text";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_ID = "id";

    private String id;
    Content content;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response){
        getRequestParam(request);
        getContent();
        setParameters(request, content);
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getRequestParam(HttpServletRequest request){
        id = request.getParameter(KEY_ID);
    }

    private void setParameters(HttpServletRequest request, Content content){
        request.getSession().setAttribute(KEY_NAME, content.getName());
        request.getSession().setAttribute(KEY_TEXT, content.getText());
    }

    private void getContent() {
        ContentProvider contentProvider = new ContentProvider();
        Integer idInteger = new Integer(id);
        content = contentProvider.getContentByID(idInteger);
    }
}
