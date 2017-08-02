package sample.data_baseUser.dao;

import sample.data_baseUser.entity.User;

import java.util.List;

/**
 * Created by Alexsandr on 30.07.2017.
 */
public interface UserDAO {
    Long create(User user);

    User read(Long id);

    boolean update(User update);

    boolean delete(Long delete);

    List<User> getAll();

    List<User> getAllId();

    List<User> getLogin();

    List<User> getPasswood();

    List<User> getRale();


}