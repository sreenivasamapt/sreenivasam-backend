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
@Table(name = "t_fixed_deposit")
public class Fd extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "BANK")
	private String bank;

	@Column(name = "DEP_AMOUNT")
	private Float depAmount;

	@Column(name = "ROI")
	private Float roi;

	@Column(name = "MAT_AMOUNT")
	private Float maturedAmount;

	@Column(name = "DEPOSITED_ON")
	private Date depositedOn;

	@Column(name = "PERIOD_IN_MONTHS")
	private Integer periodInMonths;

	@Column(name = "MATURED_ON")
	private Date maturedOn;

}
