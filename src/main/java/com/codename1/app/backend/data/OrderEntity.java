package com.codename1.app.backend.data;

import com.codename1.app.backend.service.dao.Order;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Purchases")
public class OrderEntity {

    @Id
    private String id;
    
    @OneToMany
    private Set<OrderLineEntity> dishQuanity;

    private Date date;
    
    private String notes;

    private String name;
    private String line1;
    private String line2;
    private String city;
    private String phone;
    private String email;
    private String addressNotes;
    
    public OrderEntity() {
        id = UUID.randomUUID().toString();
    }

    public OrderEntity(Order i, OrderLineEntity... entries) {
        id = UUID.randomUUID().toString();
        date = new Date();
        notes = i.getNotes();
        
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the dishQuanity
     */
    public Set<OrderLineEntity> getDishQuanity() {
        return dishQuanity;
    }

    /**
     * @param dishQuanity the dishQuanity to set
     */
    public void setDishQuanity(Set<OrderLineEntity> dishQuanity) {
        this.dishQuanity = dishQuanity;
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
     * @return the line1
     */
    public String getLine1() {
        return line1;
    }

    /**
     * @param line1 the line1 to set
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * @return the line2
     */
    public String getLine2() {
        return line2;
    }

    /**
     * @param line2 the line2 to set
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the addressNotes
     */
    public String getAddressNotes() {
        return addressNotes;
    }

    /**
     * @param addressNotes the addressNotes to set
     */
    public void setAddressNotes(String addressNotes) {
        this.addressNotes = addressNotes;
    }
   
}