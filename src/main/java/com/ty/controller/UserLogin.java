package com.ty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ty.dao.EmployeeDao;
import com.ty.dto.Employee;
import com.ty.dto.Task;

@WebServlet(value = "/login")
public class UserLogin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("user_name");
		String password = req.getParameter("user_password");
		HttpSession session = req.getSession();

		Employee employee = EmployeeDao.loginEmployee(userName, password);
		if (employee != null) {
			session.setAttribute("employee", employee);
			if (employee.getRole().equals("Manager")) {
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("ManagerDashboard.html");
				requestDispatcher.forward(req, resp);
			} else if (employee.getRole().equals("Employee")) {
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("EmployeeDashboard.html");
				requestDispatcher.forward(req, resp);
			} 
		}
		else {
			PrintWriter printWriter = resp.getWriter();
			printWriter.print("<h1>Invalid Credentials....!</h1>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.html");
			requestDispatcher.include(req, resp);
		}
	}
}
