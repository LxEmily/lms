package ie.ucd.lms.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "login")
public class Login implements Serializable {

	public Login() {
	}

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// Long id;

	@Id
	@NotEmpty
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@NotEmpty
	@Size(min = 4)
	private String hash;

	@OneToOne(mappedBy = "login")
	private Member member;

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}