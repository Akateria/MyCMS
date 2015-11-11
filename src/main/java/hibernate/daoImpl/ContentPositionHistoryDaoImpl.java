package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;

import hibernate.dao.ContentPositionHistoryDao;
import hibernate.tables.ContentPositionHistory;
import hibernate.util.HibernateDaoBuilder;

public class ContentPositionHistoryDaoImpl implements ContentPositionHistoryDao{

	public void addContentPositionHistory(ContentPositionHistory contentPositionHistory) throws SQLException {
		HibernateDaoBuilder.saveTableValue(contentPositionHistory);
	}

	public ContentPositionHistory getContentPositionHistory(int id)throws SQLException {
		return (ContentPositionHistory)HibernateDaoBuilder.getTableValue(id, new ContentPositionHistory());
	}

	public List<ContentPositionHistory> getContentPositionHistory()throws SQLException {
		return (List<ContentPositionHistory>)HibernateDaoBuilder.getTableValues(new ContentPositionHistory());
	}

	public void deleteContentPositionHistory(
			ContentPositionHistory contentPositionHistory) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(contentPositionHistory);
	}
	
	/**
	 * @propertyName is a field in Object which is assigned with DB table. 
	 * @propertyValue is a value of propertyName 
	 */
	public List<ContentPositionHistory> getContentPositionHistoryByProperty(
			String propertyName, Object propertyValue) throws SQLException {
		return (List<ContentPositionHistory>)HibernateDaoBuilder.getTableValuesByProperty(propertyName, propertyValue, new ContentPositionHistory());
	}

	public void updateContentPositionHistory(
			ContentPositionHistory contentPositionHistory) throws SQLException {
		HibernateDaoBuilder.updateTableValue(contentPositionHistory);
	}
}
