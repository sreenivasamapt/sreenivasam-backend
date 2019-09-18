package com.sreenivasam.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventBean extends BaseBean{

	private Long id;

	private String type;

	private Long userId;
	
	private String userName;

	private String description;

	private String eventDateStr;

	private String imagePath;

}
