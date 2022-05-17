package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@FieldDefaults(level = AccessLevel.PRIVATE)
class StudentServiceTest {
	
	static StudentService studentService;
	
	@BeforeEach
	void setUp() {
	}
	
	@BeforeAll
	static void beforeAll() {
		studentService = new StudentService();
		CommandLine.addData();
	}
	
	@Test
	@Order(1)
	@DisplayName("Get All Employees")
	void getAllStudents() {
		List<Student> expected = new ArrayList<>(Arrays.asList(
				new Student("reema@gmail.com", "reema brown", "password"),
				new Student("annette@gmail.com", "annette allen", "password"),
				new Student("anthony@gmail.com", "anthony gallegos", "password"),
				new Student("ariadna@gmail.com", "ariadna ramirez", "password"),
				new Student("bolaji@gmail.com", "bolaji saibu", "password")
		));
		List<Student> actual = studentService.getAllStudents();
		assertEquals(expected.size(), actual.size());
	}
	
//	@Test
//	void createStudent() {
//
//	}
//
//	@Test
//	void getStudentByEmail() {
//	}
//
//	@Test
//	void validateStudent() {
//	}
//
//	@Test
//	void registerStudentToCourse() {
//	}
//
//	@Test
//	void getStudentCourses() {
//	}
//
//	@AfterEach
//	void tearDown() {
//	}
}