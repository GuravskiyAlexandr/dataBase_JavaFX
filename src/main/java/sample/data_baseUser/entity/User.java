package sample.data_baseUser.entity;

import javax.persistence.*;

/**
 * Created by Alexsandr on 30.07.2017.
 */
@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "ID")
    public Long userId;

    @Column(name = "LOGIN")
    public String login;

    @Column(name = "PASSWORD")
    public String password;

    @Column(name = "RALE")
    public String role;

    public User(Long id, String login, String password, String role) {
        this.userId = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
