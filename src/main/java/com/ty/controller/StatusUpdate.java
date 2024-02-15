package com.ty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ty.dto.Employee;
import com.ty.dto.Task;

@WebServlet(value = "/status_update")
public class StatusUpdate extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
	
		PrintWriter printWritter = resp.getWriter();
		if (employee != null) {
			List<Task> tasks = employee.getTasks();
			for (Task task : tasks) {
				if (task.getStatus().equals("Open")) {
					printWritter.print("<html><body>");
					printWritter.print("<h5> Task Id :" + task.getTaskId()+"</h5>");
					printWritter.print("<h5> Description :" + task.getDescription()+"</h5>");
					printWritter.print("<h5> Status :" + task.getStatus()+"</h5>");
					printWritter.print("</body></html>");
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("Update.jsp");
					requestDispatcher.include(req, resp);
				}
			}
		}
		else {
			printWritter.print("<h1>Tasks Not Found..!</h1>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("EmployeeDashboard.html");
			requestDispatcher.include(req, resp);
		}
	}
}
