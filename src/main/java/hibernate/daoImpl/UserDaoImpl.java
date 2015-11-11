package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;
import hibernate.dao.UserDao;
import hibernate.tables.User;
import hibernate.util.HibernateDaoBuilder;

public class UserDaoImpl implements UserDao{

	public void addUser(User user) throws SQLException {
		HibernateDaoBuilder.saveTableValue(user);
	}

	public User getUser(int id) throws SQLException {
		return (User)HibernateDaoBuilder.getTableValue(id, new User());
	}

	public List<User> getUsers() throws SQLException {
		return (List<User>) HibernateDaoBuilder.getTableValues(new User());
	}

	public void deleteUser(User user) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(user);
	}

	/**
	 * @propertyName is a field in Object which is assigned with DB table. 
	 * @propertyValue is a value of propertyName 
	 */
	public List<User> getUsersByProperty(String propertyName, Object propertyValue) throws SQLException {
		return (List<User>)HibernateDaoBuilder.getTableValuesByProperty(propertyName,propertyValue, new User());
	}

	public void updateUser(User user) throws SQLException {
		HibernateDaoBuilder.updateTableValue(user);
	}
}
