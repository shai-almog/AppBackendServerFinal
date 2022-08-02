package com.codename1.app.backend.service.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private String id;
    private Date date;
    private boolean purchased;
    private Map<String, Integer> dishQuantity = new HashMap<>();
    private String notes;
    private String nonce;
    private String auth;
    private Address address;
    
    /**
     * @return the nonce
     */
    public String getNonce() {
        return nonce;
    }

    /**
     * @param nonce the nounce to set
     */
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the purchased
     */
    public boolean isPurchased() {
        return purchased;
    }

    /**
     * @param purchased the purchased to set
     */
    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    /**
     * @return the dishQuantity
     */
    public Map<String, Integer> getDishQuantity() {
        return dishQuantity;
    }

    /**
     * @param dishQuantity the dishQuantity to set
     */
    public void setDishQuantity(Map<String, Integer> dishQuantity) {
        this.dishQuantity = dishQuantity;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the auth
     */
    public String getAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public void setAuth(String auth) {
        this.auth = auth;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }
}
