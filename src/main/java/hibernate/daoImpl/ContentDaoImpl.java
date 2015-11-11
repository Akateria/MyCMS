package hibernate.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hibernate.dao.ContentDao;
import hibernate.tables.Content;
import hibernate.tables.ContentTagLinker;
import hibernate.tables.Tag;
import hibernate.util.HibernateDaoBuilder;

public class ContentDaoImpl implements ContentDao{

	public void addContent(Content content) throws SQLException {
		HibernateDaoBuilder.saveTableValue(content);
	}

	public Content getContent(int id) throws SQLException {
		return (Content)HibernateDaoBuilder.getTableValue(id, new Content());
	}

	public List<Content> getContents() throws SQLException {
		return (List<Content>)HibernateDaoBuilder.getTableValues(new Content());
	}

	public void deleteContent(Content content) throws SQLException {
		HibernateDaoBuilder.deleteTableValue(content);
	}

	/**
	 * @propertyName is a field in Object which is assigned with DB table. 
	 * @propertyValue is a value of propertyName 
	 */
	public List<Content> getContentsByProperty(String propertyName,
			Object propertyValue) throws SQLException {
		return (List<Content>)HibernateDaoBuilder.getTableValuesByProperty(propertyName, propertyValue, new Content());
	}
	
	public List<Content> getContentsByProperty(String propertyName1, Object propertyValue1, String propertyName2,
			Object propertyValue2) throws SQLException {
		return (List<Content>)HibernateDaoBuilder.getTableValuesByProperty(propertyName1, propertyValue1,propertyName2, propertyValue2, new Content());
	}

	public List<Content> getContentsByProperty(String propertyName1, Object propertyValue1, String propertyName2,
			Object propertyValue2, String propertyName3, Object propertyValue3) throws SQLException {
		return (List<Content>)HibernateDaoBuilder.getTableValuesByProperty(propertyName1, propertyValue1, propertyName2, propertyValue2, propertyName3, propertyValue3, new Content());
	}
	

	public void updateContent(Content content) throws SQLException {
		HibernateDaoBuilder.updateTableValue(content);
	}

    public ArrayList<Content>  getContentByString(String keyword, String contentsIdArray) throws SQLException{
        ArrayList<Content> contents = new ArrayList<>();
        String query = "from Content where c_text like '%" + keyword + "%' and c_id in (" + contentsIdArray + ")";
        List<Content> contentList = HibernateDaoBuilder.getListByQuery(query);
        for (Content content: contentList){
            contents.add(content);
        }
        return contents;
    }

    public ArrayList<Content>  getContentByTags(String tagsID, String contentsIdArray) throws SQLException{
        ArrayList<Content> contents = new ArrayList<>();
        String query = "from ContentTagLinker where t_id in (" + tagsID + ") and c_id in (" + contentsIdArray + ")";
          List<ContentTagLinker> contentList = HibernateDaoBuilder.getListByQuery(query);
        for (ContentTagLinker contentTagLinker: contentList){
            contents.add(contentTagLinker.getContent());
        }
        return contents;
    }

    public ArrayList<Content>  getContentByTags(String tagsID) throws SQLException{
        ArrayList<Content> contents = new ArrayList<>();
        String query = "from ContentTagLinker where t_id in (" + tagsID + ")";
        List<ContentTagLinker> contentList = HibernateDaoBuilder.getListByQuery(query);
        for (ContentTagLinker contentTagLinker: contentList){
            contents.add(contentTagLinker.getContent());
        }
        return contents;
    }
}
