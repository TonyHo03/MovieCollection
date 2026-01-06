package dk.easv.moviecollection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void btnOnNewMovie(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/moviecollection/addMovieView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New Movie");

            stage.initModality(Modality.APPLICATION_MODAL);



            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {

        }
    }

    public void btnOnNewCategory(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/moviecollection/addCategory.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New category");

            stage.initModality(Modality.APPLICATION_MODAL);



            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {

        }
    }
    }

