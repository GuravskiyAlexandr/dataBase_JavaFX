package sample.fx.adminTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.data_baseEmployee.entity.Employee;
import sample.data_baseEmployee.service.EmployeeService;
import sample.data_baseEmployee.service.EmployeeServiceImpl;
import sample.data_baseUser.entity.User;
import sample.data_baseUser.service.UserService;
import sample.data_baseUser.service.UserServiceImpl;


import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.List;

public class Controller implements Initializable {
    private  AddUserAccountController addUserAccountController;
    @FXML private TextField idAdd;
    @FXML private TextField nameAdd;
    @FXML private TextField surnameAdd;
    @FXML private TextField ageAdd;
    @FXML private TextField employeeDateAdd;
    @FXML private TextField positionAdd;

    @FXML private TableView<Person> personTableView;
    @FXML private TableColumn<Person, Long> idPerson;
    @FXML private TableColumn<Person, String> name;
    @FXML private TableColumn<Person, String> surName;
    @FXML private TableColumn<Person, Integer> age;
    @FXML private TableColumn<Person, String> employeeDate;
    @FXML private TableColumn<Person, String> position;

    @FXML private TableView<Account> userTableView;
    @FXML private TableColumn<Account, Long> idUser;
    @FXML private TableColumn<Account, String> login;
    @FXML private TableColumn<Account, String> password;
    @FXML private TableColumn<Account, String> rale;

    private Connection connection;
    private   String url = "jdbc:mysql://localhost:3306/my_db";
    private   String log  = "root";
    private   String passvord = "root";


    private ObservableList<Person> personList = FXCollections.observableArrayList();
    private ObservableList<Account> userList = FXCollections.observableArrayList();
    private EmployeeService employeeService;
    private UserService userService;
    private int count;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idPerson.setCellValueFactory(new PropertyValueFactory<Person, Long>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        surName.setCellValueFactory(new PropertyValueFactory<Person, String>("surName"));
        age.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
        employeeDate.setCellValueFactory(new PropertyValueFactory<Person, String>("employeeDate"));
        position.setCellValueFactory(new PropertyValueFactory<Person, String>("position"));
        personTableView.setItems(personList);

        idUser.setCellValueFactory(new PropertyValueFactory<Account, Long>("id"));
        login.setCellValueFactory(new PropertyValueFactory<Account, String>("login"));
        password.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
        rale.setCellValueFactory(new PropertyValueFactory<Account, String>("rale"));
        userTableView.setItems(userList);


    }
    @FXML
    public void add_Person(ActionEvent actionEvent) throws SQLException {
        AddEmployeeController addEmployee = new AddEmployeeController();
        addEmployee.appendModalWind(actionEvent);

    }


    public void show(ActionEvent event) throws SQLException {


        if (1 != (personTableView.getItems().hashCode())) {
            ObservableList<Person> people = personTableView.getItems();
            ObservableList<Account>accounts = userTableView.getItems();
            people.removeAll(people);
            accounts.removeAll(accounts);
        }

            EmployeeService service = new EmployeeServiceImpl();
            List<Employee> employe = service.getAll();

            for (Employee emp : employe) {
                personList.addAll(new Person(emp.getEmployeeID(),
                        emp.getName(), emp.getSurName(), emp.getAge(), String.valueOf(emp.getEmployeeDate()), emp.getPosition()));
            }

            userService = new UserServiceImpl();
            List<User>users = userService.getAll();
             for (User user:users) {
                 userList.addAll(new Account(user.userId, user.getLogin(), user.getPassword(), user.role));
            }

    }
    public void delete(ActionEvent event) throws SQLException {

        if (!personTableView.getSelectionModel().isEmpty()) {

            Long id = personTableView.getSelectionModel().getSelectedItem().getId();

            employeeService = new EmployeeServiceImpl();
            employeeService.delete(id);
            userService = new UserServiceImpl();
            userService.delete(id);
            show(event);


        }

    }
    @FXML
    public void account(ActionEvent event){
         count = 0;
        if (!personTableView.getSelectionModel().isEmpty()) {


            Long id = personTableView.getSelectionModel().getSelectedItem().getId();
            userService = new UserServiceImpl();

            List<User> users =  userService.getAllId();
            for ( Object user:users) {
                if (id == user){
                    count++;
                }

            }
            if (count == 0){
                addUserAccountController = new AddUserAccountController(count, id);
                addUserAccountController.addAccount(event);

            }else {
                addUserAccountController = new AddUserAccountController(count, id);
                addUserAccountController.addAccount(event);
            }


        }

    }


    public void deleteAccount(ActionEvent event) throws SQLException {

        if (!personTableView.getSelectionModel().isEmpty()) {

            Long id = personTableView.getSelectionModel().getSelectedItem().getId();
            userService = new UserServiceImpl();
            userService.delete(id);

            employeeService = new EmployeeServiceImpl();
            Employee employee = employeeService.read(id);
            employee.setPosition("Slave");
            employeeService.update(employee);
            show(event);

        }
    }
}
