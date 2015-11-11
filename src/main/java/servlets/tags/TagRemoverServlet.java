package servlets.tags;

import hibernate.dao.ContentTagLinkerDao;
import hibernate.dao.TagDao;
import hibernate.daoImpl.ContentTagLinkerDaoImpl;
import hibernate.daoImpl.TagDaoImpl;
import hibernate.tables.ContentTagLinker;
import hibernate.tables.Tag;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/remove_tag")
public class TagRemoverServlet extends ServletProvider{

    private static final String pageName = "/tags";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_CHECKBOX = "selected";
    String[] tags;
    ArrayList<Tag> tagList;

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
        getTagByID();
        removeTags();
        response.setContentType(CONTENT_TYPE);
        super.forwardRequest(request, response, pageName);
    }

    private void getParametersFromRequest(HttpServletRequest request){
        tags = request.getParameterValues(KEY_CHECKBOX);
        getTagByID();
    }

    private void getTagByID(){
        tagList = new ArrayList<>();
        TagDao tagDao = new TagDaoImpl();
        for (String id: tags){
            Integer intId = new Integer(id);
            try {
                Tag tag = tagDao.getTag(intId);
                boolean linkExists = getLinksForTag(tag);
                if(linkExists == false){
                    tagList.add(tag);
                }
            }
            catch (SQLException e){

            }
        }
    }

    private void removeTags(){
        TagDao tagDao = new TagDaoImpl();
        try {
            for (Tag tag: tagList){
                tagDao.deleteTag(tag);
            }

        }
        catch (SQLException e){

        }
    }

    private boolean getLinksForTag(Tag tag){
        ContentTagLinkerDao contentTagLinkerDao = new ContentTagLinkerDaoImpl();
        try {
            List<ContentTagLinker> tagsLink = contentTagLinkerDao.getContentTagLinkersByProperty("tag", tag);
            if(tagsLink.size() > 0) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e){
            return false;
        }
    }
}
