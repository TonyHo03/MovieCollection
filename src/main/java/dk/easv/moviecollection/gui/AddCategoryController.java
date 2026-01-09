package dk.easv.moviecollection.gui;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCategoryController {

    private MovieModel movieModel;
    private Stage currentStage;

    @FXML
    private TextField txtFldCategory;

    public void btnOnAddCategory(ActionEvent actionEvent) {
        try {
            movieModel.createCategory(new Category(txtFldCategory.getText()));
            currentStage.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initializeClass(MovieModel movieModel, Stage stage) {

        this.movieModel = movieModel;
        this.currentStage = stage;

    }
}
