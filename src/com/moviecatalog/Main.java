package com.moviecatalog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Movie A = new Movie("The Imitation Game", new ArrayList<String>(Arrays.asList("Biography", "Drama", "Thriller")), new Date(114, 12, 25), 8.2, "2084970");
        Movie B = new Movie("Defiance", new ArrayList<String>(Arrays.asList("Action", "Drama", "History")), new Date(108, 01, 16), 7.2, "1034303");
        Movie C = new Movie("The Shawshank Redemption", new ArrayList<String>(Arrays.asList("Crime", "Drama")), new Date(94, 10, 14), 9.3, "0111161");
        Movie D = new Movie("The Godfather", new ArrayList<String>(Arrays.asList("Crime", "Drama")), new Date(72, 03, 24), 9.2, "0068646");
        Movie E = new Movie("The Godfather: Part II", new ArrayList<String>(Arrays.asList("Crime", "Drama")), new Date(74, 12, 20), 9.1, "0071562");
        Movie F = new Movie("The Dark Knight", new ArrayList<String>(Arrays.asList("Action", "Crime", "Drama")), new Date(108, 07, 18), 9.0, "0468569");
        Movie G = new Movie("Pulp Fiction", new ArrayList<String>(Arrays.asList("Crime", "Drama", "Thriller")), new Date(94, 10, 14), 7.2, "0110912");
        Movie H = new Movie("Fight Club", new ArrayList<String>(Arrays.asList("Drama")), new Date(99, 10, 15), 7.2, "0137523");
        Movie I = new Movie("Schindler's List", new ArrayList<String>(Arrays.asList("Biography", "Drama", "History")), new Date(93, 02, 4), 8.9, "0108052");
        Movie J = new Movie("The Lord of the Rings: The Return of the King", new ArrayList<String>(Arrays.asList("Adventure", "Fantasy")), new Date(103, 12, 17), 8.9, "0167260");
        Movie K = new Movie("Star Wars: Episode V - The Empire Strikes Back", new ArrayList<String>(Arrays.asList("Action", "Adventure", "Fantasy")), new Date(80, 06, 20), 8.8, "0080684");
        Movie L = new Movie("3 Idiots", new ArrayList<String>(Arrays.asList("Comedy", "Drama")), new Date(109, 12, 25), 8.5, "1187043");

        MovieCatalog MC = new MovieCatalog("Corneliu");
       /* MC.addMovie(A);
        MC.addMovie(B);
        MC.addMovie(C);
        MC.addMovie(D);
        MC.addMovie(E);
        MC.addMovie(F);
        MC.addMovie(G);
        MC.addMovie(H);
        MC.addMovie(I);
        MC.addMovie(J);
        MC.addMovie(K);
        MC.addMovie(L);
        MC.serialize();*/
        MC=MC.deserialize();
        Report report=new Report(MC);
        report.generateReport("name","Drama");
        Report report2=new Report(MC);
        report2.generateReport("rating");
    }
}
