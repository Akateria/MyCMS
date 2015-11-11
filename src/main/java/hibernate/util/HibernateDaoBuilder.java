package hibernate.util;

import hibernate.tables.Content;
import hibernate.tables.ContentTagLinker;
import hibernate.tables.Table;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class HibernateDaoBuilder{
	private HibernateDaoBuilder(){}

	public static void saveTableValue(Table table) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(table);
			session.getTransaction().commit();
		}
        finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

    public static List getListByQuery(String query){
        Session session = null;
        List resultList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            resultList = session.createQuery(query).list();
            session.getTransaction().commit();
        }
        finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return resultList;
    }

	public static void updateTableValue(Table table) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(table);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}
	
	public static Table getTableValue(int id, Table table) throws SQLException {
		Session session = null;
		Table newTable = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			newTable = (Table) session.get(table.getClass(), id);
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return newTable;
	}
	
	public static List<? extends Table> getTableValuesByProperty(String propertyName, Object propertyValue, Table table) throws SQLException {
		Session session = null;
		List<? extends Table> tableValues = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tableValues= (List<? extends Table>)session.createCriteria(table.getClass()).add(Restrictions.eq(propertyName, propertyValue)).list();
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return tableValues;
	}
	
	public static List<? extends Table> getTableValuesByProperty(String propertyName1,
			Object propertyValue1, String propertyName2, Object propertyValue2,
			Table table) {
		Session session = null;
		List<? extends Table> tableValues = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tableValues= (List<? extends Table>)session.createCriteria(table.getClass()).add(Restrictions.eq(propertyName1, propertyValue1)).add(Restrictions.eq(propertyName2, propertyValue2)).list();
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return tableValues;
	}

    public static List<? extends Table> getContents(Table table) {
        Session session = null;
        List<? extends Table> tableValues = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            tableValues= (List<? extends Table>)session.createCriteria(table.getClass()).list();;
            session.getTransaction().commit();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return tableValues;
    }

    public static List<Content> getContentsList(Table table) {
        Session session = null;
        List<Content> tableValues = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            tableValues= (List<Content>)session.createCriteria(table.getClass()).list();;
            session.getTransaction().commit();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return tableValues;
    }

	
	public static List<? extends Table> getTableValuesByProperty(String propertyName1,
			Object propertyValue1, String propertyName2, Object propertyValue2, String propertyName3, Object propertyValue3,
			Table table) {
		Session session = null;
		List<? extends Table> tableValues = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			tableValues= (List<? extends Table>)session.createCriteria(table.getClass()).add(Restrictions.eq(propertyName1, propertyValue1)).add(Restrictions.eq(propertyName2, propertyValue2)).add(Restrictions.eq(propertyName3, propertyValue3)).list();
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return tableValues;
	}
	
	
	public static List<? extends Table> getTableValues(Table table) throws SQLException {
		Session session = null;
		List<? extends Table> tableValues = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			System.out.println(table.getClass().toString());
			tableValues = (List<? extends Table>)session.createCriteria(table.getClass()).list();
			session.getTransaction().commit();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return tableValues;
	}
	
	public static void deleteTableValue(Table obj) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
	}

}
