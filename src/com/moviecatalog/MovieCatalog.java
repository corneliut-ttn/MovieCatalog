package com.moviecatalog;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by cornelius on 3/4/15.
 */
public class MovieCatalog implements Serializable {

    private String user;
    private List<Movie> colection;

    public MovieCatalog(String user, List<Movie> colection) {
        this.user = user;
        this.colection = colection;
    }

    public MovieCatalog(String user) {
        this.user = user;
        this.colection = new ArrayList<Movie>();
    }

    public MovieCatalog() {
        this.colection = new ArrayList<Movie>();
    }

    @Override
    public String toString() {
        return "MovieCatalog{" +
                "user='" + user + '\'' +
                ", colection=" + colection +
                '}';
    }


    public void addMovie(Movie movie) {
        this.colection.add(movie);
    }

    public void addMovie() {

        this.colection.add(new Movie());
    }

    private String scanName(Scanner scanner) {
        System.out.println("Write the title");
        return scanner.next();
    }

    private String scanImdbID(Scanner scanner) {
        System.out.println("Write the IMDB id");
        return scanner.next();
    }

    private Date scanReleaseDate(Scanner scanner) {
        System.out.println("Write the release date");
        String dateS = scanner.next("[0-3][0-9].[01][0-2].[12][089][0-9][0-9]");
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private double scanRating(Scanner scanner) {
        System.out.println("Write movie rating");
        return scanner.nextDouble();
    }

    private List<String> scanGenres(Scanner scanner) {
        List<String> genres = new ArrayList<String>();
        System.out.println("Write the genres");
        scanner.nextLine();
        String genre = scanner.nextLine();
        String[] aux = genre.split(" ");
        for (int i = 0; i < aux.length; i++)
            genres.add(aux[i]);
        return genres;
    }

    public Movie scanMovie() {
        Scanner keyboard = new Scanner(System.in);
        return new Movie(scanName(keyboard), scanGenres(keyboard), scanReleaseDate(keyboard), scanRating(keyboard), scanImdbID(keyboard));
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Movie> getColection() {
        return colection;
    }

    public void setColection(List<Movie> colection) {
        this.colection = colection;
    }

    public void serialize() {
        System.out.println("Starting serializing...");
        try {
            writeMovieCatalog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MovieCatalog deserialize() {
        System.out.println("Starting deserializing...");
        try {
            return readMovieCatalog();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeMovieCatalog() throws IOException {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./src/com/moviecatalog/database.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./src/com/moviecatalog/database.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public MovieCatalog readMovieCatalog() throws IOException {
        try {
            FileInputStream fileIn =
                    new FileInputStream("./src/com/moviecatalog/database.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            return (MovieCatalog)in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }
}

