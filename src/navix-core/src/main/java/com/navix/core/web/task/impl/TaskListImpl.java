package com.navix.core.web.task.impl;

import java.util.List;

import com.navix.core.web.task.ServletInitJobInter;
import com.navix.core.web.task.TaskListInter;

public class TaskListImpl implements TaskListInter {
	private List<ServletInitJobInter> tasks;

	@Override
	public List<ServletInitJobInter> getTasks() {
		return tasks;
	}

	public void setTasks(List<ServletInitJobInter> tasks) {
		this.tasks = tasks;
	}

}
