package servlets;

import context.ArticleStatus;
import providers.ContentListProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/rest/website/allarticles")
public class AllContentServlet extends HttpServlet{

    private static final String KEY_PAGE = "page";
    private String pageID;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getParametersFromRequest(req);
        ContentListProvider contentListProvider = new ContentListProvider();
        Integer page = new Integer(pageID);
        String contentsInString = contentListProvider.getContentsByNumber(page, ArticleStatus.WEBSITE);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(contentsInString);
        out.flush();

    }

    private void getParametersFromRequest(HttpServletRequest req){
        pageID = req.getParameter(KEY_PAGE);

    }
}
