package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.CategoryBean;
import com.bean.ExpenseBean;
import com.bean.UserBean;

@Repository
public class ExpenseDao {

	@Autowired
	JdbcTemplate stmt;

	public void insertExpense(ExpenseBean expBean) {
		// TODO Auto-generated method stub
		stmt.update(
				"insert into expense(expensename,amount,expensedate,expensecategory,paymenttype,comment,userid) values(?,?,?,?,?,?,?)",
				expBean.getExpensename(), expBean.getAmount(), expBean.getExpensedate(), expBean.getExpensecategory(),
				expBean.getPaymenttype(), expBean.getComment(), expBean.getUserid());
	}

	public void insertCategory(CategoryBean categoryBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into category(categoryname) values(?)", categoryBean.getCategoryname());
	}

	@SuppressWarnings("deprecation")
	public CategoryBean CategorySameValidation(String categoryname) {
		// TODO Auto-generated method stub

		CategoryBean categoryBean = null;

		try {
			categoryBean = stmt.queryForObject("select * from category where categoryname=?",

					new Object[] { categoryname }, BeanPropertyRowMapper.newInstance(CategoryBean.class));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return categoryBean;
	}

	public List<CategoryBean> listCategory() {
		// TODO Auto-generated method stub
		java.util.List<CategoryBean> categoryBean = stmt.query("select * from category",
				BeanPropertyRowMapper.newInstance(CategoryBean.class));
		return categoryBean;
	}

	@SuppressWarnings("deprecation")
	public List<ExpenseBean> listExpense(int userid) {
		// TODO Auto-generated method stub
		java.util.List<ExpenseBean> expBean = stmt.query("select * from expense where userid =?",
				new Object[] { userid }, BeanPropertyRowMapper.newInstance(ExpenseBean.class));
		return expBean;
	}

	@SuppressWarnings("deprecation")
	public ExpenseBean getExpenseById(int expenseid) {
		// TODO Auto-generated method stub
		ExpenseBean bean = null;
		try {
			bean = stmt.queryForObject("select * from expense where expenseid=?", new Object[] { expenseid },
					BeanPropertyRowMapper.newInstance(ExpenseBean.class));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bean;

	}

	public void updateExpense(ExpenseBean expBean) {
		// TODO Auto-generated method stub

		stmt.update(
				"update expense set expensename=?,amount=?,expensedate=?,expensecategory=?,paymenttype=?,comment=?,userid=? where expenseid=?",
				expBean.getExpensename(), expBean.getAmount(), expBean.getExpensedate(), expBean.getExpensecategory(),
				expBean.getPaymenttype(), expBean.getComment(), expBean.getUserid(), expBean.getExpenseid());

	}

	public void deleteExpense(int expenseid) {
		// TODO Auto-generated method stub
		stmt.update("delete from expense where expenseid = ?", expenseid);
	}

	@SuppressWarnings("deprecation")
	public List<ExpenseBean> listExpenseDaily(int userid) {
		// TODO Auto-generated method stub
		java.util.List<ExpenseBean> expbean = stmt.query(
				"select * from expense where expensedate=CURRENT_DATE and userid =?", new Object[] { userid },
				BeanPropertyRowMapper.newInstance(ExpenseBean.class));

		return expbean;
	}

	@SuppressWarnings("deprecation")
	public List<ExpenseBean> listExpenseMonth(int expensedate, int userid) {
		// TODO Auto-generated method stub

		java.util.List<ExpenseBean> expbean = stmt.query(
				"SELECT * FROM expense where EXTRACT(MONTH FROM expensedate)=? and EXTRACT(YEAR FROM expensedate)=EXTRACT(YEAR FROM CURRENT_DATE) and userid=?",
				new Object[] { expensedate, userid }, BeanPropertyRowMapper.newInstance(ExpenseBean.class));
		return expbean;
	}
	
	@SuppressWarnings("deprecation")
	public List<ExpenseBean> listExpenseYear(int expensedate, int userid) {
		// TODO Auto-generated method stub

		java.util.List<ExpenseBean> expbean = stmt.query(
				"SELECT * FROM expense where EXTRACT(YEAR FROM expensedate)=? and userid=?",
				new Object[] { expensedate, userid }, BeanPropertyRowMapper.newInstance(ExpenseBean.class));
		return expbean;
	}
	
	@SuppressWarnings("deprecation")
	public List<ExpenseBean> listExpensePaymentType(String paymenttype, int userid) {
		// TODO Auto-generated method stub

		java.util.List<ExpenseBean> expbean = stmt.query(
				"SELECT * FROM expense where paymenttype=? and userid=?",
				new Object[] { paymenttype, userid }, BeanPropertyRowMapper.newInstance(ExpenseBean.class));
		return expbean;
	}

	
}
