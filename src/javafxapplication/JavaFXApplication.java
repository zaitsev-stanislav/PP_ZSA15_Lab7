package javafxapplication;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {

    static {
        // Установка формата вывода для java.util.logging.SimpleFormatter
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tF %1$tT [%4$-7s] %3$s - %5$s %n");
    }
    // Объявление логировцика типа java.util.logging.Logger 
    static java.util.logging.Logger log = java.util.logging.Logger.getLogger(JavaFXApplication.class.getName());
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Handler fileHandler = new FileHandler("logging.log", 100 *  1024, 3, true);
        fileHandler.setFormatter(new SimpleFormatter());
        log.addHandler(fileHandler);

        // Технология логирования java.util.logging
        log.setLevel(Level.ALL);
        log.info("Start programm");
        log.log(Level.INFO, "Запись лога с уровнем INFO (информационная)");
        log.log(Level.WARNING, "Запись лога с уровнем WARNING (Предупреждение)");
        log.log(Level.SEVERE, "Запись лога с уровнем SEVERE (серъёзная ошибка)");
        log.info("Some message");

        try {
            throw new Exception("ERR!");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "My Exception: {0}", ex.getMessage());
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/image/icon.jpg"));
        stage.setResizable(false);
        stage.setTitle("Создание простейшей программы");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
