package servlets.tags;

import hibernate.dao.TagDao;
import hibernate.daoImpl.TagDaoImpl;
import hibernate.tables.Tag;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/record_tag")
public class NewTagController extends ServletProvider {

    private static final String pageName = "/tags";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_TAG_NAME = "tag";
    private static final String KEY_KEYWORDS = "keywords";

    String tagName;
    String keywords;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response){
        getParametersFromRequest(request);
        createNewTag();
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getParametersFromRequest(HttpServletRequest request){
        tagName = request.getParameter(KEY_TAG_NAME);
        keywords = request.getParameter(KEY_KEYWORDS);
    }

    private void createNewTag(){
        TagDao tagDao = new TagDaoImpl();
        Tag tag = new Tag();
        tag.setName(tagName);
        tag.setKeywords(keywords);
        try {
            tagDao.addTag(tag);
        }
        catch (SQLException e){

        }
    }

}
