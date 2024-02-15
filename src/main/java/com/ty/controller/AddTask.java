package com.ty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.ty.dto.Employee;
import com.ty.dto.Task;
import com.ty.helper.Helper;

@WebServlet(value = "/add_task")
public class AddTask extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("user_name");

		Query query = Helper.entityManager
				.createQuery("Select e from Employee e where e.employeeName='" + userName + "'");
		PrintWriter printWriter = resp.getWriter();
		try {
			Employee employee = (Employee) query.getResultList().get(0);
			if (employee != null) {
				String description = req.getParameter("task_description");
				String status = req.getParameter("status");

				Task task=new Task();
				task.setDescription(description);
				task.setStatus(status);
				
				List <Task> tasks=employee.getTasks();
				tasks.add(task);
				employee.setTasks(tasks);
				
				
				Helper.entityTransaction.begin();
				Helper.entityManager.persist(task);
				Helper.entityManager.merge(employee);
				Helper.entityTransaction.commit();

				printWriter.print("<h1>Task Added..!</h1>");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("DisplayEmployee.html");
				requestDispatcher.include(req, resp);
			}
		} catch (IndexOutOfBoundsException e) {
			printWriter.print("<h1>In Valid Credential..! Provide Valid Employee Name..!</h1>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("Task.html");
			requestDispatcher.include(req, resp);
		}
	}
}
