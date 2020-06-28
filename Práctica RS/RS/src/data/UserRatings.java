/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 * Class which contains the data valuation for each movie. 
 * Their fields are: userId, movieId, valuation.
 * 
 * @author Lidia Sánchez Mérida
 */
public class UserRatings {
    /**
     * User id.
     */
    private int userId;
    /**
     * List of the movies which the user has been rated.
     */
    private ArrayList<Movie> ratings;
    
    /**
     * Constructor.
     * @param userId user id which has appreciated the movie.
     * @param ratings movies rated by the user
     */
    public UserRatings(int userId, ArrayList<Movie> ratings) {
        this.userId = userId;
        this.ratings = ratings;
    }
    /**
     * Gets the id of an user who has written a valuation.
     * @return user id.
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Gets the movies rated by the user.
     * @return movies rated by the user.
     */
    public ArrayList<Movie> getRatings() {
        return ratings;
    }
    /**
     * Calculates the average of the ratings of an user.
     * @return ratings average.
     */
    public double getRatingsAverage() {
        double add = 0.0;
        for (Movie movie: ratings) {
            add += movie.getRating();
        }
        return add/ratings.size();
    }
    /**
     * Searchs for a specific movie by its id. 
     * @param movieId movie id to search for.
     * @return -1 if it's not found or its position if it's found.
     */
    public int searchForMovie(int movieId) {
        for (Movie movie: ratings) {
            if (movie.getId() == movieId)
                return ratings.indexOf(movie);
        }
        return -1;
    }
    /**
     * Show the data of the user and their movie ratings.
     * @return user ratings.
     */
    @Override
    public String toString() {
        String result = "\nUser id: "+userId + "\nMovies rated\n";
        for (Movie movie: ratings) {
            result += movie.toString();
        }
        return result;
    }
}
