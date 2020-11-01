package com.student.demo.service;

import com.student.demo.exeptions.ApiEmailExeption;
import com.student.demo.exeptions.ApiRequstExeption;
import com.student.demo.service.component.EmailValidator;
import com.student.demo.student.Student;
import com.student.demo.student.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

import static java.util.Optional.*;

@Service
public class StudentServiceImp implements StudentService{

    private final StudentDataAccessService dataAccessService;
    private final EmailValidator emailValidator;

    @Autowired
    public StudentServiceImp(StudentDataAccessService dataAccessService,
                             EmailValidator emailValidator) {
        this.dataAccessService = dataAccessService;
        this.emailValidator = emailValidator;
    }

    @Override
    public List<Student> getAllStudent() {
       return ofNullable(dataAccessService.selectAllStudents())
               .orElseThrow(() -> new ApiRequstExeption("Opps something wrong"));
    }

    @Override
    public List<Student> offsetStudents(int start, int limit) {
        return ofNullable(dataAccessService.offsetStudents(start, limit))
                .orElseThrow(() -> new ApiRequstExeption("We wasn't found any student"));
    }

    @Override
    public void addNewStudent(Student student) {

        UUID newStudnetId = ofNullable(student.getStudentId()).orElse(UUID.randomUUID());
        if (!emailValidator.test(student.getEmail())) {
            throw new ApiEmailExeption(student.getEmail() + " is not valid");
        }
        if (dataAccessService.isEmailTaken(student.getEmail())) {
            throw new ApiEmailExeption(student.getEmail() + " is taken");
        }
        dataAccessService.insertNewStudent(newStudnetId, student);
    }

    @Override
    public List<StudentCourse> getAllStudentCourse(UUID studentId) {
        return dataAccessService.selectAllStudentCourse(studentId);
    }

    @Override
    public void deleteStudent(UUID studentId) {
        dataAccessService.deleteStudent(studentId);
    }

    @Override
    public void updateStudent(UUID studentId, Student student) {

        ofNullable(student.getFirstName())
                .filter(firstName -> !firstName.isBlank() && !firstName.isEmpty())
                .map(StringUtils::capitalize)
                .ifPresent(firstName -> dataAccessService.updateFirstName(studentId, firstName));

        ofNullable(student.getLastName())
                .filter(lastName -> !lastName.isBlank() && !lastName.isEmpty())
                .map(StringUtils::capitalize)
                .ifPresent(lastName -> dataAccessService.updateLastName(studentId, lastName));

        ofNullable(student.getEmail())
                .filter(email -> !email.isEmpty() && !email.isBlank())
                .ifPresent(email -> {
                    boolean isTaken = dataAccessService.selectExistsEmail(studentId, email);
                    if (!emailValidator.test(email)) {
                        throw new ApiEmailExeption(student.getEmail() + " is not valid");
                    }
                    if (!isTaken) {

                        dataAccessService.updateEmail(studentId, student.getEmail());
                    } else {
                        throw new ApiEmailExeption("Email already in use: " + email);
                    }
                });

        ofNullable(student.getGender())
                .filter(gender -> gender.name().toUpperCase().equals("MALE") || gender.name().toUpperCase().equals("FEMALE"))
                .ifPresent(gender -> dataAccessService.updateGender(studentId, gender.name().toUpperCase()));

    }

    @Override
    public int countStudents() {
        return dataAccessService.countStudents();
    }
}
