package sample.data_baseUser;

import sample.data_baseUser.dao.UserDAO;
import sample.data_baseUser.dao.UserDAOImpl;
import sample.data_baseUser.entity.User;
import sample.data_baseUser.service.UserService;
import sample.data_baseUser.service.UserServiceImpl;
import sample.data_baseUser.util.HibernateUtil;


import java.util.List;

/**
 * Created by Alexsandr on 30.07.2017.
 */
public class Test {
    public static void main(String[] args) {

        UserService userServiceDAO = new UserServiceImpl();

//        userDAO.create(new User(111l, "ggggggg", "hhh", "ssggg"));
//        userDAO.create(new User(4002l, "ggggggg", "hhh", "kkkkk"));



        List<User> users = userServiceDAO.getAll();
        for (User user:users) {
            System.out.println(user);
        }

//        User user = userDAO1.read(9l);
//        user.setLogin("dddddddd");
//        userDAO1.update(user);

        User user = userServiceDAO.read(8l);
        userServiceDAO.delete(0l);


        users = userServiceDAO.getAll();
        for (User useri:users) {
            System.out.println(useri);
        }

        HibernateUtil.getFactory().close();
    }
}
