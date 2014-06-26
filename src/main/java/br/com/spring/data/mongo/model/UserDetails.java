/**
 * 
 */
package br.com.spring.data.mongo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author cad_rfirmino
 *
 */
@Document(collection="UserDetails")
public class UserDetails implements Serializable {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -408792323018501921L;
	
	@Id 
	private String id;
	private String username;
	
	public UserDetails(String id, String userName) {
		this.id = id;
		this.username = userName;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
