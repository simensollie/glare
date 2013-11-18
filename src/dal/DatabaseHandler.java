package dal;

import java.util.List;

/**
 * @author Andreas Bjerga & Marius Vasshus
 */

import org.hibernate.Query;
import org.hibernate.Session;

public class DatabaseHandler {
	
	public static void addPictureToDB(PictureData pic){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		session.save(pic);
		
		session.getTransaction().commit();
	}
	public static void addPictureToDBOPT(List<PictureData> picList){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		for(PictureData pd: picList){
			session.save(pd);
		}
		session.getTransaction().commit();
	}
	
	public static List<PictureData> listOfPicturesFromDB(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		List<PictureData> result = session.createQuery("from PictureData").list();
		
		session.getTransaction().commit();
		
		return result;
	}
	
	public static void addHashtagToDB(Hashtag hash){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		session.save(hash);
		
		session.getTransaction().commit();
	}

	public static List<String> listOfHashtagsFromDB() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		List<String> result = session.createQuery("SELECT hashtag FROM Hashtag").list();
		
		session.getTransaction().commit();
		
		return result;
	}
	
	public static void removePictureDataFromDB(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		Query q = session.createSQLQuery("DELETE FROM PictureData");
		q.executeUpdate();
		
		session.getTransaction().commit();
	}
	
	public static void removeHashtagFromDB(String hashName){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		String s = "DELETE FROM Hashtag WHERE hashtag=\'" + hashName.toLowerCase() + "\'";
		
		Query q = session.createQuery(s);
		q.executeUpdate();

		session.getTransaction().commit();
	}
	
	//NOTE TO SELF!! PICS WITH REMOVEFLAG SHOULD NOT BE REMOVED! FIX THIS LATER
	public static void removePicturesWithoutHashTagFromDB(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		String s = "DELETE FROM PictureData "
				+ "WHERE removeFlag = (0) "
				+ "AND NOT EXISTS ("
					+ "SELECT * "
					+ "FROM Hash_Pics "
					+ "WHERE Hash_Pics.picID=PictureData.id"
					+ ")";
		
		Query q = session.createSQLQuery(s);
		q.executeUpdate();

		session.getTransaction().commit();
	}
	public static void setRemoveFlag(String picID){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		String s = "update PictureData set removeFlag = :newFlag where id = :picID";
		Query q = session.createQuery(s);
		q.setBoolean("newFlag", true);
		q.setString("picID", picID);
		
		q.executeUpdate();
		
		session.getTransaction().commit();
		
	}
}
