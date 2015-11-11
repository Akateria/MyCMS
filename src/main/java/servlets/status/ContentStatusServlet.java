package servlets.status;

import hibernate.dao.ContentDao;
import hibernate.dao.UserDao;
import hibernate.daoImpl.ContentDaoImpl;
import hibernate.daoImpl.UserDaoImpl;
import hibernate.tables.Content;
import hibernate.tables.User;
import hibernate.tables.userInfo.UserRole;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/editstatus")
public class ContentStatusServlet extends ServletProvider {

    private static final String pageName = "/articleStatusEdit.jsp";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_ARTICLE_ID = "id";
    private static final String KEY_ARTICLE = "article";
    private static final String KEY_USERS = "users";

    String contentID;
    Content content;
    ArrayList<User> users;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        makeResponse(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        makeResponse(request, response);
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response){
        response.setContentType(CONTENT_TYPE);
        getParametersFromRequest(request);
        content = getContent();
        users = getUsers();
        setResponseParameters(request);
        super.forwardRequest(request, response, pageName);
    }

    private void getParametersFromRequest(HttpServletRequest request){
        contentID = request.getParameter(KEY_ARTICLE_ID);
    }

    private Content getContent(){
        ContentDao contentDao = new ContentDaoImpl();
        try {
            Integer id = new Integer(contentID);
            List<Content> contents = contentDao.getContentsByProperty("id", id);
            if(contents.size() > 0){
                content = contents.get(0);
            }
        }
        catch (SQLException e){
            content = null;
        }
        return content;
    }

    private void setResponseParameters(HttpServletRequest request){
        request.getSession().setAttribute(KEY_ARTICLE, content);
        request.getSession().setAttribute(KEY_USERS, users);
    }

    private ArrayList<User> getUsers(){
        users = new ArrayList<>();
        UserDao userDao = new UserDaoImpl();
        try {
            List<User> userList = userDao.getUsers();
            for(User user: userList){
                UserRole userRole = user.getRole();
                if(userRole == UserRole.CORRECTOR)
                    users.add(user);
            }
        }
        catch (SQLException e){

        }
        return users;
    }

}
