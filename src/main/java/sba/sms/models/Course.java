package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;

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
	private Long id;
	
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
}
