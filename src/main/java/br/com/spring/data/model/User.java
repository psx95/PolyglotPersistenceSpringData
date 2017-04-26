/**
 * 
 */
package br.com.spring.data.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.mongodb.crossstore.RelatedDocument;

import br.com.spring.data.mongo.model.UserDetails;

/**
 * @author cad_rfirmino
 * 
 */
@Entity
public class User implements Serializable {

	/**
	 * 
	 */
    // @transient -  this value is not to be stored in the database    
	@Transient
	private static final long serialVersionUID = -7048268770422833351L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
        @Transient
	@RelatedDocument
	private UserDetails userDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
}
