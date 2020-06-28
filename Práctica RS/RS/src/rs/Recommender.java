/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs;

import data.Movie;
import data.UserRatings;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class which calculates the similarity between two users based on their
 * ratings by Pearson correlation. Besides that, it will predict the rating
 * of movies which the user hasn't seen to suggest them.
 * 
 * @author Lidia Sánchez Mérida
 */
public class Recommender {

    /**
     * Calculates the numerator of the Pearson correlation form according to the
     * movies which have been seen by the two users.
     * @param u ratings of the user u.
     * @param v ratings of the user v.
     * @return numerator.
     */
    private double getPearsonNumerator(UserRatings u, UserRatings v) {
        double numerator = 0.0;
        for (Movie mu1: u.getRatings()) {
            for (Movie mu2: v.getRatings()) {
                if (mu1.getId() == mu2.getId()) {
                    numerator += (mu1.getRating()-u.getRatingsAverage()) *
                        (mu2.getRating()-v.getRatingsAverage());
                }
            }
        }
        return numerator;
    }
    /**
     * Calculates the denominator of the Pearson correlation form in which we search
     * again for the common movies between two users.
     * @param u ratings of the user u.
     * @param v ratings of the user v.
     * @return denominator.
     */
    private double getPearsonDenominator(UserRatings u, UserRatings v) {
        double addU = 0.0, addV = 0.0;
        for (Movie mu1: u.getRatings()) {
            for (Movie mu2: v.getRatings()) {
                if (mu1.getId() == mu2.getId()) {
                    // add of the ratings of each user minus their mean
                    addU += pow(mu1.getRating()-u.getRatingsAverage(), 2);
                    addV += pow(mu2.getRating()-v.getRatingsAverage(), 2);
                }
            }
        }
        return sqrt(addU)*sqrt(addV);
    }
    /**
     * Calculates the similarity between two users using the Pearson correlation.
     * @param u ratings of the user u.
     * @param v ratings of the user v.
     * @return similarity between u,v which could be [-1, 1]
     */
    public double getPearsonSimilarity(UserRatings u, UserRatings v) {
        double numerator = getPearsonNumerator(u, v);
        double denominator = getPearsonDenominator(u, v);
        if (numerator != 0 && denominator != 0)
            return numerator/denominator;
        else return 0;
    }
    /**
     * Gets the movies to suggest. These movies haven't been seen by the current
     * user and their predicted rating should be 4* or more.
     * @param user ratings of the current user.
     * @param movies all movies.
     * @param others ratings of the other users.
     * @param neighborhood users similar to the current user.
     * @return (predicted rating by the current user, suggested movie id).
     */
    public TreeMap<Double, Integer> getRecommendations(UserRatings user, Map<Integer,String> movies,
            ArrayList<UserRatings> others, TreeMap<Integer,Double> neighborhood) {
        TreeMap<Double, Integer> suggestedMovies = new TreeMap(Collections.reverseOrder());
        // Current user mean
        double userMean = user.getRatingsAverage();
        // All movies
        for (Integer idMovie: movies.keySet()) {
            // Movies which the active user haven't seen
            if (user.searchForMovie(idMovie) == -1) {
                double numerator = 0, denominator = 0;
                // Movies which the other users have seen
                for (Integer userId: neighborhood.keySet()) {
                    int movieIndex = others.get(userId).searchForMovie(idMovie);                    
                    if (movieIndex != -1) {
                        double mean = others.get(userId).getRatingsAverage();
                        numerator += neighborhood.get(userId)*
                            (others.get(userId).getRatings().get(movieIndex).getRating()-mean);
                        denominator += Math.abs(neighborhood.get(userId));
                    }
                }
                // Predicts the rating of the movie
                if (denominator > 0) {
                    double prediction = userMean + (numerator/denominator);
                    if (prediction >= 4) {
                        suggestedMovies.put(prediction, idMovie);
                    }
                }
            }
        }
        
        return suggestedMovies;
    }
}
