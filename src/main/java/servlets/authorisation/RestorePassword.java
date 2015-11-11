package servlets.authorisation;

import hibernate.dao.UserDao;
import hibernate.general.HibernateFactory;
import hibernate.tables.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authorization.EmailValidate;
import servlets.authorisation.ServletUtil;

@WebServlet("/restorePassword")
public class RestorePassword extends HttpServlet {
	private static final String RESTORE_PASSWORD = "/restorePassword.jsp";
	private static final long serialVersionUID = 7778541242689761958L;
	private UserDao userDao = HibernateFactory.getInstance().getUserDao();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
//		String login = request.getParameter("login");
		request.setAttribute("email", email);

		System.out.println("RestorePassword servlet working...");
		if (!EmailValidate.validateEmail(email)) {
			request.setAttribute("email", null);
			getServletContext().getRequestDispatcher(RESTORE_PASSWORD)
					.forward(request, response);
			return;
		}
//		if ((login == "") || (login == null)) {
//			getServletContext().getRequestDispatcher(RESTORE_PASSWORD)
//					.forward(request, response);
//			return;
//		}
		List<User> newUsers = null;
		User user = null;
		String restoreCode = String.valueOf(ServletUtil.getRandomCode());
		try {
			newUsers = userDao.getUsersByProperty("email", email);
			if (newUsers.size() == 1) {
				user = newUsers.get(0); 
				if (user.getEmail().equals(email)) {
					user.setRestoreCode(restoreCode);
					userDao.updateUser(user);
				} else {
					getServletContext().getRequestDispatcher(RESTORE_PASSWORD).forward(request, response);
					return;
				}
			} else {
				getServletContext().getRequestDispatcher(RESTORE_PASSWORD).forward(
								request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher(RESTORE_PASSWORD)
					.forward(request, response);
			return;
		}
		String[] recipients = { email };
		String subject = "Restorin password";
		String info = this.getServletContext().getContextPath();
		String text = "Hello! You are registered to the website. " + info
				+ ". To restore your password сlick on the link "
				+ "http://" + ServletUtil.MARGO_HOST + ":" + request.getServerPort() + info
				+ "/restoringPassword?login=" + user.getLogin()
				+ "&restoreCode=" + restoreCode + " or "
				+ "http://" + ServletUtil.MY_HOST + ":" + request.getServerPort() + info
				+ "/restoringPassword?login=" + user.getLogin()
				+ "&restoreCode=" + restoreCode;
		ServletUtil.sendMessage(recipients, subject, text);
		PrintWriter writer = response.getWriter();
		writer.println("Message was sent to your email. Check your email");
		writer.flush();
		writer.close();
	}

}
