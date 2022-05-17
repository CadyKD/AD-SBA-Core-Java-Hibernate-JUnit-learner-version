package sba.sms.services;

import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

@Log
public class StudentService implements StudentI {
	@Override
	public List<Student> getAllStudents() {
		List<Student> allStudents = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			allStudents = session.createNativeQuery("SELECT * FROM student", Student.class).list();
		} catch (HibernateException exception) {
			exception.printStackTrace();
		}
		return allStudents;
	}
	
	@Override
	public void createStudent(Student student) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.persist(student);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) tx.rollback();
			ex.printStackTrace();
		}
	}
	
	@Override
	public Student getStudentByEmail(String email) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Student student = session.get(Student.class, email);
			if (student == null) {
				throw new HibernateException("Could not find student");
			} else {
				return student;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean validateStudent(String email, String password) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Student student = session.get(Student.class, email);
			if (student == null) {
				throw new HibernateException("Student does not exist in database");
			} else if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
				return true;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void registerStudentToCourse(String email, int courseId) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			Student student = session.get(Student.class, email);
			Course course = session.get(Course.class, courseId);
			course.addStudent(student);
			session.merge(course);
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null) {
				tx.rollback();
			}
			exception.printStackTrace();
		}
	}
	
	@Override
	public List<Course> getStudentCourses(String email) {
		Transaction tx = null;
		List<Course> courseList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			NativeQuery<Course> query = session.createNativeQuery("SELECT c.id, c.name, c.instructor "
					+ "FROM course AS c JOIN student_courses AS sc ON c.id = sc.courses_id "
					+ "JOIN student as s ON sc.student_email = s.email WHERE email = :email", Course.class);
			query.setParameter("email", email);
			courseList = query.getResultList();
			tx.commit();
		} catch (HibernateException exception) {
			if (tx != null) {
				tx.rollback();
			}
			exception.printStackTrace();
		}
		return courseList;
	}
}
