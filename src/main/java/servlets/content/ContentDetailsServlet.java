package servlets.content;


import context.ContentEditMode;
import context.ContentSession;
import hibernate.tables.Content;
import providers.ContentProvider;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/content_details")
public class ContentDetailsServlet extends ServletProvider {

    private static final String pageName = "content_view.jsp";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_CONTENT_ID = "id";
    String id;
    Content content;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response) {
        getParametersFromRequest(request);
        getContent();
        createContentSession();
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getParametersFromRequest(HttpServletRequest request){
        id = request.getParameter(KEY_CONTENT_ID);
    }

    private void getContent(){
        ContentProvider contentProvider = new ContentProvider();
        Integer intID = new Integer(id);
        content = contentProvider.getContentByID(intID);
    }

    private void createContentSession(){

        ContentSession contentSession = ContentSession.getContentSession();
        contentSession.createContentSession(content.getId(), content.getName(), content.getText(),
                 content.getUrl(), content.getImage(),
                ContentEditMode.EDIT_MODE);
    }
}
