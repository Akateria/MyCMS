package servlets.content;

import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/validateArticle")
public class ValidateArticle extends ServletProvider {

    private static final String PAGE_NAME_TABS = "/main";
    private static final String PAGE_NAME_DETAILS = "/ArticleDetails.jsp";
    private static final String CONTENT_TYPE = "text/html";
    private static final String ARTICLE_NAME = "name";
    private static final String ARTICLE_TITLE = "title";
    private static final String ARTICLE_KWRD = "keywords";
    private static final String ARTICLE_DESCRIPTION = "description";

    private String article_name;
    private String article_title;
    private String keywords;
    private String description;
    private int minLengthName = 10;
    private int minLengthTitle = 10;
    private int minLengthKeywords = 20;
    private int minLengthDescription = 30;
    private String page;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getParametersFromRequest(request);
        boolean valueIsCorrect = checkValueOnParameters();
        if (valueIsCorrect){
            page = PAGE_NAME_TABS;
        }
        else {
            page = PAGE_NAME_DETAILS;
            setParameters(request);
        }
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, page);
    }

    private void getParametersFromRequest(HttpServletRequest request){
        article_name = request.getParameter(ARTICLE_NAME);
        article_title = request.getParameter(ARTICLE_TITLE);
        keywords = request.getParameter(ARTICLE_KWRD);
        description = request.getParameter(ARTICLE_DESCRIPTION);
    }

    private boolean checkValueOnParameters(){
        if (isCorrect(article_name, minLengthName)
                && isCorrect(article_title, minLengthTitle)
                && isCorrect(keywords, minLengthKeywords)
                && isCorrect(description, minLengthDescription)){
            return true;
        }
        return false;
    }

    private boolean isCorrect(String value, int length) {
        if (value != null) {
            if (value != "" && value.length() >= length) {
                return true;
            }
            else {
                return false;
            }

        }
        return false;
    }

    private void setParameters(HttpServletRequest request){
        request.getSession().setAttribute("name", article_name);
        request.getSession().setAttribute("title", article_title);
        request.getSession().setAttribute("keywords", keywords);
        request.getSession().setAttribute("description", description);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

    }
}
