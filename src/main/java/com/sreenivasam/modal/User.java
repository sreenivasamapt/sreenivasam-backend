package com.sreenivasam.modal;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLAT_ID")
	private Flat flat;

	@Column(name = "OWNER")
	private Boolean owner;

	@Column(name = "NAME")
	private String name;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "DOB")
	private Date dob;

	@OneToMany(fetch = FetchType.LAZY, targetEntity=Event.class,mappedBy = "user")
	private List<Event> events;
}
