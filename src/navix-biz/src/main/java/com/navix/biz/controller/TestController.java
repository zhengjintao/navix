package com.navix.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.navix.biz.entity.Student;
import com.navix.biz.service.StudentService;

@Controller
public class TestController {
	 //@Autowired  
	 private StudentService studentService;  

	@RequestMapping("/helloWorld")
    public String helloWorld(Model model) {
		Student stu = studentService.getStudent("1234");
        System.out.println(stu.getName());
        return "helloworld";
    }
}
