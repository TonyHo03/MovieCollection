package dk.easv.moviecollection.BE;

import java.util.Date;

public class Movie {
public String title;
private String category;
private String filePath;
public Date lastOpened;
public String score;

public Movie(String title, String category, String score){
    this.title = title;
    this.category = category;
    //this.filePath = filePath;
    //this.lastOpened = Time.valueOf(lastOpened);
    this.score = score;
}

public String getTitle(){
    return title;
}

public String getCategory(){
    return category;
}

public String getRating(){
    return score;
}


}
