package com.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.CategoryBean;
import com.bean.ExpenseBean;
import com.bean.ResponseBean;
import com.dao.ExpenseDao;

@RestController
@CrossOrigin
public class ExpenseController {

	@Autowired
	ExpenseDao expDao;

	@PostMapping("/addExpense")
	public ResponseBean<ExpenseBean> addExpense(@RequestBody ExpenseBean expBean) {
		expDao.insertExpense(expBean);
		ResponseBean<ExpenseBean> response = new ResponseBean<>();
		response.setData(expBean);
		response.setMessage("Expense Inserted successfully...!!");
		response.setStatus(200);
		return response;

	}

	@PostMapping("/addCategory")
	public ResponseBean<CategoryBean> addCategory(@RequestBody CategoryBean catgeoryBean) {
		ResponseBean<CategoryBean> response = new ResponseBean<>();
		if (expDao.CategorySameValidation(catgeoryBean.getCategoryname()) != null) {
			response.setMessage("Category Name Already Register");
			response.setStatus(201);
		}
		expDao.insertCategory(catgeoryBean);
		response.setData(catgeoryBean);
		response.setMessage("Category Inserted successfully...!!");
		response.setStatus(200);
		return response;

	}

	@GetMapping("/listCategory")
	public ResponseBean<java.util.List<CategoryBean>> listCategory() {
		ResponseBean<java.util.List<CategoryBean>> response = new ResponseBean<>();

		java.util.List<CategoryBean> categoryBean = expDao.listCategory();
		response.setData(categoryBean);
		response.setMessage("Category List Display..!!!!");
		response.setStatus(201);
		return response;
	}

	@GetMapping("/listExpense/{userid}")
	public ResponseBean<java.util.List<ExpenseBean>> listExpense(@PathVariable("userid") int userid) {
		ResponseBean<java.util.List<ExpenseBean>> response = new ResponseBean<>();

		java.util.List<ExpenseBean> expBean = expDao.listExpense(userid);
		response.setData(expBean);
		response.setMessage("Expense List Display..!!!!");
		response.setStatus(201);
		return response;
	}

	@GetMapping("/getExpense/{expenseid}")
	public ResponseBean<ExpenseBean> getUser(@PathVariable("expenseid") int expenseid, ExpenseBean bean) {

		ResponseBean<ExpenseBean> responseBean = new ResponseBean<>();
		bean = expDao.getExpenseById(expenseid);
		responseBean.setData(bean);
		responseBean.setMessage("Single Expense Return");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PutMapping("/updateExpense")
	public ResponseBean<ExpenseBean> updateExpense(@RequestBody ExpenseBean expBean) {
		expDao.updateExpense(expBean);
		ResponseBean<ExpenseBean> response = new ResponseBean<>();
		response.setData(expBean);
		response.setMessage("Expense Updated Successfully..!!");
		return response;
	}

	@DeleteMapping("/deleteExpense/{expenseid}")
	public ResponseBean<ExpenseBean> deleteExpense(@PathVariable("expenseid") int expenseid) {

		ResponseBean<ExpenseBean> response = new ResponseBean<>();
		expDao.deleteExpense(expenseid);
		response.setMessage("Expense Deleted Successfully..!!");
		response.setStatus(200);
		return response;
	}
	
	@GetMapping("/listExpenseDaily/{userid}")
	public ResponseBean<java.util.List<ExpenseBean>> listExpenseDaily(@PathVariable("userid") int userid) {
		ResponseBean<java.util.List<ExpenseBean>> response = new ResponseBean<>();

		java.util.List<ExpenseBean> expBean = expDao.listExpenseDaily(userid);
		response.setData(expBean);
		response.setMessage("Expense Daily List Display..!!!!");
		response.setStatus(201);
		return response;
	}
	
	@GetMapping("/listExpenseMonth/{expensedate}/{userid}")
	public ResponseBean<java.util.List<ExpenseBean>> listExpenseMonth(@PathVariable("expensedate") int expensedate,@PathVariable("userid") int userid) {
		ResponseBean<java.util.List<ExpenseBean>> response = new ResponseBean<>();

		java.util.List<ExpenseBean> expBean = expDao.listExpenseMonth(expensedate,userid);
		response.setData(expBean);
		response.setMessage("Expense Monthly List Display..!!!!");
		response.setStatus(201);
		return response;
	}
	
	@GetMapping("/listExpenseYear/{expensedate}/{userid}")
	public ResponseBean<java.util.List<ExpenseBean>> listExpenseYear(@PathVariable("expensedate") int expensedate,@PathVariable("userid") int userid) {
		ResponseBean<java.util.List<ExpenseBean>> response = new ResponseBean<>();

		java.util.List<ExpenseBean> expBean = expDao.listExpenseYear(expensedate,userid);
		response.setData(expBean);
		response.setMessage("Expense Yearly List Display..!!!!");
		response.setStatus(201);
		return response;
	}
	
	@GetMapping("/listExpensePaymentType/{paymenttype}/{userid}")
	public ResponseBean<java.util.List<ExpenseBean>> listExpensePaymentType(@PathVariable("paymenttype") String paymenttype,@PathVariable("userid") int userid) {
		ResponseBean<java.util.List<ExpenseBean>> response = new ResponseBean<>();

		java.util.List<ExpenseBean> expBean = expDao.listExpensePaymentType(paymenttype,userid);
		response.setData(expBean);
		response.setMessage("Expense Payment Type List Display..!!!!");
		response.setStatus(201);
		return response;
	}
}
