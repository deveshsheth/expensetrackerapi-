package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class SessionDao {
	
	 @Autowired
	    JdbcTemplate stmt;

	    public int insertUser(UserBean userBean) {
	        // TODO Auto-generated method stub
	        KeyHolder keyHolder = new GeneratedKeyHolder();
	        String insertSql = "insert into users (name,email,password,gender) values(?,?,?,?)";

	        stmt.update(new PreparedStatementCreator() {

	            @Override
	            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

	                PreparedStatement pstmt = con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
	                pstmt.setString(1, userBean.getName());
	                pstmt.setString(2, userBean.getEmail());
	                pstmt.setString(3, userBean.getPassword());
	                pstmt.setString(4, userBean.getGender());
	                return pstmt;
	            }
	        }, keyHolder);

	        int userid = (Integer) keyHolder.getKeys().get("userid");
	        userBean.setUserid(userid);
	        return userBean.getUserid();
	    }
	    
	    @SuppressWarnings("deprecation")
		public UserBean getUserByEmail(String email) {
	        // TODO Auto-generated method stub

	        UserBean userBean = null;

	        try {
	            userBean = stmt.queryForObject("select * from users where email=?",

	                    new Object[]{email}, BeanPropertyRowMapper.newInstance(UserBean.class));
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return userBean;
	    }
	    
	    @SuppressWarnings("deprecation")
		public UserBean login(String email, String password) {
	        // TODO Auto-generated method stub
	        email = email.trim();
	        password = password.trim();
	        UserBean userBean = null;
	        System.out.println(email);
	        System.out.println(password);
	        try {
	            userBean = stmt.queryForObject("select * from users where email = ? and password = ?",
	                    new Object[]{email, password}, BeanPropertyRowMapper.newInstance(UserBean.class));
	        } catch (Exception e) {
	            // TODO: handle exception
	            System.out.println("errror ...");
	            e.printStackTrace();
	        }

	        System.out.println(userBean);
	        return userBean;
	    }


}
