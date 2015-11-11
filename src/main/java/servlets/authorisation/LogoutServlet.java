package servlets.authorisation;

import context.UserSession;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends ServletProvider{

    private static final String pageName = "/login.jsp";
    private static final String CONTENT_TYPE = "text/html";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response){
        logoutUser();
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void logoutUser(){
        UserSession userSession = UserSession.getUserSession();
        userSession.removeUser();
    }
}
