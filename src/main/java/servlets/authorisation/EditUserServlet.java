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

@WebServlet("/edituser")
public class EditUserServlet extends ServletProvider {


    private static final String PAGE_EDIT_USER = "editUser.jsp";
    private static final String CONTENT_TYPE = "text/html";
    private static final String KEY_USER = "userName";

    String userName;
    User user;
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
        getUserByLogin();
        setRequestParam(request);
        super.forwardRequest(request, response, PAGE_EDIT_USER);
    }

    private void getParametersFromRequest(HttpServletRequest request){
        userName = request.getParameter(KEY_USER);
    }

    private void getUserByLogin(){
        try {
            List<User> users = userDaoImpl.getUsersByProperty("login", userName);
            if(users.size() > 0) {
                user = users.get(0);
            }
        }
        catch (SQLException e){
        }
    }

    private void setRequestParam(HttpServletRequest request){
        request.getSession().setAttribute(KEY_USER, user);
    }

}
