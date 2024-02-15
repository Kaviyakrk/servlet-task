package com.ty.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.Employee;
import com.ty.dto.Task;
import com.ty.helper.Helper;

public class EmployeeDao {

	// Register data
	public static Employee saveEmployee(String userName, String useremail, String userPassword, String userRole,
			String userDesignation, double salary) {
		// save Data into Db
		Employee employee = new Employee();
		employee.setEmployeeName(userName);
		employee.setEmail(useremail);
		employee.setPassword(userPassword);
		employee.setDesignation(userDesignation);
		employee.setRole(userRole);
		employee.setSalary(salary);
		List<Task> tasks = new ArrayList<>();
		employee.setTasks(tasks);

		Helper.entityTransaction.begin();
		Helper.entityManager.persist(employee);
		Helper.entityTransaction.commit();
		System.out.println("Registered..!");

		return employee;
	}

	// login user
	public static Employee loginEmployee(String userName, String password) {
		Query query = Helper.entityManager
				.createQuery("select e from Employee e where e.employeeName=?1 And e.password=?2");
		query.setParameter(1, userName);
		query.setParameter(2, password);

		Employee employee = (Employee) query.getResultList().get(0);
		if (employee != null) {
			return employee;
		}
		return null;
	}

	// find user
	public static Employee employeeRecord(String userName, String password) {
		Query query = Helper.entityManager.createQuery(
				"select e from Employee e where e.employeeName='" + userName + "' and e.password='" + password + "'");
		Employee employee = (Employee) query.getResultList().get(0);
		return employee;
	}
}
