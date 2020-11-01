package com.student.demo.service;

import com.student.demo.student.Student;
import com.student.demo.student.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StudentDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Student> selectAllStudents() {
        String sql = "" +
                "SELECT student_id, " +
                "first_name, " +
                "last_name, " +
                "email, " +
                "gender " +
                "FROM student";
        return jdbcTemplate.query(sql, mapStudentFromDb());
    }

    List<StudentCourse> selectAllStudentCourse(UUID studentId) {
        String sql = "" +
                "SELECT * " +
                "FROM student " +
                "JOIN student_course USING(student_id) " +
                "JOIN course USING(course_id) " +
                "WHERE student.student_id = ?";
        return jdbcTemplate.query(
                sql,
                new Object[] {studentId},
                getStudentCourseRowMapper());
    }

    @SuppressWarnings("ConstantConditions")
    boolean isEmailTaken(String email) {
        String sql = "" +
                "SELECT EXISTS (" +
                "SELECT 1 " +
                "FROM student " +
                "WHERE email = ?" +
                ")";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{email},
                (resultSet, i) -> resultSet.getBoolean(1));

    }
    @SuppressWarnings("ConstantConditions")
    public boolean selectExistsEmail(UUID studentId, String email) {
        String sql = "" +
                "SELECT EXISTS ( " +
                "   SELECT 1 " +
                "   FROM student " +
                "   WHERE student_id != ? " +
                "    AND email = ? " +
                ")";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{studentId, email},
                (resultSet, columnIndex) -> resultSet.getBoolean(1)
        );
    }
    void insertNewStudent(UUID studnetId, Student student) {
        String sql = "" +
                "INSERT INTO student (student_id, first_name, last_name, email, gender) " +
                "VALUES (?,?,?,?,?::gender)";
        jdbcTemplate.update(
                sql,
                studnetId,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGender().name().toUpperCase()
        );
    }

    void deleteStudent(UUID studentId) {
        String sql = "" +
                "DELETE FROM student " +
                "WHERE student_id = ?";
        jdbcTemplate.update(sql, studentId);
    }
    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) ->
                new Student(
                    UUID.fromString(resultSet.getString("student_id")),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    Student.Gender.valueOf(resultSet.getString("gender").toUpperCase()));
    }
    void updateFirstName(UUID studentId, String firstName) {
        String sql = "" +
                "UPDATE student " +
                "SET first_name = ? " +
                "WHERE student_id = ?";
        jdbcTemplate.update(
                sql,
                firstName,
                studentId);
    }
    void updateLastName(UUID studentId, String lastName) {
        String sql = "" +
                "UPDATE student " +
                "SET last_name = ? " +
                "WHERE student_id = ?";
        jdbcTemplate.update(
                sql,
                lastName,
                studentId);
    }
    void updateEmail(UUID studentId, String email) {
        String sql = "" +
                "UPDATE student " +
                "SET email = ? " +
                "WHERE student_id = ?";
        jdbcTemplate.update(
                sql,
                email,
                studentId);
    }
    void updateGender(UUID studentId, String gender) {
        String sql = "" +
                "UPDATE student " +
                "SET gender = ?::gender " +
                "WHERE student_id = ?";
        jdbcTemplate.update(
                sql,
                gender.toUpperCase(),
                studentId);
    }

    private RowMapper<StudentCourse> getStudentCourseRowMapper() {
        return (resultSet, i) ->
                new StudentCourse(
                    UUID.fromString(resultSet.getString("student_id")),
                    UUID.fromString(resultSet.getString("course_id")),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("department"),
                    resultSet.getString("teacher_name"),
                    resultSet.getDate("start_date").toLocalDate(),
                    resultSet.getDate("end_date").toLocalDate(),
                        Optional.ofNullable(resultSet.getString("grade"))
                                .map(Integer::parseInt)
                                .orElse(null));
    }
    List<Student> offsetStudents(int start, int limit) {
//        SELECT * FROM student OFFSET 5 LIMIT 5;
        String sql = "" +
                "SELECT * FROM student " +
                "OFFSET ? LIMIT ?";
        return jdbcTemplate.query(
                sql,
                new Object[] {start, limit},
                mapStudentFromDb());
    }
    @SuppressWarnings("ConstantConditions")
    int countStudents() {
        String sql = "SELECT COUNT(student_id) FROM student";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> resultSet.getInt(1));
    }
}
