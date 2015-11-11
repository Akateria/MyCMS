package servlets.content;

import context.ContentEditMode;
import context.ContentSession;
import hibernate.tables.Tag;
import servlets.ServletProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddingContentServlet extends ServletProvider {

    private static final String pageName = "/select_tag";
    private static final String CONTENT_TYPE = "text/html";

    private static final String KEY_TEXT = "text";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMAGE_NAME = "imageName";

    private String text;
    private String name;
    private String link;
    private String imageName;
    ContentSession contentSession;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response){
        getParameters(request);
        contentSession = ContentSession.getContentSession();
        createReference();
        if(contentSession.editMode == ContentEditMode.EDIT_MODE) {
            updateContent();
        }
        else {
            createContent();
        }

        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);

    }


    private void createContent(){
        contentSession.createContentSession(0, name, text, link, imageName, ContentEditMode.CREATE_MODE);
    }

    private void updateContent(){
        contentSession.updateSession(name, text, link, imageName);
    }

    private void getParameters(HttpServletRequest request){
        text = request.getParameter(KEY_TEXT);
        name = request.getParameter(KEY_NAME);
        imageName = request.getParameter(KEY_IMAGE_NAME);
    }

    private void createReference(){
        String nameReference = name.replace(" ", "_");
        link = nameReference.toLowerCase();
    }


}
