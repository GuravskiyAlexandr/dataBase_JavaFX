package sample.data_baseUser.service;

import sample.data_baseUser.entity.User;

import java.security.acl.LastOwnerException;
import java.util.List;

/**
 * Created by Alexsandr on 30.07.2017.
 */
public interface UserService {
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