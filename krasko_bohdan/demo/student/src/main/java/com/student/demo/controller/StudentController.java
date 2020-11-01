package com.student.demo.controller;

import com.student.demo.service.StudentService;
import com.student.demo.student.Student;
import com.student.demo.student.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;
    
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping(path = "{studentId}/course")
    public List<StudentCourse> getAllStudentCourse(@PathVariable("studentId") UUID studentId) {
        return studentService.getAllStudentCourse(studentId);
    }
    @GetMapping(path = "{start}/{limit}")
    public List<Student> offsetStudents(@PathVariable("start") int start, @PathVariable("limit") int limit) {
        return studentService.offsetStudents(start, limit);
    }
    @PostMapping
    public void addNewStudent(@RequestBody @Valid Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") UUID studentId) {
         studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") UUID studentId, @RequestBody Student student) {
        studentService.updateStudent(studentId, student);
    }

    @GetMapping("count")
    int countStudents() {
        return studentService.countStudents();
    }

}
