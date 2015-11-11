package servlets.tags;

import hibernate.tables.Tag;
import hibernate.dao.TagDao;
import hibernate.daoImpl.TagDaoImpl;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tags")
public class TagController extends ServletProvider {
    private static final String pageName = "/tagsList.jsp";
    private static final String CONTENT_TYPE = "text/html";

    private static final String KEY_TAG_LIST = "tagList";

    ArrayList<Tag> tags;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response){
        getAllTags();
        setParameters(request);
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getAllTags(){
        tags = new ArrayList<Tag>();
        TagDao tagDao = new TagDaoImpl();
        try {
            List<Tag> tagList = tagDao.getTags();
            for (Tag tag:tagList){
                tags.add(tag);
            }
        }
        catch (SQLException e){

        }
    }

    private void setParameters(HttpServletRequest request){
        request.getSession().setAttribute(KEY_TAG_LIST, tags);
    }

}
