package servlets;


import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class MainController extends ServletProvider {

    private static final String pageName = "/main.jsp";
    private static final String CONTENT_TYPE = "text/html";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }
}
