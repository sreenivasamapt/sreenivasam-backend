package com.sreenivasam.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlatBean  extends BaseBean{

	private Long id;

	private Integer floor;

	private Integer flatNo;

	private Boolean vacancy;
	
	private String ownerName;
}
