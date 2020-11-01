package com.student.demo.service;

import com.student.demo.student.Student;
import com.student.demo.student.StudentCourse;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    List<Student> getAllStudent();

    List<Student> offsetStudents(int start, int limit);

    void addNewStudent(Student student);

    List<StudentCourse> getAllStudentCourse(UUID studentId);

    void deleteStudent(UUID studentId);

    void updateStudent(UUID studentId, Student student);

    int countStudents();

}
