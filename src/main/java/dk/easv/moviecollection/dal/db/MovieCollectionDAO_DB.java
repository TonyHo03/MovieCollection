package dk.easv.moviecollection.dal.db;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class MovieCollectionDAO_DB implements IMovieCollectionDataAccess {

    private DBConnector dbConnector = new DBConnector();

    public MovieCollectionDAO_DB() throws IOException {
    }





    @Override
    public void createMovie(Movie movie) throws Exception {
        String insertSql = "INSERT INTO dbo.Movie (Title, Category, FilePath, lastOpened, rating) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.executeUpdate();

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

    @Override
    public List<Movie> loadMovies() {
        return List.of();
    }

    @Override
    public List<Category> loadCategories() {
        return List.of();
    }
}


