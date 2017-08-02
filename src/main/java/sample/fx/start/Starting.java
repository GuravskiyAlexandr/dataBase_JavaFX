package sample.fx.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Alexsandr on 25.07.2017.
 */
public class Starting extends Application{
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Starting.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/start/start.fxml"));
        primaryStage.setTitle("Hello");
        primaryStage.setScene(new Scene(root, 505, 315));
        primaryStage.show();
        setStage(primaryStage);

    }


}

