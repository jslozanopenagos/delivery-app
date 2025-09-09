package com.solvd.delivery.interfaces;

public interface IRateManager {
    float getTotalRating();
    void setTotalRating(float totalRating);

    int getRatingCount();
    void setRatingCount(int count);

    default void addRating(float rating) {
        if (rating >= 0 && rating <= 5) {
            setTotalRating(getTotalRating() + rating);
            setRatingCount(getRatingCount() + 1);
        } else {
            System.out.println("Rating must be between 0 and 5.");
        }
    }

    default float getAverageRating() {
        return getRatingCount() == 0 ? 0f : getTotalRating() / getRatingCount();
    }
}
