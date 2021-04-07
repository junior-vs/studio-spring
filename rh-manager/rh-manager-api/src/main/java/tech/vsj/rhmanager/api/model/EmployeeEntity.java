package tech.vsj.rhmanager.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class EmployeeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_ID_SEQ")
    @SequenceGenerator(allocationSize = 1, name = "EMPLOYEE_ID_SEQ", sequenceName = "EMPLOYEE_ID_SEQ" )
    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private String phone;
    private String imgURL;
    private String employeeCode;

    public EmployeeEntity(Long id, String name, String email, String jobTitle, String phone,
            String imgURL, String employeeCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.imgURL = imgURL;
        this.employeeCode = employeeCode;
    }

    public EmployeeEntity() {
    }

    @Override
    public String toString() {
        return String.format(
                "EmployeeEntity [email=%s, employeeCode=%s, id=%s, imgURL=%s, jobTitle=%s, name=%s, phone=%s]",
                email, employeeCode, id, imgURL, jobTitle, name, phone);
    }

    


}

