package sample.data_baseUser.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sample.data_baseUser.entity.User;
import sample.data_baseUser.util.HibernateUtil;

import java.util.List;

/**
 * Created by Alexsandr on 30.07.2017.
 */
public class UserDAOImpl implements UserDAO {
    private SessionFactory factory;

    public UserDAOImpl() {
        factory = HibernateUtil.getFactory();
    }

    @Override
    public Long create(User user) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
             Long id = (Long) session.save(user);
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
    public User read(Long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        User user  = session.get(User.class, id);
        return user;
    }

    @Override
    public boolean update(User update) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "update User set " +
                            "login = :login," +
                            "password = :password, role = :role where userId = :id");

            query.setParameter("login", update.getLogin());
            query.setParameter("password", update.getPassword());
            query.setParameter("role", update.getRole());
            query.setParameter("id", update.getUserId());

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
                    "delete from User where id = :id"
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
    public List<User> getAll() {
        return factory
                .openSession()
                .createQuery("from User ")
                .list();
    }

    @Override
    public List<User> getAllId() {
        return factory
                .openSession()
                .createQuery("select userId from User ")
                .list();
    }

    @Override
    public List<User> getLogin() {
        return factory.openSession().createQuery("select login from User ").list();
    }

    @Override
    public List<User> getPasswood() {
        return factory.openSession().createQuery("select password from User ").list();
    }

    @Override
    public List<User> getRale() {
        return factory.openSession().createQuery("select role from User ").list();
    }
}
