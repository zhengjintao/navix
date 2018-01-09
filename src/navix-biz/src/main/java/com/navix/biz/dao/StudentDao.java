package com.navix.biz.dao;

import com.navix.biz.entity.Student;

public interface StudentDao {
	public void save(Student student);
	public Student getStudent(String id);
}
