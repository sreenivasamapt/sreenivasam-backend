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
@Table(name = "t_bank")
public class Chit extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "MONTH")
	private Integer month;
	
	@Column(name = "YEAR")
	private Integer year;
	
	@Column(name = "ACTUAL_AMOUNT")
	private Float actualAmount;
	
	@Column(name = "PAID_AMOUNT")
	private Float paidAmount;
	
	@Column(name = "PROFIT")
	private Float profit;
}
