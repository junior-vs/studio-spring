package tech.vsj.study.springsecuritydemo.controllers;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vsj.study.springsecuritydemo.model.Student;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {
  
  
  private static final Logger loggger = LoggerFactory.getLogger(StudentManagementController.class);

  private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "James Bond"),
      new Student(2, "Maria Jones"), new Student(3, "Anna Smith"));

  // hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

  @GetMapping
 // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
  public List<Student> getAllStudents() {
    loggger.info("getAllStudents");
    return STUDENTS;
  }
  
  @GetMapping("/{studendsID}")
  public Student requestMethodName(@PathVariable Integer studendsID) {
    return STUDENTS.stream().filter(stu -> studendsID.equals(stu.getId())).findFirst()
        .orElseThrow(() -> new IllegalStateException("Student not found"));
  }
  


  @PostMapping
 // @PreAuthorize("hasAuthority('student:write')")
  public void registerNewStudent(@RequestBody Student student) {
    loggger.info("registerNewStudent");
    loggger.info(student.toString());
  }

  @DeleteMapping(path = "{studentId}")
//  @PreAuthorize("hasAuthority('student:write')")
  public void deleteStudent(@PathVariable("studentId") Integer studentId) {
    loggger.info("deleteStudent");
    loggger.info(studentId.toString());
  }

  @PutMapping(path = "{studentId}")
//  @PreAuthorize("hasAuthority('student:write')")
  public void updateStudent(@PathVariable("studentId") Integer studentId,
      @RequestBody Student student) {
    loggger.info("updateStudent");
    loggger.info(String.format("%s %s", studentId, student));
  }
}
