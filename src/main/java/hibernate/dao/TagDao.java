package hibernate.dao;

import hibernate.tables.Tag;
import java.sql.SQLException;
import java.util.List;

public interface TagDao{
	void addTag(Tag tag) throws SQLException;

	Tag getTag(int id) throws SQLException;

	List<Tag> getTagsByProperty(String propertyName, Object propertyValue) throws SQLException;
	
	List<Tag> getTags() throws SQLException;

	void updateTag(Tag tag) throws SQLException;
	
	void deleteTag(Tag tag) throws SQLException;
}
