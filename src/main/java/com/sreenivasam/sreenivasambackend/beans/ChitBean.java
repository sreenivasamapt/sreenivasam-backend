package com.sreenivasam.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChitBean {
	private Long id;
	private String month;
	private Integer year;
	private Float actualAmount;
	private Float paidAmount;
	private Float profit;
}
