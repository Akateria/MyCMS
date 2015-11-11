package providers;


import context.UserSession;
import hibernate.dao.UserDao;
import hibernate.daoImpl.UserDaoImpl;
import hibernate.tables.User;

import java.sql.SQLException;
import java.util.List;

public class UserProvider {

    private static final String KEY_LOGIN = "login";

    public User getCurrentUser(){
        UserDao userDao = new UserDaoImpl();
        User user = null;
        try {
            List<User> users = userDao.getUsersByProperty(KEY_LOGIN, UserSession.getUserSession().getName());
            if (users != null){
                user = users.get(0);
            }
        }
        catch (SQLException e){

        }
        return user;
    }

    public User getUserByLogin(String login){
        User user = new User();
        UserDao userDao = new UserDaoImpl();
        try {
            List<User> users = userDao.getUsersByProperty(KEY_LOGIN, login);
            if(users != null){
                if (users.size() > 0){
                    user = users.get(0);
                }
            }
        }
        catch (SQLException e){

        }
    return user;

    }
}
