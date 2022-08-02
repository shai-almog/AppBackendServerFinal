package com.codename1.app.backend.service.dao;

public class DishContainer {
    private String stamp;
    private Dish[] dishes;
    private String[] categories;

    public DishContainer() {
    }

    public DishContainer(String stamp, Dish[] dishes, String[] categories) {
        this.stamp = stamp;
        this.dishes = dishes;
        this.categories = categories;
    }
    
    
    /**
     * @return the stamp
     */
    public String getStamp() {
        return stamp;
    }

    /**
     * @return the dishes
     */
    public Dish[] getDishes() {
        return dishes;
    }

    /**
     * @return the categories
     */
    public String[] getCategories() {
        return categories;
    }
    
    
}
