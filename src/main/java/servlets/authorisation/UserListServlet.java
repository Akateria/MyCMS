package servlets.authorisation;


import hibernate.dao.UserDao;
import hibernate.daoImpl.UserDaoImpl;
import hibernate.tables.User;
import servlets.ServletProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userlist")
public class UserListServlet extends ServletProvider {
    private static final String pageName = "/userList.jsp";
    private static final String CONTENT_TYPE = "text/html";
    public ArrayList<User> userList;

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
        fillUserTable();
        request.getSession().setAttribute("userList", userList);
        super.forwardRequest(request, response, pageName);
    }

    private void fillUserTable(){
        userList = new ArrayList<>();
        UserDao userDao = new UserDaoImpl();
        try {
            List<User> users = userDao.getUsers();
            for(User user:users){
                userList.add(user);
            }
        }
        catch (SQLException e){

        }

    }
}
