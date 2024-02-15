package com.ty.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ty.dao.EmployeeDao;
import com.ty.dto.Employee;

@WebServlet(value = "/register")
public class UserRegister extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("user_name");
		String useremail = req.getParameter("user_email");
		String userPassword = req.getParameter("user_password");
		String userRole = req.getParameter("user_role");
		String userDesignation = req.getParameter("user_designation");
		String userSalary = req.getParameter("user_salary");

		double salary = Double.parseDouble(userSalary);
		Employee employee = EmployeeDao.saveEmployee(userName, useremail, userPassword, userRole, userDesignation,
				salary);
		if (employee != null) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.html");
			requestDispatcher.forward(req, resp);
		} else {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("Register.html");
			requestDispatcher.forward(req, resp);
		}
	}
}
