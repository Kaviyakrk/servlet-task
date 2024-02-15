package com.ty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ty.dao.EmployeeDao;
import com.ty.dto.Employee;
import com.ty.dto.Task;

@WebServlet(value = "/employee_home")
public class EmployeeHome extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String employeeName = req.getParameter("emp_name");
		String employeePassword=req.getParameter("emp_password");

		Employee employee = EmployeeDao.employeeRecord(employeeName,employeePassword);
		PrintWriter printWriter = resp.getWriter();
		if (employee != null) {
			printWriter.print("<h1>--------------Employee Info------------------</h1>");
			printWriter.print("<h2> Name :" + employee.getEmployeeName() + "</h2>");
			printWriter.print("<h2>Email :" +employee.getEmail()  + "</h2>");
			printWriter.print("<h2> Password :" + employee.getPassword() + "</h2>");
			printWriter.print("<h2> Designation :" + employee.getDesignation() + "</h2>");
			printWriter.print("<h2>Role :" + employee.getRole()+ "</h2>");
			
			List<Task> tasks = employee.getTasks();
			for (Task task : tasks) {
				printWriter.print("<h1>--------------Task Info------------------</h1>");
				printWriter.print("<h2> Description :" + task.getDescription() + "</h2>");
				printWriter.print("<h2> Status :" + task.getStatus() + "</h2>");
				printWriter.print("<h2> Assigned Time :" + task.getAssignedTime() + "</h2>");
				printWriter.print("<h2> UpdatedTime :" + task.getCompletedTime() + "</h2>");
			}
		}
		else {
			printWriter.print("<h1>Invalid Employee Details..!</h1>");
			RequestDispatcher requestDispatcher=req.getRequestDispatcher("DisplayEmployee.html");
			requestDispatcher.include(req, resp);
		}
	}
}
