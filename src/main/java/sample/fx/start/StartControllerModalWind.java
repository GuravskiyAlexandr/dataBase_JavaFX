package sample.fx.start;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.data_baseUser.entity.User;
import sample.data_baseUser.service.UserService;
import sample.data_baseUser.service.UserServiceImpl;
import sample.fx.adminTable.Main;
import sample.fx.adminTable.ManagerController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alexsandr on 25.07.2017.
 */
public class StartControllerModalWind {
    private final String ADMIN = "a";//"ADMIN";
    private final String PASSWORD = "a";// "admin";

    @FXML private PasswordField password;
    @FXML private TextField login;
    @FXML private Label exception;
    static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }



    public void modalWInd(ActionEvent event) throws IOException {

        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/start/singUp.fxml"));
            stage.setTitle("SIN UP");
            stage.setResizable(false);
            stage.setScene(new Scene(root, 332, 330));
            stage.initModality(Modality.WINDOW_MODAL);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
            setStage(stage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void enter(ActionEvent event) throws Exception {
        exception.setText("");
        if (login.getText().equals(ADMIN) && password.getText().equals(PASSWORD)) {
            getStage().close();
            Starting.getStage().close();
            Main main = new Main();
            stage = new Stage();
            main.start(stage);
        } else {
            UserService userService = new UserServiceImpl();
            HashMap<String, String> accaunts = new HashMap<>();
            HashMap<String, String> position = new HashMap<>();
            List<User> loginList = userService.getLogin();
            List<User> passwordLIst = userService.getPasswood();
            List<User> raleList = userService.getRale();
            for (int i = 0; i < loginList.size(); i++){
                String key = String.valueOf(loginList.get(i));
                String value = String.valueOf(passwordLIst.get(i));
                String rale = String.valueOf(raleList.get(i));
                accaunts.put(key, value);
                position.put(key, rale);

                if (accaunts.containsKey(login.getText())){
                    String vel =  accaunts.get(login.getText());

                    if (password.getText().equals(vel)){

                        if ("Administrator".equals(position.get(login.getText()))) {

                            getStage().close();
                            Starting.getStage().close();
                            Main main = new Main();
                            stage = new Stage();
                            main.start(stage);
                        }else {
                            getStage().close();
                            Starting.getStage().close();
                            ManagerController managerController = new ManagerController();
                            managerController.start(stage);


                        }
                    }
                }else {

                    if (!login.getText().equals("") && !password.getText().equals("")) {
                        exception.setText(" Неверный логин или палоль ");
                    }else {
                        exception.setText(" Не заполнены все поля");
                    }
                }
            }
        }
    }
}
