package hibernate.dao;

import hibernate.tables.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao{

	void addUser(User user) throws SQLException;

	User getUser(int id) throws SQLException;
	
	List<User> getUsersByProperty(String propertyName, Object propertyValue) throws SQLException;

	List<User> getUsers() throws SQLException;
	
	void updateUser(User user) throws SQLException;
	
	void deleteUser(User user) throws SQLException;
}
