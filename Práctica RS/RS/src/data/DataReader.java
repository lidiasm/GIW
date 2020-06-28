/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class which reads the movie and ratings files and save the data into arrays.
 * 
 * @author Lidia Sánchez Mérida
 */
public class DataReader {
    /**
     * Path to the movies data file.
     */
    private String moviesFile;
    /**
     * Path to the user ratings data file.
     */
    private String ratingsFile;
    /**
     * Movies data.
     */
    private Map<Integer,String> movies;
    /**
     * Ratings data.
     */
    private ArrayList<UserRatings> ratings;
        
    /**
     * Constructor.
     * @param moviesFile path to the movies file.
     * @param ratingsFile path to the user ratings file.
     */
    public DataReader(String ratingsFile, String moviesFile) {
        this.moviesFile = moviesFile;
        this.ratingsFile = ratingsFile;
    }
    /**
     * Gets the ratings of the movies.
     * @return ratings data.
     */
    public ArrayList<UserRatings> getRatings() {
        return ratings;
    }
    /**
     * Gets the data of the movies.
     * @return movies data.
     */
    public Map<Integer,String> getMovies() {
        return movies;
    }
    /**
     * Reads the movies data file and save them into an aux map.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void readMoviesData() throws FileNotFoundException, IOException {
        movies = new HashMap();
        BufferedReader br = new BufferedReader(new FileReader(moviesFile));
        String line;  
        while((line=br.readLine()) != null) {
            String data[] = line.split("\\|");
            movies.put(Integer.parseInt(data[0]), data[1]);
        }
    }
    /**
     * Searchs for an user through their user id. -1 if it doesn't exist, 
     * or its index in the array if it already exists.
     * @param userId
     * @return -1 if the user doesn't exist, its index if it exists.
     */
    public int searchUser(int userId) {
        for (int i=0; i<ratings.size(); i++) {
            if (ratings.get(i).getUserId() == userId)
                return i;
        }
        return -1;
    }
    /**
     * Reads the ratings data file and save them into a ratings array.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void readRatingsData() throws FileNotFoundException, IOException {
        ratings = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(ratingsFile));
        String line;  
        while((line=br.readLine()) != null) {
            String data[] = line.split("\\s+");
            int userId = Integer.parseInt(data[0]);
            int userIndex = searchUser(userId);
            // The user already exists
            if (userIndex != -1) {
                int movieId = Integer.parseInt(data[1]);
                int movieRating = Integer.parseInt(data[2]);
                String movieTitle = movies.get(movieId);
                ratings.get(userIndex).getRatings().add(new Movie(movieId, movieTitle, movieRating));
            }
            // The user doesn't exists
            else {
                ratings.add(new UserRatings(userId, new ArrayList()));
            }
        }
    }
    
    /**
     * Shows the data of each ratings of a movie.
     */
    public void showRatingsData() {
        for (UserRatings val: ratings) 
            System.out.println(val.toString());
    }
    /**
     * Shows the data of each movie.
     */
    public void showMoviesData() {
        for (Integer idMovie: movies.keySet()) {
            System.out.println(idMovie +" - " + movies.get(idMovie));
        }
    }
}
