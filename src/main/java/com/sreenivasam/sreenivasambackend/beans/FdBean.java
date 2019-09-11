package com.sreenivasam.beans;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FdBean {
	private Long id;
	private String bank;
	private Float depAmount;
	private Float roi;
	private Float maturedAmount;
	private String depositedOnStr;
	private Date depositedOn;
	private Integer periodInMonths;
	private Date maturedOn;
	private String maturedOnStr;
	private String remainingTime;
}
