import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.DictionaryController;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        DictionaryController controller = new DictionaryController();

        Scene scene = new Scene(controller.getView(), 800, 500);

        stage.setTitle("Dictionary App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}