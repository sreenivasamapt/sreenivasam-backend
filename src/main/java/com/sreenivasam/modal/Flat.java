package com.sreenivasam.modal;

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
@Table(name = "t_flat")
public class Flat extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FLOOR")
	private Integer floor;

	@Column(name = "FLAT_NO")
	private Integer flatNo;

	@Column(name = "VACANCY")
	private Boolean vacancy;
	
}
