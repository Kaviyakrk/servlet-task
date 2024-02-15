package com.ty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ty.dto.Employee;
import com.ty.dto.Task;
import com.ty.helper.Helper;

@WebServlet(value = "/UpdateStatus")
public class UpdateStatus1 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String status = req.getParameter("status");
		String id = req.getParameter("user_id");

		int id1 = Integer.parseInt(id);

		Query query = Helper.entityManager.createQuery("select e from Task e where e.taskId='" + id1 + "'");
		Task task = (Task) query.getResultList().get(0);

		PrintWriter printWriter = resp.getWriter();
		printWriter.print("<html><body>");
		printWriter.print("<h4>-------------Before Update--------------</h4>");
		printWriter.print("<table>");
		printWriter.print("<tr><td> Task </td><td>Status</td></tr>");
		printWriter.print("<tr><td> Task Id </td><td>" + task.getTaskId() + "</td></tr>");
		printWriter.print("<tr><td> Task Description </td><td>" + task.getDescription() + "</td></tr>");
		printWriter.print("<tr><td> Task Status</td><td>" + task.getStatus() + "</td></tr>");
		printWriter.print("<tr><td> Assigned Time  </td><td>" + task.getAssignedTime() + "</td></tr>");
		printWriter.print("<tr><td> Completed Time </td><td>" + task.getCompletedTime() + "</td></tr>");
		printWriter.print("</table>");
		printWriter.print("</body></html>");
		
		if (task != null) {
			task.setStatus(status);
			task.setCompletedTime(LocalDateTime.now());
			Helper.entityTransaction.begin();
			Helper.entityManager.merge(task);
			Helper.entityTransaction.commit();
			
			printWriter.print("<html><body>");
			printWriter.print("<h4>-------------Updated Status--------------</h4>");
			printWriter.print("<table style='border:1px black solid'>");
			printWriter.print("<tr><td> Task </td><td>Status</td></tr>");
			printWriter.print("<tr><td> Task Id </td><td>" + task.getTaskId() + "</td></tr>");
			printWriter.print("<tr><td> Task Description </td><td>" + task.getDescription() + "</td></tr>");
			printWriter.print("<tr><td> Task Status</td><td>" + task.getStatus() + "</td></tr>");
			printWriter.print("<tr><td> Assigned Time  </td><td>" + task.getAssignedTime() + "</td></tr>");
			printWriter.print("<tr><td> Completed Time </td><td>" + task.getCompletedTime() + "</td></tr>");
			printWriter.print("</table>");
			printWriter.print("</body></html>");
		}
	}
}
