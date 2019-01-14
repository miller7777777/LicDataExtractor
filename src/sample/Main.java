package sample;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    public static final String WINDOW_TITLE = "LicDataExtractor version. 1.01";
    public static final String BUTTON_LABLE = "Выбор папки";
    private File dir = null;
    private String licFileName;

    @Override
    public void start(final Stage primaryStage) throws Exception {

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryChooser);

        final TextArea textArea = new TextArea();
        textArea.setMinHeight(70);
        Button button = new Button(BUTTON_LABLE);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.setText("Ждите...");
                configuringDirectoryChooser(directoryChooser);
                dir = directoryChooser.showDialog(primaryStage);

                if (dir != null) {
                    Extractor extractor = new Extractor(dir.getAbsolutePath());
                    String regInfo = extractor.getResult();

                    if (regInfo.startsWith("Файл: ")) {
                        RegDataWriter writer = new RegDataWriter(dir.getAbsolutePath(), regInfo);
                        writer.saveToFile(extractor.getLicFileName());
                    }

                    textArea.setText(dir.getAbsolutePath() + "\n" + regInfo);
                } else {
                    textArea.setText(null);
                }
            }
        });

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(5);
        root.getChildren().addAll(textArea, button);

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for DirectoryChooser
        directoryChooser.setTitle("Выберите папку: ");

        // Set Initial Directory
//        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if (dir != null) {
            directoryChooser.setInitialDirectory(dir);
        }
    }


    public static void main(String[] args) {

        launch(args);
    }
}
