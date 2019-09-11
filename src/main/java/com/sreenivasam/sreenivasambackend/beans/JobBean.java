package com.sreenivasam.beans;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobBean {
	private Long id;
	private String company;
	private String dojStr;
	private Date doj;
	private String dolStr;
	private Date dol;
	private String experience;
}
