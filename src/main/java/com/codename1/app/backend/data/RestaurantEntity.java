package com.codename1.app.backend.data;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="Restaurant")
public class RestaurantEntity {

    @Id
    private String id;
    private String name;
    private String merchantId;
    private String publicKey;
    private String privateKey;
    private double currentVersionIOS = 1.0;
    private double lastSupportedVersionIOS = 0;
    private double currentVersionAnd = 1.0;
    private double lastSupportedVersionAnd = 0;
    private double currentVersionUWP = 1.0;
    private double lastSupportedVersionUWP = 0;
    private long dishListUpdateTimestamp = 1;
    private String restaurantEmail;
    private String sendgridKey;

    @OneToMany
    @OrderBy("name ASC")
    private Set<DishEntity> dishes;

    
    @OneToMany
    @OrderBy("name ASC")
    private Set<CategoryEntity> categories;
    
    public RestaurantEntity() {
        id = UUID.randomUUID().toString();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the merchantId
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId the merchantId to set
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return the publicKey
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * @param publicKey the publicKey to set
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * @return the privateKey
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * @param privateKey the privateKey to set
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * @return the categories
     */
    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    /**
     * @return the currentVersionIOS
     */
    public double getCurrentVersionIOS() {
        return currentVersionIOS;
    }

    /**
     * @param currentVersionIOS the currentVersionIOS to set
     */
    public void setCurrentVersionIOS(double currentVersionIOS) {
        this.currentVersionIOS = currentVersionIOS;
    }

    /**
     * @return the lastSupportedVersionIOS
     */
    public double getLastSupportedVersionIOS() {
        return lastSupportedVersionIOS;
    }

    /**
     * @param lastSupportedVersionIOS the lastSupportedVersionIOS to set
     */
    public void setLastSupportedVersionIOS(double lastSupportedVersionIOS) {
        this.lastSupportedVersionIOS = lastSupportedVersionIOS;
    }

    /**
     * @return the currentVersionAnd
     */
    public double getCurrentVersionAnd() {
        return currentVersionAnd;
    }

    /**
     * @param currentVersionAnd the currentVersionAnd to set
     */
    public void setCurrentVersionAnd(double currentVersionAnd) {
        this.currentVersionAnd = currentVersionAnd;
    }

    /**
     * @return the lastSupportedVersionAnd
     */
    public double getLastSupportedVersionAnd() {
        return lastSupportedVersionAnd;
    }

    /**
     * @param lastSupportedVersionAnd the lastSupportedVersionAnd to set
     */
    public void setLastSupportedVersionAnd(double lastSupportedVersionAnd) {
        this.lastSupportedVersionAnd = lastSupportedVersionAnd;
    }

    /**
     * @return the currentVersionUWP
     */
    public double getCurrentVersionUWP() {
        return currentVersionUWP;
    }

    /**
     * @param currentVersionUWP the currentVersionUWP to set
     */
    public void setCurrentVersionUWP(double currentVersionUWP) {
        this.currentVersionUWP = currentVersionUWP;
    }

    /**
     * @return the lastSupportedVersionUWP
     */
    public double getLastSupportedVersionUWP() {
        return lastSupportedVersionUWP;
    }

    /**
     * @param lastSupportedVersionUWP the lastSupportedVersionUWP to set
     */
    public void setLastSupportedVersionUWP(double lastSupportedVersionUWP) {
        this.lastSupportedVersionUWP = lastSupportedVersionUWP;
    }

    /**
     * @return the dishListUpdateTimestamp
     */
    public long getDishListUpdateTimestamp() {
        return dishListUpdateTimestamp;
    }

    /**
     * @param dishListUpdateTimestamp the dishListUpdateTimestamp to set
     */
    public void setDishListUpdateTimestamp(long dishListUpdateTimestamp) {
        this.dishListUpdateTimestamp = dishListUpdateTimestamp;
    }

    /**
     * @return the restaurantEmail
     */
    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    /**
     * @param restaurantEmail the restaurantEmail to set
     */
    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    /**
     * @return the dishes
     */
    public Set<DishEntity> getDishes() {
        return dishes;
    }

    /**
     * @param dishes the dishes to set
     */
    public void setDishes(Set<DishEntity> dishes) {
        this.dishes = dishes;
    }

    /**
     * @return the sendgridKey
     */
    public String getSendgridKey() {
        return sendgridKey;
    }

    /**
     * @param sendgridKey the sendgridKey to set
     */
    public void setSendgridKey(String sendgridKey) {
        this.sendgridKey = sendgridKey;
    }

 
   
}