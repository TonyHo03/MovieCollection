package dk.easv.moviecollection.dal.db;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieCollectionDAO_DB implements IMovieCollectionDataAccess {

    private DBConnector dbConnector = new DBConnector();

    public MovieCollectionDAO_DB() throws IOException {
    }





    @Override
    public void createMovie(Movie movie) throws Exception {
        String insertSql = "INSERT INTO dbo.Movie (Title, Genre, Score) VALUES (?, ?, ?)";
        try (Connection conn = dbConnector.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.setString(1,movie.getTitle());
            preparedStatement.setString(2,movie.getCategory());
            preparedStatement.setString(3,movie.getRating());
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
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT name, rating FROM Movie"; try (Connection conn = dbConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) { while
        (rs.next())
        { String title = rs.getString("name");

            float rating = rs.getFloat("rating");

            movies.add(new Movie(title, rating)); } }

        catch (SQLException e)

        { e.printStackTrace(); }

        return movies;

    }


    @Override
    public List<Category> loadCategories() {
        return List.of();
    }
}


