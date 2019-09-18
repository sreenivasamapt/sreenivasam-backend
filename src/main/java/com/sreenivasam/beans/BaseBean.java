package com.sreenivasam.beans;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class BaseBean {

	private Timestamp createdOn;

	private Timestamp updatedOn;

}
