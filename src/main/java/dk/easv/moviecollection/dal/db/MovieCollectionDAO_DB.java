package dk.easv.moviecollection.dal.db;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
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
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM Movie";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String title = rs.getString("name");
                float rating = rs.getFloat("rating");
                String filelink = rs.getString("filelink");
                Date lastOpened = rs.getDate("lastview");

                movies.add(new Movie(id, title, null, rating, filelink, lastOpened));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return movies;

    }


    @Override
    public List<Category> loadCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Category";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");

                String name = rs.getString("name");

                categories.add(new Category(id, name));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return categories;
    }
}


