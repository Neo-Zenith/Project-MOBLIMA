package src.model;

import java.io.Serializable;

/**
 * Stores and modifies all {@link MovieGoer} permissions
 * @author Lee Juin
 * @version 1.0
 */
public class Permission implements Serializable {
    /**
     * Permission to view the top 5 movies by overall ratings
     */
    private boolean movieGoerViewTop5ByOverallRatings;

    /**
     * Permission to view the top 5 movies by total movie ticket sales
     */
    private boolean movieGoerViewTop5BySales;
    private final static long serialVersionUID = 17L;

    /**
     * COnstructor
     */
    public Permission() {
        this.movieGoerViewTop5ByOverallRatings = true;
        this.movieGoerViewTop5BySales = true;
    }

    /**
     * Gets the permission to view the top 5 movies by overall ratings
     * @return {@code true} if permission granted, {@code false} otherwise
     */
    public boolean getMovieSalesPermission() {
        return this.movieGoerViewTop5BySales;
    }

    /**
     * Sets the permission to view the top 5 movies by movie ratings
     * @param permission {@code true} if permission granted, {@code false} otherwise
     */
    public void setMovieSalesPermission(boolean permission) {
        this.movieGoerViewTop5BySales = permission;
    }

    /**
     * Gets the permission to view the top 5 movies by movie ticket sales
     * @return {@code true} if permission granted, {@code false} otherwise
     */
    public boolean getOverallRatingsPermission() {
        return this.movieGoerViewTop5ByOverallRatings;
    }

    /**
     * Sets the permission to view the top 5 movies by movie ticket sales
     * @param permission {@code true} if permission granted, {@code false} otherwise
     */
    public void setOverallRatingsPermission(boolean permission) {
        this.movieGoerViewTop5ByOverallRatings = permission;
    }
}
