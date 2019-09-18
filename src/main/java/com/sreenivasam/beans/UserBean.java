package com.sreenivasam.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean extends BaseBean {

	private Long id;

	private Long flatId;
	
	private Integer flatNo;

	private Boolean owner;

	private String name;

	private String gender;

	private String dobStr;
}
