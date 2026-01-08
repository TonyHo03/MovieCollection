package dk.easv.moviecollection.BE;

import java.sql.Time;
import java.sql.Date;

public class Movie {

    private int id;
    private String title;
    private String category;
    private String filePath;
    public Date lastOpened;
    public float rating;

    public Movie(int id, String title, String category, float rating, String filePath, Date lastOpened){
        this.id = id;
        this.title = title;
        this.category = category;
        this.filePath = filePath;
        this.lastOpened = lastOpened;
        this.rating = rating;
    }

    public Movie(String title, float rating) {
        this.title = title;
        this.rating = rating;
        this.filePath = null;
        this.lastOpened = null;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return this.rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getFilePath() {
        return this.filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getLastOpened() {
        return this.lastOpened;
    }
    public void setLastOpened(Date lastOpened) {
        this.lastOpened = lastOpened;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
