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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data_baseEmployee.entity.Employee;
import sample.data_baseEmployee.service.EmployeeService;
import sample.data_baseEmployee.service.EmployeeServiceImpl;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Alexsandr on 26.07.2017.
 */
public class AddEmployeeController implements Initializable {
    private static Stage stage;

    @FXML TreeView<String> treeView ;
   
    @FXML private TextField idAdd;
    @FXML private TextField nameAdd;
    @FXML private TextField surnameAdd;
    @FXML private TextField ageAdd;
   // @FXML private TextField employeeDateAdd;
    @FXML private TextField positionAdd;
    @FXML private Label labelException;
    private String name;
    private String surname;
    @FXML
    private String position;
    private String date;
    private  int age;
    private int proverka;

    public AddEmployeeController(String name, String surname, String position, int age , int proverka, Stage stage) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.age = age;
        this.proverka= proverka;
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public AddEmployeeController() {

    }

    public  void appendModalWind(ActionEvent event){

        try {

            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/start/addEmloyee.fxml"));
            stage.setTitle("Create staff");
            stage.setResizable(false);


            stage.setScene(new Scene(root,350, 500 ));
            stage.initModality(Modality.WINDOW_MODAL);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
            setStage(stage);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void checkSave(ActionEvent event)  {
        labelException.setText("");

        if (!nameAdd.getText().equals("") && !surnameAdd.getText().equals("")
                && !ageAdd.getText().equals("") && ! positionAdd.getText().equals("")) {

            try {
                proverka = Integer.parseInt(nameAdd.getText());
                labelException.setText(" имя должно состоять только из букв");

            }catch (Exception ex) {
                try {
                    proverka = Integer.parseInt(surnameAdd.getText());
                    labelException.setText(" фамилия должно состоять только из букв");

                }catch (Exception e) {
                   try {
                       proverka = Integer.parseInt(positionAdd.getText());

                   }catch (Exception x) {


//                labelException.setText(" имя и фамилия и позиция должно состоять только из букв");
//
//                nameAdd.setText("");
//                surnameAdd.setText("");
//                positionAdd.setText("");

                       int pm = 0;

                       try {

                           name = nameAdd.getText();
                       } catch (Exception exs) {
                           ex.printStackTrace();
                           labelException.setText(" имя должно состоять только из букв");
                           pm = 1;
                       }
                       try {
                           surname = surnameAdd.getText();
                       } catch (Exception exs) {
                           ex.printStackTrace();
                           labelException.setText(" имя должно состоять только из букв");
                           pm = 1;

                       }
                       try {
                           age = Integer.parseInt(ageAdd.getText());
                       } catch (Exception exs) {
                           ex.printStackTrace();
                           labelException.setText(" возраст указывать только цыфрами");
                           pm = 1;

                       }
                       try {
                           position = positionAdd.getText();
                       } catch (Exception exs) {
                           ex.printStackTrace();
                           labelException.setText(" фамилия должно состоять только из букв ");
                           pm = 1;

                       }
                       if (pm == 0) {
                           EmployeeService service = new EmployeeServiceImpl();
                           service.create(new Employee(name, surname, new Date(), age, position));
                           // getStage().close();
                           getStage().close();

                       }
                   }
                }
            }
        }else {
            labelException.setText(" Поля не могут быть пустыми ");
        }
    }
    public void treeViewShow(Event event){
          treeView.setOpacity(1);
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            treeView.setOpacity(0);
            if (newValue.getValue() != null) {
                positionAdd.setText(newValue.getValue());
            }
        });;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> root = new TreeItem<>();

        TreeItem<String> item1 = new TreeItem<>("Slave");
        TreeItem<String> item2 = new TreeItem<>("Manager");
//        TreeItem<String> item3 = new TreeItem<>("Administrator");

        root.getChildren().addAll(item1, item2);
        treeView.setStyle("-fx-color: red");
        treeView.setRoot(root);
    }

    public static void main(String[] args) {


    }
}
