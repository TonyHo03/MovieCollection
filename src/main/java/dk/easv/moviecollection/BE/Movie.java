package dk.easv.moviecollection.BE;

import java.util.Date;

public class Movie {
private String title;
private String category;
private String filePath;
public Date lastOpened;
public Float rating;


    public Movie(String title, Float rating, String filePath, Date lastOpened) {
        this.title = title;
        this.rating = rating;
        this.filePath = filePath;
        this.lastOpened = lastOpened;
    }

    public Movie() {

    }


    public String getTitle() {
    return title;
    }
    public void setTitle(String title) {
    this.title = title;
    }
            public Float getRating() {
    return rating;
    }
    public void setRating(Float rating) {
    this.rating = rating;
    }

    public String getCategory() {
    return category;
    }
    public void setCategory(String category) {
    this.category = category;
    }
    public String getFilePath() {
    return filePath;
    }
    public void setFilePath(String filePath) {
    this.filePath = filePath;
    }
    public Date getLastOpened() {
    return lastOpened;
    }
    public void setLastOpened(Date lastOpened) {
    this.lastOpened = lastOpened;
    }
}
