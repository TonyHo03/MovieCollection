package dk.easv.moviecollection.BE;

import java.sql.Time;
import java.util.Date;

public class Movie {
private String title;
private String category;
private String filePath;
public Date lastOpened;
public float rating;

public Movie(String title, String category, String filePath, String lastOpened, float rating){
    this.title = title;
    this.category = category;
    this.filePath = filePath;
    this.lastOpened = Time.valueOf(String.valueOf(lastOpened));
    this.rating = rating;

}

    public Movie(String title, float rating) {
        this.title = title;
        this.rating = rating;
    }


    public String getTitle() {
    return title;
    }
    public void setTitle(String title) {
    this.title = title;
    }
    public float getRating() {
    return rating;
    }
    public void setRating(float rating) {
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
