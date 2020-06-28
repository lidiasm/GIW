/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 * Class which contains the data of a movie.
 * Their fields are the id of the movie and its title.
 * 
 * @author Lidia Sánchez Mérida
 */
public class Movie {
    /**
     * Movie id.
     */
    private int id;
    /**
     * Title of the movie.
     */
    private String title;
    /**
     * Rating of the movie.
     */
    private int rating;
    
    /**
     * Constructor.
     * @param id movie id.
     * @param title movie title.
     * @param rating movie rating
     */
    public Movie(int id, String title, int rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }
    /**
     * Gets the id of a movie.
     * @return movie id.
     */
    public int getId() {
        return id;
    }
    /**
     * Gets the title of a movie.
     * @return movie title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Gets the rating of a movie.
     * @return movie rating.
     */
    public int getRating() {
        return rating;
    }
    /**
     * Sets the rating of a movie.
     * @param rating new rating.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    /**
     * Shows the data of a movie.
     * @return movie data.
     */
    @Override
    public String toString() {
        return "\nId: "+id+"\nTitle: "+title+"\nRating: "+rating;
    } 
}
