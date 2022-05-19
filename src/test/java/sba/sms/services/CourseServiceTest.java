package sba.sms.services;

import org.junit.jupiter.api.*;
import sba.sms.models.Course;
import sba.sms.utils.CommandLine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CourseServiceTest {
	
	static CourseService courseService;
	
	@BeforeEach
	void setUp() {
	}
	
	@BeforeAll
	static void beforeAll() {
		courseService = new CourseService();
		CommandLine.addData();
	}
	
	@AfterEach
	void tearDown() {
	}
	
	@Test
	@Order(1)
	@DisplayName("Create Course")
	void createCourse() {
		Course expected = new Course("Lunch", "Nobody");
		Course actual = new Course("Lunch", "Nobody");
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	@Order(2)
	@DisplayName("Get Course By ID")
	void getCourseById() {
		Course expected = new Course("Java", "Phillip Witkin");
		Course actual = courseService.getCourseById(1);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	@Order(3)
	@DisplayName("Get All courses")
	void getAllCourses() {
		List<Course> expected = new ArrayList<>(Arrays.asList(
				new Course("Java", "Phillip Witkin"),
				new Course("Frontend", "Kasper Kain"),
				new Course("JPA", "Jafer Alhaboubi"),
				new Course("Spring Framework", "Phillip Witkin"),
				new Course("SQL", "Phillip Witkin")
		));
		List<Course> actual = courseService.getAllCourses();
		Assertions.assertEquals(expected, actual);
	}
}