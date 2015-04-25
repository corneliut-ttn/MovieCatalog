package com.moviecatalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by cornelius on 3/4/15.
 */
public class Movie implements Serializable {

    private String name;
    private List<genre> categories;
    private Date launchDate;
    private double rating;
    private String imdbID;

    public Movie(String name, List<String> categories, Date launchDate, double rating, String imdbID) {
        this.categories = new ArrayList<genre>();
        this.name = name;
        for (ListIterator<String> it = categories.listIterator(); it.hasNext(); ) {
            String aux = it.next();
            try {
                this.categories.add(genre.valueOf(aux));
            } catch (IllegalArgumentException ex) {
                System.out.println("Error" + ex + " genre invalid");
            }
        }
        try {
            this.setLaunchDate(launchDate);
        } catch (InvalidDataException ide) {
            System.out.println(ide.getMessage());
        }
        try {
            this.setRating(rating);
        } catch (InvalidDataException ide) {
            System.out.println(ide.getMessage());
        }
        this.imdbID = imdbID;
    }

    public Movie() {
        this.categories = new ArrayList<genre>();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbID='" + imdbID + '\'' +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                ", launchDate=" + launchDate +
                ", rating=" + rating +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<genre> getCategories() {
        return categories;
    }

    public void setCategories(List<genre> categories) {
        this.categories = categories;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) throws InvalidDataException {
        if ((launchDate).before(new Date()) == true) {
            this.launchDate = launchDate;
        } else {
            throw new InvalidDataException("Invalid date");
        }
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) throws InvalidDataException {
        if (rating < 0 || rating > 10) throw new InvalidDataException("Invalid rating value");
        this.rating = rating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public enum genre {
        Action, Adventure, Thriller, Drama, Comedy, SciFi, Fantasy, Crime, Romance,
        Family, Animation, Mystery, Horror, War, History, Sport, Biography, Musical,
        Music, Western, Short
    }
}
