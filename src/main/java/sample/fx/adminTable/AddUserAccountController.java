package sample.fx.adminTable;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data_baseEmployee.entity.Employee;
import sample.data_baseEmployee.service.EmployeeService;
import sample.data_baseEmployee.service.EmployeeServiceImpl;
import sample.data_baseUser.entity.User;
import sample.data_baseUser.service.UserService;
import sample.data_baseUser.service.UserServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Alexsandr on 30.07.2017.
 */
public class AddUserAccountController implements Initializable {
    private static Stage stage;
    @FXML private Label exceptionId;

    @FXML private TreeView<String> treeView;
    @FXML private TextField rale;
    @FXML private TextField login;
    @FXML private TextField password;
    @FXML Label createAccount;
    @FXML Label updateAccount;
    private static Long id;
    private static   int count;

    public AddUserAccountController(){

    }
    public AddUserAccountController(int count, Long id) {
        this.id = id;
        this.count = count;
    }

    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  int getCount() {
        return count;
    }

    public  void setCount(int count) {
        this.count = count;
    }

    public void addAccount(ActionEvent event) {
        try {

            stage = new Stage();
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/start/addAccount.fxml"));
            stage.setTitle("Create staff");
            stage.setResizable(false);
            stage.setScene(new Scene(root, 350, 470));
            stage.initModality(Modality.WINDOW_MODAL);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> root = new TreeItem<>();

        TreeItem<String> item2 = new TreeItem<>("Manager");
        TreeItem<String> item3 = new TreeItem<>("Administrator");

        root.getChildren().addAll(item2, item3);
        treeView.setStyle("-fx-color: red");
        treeView.setRoot(root);
        treeView.setOpacity(0);
        if (getCount() == 0){
            System.out.println("Create Account "+ getCount());
            createAccount.setText("Create Account");
            updateAccount.setText("");
            System.out.println("Create Account");
        }
        if (getCount() == 1){
            System.out.println("Updatet " + getCount());
            createAccount.setText("");
            updateAccount.setText("Update Account ID № "+ id );
            System.out.println("Update");
        }


    }

    @FXML
    public void treeViewShow(Event event) {
        System.out.println("1");
        treeView.setOpacity(1);
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("3");

            treeView.setOpacity(0);
            if (newValue.getValue() != null) {
                rale.setText(newValue.getValue());
            }
        });
        ;
    }

    public void done(ActionEvent event) {
        System.out.println(count);

        if (getCount() == 0) {
            if (!password.getText().equals("") && !login.getText().equals("") && !rale.getText().equals("")) {

                if (check(login.getText())) {
                    UserService userService = new UserServiceImpl();
                    userService.create(new User(id, login.getText(), password.getText(), rale.getText()));


                    EmployeeService service = new EmployeeServiceImpl();
                    Employee employee = service.read(id);
                    String s = rale.getText();
                    System.out.println(s);

                    employee.setPosition(s);
                    service.update(employee);
                    stage.close();
                }else {
                    exceptionId.setText(" Такой логин уже есть выбирите другой");

                }
            }else {
                exceptionId.setText(" Заполните все поля ");
            }
        } else {
            if (!password.getText().equals("") && !login.getText().equals("") && !rale.getText().equals("")) {
                UserService userService = new UserServiceImpl();
                User user = userService.read(id);
               // user.setLogin(login.getText());
               // user.setPassword(password.getText());
                user.setRole(rale.getText());
                userService.update(user);

                EmployeeService service = new EmployeeServiceImpl();
                Employee employee = service.read(id);
                String s = rale.getText();
                employee.setPosition(s);
                service.update(employee);
                stage.close();
            }else {
                exceptionId.setText(" Заполните все поля ");
            }


        }

    }

    private boolean check(String text) {
        UserService userService = new UserServiceImpl();
       List <User> users  = userService.getLogin();
       for (Object us :users ){
           if (us.equals(text)){
               return false;
           }
       }
       return true;
    }
}
