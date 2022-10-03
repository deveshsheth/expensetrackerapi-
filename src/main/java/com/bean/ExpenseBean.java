package com.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ExpenseBean {
	
	int expenseid,userid;
	
	String expensename,amount,expensecategory,paymenttype,comment;
	Date expensedate;

}
