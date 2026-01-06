package dk.easv.moviecollection.dal;
import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;
import dk.easv.moviecollection.dal.db.DBConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieCollectionDAO_DB implements IMovieCollectionDataAccess {

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

    @Override
    public List<Movie> loadMovies() throws Exception {

        List<Movie> movieList = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection()) {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM dbo.Movie");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                float rating = rs.getFloat("rating");
                String filelink = rs.getString("filelink");
                Date lastview = rs.getDate("lastview");

                movieList.add(new Movie(id, name, rating, filelink, lastview));

            }

        } catch (SQLException e) {

            throw new Exception("Could not load movies", e);

        }

        return movieList;

    }

    @Override
    public List<Category> loadCategories() throws Exception {
        List<Category> categoryList = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection()) {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM dbo.Category");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");

                categoryList.add(new Category(id, name));

            }

        } catch (SQLException e) {

            throw new Exception("Could not load categories", e);

        }

        return categoryList;
    }
}
