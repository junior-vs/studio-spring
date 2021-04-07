package tech.vsj.study.springsecuritydemo.controllers;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vsj.study.springsecuritydemo.model.Students;

@RestController
@RequestMapping("/api/v1/students")
public class StudentsController {

  private static final List<Students> STUDENTS = Arrays.asList(new Students(1, "James Bond"),
      new Students(2, "Maria Jones"), new Students(3, "Ana Smith"));

  @GetMapping("/{studendsID}")
  public Students requestMethodName(@PathVariable Integer studendsID) {
    return STUDENTS.stream().filter(stu -> studendsID.equals(stu.getId())).findFirst()
        .orElseThrow(() -> new IllegalStateException("Student not found"));
  }


}
