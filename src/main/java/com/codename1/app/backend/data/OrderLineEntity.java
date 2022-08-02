package com.codename1.app.backend.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderLine")
public class OrderLineEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private DishEntity dish;
    private int amount;
    
    // sharded as price might change in future
    private double price;
    
    public OrderLineEntity() {}

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the dish
     */
    public DishEntity getDish() {
        return dish;
    }

    /**
     * @param dish the dish to set
     */
    public void setDish(DishEntity dish) {
        this.dish = dish;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

   
}