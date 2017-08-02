package sample.data_baseUser.service;

import sample.data_baseUser.dao.UserDAO;
import sample.data_baseUser.dao.UserDAOImpl;
import sample.data_baseUser.entity.User;

import java.util.List;

/**
 * Created by Alexsandr on 30.07.2017.
 */
public class UserServiceImpl implements UserService {
    UserDAO userDAO;

    public UserServiceImpl(){
        userDAO = new UserDAOImpl();
    }



    @Override
    public Long create(User user) {
        return userDAO.create(user);
    }

    @Override
    public User read(Long id) {

        return userDAO.read(id);
    }

    @Override
    public boolean update(User update) {
        return userDAO.update(update);
    }

    @Override
    public boolean delete(Long delete) {
        System.out.println("aaaaaaaaaaaaa");
        System.out.println(delete);
        if (delete != null) {
            System.out.println("bbbbbbbbbbb");
            return userDAO.delete(delete);
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public List<User> getAllId() {
        return userDAO.getAllId();
    }

    @Override
    public List<User> getLogin() {
        return userDAO.getLogin();
    }

    @Override
    public List<User> getPasswood() {
        return userDAO.getPasswood();
    }

    @Override
    public List<User> getRale() {
        return userDAO.getRale();
    }
}
