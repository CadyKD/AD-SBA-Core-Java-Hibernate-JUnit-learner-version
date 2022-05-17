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
@Table(name = "student")
public class Student {
	@Id
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	
	@NonNull
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@NonNull
	@Column(name = "password", nullable = false, length = 50)
	private String password;
	
	@ToString.Exclude
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch= FetchType.EAGER)
	@JoinTable(name = "student_courses",
			joinColumns = @JoinColumn(name = "student_email"),
			inverseJoinColumns = @JoinColumn(name = "courses_id"))
	private List<Course> courses = new ArrayList<>();
	
	public Student(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}
}
