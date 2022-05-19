package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@NonNull
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@NonNull
	@Column(name = "instructor", nullable = false, length = 50)
	private String instructor;
	
	@ToString.Exclude
	@ManyToMany(mappedBy = "courses",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.EAGER)
	private List<Student> students = new ArrayList<>();
	
	public void addStudent(Student student) {
		students.add(student);
		student.getCourses().add(this);
	}
	
	@Override
	public String toString() {
		return "Course{Name:'" + name + "', Instructor:'" + instructor + "'}";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Course other = (Course) obj;
		return name.equals(other.name) && instructor.equals(other.instructor);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, instructor);
	}
}
