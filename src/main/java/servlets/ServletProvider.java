package servlets;

import context.UserSession;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ServletProvider extends HttpServlet {

    public void checkAuthorization(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, ServletException {

        String pageName = "/login.jsp";
        if (UserSession.getUserSession().isAuthorized() == false) {
            forwardRequest(request, response, pageName);
        }
        else {
            execute(request, response);
        }
    }

    public void forwardRequest(HttpServletRequest request, HttpServletResponse response, String pageName) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(pageName);
            dispatcher.forward(request, response);
        }
        catch (ServletException | IOException e){
            
        }

    }

    public abstract void execute(HttpServletRequest request, HttpServletResponse response);

}