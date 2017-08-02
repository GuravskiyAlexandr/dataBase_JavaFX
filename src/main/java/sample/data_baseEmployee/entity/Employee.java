package sample.data_baseEmployee.entity;





import javax.persistence.*;
import java.util.Date;

/**
 * Created by IEvgen Boldyr on 21.07.17.
 */

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long EmployeeID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surName;

    @Column(name = "EMPLOYEEDATE")
    @Temporal(TemporalType.DATE)
    private Date employeeDate;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "POSITION")
    private String position;

    public Employee() {
    }

    public Employee(String name, String surname, Date employeeDate, Integer age, String position) {
        this.name = name;
        this.surName = surname;
        this.employeeDate = employeeDate;
        this.age = age;
        this.position = position;
    }

    public Long getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(Long employeeID) {
        EmployeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Date getEmployeeDate() {
        return employeeDate;
    }

    public void setEmployeeDate(Date employeeDate) {
        this.employeeDate = employeeDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeID=" + EmployeeID +
                ", name='" + name + '\'' +
                ", surname='" + surName + '\'' +
                ", employeeDate=" + employeeDate +
                ", age=" + age +
                ", position='" + position + '\'' +
                '}';
    }

}