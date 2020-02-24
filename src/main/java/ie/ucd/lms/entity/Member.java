package ie.ucd.lms.entity;

import java.io.Serializable;
import java.time.*;
import javax.persistence.*;

@Entity
@Table(name = "members")
public class Member implements Serializable {

	public Member() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email")
	private String email;
	private String fullName;
	private String mobileNumber;
	private LocalDateTime bornOn;
	private LocalDateTime joinedOn = LocalDateTime.now();
	private LocalDateTime lastActiveOn;
	private String bio;
	private String type = "member";

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "email")
	private Login login;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDateTime getBornOn() {
		return bornOn;
	}

	public void setBornOn(LocalDateTime bornOn) {
		this.bornOn = bornOn;
	}

	public LocalDateTime getJoinedOn() {
		return joinedOn;
	}

	public void setJoinedOn(LocalDateTime joinedOn) {
		this.joinedOn = joinedOn;
	}

	public LocalDateTime getLastActiveOn() {
		return lastActiveOn;
	}

	public void setLastActiveOn(LocalDateTime lastActiveOn) {
		this.lastActiveOn = lastActiveOn;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}