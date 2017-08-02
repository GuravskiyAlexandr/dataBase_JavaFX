package sample.fx.adminTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.data_baseEmployee.entity.Employee;
import sample.data_baseEmployee.service.EmployeeService;
import sample.data_baseEmployee.service.EmployeeServiceImpl;
import sample.data_baseUser.entity.User;
import sample.data_baseUser.service.UserServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Alexsandr on 01.08.2017.
 */
public class ManagerController implements Initializable {

    @FXML
    private TableView<Person> personTableView;
    @FXML private TableColumn<Person, Long> idPerson;
    @FXML private TableColumn<Person, String> name;
    @FXML private TableColumn<Person, String> surName;
    @FXML private TableColumn<Person, Integer> age;
    @FXML private TableColumn<Person, String> employeeDate;
    @FXML private TableColumn<Person, String> position;

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/start/ManagerTable'.fxml"));
        primaryStage.setTitle("Hello");
        primaryStage.setScene(new Scene(root, 720, 450));
        primaryStage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idPerson.setCellValueFactory(new PropertyValueFactory<Person, Long>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        surName.setCellValueFactory(new PropertyValueFactory<Person, String>("surName"));
        age.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
        employeeDate.setCellValueFactory(new PropertyValueFactory<Person, String>("employeeDate"));
        position.setCellValueFactory(new PropertyValueFactory<Person, String>("position"));
        personTableView.setItems(personList);
        try {ActionEvent event = new ActionEvent();
            show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void show() throws SQLException {


        if (1 != (personTableView.getItems().hashCode())) {
            ObservableList<Person> people = personTableView.getItems();
            people.removeAll(people);
        }

        EmployeeService service = new EmployeeServiceImpl();
        List<Employee> employe = service.getAll();

        for (Employee emp : employe) {
            personList.addAll(new Person(emp.getEmployeeID(),
                    emp.getName(), emp.getSurName(), emp.getAge(), String.valueOf(emp.getEmployeeDate()), emp.getPosition()));
        }
    }
}
