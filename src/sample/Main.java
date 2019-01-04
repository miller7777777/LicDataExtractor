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

    @Override
    public void start(final Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        configuringDirectoryChooser(directoryChooser);

        final TextArea textArea = new TextArea();
        textArea.setMinHeight(70);
        Button button = new Button("Open DirectoryChooser and select a directory");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File dir = directoryChooser.showDialog(primaryStage);

                if (dir != null) {
                    Extractor extractor = new Extractor(dir.getAbsolutePath());

                    String regInfo = extractor.getResult();



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

        primaryStage.setTitle("JavaFX DirectoryChooser (o7planning.org)");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for DirectoryChooser
        directoryChooser.setTitle("Select Some Directories");

        // Set Initial Directory
//        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }


    public static void main(String[] args) {

        launch(args);
    }
}
