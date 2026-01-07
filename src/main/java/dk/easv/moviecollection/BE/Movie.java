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
    this.lastOpened = Time.valueOf(lastOpened);
    this.rating = Float.parseFloat(String.valueOf(rating));

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
}
