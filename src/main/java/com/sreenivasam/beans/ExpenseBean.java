package com.sreenivasam.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseBean extends BaseBean{

	private Long id;

	private String title;

	private Float amount;
}
