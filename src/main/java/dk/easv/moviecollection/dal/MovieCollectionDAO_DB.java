package dk.easv.moviecollection.dal;
import dk.easv.moviecollection.BE.Movie;
import dk.easv.moviecollection.dal.db.DBConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MovieCollectionDAO_DB implements iMovieCollectionDataAccess{

private DBConnector dbConnector = new DBConnector();

    public MovieCollectionDAO_DB() throws IOException {
    }

    @Override
    public void createMovie() throws Exception {
        String insertSql = "INSERT INTO MOVIES (title, category, filePath, lastView, rating) values (?, ?, ?)";
        try (Connection conn = dbConnector.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
        }





    }

    @Override
    public void deleteMovie() throws Exception {

    }

    @Override
    public void createCategory() throws Exception {

    }

    @Override
    public void deleteCategory() throws Exception {

    }
}
