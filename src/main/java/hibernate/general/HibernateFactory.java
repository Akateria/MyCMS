package hibernate.general;

import hibernate.dao.ContentDao;
import hibernate.dao.ContentTagLinkerDao;
import hibernate.dao.FrontPageDao;
import hibernate.dao.TagDao;
import hibernate.dao.UserDao;
import hibernate.daoImpl.ContentDaoImpl;
import hibernate.daoImpl.ContentTagLinkerDaoImpl;
import hibernate.daoImpl.FrontPageDaoImpl;
import hibernate.daoImpl.TagDaoImpl;
import hibernate.daoImpl.UserDaoImpl;

public class HibernateFactory {
	private static HibernateFactory factory = new HibernateFactory();

	private static ContentDao contentDao;
	private static ContentTagLinkerDao contentTagLinkerDao;
	private static FrontPageDao frontPageDao;
	private static UserDao userDao;
	private static TagDao tagDao;

	private HibernateFactory() {
	}

	public static HibernateFactory getInstance() {
		return HibernateFactory.factory;
	}

	public ContentDao getContentDao() {
		if (contentDao == null)
			contentDao = new ContentDaoImpl();
		return contentDao;
	}

	public ContentTagLinkerDao getContentTagLinkerDao() {
		if (contentTagLinkerDao == null)
			contentTagLinkerDao = new ContentTagLinkerDaoImpl();
		return contentTagLinkerDao;
	}

	public FrontPageDao getFrontPageDao() {
		if (frontPageDao == null)
			frontPageDao = new FrontPageDaoImpl();
		return frontPageDao;
	}

	public UserDao getUserDao() {
		if (userDao == null)
			userDao = new UserDaoImpl();
		return userDao;
	}

	public TagDao getTagDao() {
		if (tagDao == null)
			tagDao = new TagDaoImpl();
		return tagDao;
	}
	
}
