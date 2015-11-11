package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.List;
import hibernate.dao.ContentTagLinkerDao;
import hibernate.tables.ContentTagLinker;
import hibernate.util.HibernateDaoBuilder;

public class ContentTagLinkerDaoImpl implements ContentTagLinkerDao {

	public void addLink(ContentTagLinker contentTagLinker) throws SQLException {
		HibernateDaoBuilder.saveTableValue(contentTagLinker);
	}

	public ContentTagLinker getLink(int id) throws SQLException {
		return (ContentTagLinker)HibernateDaoBuilder.getTableValue(id, new ContentTagLinker());
	}

	public List<ContentTagLinker> getLinks() throws SQLException {
		return (List<ContentTagLinker>) HibernateDaoBuilder.getTableValues(new ContentTagLinker());
	}

	public void deleteLink(ContentTagLinker contentTagLinker) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(contentTagLinker);
	}

	/**
	 * @propertyName is a field in Object which is assigned with DB table. 
	 * @propertyValue is a value of propertyName 
	 */
	public List<ContentTagLinker> getContentTagLinkersByProperty(String propertyName,
			Object propertyValue) throws SQLException {
		return (List<ContentTagLinker>)HibernateDaoBuilder.getTableValuesByProperty(propertyName, propertyValue, new ContentTagLinker());
	}

	public void updateLink(ContentTagLinker contentTagLinker)
			throws SQLException {
		HibernateDaoBuilder.updateTableValue(contentTagLinker);
	}
}
