package tech.vsj.study.springsecuritydemo.model;

public class Students {

  private Integer id;
  private String name;

  public Students(Integer id, String name) {
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



}
