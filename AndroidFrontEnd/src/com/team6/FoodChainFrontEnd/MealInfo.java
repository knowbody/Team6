package com.team6.FoodChainFrontEnd;

public enum MealInfo {
    REGULAR, VEGETARIAN,AFRICAN, DIABETIC, SOFT_FOOD, GLUTEN_FREE, TRADITIONAL_BRITISH, HALAL;
    public String toString() {
          switch(this) {
              case REGULAR: return "REGULAR";
              case VEGETARIAN: return "VEGETARIAN";
              case AFRICAN: return "AFRICAN";
              case DIABETIC: return "DIABETIC";
              case SOFT_FOOD: return "SOFT_FOOD";
              case GLUTEN_FREE: return "GLUTEN_FREE";
              case TRADITIONAL_BRITISH: return "TRADITIONAL_BRITISH";
              case HALAL: return "HALAL";
          }
        return null;
    }
}
