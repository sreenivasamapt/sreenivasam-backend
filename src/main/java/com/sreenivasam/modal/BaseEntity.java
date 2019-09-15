package com.sreenivasam.modal;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class BaseEntity {

	@CreationTimestamp
	@Column(name = "CREATED_ON")
	private Timestamp createdOn;

	@UpdateTimestamp
	@Column(name = "UPDATED_ON")
	private Timestamp updatedOn;

}
