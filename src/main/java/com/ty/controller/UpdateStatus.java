package com.ty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ty.dto.Employee;
import com.ty.dto.Task;
import com.ty.helper.Helper;

@WebServlet(value = "/update_status")
public class UpdateStatus extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("user_name");
		String status = req.getParameter("status");
		String id = req.getParameter("user_id");

		int id1 = Integer.parseInt(id);

		Query query = Helper.entityManager.createQuery("select e from Employee e where e.employeeName='" + name + "'");
		Employee employee = (Employee) query.getResultList().get(0);

		List<Task> tasks = employee.getTasks();

		for (Task task : tasks) {

			if (task.getTaskId() == id1) {

				task.setStatus(status);
				task.setCompletedTime(LocalDateTime.now());
			}

			Helper.Update(employee, task);
//			Helper.entityTransaction.begin();
//			Helper.entityManager.merge(employee);
//			Helper.entityManager.merge(task);
//			Helper.entityTransaction.commit();
		}
	}
}
