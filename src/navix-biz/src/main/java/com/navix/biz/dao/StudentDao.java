package com.navix.biz.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.navix.biz.entity.Student;

public interface StudentDao {
	public void save(Student student);
	public Student getStudent(String id);
}
