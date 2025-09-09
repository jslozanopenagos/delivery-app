    package com.solvd.delivery.model.foodEstablishments;

    import com.solvd.delivery.interfaces.IRateHandler;

    import java.util.Set;

    public class Restaurant extends FoodEstablishment implements IRateHandler {
        private String cuisineType;

        private float totalRating = 0f;
        private int ratingCount = 0;

        public Restaurant(
                long id, String name,
                String address, int phone,
                String openingHours, boolean isOpen,
                String description, Set<MenuItem> menuItems,
                float rating, String cuisineType
        ) {
            super(id, name, address, phone, openingHours, isOpen, description, menuItems, rating );
            this.cuisineType = cuisineType;
        }

        public String getCuisineType() {
            return cuisineType;
        }

        public void setCuisineType(String cuisineType) {
            this.cuisineType = cuisineType;
        }

        @Override
        public float getTotalRating() {
            return totalRating;
        }

        @Override
        public int getRatingCount() {
            return ratingCount;
        }

        @Override
        public void setTotalRating(float totalRating) {
            this.totalRating = totalRating;
        }

        @Override
        public void setRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
        }

        @Override
        public String toString() {
            return super.toString() + "\n" + "Cuisine type= " + cuisineType;
        }
    }