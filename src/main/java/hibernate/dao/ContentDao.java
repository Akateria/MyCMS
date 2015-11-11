package hibernate.dao;

import hibernate.tables.Content;
import hibernate.tables.ContentTagLinker;
import hibernate.tables.Tag;
import hibernate.util.HibernateDaoBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ContentDao{

	void addContent(Content content) throws SQLException;

	Content getContent(int id) throws SQLException;

	List<Content> getContentsByProperty(String propertyName, Object propertyValue) throws SQLException;
	
	List<Content> getContents() throws SQLException;

	void updateContent(Content content) throws SQLException;
	
	void deleteContent(Content content) throws SQLException;

    ArrayList<Content> getContentByTags(String tagsID, String contentsIdArray) throws SQLException;

    ArrayList<Content> getContentByTags(String tagsID) throws SQLException;

    ArrayList<Content> getContentByString(String keyword, String contentsIdArray) throws SQLException;
	
}
