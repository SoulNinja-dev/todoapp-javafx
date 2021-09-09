package todo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/home.fxml"));
        Image icon = new Image("todo/icons/icon.png");

        stage.setTitle("Tickk");
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

}
