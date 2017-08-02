package sample.fx.adminTable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Alexsandr on 12.07.2017.
 */
public class Person {
    private final SimpleLongProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty surName;
    private final SimpleIntegerProperty age;
    private final SimpleStringProperty employeeDate;
    private final SimpleStringProperty position;

    public Person(Long id, String name, String surname, Integer age, String employeeDate, String position) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.surName = new SimpleStringProperty(surname);
        this.age = new SimpleIntegerProperty(age);
        this.employeeDate =new  SimpleStringProperty(employeeDate);
        this.position = new SimpleStringProperty(position);
    }


    public SimpleLongProperty idProperty() {
        return id;
    }
    public Long getId(){
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty surNameProperty() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName.set(surName);
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getEmployeeDate() {
        return employeeDate.get();
    }

    public SimpleStringProperty employeeDateProperty() {
        return employeeDate;
    }

    public void setEmployeeDate(String employeeDate) {
        this.employeeDate.set(employeeDate);
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getName() {
        return name.get();
    }

    public String getSurName() {
        return surName.get();
    }

    public int getAge() {
        return age.get();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name=" + name +
                ", surName=" + surName +
                ", age=" + age +
                ", employeeDate=" + employeeDate +
                ", position=" + position +
                '}';
    }
}
