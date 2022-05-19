package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.*;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;

import java.util.*;

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
	
	@AfterEach
	void tearDown() {
	}
	
	@Test
	@Order(1)
	@DisplayName("Get All Students")
	void getAllStudents() {
		List<Student> expected = new ArrayList<>(Arrays.asList(
				new Student("reema@gmail.com", "reema brown", "password"),
				new Student("annette@gmail.com", "annette allen", "password"),
				new Student("anthony@gmail.com", "anthony gallegos", "password"),
				new Student("ariadna@gmail.com", "ariadna ramirez", "password"),
				new Student("bolaji@gmail.com", "bolaji saibu", "password")
		));
		expected.sort(Comparator.comparing(Student::getEmail));
		List<Student> actual = studentService.getAllStudents();
		actual.sort(Comparator.comparing(Student::getEmail));
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	@Order(2)
	@DisplayName("Create Student")
	void createStudent() {
		Student expected = new Student("reema@gmail.com", "reema brown", "password");
		Student actual = new Student("reema@gmail.com", "reema brown", "password");
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@Order(3)
	@DisplayName("Get Student By Email")
	void getStudentByEmail() {
		Student expected = new Student("reema@gmail.com", "reema brown", "password");
		Student actual = studentService.getStudentByEmail("reema@gmail.com");
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@Order(4)
	@DisplayName("Validate Student")
	void validateStudent() {
		Assertions.assertTrue(studentService.validateStudent("reema@gmail.com", "password"));
	}
}