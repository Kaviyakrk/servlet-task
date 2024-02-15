package com.ty.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "task")
	@SequenceGenerator(name = "task", initialValue = 1, allocationSize = 1, sequenceName = "task_sequence")
	private int taskId;
	private String description;
	private String status;
	@CreationTimestamp
	private LocalDateTime assignedTime;
	@UpdateTimestamp
	private LocalDateTime completedTime;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getAssignedTime() {
		return assignedTime;
	}

	public void setAssignedTime(LocalDateTime assignedTime) {
		this.assignedTime = assignedTime;
	}

	public LocalDateTime getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(LocalDateTime completedTime) {
		this.completedTime = completedTime;
	}
}
