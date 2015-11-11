package hibernate.dao;

import hibernate.tables.ContentPositionHistory;
import java.sql.SQLException;
import java.util.List;

public interface ContentPositionHistoryDao {
	void addContentPositionHistory(ContentPositionHistory contentPositionHistory) throws SQLException;

	ContentPositionHistory getContentPositionHistory(int id) throws SQLException;
	
	List<ContentPositionHistory> getContentPositionHistoryByProperty(String propertyName, Object propertyValue) throws SQLException;

	List<ContentPositionHistory> getContentPositionHistory() throws SQLException;

	void updateContentPositionHistory(ContentPositionHistory contentPositionHistory) throws SQLException;
	
	void deleteContentPositionHistory(ContentPositionHistory contentPositionHistory) throws SQLException;
}
