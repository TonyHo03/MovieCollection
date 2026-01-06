package dk.easv.moviecollection.BE;

import java.sql.Date;
import java.sql.Time;

public class Movie {

    private int id;
    private String title;
    private String filePath;
    public Date lastOpened;
    public float rating;

    public Movie(int id, String title, float rating, String filelink, Date lastOpened){
        this.id = id;
        this.title = title;
        this.filePath = filelink;
        this.lastOpened = lastOpened;
        this.rating = rating;
    }

    public Movie(String title, String filePath, Date lastOpened, float rating){
        this.title = title;
        this.filePath = filePath;
        this.lastOpened = lastOpened;
        this.rating = rating;
    }

    public String getTitle() {
        return this.title;
    }

    public float getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
