package sba.sms.services;

import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

@Log
public class CourseService implements CourseI {
	@Override
	public void createCourse(Course course) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.persist(course);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
		}
	}
	
	@Override
	public Course getCourseById(int courseId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Course course = session.get(Course.class, courseId);
			if (course == null) {
				throw new HibernateException("Could not find course");
			} else {
				return course;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Course> getAllCourses() {
		ArrayList<Course> allCourses = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			allCourses = (ArrayList<Course>) session.createNativeQuery("SELECT * FROM course", Course.class).list();
		} catch (HibernateException exception) {
			exception.printStackTrace();
		}
		return allCourses;
	}
}
