package com.ty.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ty.dao.EmployeeDao;
import com.ty.dto.Employee;

@WebServlet(value = "/manager_home")
public class ManagerHome extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Employee employee = (Employee) session.getAttribute("employee");

		PrintWriter printWriter = resp.getWriter();
		if (employee != null) {
			printWriter.print("<h1>--------------Manager Info------------------</h1>");
			printWriter.print("<h1>Id :" + employee.getEmployeeId() + "</h1>");
			printWriter.print("<h1> Name :" + employee.getEmployeeName() + "</h1>");
			printWriter.print("<h1>Email :" + employee.getEmail() + "</h1>");
			printWriter.print("<h1> Password :" + employee.getPassword() + "</h1>");
			printWriter.print("<h1> Designation :" + employee.getDesignation() + "</h1>");
			printWriter.print("<h1>Role :" + employee.getRole() + "</h1>");
		} else {
			printWriter.print("<h1>Invalid Manager Details..!</h1>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("ManagerDashboard.html");
			requestDispatcher.include(req, resp);
		}
	}
}
