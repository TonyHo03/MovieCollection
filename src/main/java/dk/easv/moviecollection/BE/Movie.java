package dk.easv.moviecollection.BE;

import java.sql.Time;
import java.util.Date;

public class Movie {
private String title;
private String category;
private String filePath;
public Date lastOpened;
public String rating;

public Movie(String title, String category, String filePath, String lastOpened, String rating){
    this.title = title;
    this.category = category;
    this.filePath = filePath;
    this.lastOpened = Time.valueOf(lastOpened);
    this.rating = rating;
}
}
