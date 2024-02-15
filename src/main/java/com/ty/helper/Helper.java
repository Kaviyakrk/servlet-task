package com.ty.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.Employee;
import com.ty.dto.Task;

public class Helper {
	
	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	public static	EntityManager entityManager = entityManagerFactory.createEntityManager();
	public static	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	public static void Update(Employee employee,Task task) {
		Helper.entityTransaction.begin();
		Helper.entityManager.merge(employee);
		Helper.entityManager.merge(task);
		Helper.entityTransaction.commit();
	}
}
