package servlets.authorisation;


import context.UserSession;
import hibernate.dao.UserDao;
import hibernate.daoImpl.UserDaoImpl;
import hibernate.general.HibernateFactory;
import hibernate.tables.User;
import hibernate.tables.userInfo.UserRole;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/commituser")
public class CommitUserServlet  extends ServletProvider {

    private static final String PAGE_NAME_MAIN = "/userlist";
    private static final String KEY_USER = "user";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_USER_NAME = "login";
    private static final String KEY_NEW_ROLE = "newRole";

    User user;
    String userName;
    String newRole;
    hibernate.dao.UserDao userDaoImpl = HibernateFactory.getInstance().getUserDao();

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
        user = getUser(userName);
        setNewRole(user);
        super.forwardRequest(request, response, PAGE_NAME_MAIN);
    }

    private void getParametersFromRequest(HttpServletRequest request){
        userName = request.getParameter(KEY_USER_NAME);
        newRole = request.getParameter(KEY_NEW_ROLE);
    }

    private User getUser(String login) {
        User user = null;
        try {
            List<User> users = userDaoImpl.getUsersByProperty("login", login);
            if (users.size() == 1) {
                user = users.get(0);
            }
        } catch (SQLException e) {
            return null;
        }
        return user;
    }

    private void setNewRole(User user){
        UserSession userSession = UserSession.getUserSession();
        userSession.setRole(UserRole.valueOf(newRole));
        user.setRole(UserRole.valueOf(newRole));
        UserDao userDao = new UserDaoImpl();
        try {
            userDao.updateUser(user);
        }
        catch (SQLException e){

        }
    }
}
