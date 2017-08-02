package sample.data_baseEmployee.dao;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sample.data_baseEmployee.entity.Employee;
import sample.data_baseEmployee.util.HibernateUtil;
import sample.data_baseUser.entity.User;

import java.util.List;

/**
 * Created by IEvgen Boldyr on 21.07.17.
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private SessionFactory factory;

    public EmployeeDAOImpl() {
        factory = HibernateUtil.getFactory();
    }

    @Override
    public Long create(Employee human) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Long id = (Long) session.save(human);
            session.getTransaction().commit();
            session.close();
            return id;
        }catch (HibernateException ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Employee read(Long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        Employee employee  = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public boolean update(Employee update) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "update Employee set " +
                            "name = :name," +
                            "surName = :surname, position = :position where id = :id");

            query.setParameter("name", update.getName());
            query.setParameter("surname", update.getSurName());
            query.setParameter("id", update.getEmployeeID());
            query.setParameter("position", update.getPosition());

            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        }catch (HibernateException ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Long delete) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "delete from Employee where id = :id"
            );
            query.setParameter("id", delete);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        }catch (HibernateException ex){
            ex.printStackTrace();
            session.beginTransaction().rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Employee> getAll() {
        return factory
                .openSession()
                .createQuery("from Employee")
                .list();
    }

    @Override
    public List<Employee> getByName(String name) {
        return factory
                .openSession()
                .createQuery("from Employee where name = :name")
                .setParameter("name", name)
                .list();
    }

    @Override
    public List<Employee> getNameAndSurname() {
        return factory
                .openSession()
                .createQuery(" select name, surName  from Employee")
                .list();
    }
}
