package god.uaa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tb_user") // This tells Hibernate to make a table out of this
							// class
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(columnDefinition = "사용자ID")
	// @Column(name = "id", columnDefinition = "사용자ID")
	private Integer id;

	// @Column(columnDefinition = "사용자명")
	// @Column(name = "name", columnDefinition = "사용자명")
	private String name;

	// @Column(columnDefinition = "사용자이메일")
	// @Column(name = "email", columnDefinition = "사용자이메일")
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
