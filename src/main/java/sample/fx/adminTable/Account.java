package sample.fx.adminTable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Alexsandr on 31.07.2017.
 */
public class Account {
    private final SimpleLongProperty id;
    private final SimpleStringProperty login;
    private final SimpleStringProperty password;
    private final SimpleStringProperty rale;



    public Account(Long id, String login, String password, String role) {
        this.id = new SimpleLongProperty(id);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.rale = new SimpleStringProperty(role);

    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getRale() {
        return rale.get();
    }

    public SimpleStringProperty raleProperty() {
        return rale;
    }

    public void setRale(String rale) {
        this.rale.set(rale);
    }
}
