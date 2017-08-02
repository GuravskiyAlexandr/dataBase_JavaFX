package sample.data_baseEmployee.service;



import sample.data_baseEmployee.entity.Employee;

import java.util.List;

/**
 * Created by IEvgen Boldyr on 21.07.17.
 */
public interface EmployeeService {

    void create(Employee human);

    Employee read(Long id);

    boolean update(Employee update);

    boolean delete(Long delete);

    List<Employee> getAll();

    List<Employee> getByName(String name);

    List<Employee> getNameAndSurname();
}
