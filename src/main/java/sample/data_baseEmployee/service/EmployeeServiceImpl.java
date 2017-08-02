package sample.data_baseEmployee.service;


import sample.data_baseEmployee.dao.EmployeeDAO;
import sample.data_baseEmployee.dao.EmployeeDAOImpl;
import sample.data_baseEmployee.entity.Employee;


import java.util.List;

/**
 * Created by IEvgen Boldyr on 21.07.17.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO dao;

    public EmployeeServiceImpl() {
        dao = new EmployeeDAOImpl();
    }

    @Override
    public void create(Employee employee) {
        if (employee != null) {
            employee.setEmployeeID(dao.create(employee));
        }
    }

    @Override
    public Employee read(Long id) {

        return dao.read(id);
    }

    @Override
    public boolean update(Employee update) {
        dao.update(update);
        return true;
    }

    @Override
    public boolean delete(Long delete) {
        return dao.delete(delete);
    }

    @Override
    public List<Employee> getAll() {
        return dao.getAll();

    }

    @Override
    public List<Employee> getByName(String name) {
        return dao.getByName(name);
    }


    @Override
    public List<Employee> getNameAndSurname() {
        return dao.getNameAndSurname();
    }
}
