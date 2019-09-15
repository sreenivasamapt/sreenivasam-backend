package com.sreenivasam.modal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_user")
public class User  extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FLAT_NO")
	private String flatNo;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "Gender")
	private String gender;
	

	@Column(name = "DOB")
	private Date dob;
}
