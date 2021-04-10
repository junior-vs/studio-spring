package tech.vsj.study.springsecuritydemo.controllers;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vsj.study.springsecuritydemo.model.Student;
/**
 * 
 * @author Valdir Junior
 *
 */
@RestController
@RequestMapping("/api/v1/students")
public class StudentsController {

  private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "James Bond"),
      new Student(2, "Maria Jones"), new Student(3, "Ana Smith"));

  /*
   * teste
   */
  @GetMapping("/{studendsID}")
  public Student requestMethodName(@PathVariable Integer studendsID) {
    return STUDENTS.stream().filter(stu -> studendsID.equals(stu.getId())).findFirst()
        .orElseThrow(() -> new IllegalStateException("Student not found"));
  }
  
  


}
