package dk.easv.moviecollection.dal.db;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieCollectionDAO_DB implements IMovieCollectionDataAccess {

    private DBConnector dbConnector = new DBConnector();

    public MovieCollectionDAO_DB() throws IOException {
    }

    @Override
    public void createMovie(Movie movie) throws Exception {
        String insertSql = "INSERT INTO dbo.Movie (title, rating, filelink, lastview) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnector.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {

            throw new Exception("Could not create movie", e);

        }

    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {
        try (Connection conn = dbConnector.getConnection()) {
            int movieId = 0;

            PreparedStatement stmt1 = conn.prepareStatement("SELECT Id FROM dbo.Movie WHERE name = ?");
            stmt1.setString(1,movie.getTitle());
            ResultSet rs1 = stmt1.executeQuery();

            if (rs1.next()) {
                movieId = rs1.getInt("Id");
            }
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM dbo.CatMovie WHERE MovieId = ?");
            stmt2.setInt(1,movieId);
            stmt2.executeUpdate();

            PreparedStatement stmt3 = conn.prepareStatement("DELETE FROM dbo.Movie WHERE Id = ?");
            stmt3.setInt(1,movieId);
            stmt3.executeUpdate();
        }
    }



    @Override
    public void createCategory(Category category) throws Exception {

        try (Connection conn = dbConnector.getConnection()){

            PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO dbo.Category VALUES (?)");
            stmt1.setString(1, category.getName());
            stmt1.executeUpdate();

        }
    }

    @Override
    public void deleteCategory(Category category) throws Exception {
        try (Connection conn = dbConnector.getConnection()){
            int categoryId = 0;

            PreparedStatement stmt1 = conn.prepareStatement("SELECT Id FROM dbo.Category WHERE name = ?");
            stmt1.setString(1,category.getName());
            ResultSet rs1 = stmt1.executeQuery();

            if (rs1.next()) {
                categoryId = rs1.getInt("Id");
            }

            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM dbo.CatMovie WHERE CategoryId = ?");
            stmt2.setInt(1, categoryId);
            stmt2.executeUpdate();

            PreparedStatement stmt3 = conn.prepareStatement("DELETE FROM dbo.Category WHERE Id = ?");
            stmt3.setInt(1, categoryId);
            stmt3.executeUpdate();

        }
    }

    @Override
    public List<Movie> loadMovies() {
        //a list to store the movies loaded from database
        List<Movie> movies = new ArrayList<>();

        //selects the relevant columns
        String sql = "SELECT * FROM Movie";

        try (Connection conn = dbConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("name");
                float rating = rs.getFloat("rating");
                String filelink = rs.getString("filelink");
                Date lastOpened = rs.getDate("lastview");

                PreparedStatement categorystmt = conn.prepareStatement("SELECT Category.Name FROM Category JOIN CatMovie ON Category.id = CatMovie.CategoryId JOIN Movie ON Movie.id = CatMovie.MovieId WHERE Movie.id = ?");
                categorystmt.setInt(1, id);

                ResultSet rs2 = categorystmt.executeQuery();
                String categories = "";

                while (rs2.next()) {

                    categories = rs.getString("Name") + ":";

                }

                char[] catCharArray = categories.toCharArray();

                if (catCharArray.length != 0) {
                    if (catCharArray[catCharArray.length - 1] == ':') {
                        char[] newCharArray = Arrays.copyOfRange(catCharArray, 0, catCharArray.length - 2);
                        categories = String.valueOf(newCharArray);
                    }
                }
                else {
                    System.out.println("Har ingen genre.");
                }
                System.out.println(categories);

                movies.add(new Movie(id, title, categories, rating, filelink, lastOpened));

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
        //a list to store the categories loaded from database
        List<Category> categories = new ArrayList<>();

        //selects the relevant columns
        String sql = "SELECT * FROM Category"; try (Connection conn = dbConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) { while
        (rs.next())
        { int id = rs.getInt("id");

            String name = rs.getString("name");

            categories.add(new Category(id, name)); } }

        catch (SQLException e)

        { e.printStackTrace(); }

        return categories;
    }
}


