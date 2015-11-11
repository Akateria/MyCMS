package hibernate.dao;

import hibernate.tables.FrontPage;

import java.sql.SQLException;
import java.util.List;

public interface FrontPageDao{
	void addFrontPageContent(FrontPage frontPage) throws SQLException;

	FrontPage getFrontPage(int id) throws SQLException;

	List<FrontPage> getFrontPagesByProperty(String propertyName, Object propertyValue) throws SQLException;
	
	List<FrontPage> getFrontPages() throws SQLException;
	
	void updateFrontPage(FrontPage frontPage) throws SQLException;
	
	void deleteFrontPage(FrontPage frontPage) throws SQLException;
}
