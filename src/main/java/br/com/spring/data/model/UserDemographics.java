/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.spring.data.model;

import br.com.spring.data.mongo.model.UserFeedback;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.springframework.data.mongodb.crossstore.RelatedDocument;

/**
 *
 * @author Pranav
 */
@Entity
public class UserDemographics implements Serializable{
    
    // Unique ID for MySQL primary key 
    //  It is necessary to provide @Id with @Entity 
    // this id will also act as a join in the mongo 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    @Transient
    @RelatedDocument
    UserFeedback feedback;
            
    String title;
    String name;
    String email;
    String birthdate;
    String country;
    String phonenumber;

    public UserDemographics (){
        // empty constructor 
    }
    
    public UserDemographics (String title, String name, String email, String birthdate, String country, String phonenumber){
        this.title = title;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.country = country;
        this.phonenumber = phonenumber;
    }
    
    // getters

    public Long getId() {
        return id;
    }

    public UserFeedback getFeedback() {
        return feedback;
    }
    
        
    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getTitle() {
        return title;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    // setters
    
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFeedback(UserFeedback feedback) {
        this.feedback = feedback;
    }
     
}
