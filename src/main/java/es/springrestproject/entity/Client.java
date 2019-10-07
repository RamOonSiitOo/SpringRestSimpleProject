package es.springrestproject.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

	/**
	 * Aggregated because we use Serializable and it need it
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*
	 * @Column used when the attribute name is not the same at database column
	 */
	@NotEmpty(message="El campo nombre no debe estar vacío...")
	@Size(min=4,max=12)
	@Column(name = "first_name")
	private String firstName;
	@NotEmpty
	@Column(name = "last_name")
	private String lastName;
	@NotEmpty
	@Email
	@Column(unique=true)
	private String email;

	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE) // Conversion to Date time of DB to App and viceversa
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createAt;

	private String picture;

	/*@PrePersist
	public void prePersist() {
		createAt = new Date();
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
