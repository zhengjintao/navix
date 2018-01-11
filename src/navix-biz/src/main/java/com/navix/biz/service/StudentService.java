package com.navix.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navix.biz.dao.StudentDao;
import com.navix.biz.entity.Student;

@Service
public class StudentService {
	@Autowired
    private StudentDao studentDao;
   
    public void save(Student student) {
       studentDao.save(student);
    }
   
    public Student getStudent(String id) {
       Student student =studentDao.getStudent(id);
       return student;
    }
}
