package tech.vsj.study.springsecuritydemo.model;

public class Student {

  private Integer id;
  private String name;

  public Student(Integer id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public final Integer getId() {
    return id;
  }

  public final String getName() {
    return name;
  }

  @Override
  public String toString() {
    return String.format("Student [id=%s, name=%s]", id, name);
  }



}
