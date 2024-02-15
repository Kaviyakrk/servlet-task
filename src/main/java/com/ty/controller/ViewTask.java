package com.ty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ty.dto.Employee;
import com.ty.dto.Task;
import com.ty.helper.Helper;

@WebServlet(value = "/view_task")
public class ViewTask extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Query q=Helper.entityManager.createQuery("select e count(*) from Employee e
		// where e.role='"+emp.getRole()+"'");
		Query query = Helper.entityManager.createQuery("select e from Employee e");
		List<Employee> employees = (List<Employee>) query.getResultList();

		PrintWriter printWriter = resp.getWriter();
		for (Employee employee : employees) {
			if (employee.getRole().equals("Employee") ) {
				List<Task> task = employee.getTasks();
				int count = task.size();
				printWriter.print("<table style='border:3px black solid'><tr>");
				printWriter.print("<td style='border:1px black solid'>Employee Name</td>");
				printWriter.print("<td style='border:1px black solid'>Number Of Task</td>");
				printWriter.print("</tr>");
				printWriter.print("<tr>");
				printWriter.print("<td style='border:1px black solid'>" + employee.getEmployeeName() + "</td>");
				printWriter.print("<td style='border:1px black solid'>" + count + "</td>");
				printWriter.print("</tr></table>");
			}
		}
	}
}
