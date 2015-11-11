package servlets.authorisation;

import hibernate.dao.UserDao;
import hibernate.general.HibernateFactory;
import hibernate.tables.User;
import servlets.authorisation.ServletUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/successRegistration")
public class SuccessRegistration extends HttpServlet {
	private static final long serialVersionUID = 3752212939930588492L;
	UserDao userDao = HibernateFactory.getInstance().getUserDao();

	public SuccessRegistration() {
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String newConfirmCode = (String) request.getParameter("confirmationCode");
		String login = (String) request.getParameter("login");
		if ((newConfirmCode != null) && (newConfirmCode != "")
				&& (login != null) && (login != "")) {
			String savedConfirmCode = null;
			List<User> users = null;
			try {
				users = userDao.getUsersByProperty("login", login);
				if ((users != null) && (!users.isEmpty()) && (users.size() == 1)) {
					User user = users.get(0);
					savedConfirmCode = user.getConfirmCode();
					request.setAttribute("firstname", user.getFirstName());
					request.setAttribute("lastname", user.getLastName());
					user.setRestoreCode(String.valueOf(ServletUtil.getRandomCode()));
					user.setConfirmCode(String.valueOf(ServletUtil.getRandomCode()));
					if ((savedConfirmCode != null) && (newConfirmCode.equals(savedConfirmCode))) {
						System.out.println("User has changed his password successful!");
						userDao.updateUser(user);
						request.getRequestDispatcher("/successRegistration.jsp").forward(request, response);
						return;
					} else {
						request.getRequestDispatcher("/error.jsp").forward(request,
								response);
						return;
					}
				} else {
					request.getRequestDispatcher("/error.jsp").forward(request,
							response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
