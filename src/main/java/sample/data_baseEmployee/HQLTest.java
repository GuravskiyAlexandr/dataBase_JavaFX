package sample.data_baseEmployee;




import sample.data_baseEmployee.dao.EmployeeDAO;
import sample.data_baseEmployee.dao.EmployeeDAOImpl;
import sample.data_baseEmployee.entity.Employee;
import sample.data_baseEmployee.service.EmployeeService;
import sample.data_baseEmployee.service.EmployeeServiceImpl;
import sample.data_baseEmployee.util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by IEvgen Boldyr on 21.07.17.
 */
public class HQLTest {

    public static void main(String[] args) throws ParseException {
//        Session session = HibernateUtil
//                .getFactory()
//                .openSession();

        SimpleDateFormat fm = new SimpleDateFormat("dd.MM.yyyy");

        Employee hm1 = new Employee("Ivan", "Ivanov", fm.parse("10.01.1990"), 27,"slave");
        Employee hm2 = new Employee("Petr", "Petrov", fm.parse("17.08.1990"), 27, "slave");
        Employee hm3 = new Employee("Vasilij", "Petrov", fm.parse("16.04.1990"), 27, "slave");
        Employee hm4 = new Employee("Ivan", "Vasiliev", fm.parse("16.04.1990"), 27, "Administrator");

        EmployeeService service = new EmployeeServiceImpl();
        service.create(hm1);
        service.create(hm2);
        service.create(hm3);
        service.create(hm4);

        List<Employee> employees = service.getAll();
        for (Employee human : employees) {
            System.out.println(human);
        }

        employees = service.getByName("Ivan");
        for (Employee emp : employees) {
            System.out.println(emp);
        }


        List<Employee> employees1 =service.getNameAndSurname();
        for (Object emp : employees1) {
            System.out.println( emp);
        }

//        hm1.setName("Kostya");
//        hm1.setSurName("Kostevich");
        Employee e = service.read(9l);
        e.setName("hhhhhhhh");
        service.update(e);

        //service.update(hm1);

        employees = service.getAll();
        for (Employee emp : employees) {
            System.out.println(emp);
        }

       Employee employee =  service.read( 16l);
        System.out.println(employee);
//        employee.setName("ssss");
//        service.update(employee);


        HibernateUtil.getFactory().close();
    }
}
