package servlets.authorisation;

import authorization.EmailValidate;
import authorization.PasswordHash;
import hibernate.dao.UserDao;
import hibernate.general.HibernateFactory;
import hibernate.tables.User;
import hibernate.tables.userInfo.UserRole;
import hibernate.tables.userInfo.UserStatus;
import servlets.ServletProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

public class CheckLoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/successLogin.jsp");
        dispatcher.forward(request, response);
    }

    @WebServlet(description = "to register a new user", urlPatterns = { "/registration" })
    public static class Registration extends HttpServlet {
        private static final long serialVersionUID = 5393044129773580802L;
        UserDao userDao = HibernateFactory.getInstance().getUserDao();
        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String login = request.getParameter("login");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("password_confirmation");
            System.out.println("Registration servlet working...");

            request.setAttribute("firstname", firstName);
            request.setAttribute("lastname", lastName);
            request.setAttribute("email", email);

            if (!EmailValidate.validateEmail(email)) {
                request.setAttribute("email", null);
                getServletContext().getRequestDispatcher("/registration.jsp")
                        .forward(request, response);
                return;
            }
            if ((login == "") || (login == null) || (password == "")
                    || (password == null) || (firstName == "")
                    || (firstName == null) || (lastName == "")
                    || (lastName == null) || (email == null)
                    || (!password.equals(passwordConfirm))
                    || (password.length() > 100) || (password.length() < 8)
                    || (password.contains(" ")) || (password.contains("/r"))
                    || (password.contains("/n"))) {
                getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
                return;
            }
            if (checkingLoginExists(login)){
                request.setAttribute("login", "");
                getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
                return;
            }
            if (checkingEmailExists(email)){
                request.setAttribute("email", "");
                getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
                return;
            }
            User newUser = registerUser(firstName, lastName, login, email);
            try {
                newUser.setPassword(PasswordHash.createHash(password));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
                e1.printStackTrace();
                getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
                return;
            }
            try {
                userDao.addUser(newUser);
                System.out.println("New user was saved in database");
                String[] recipients = { email };
                String subject = "Confirmation of registration ";
                String info = this.getServletContext().getContextPath();
                String text = "You have already registered  in website" + info
                        + ". To confirm your registration go by reference "
                        + "http://" + ServletUtil.HOST + ":"
                        + request.getServerPort() + info
                        + "/successRegistration?login="
                        + newUser.getLogin() + "&confirmationCode="
                        + newUser.getConfirmCode();
                ServletUtil.sendMessage(recipients, subject, text);
                PrintWriter writer = response.getWriter();
                writer.println("Message was sent. Check your email");
                writer.flush();
                writer.close();
    //			try{
    //				DumpCreator.getDump();
    //			}catch(Exception e){
    //				e.printStackTrace();
    //			}
            } catch (SQLException e) {
                e.printStackTrace();
                getServletContext().getRequestDispatcher("/error.jsp").forward(
                        request, response);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
        }

        private boolean checkingEmailExists(String email) {
            try {
                List<User> users = userDao.getUsersByProperty("email", email);
                if (users.size() >= 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return true;
            }
            return false;
        }

        private User registerUser(String firstName, String lastName, String login,String email) {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLogin(login);
            user.setEmail(email);
            user.setRole(UserRole.UNCONFIRMED);
            user.setStatus(UserStatus.AVAILABLE);
            user.setConfirmCode(String.valueOf(ServletUtil.getRandomCode()));
            user.setRestoreCode(String.valueOf(ServletUtil.getRandomCode()));
            return user;
        }

        private boolean checkingLoginExists(String login){
            try {
                List<User> users = userDao.getUsersByProperty("login", login);
                if (users.size() >= 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return true;
            }
            return false;
        }
    }
}
