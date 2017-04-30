/**
 * 
 */
package br.com.spring.data.mongo.model;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Required;

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
	
//	@Id 
//	private String id;       
	private String username=null;
        @Id
        private Long joinid;
	
        
        // different types of constructors 
	public UserDetails(String username, Long joinid) {
		//this.id = id;
               
                this.username = username;                
                this.joinid = joinid;
	}
        
        public UserDetails (Long joinid) {
            this.joinid = joinid;
        }
        
        public UserDetails(){
            // required- empty constructor 
        }
        
        public Long getJoinid (){
            return joinid;
        }
        
        public void setJoinid (Long joinid){
            this.joinid = joinid;
        }
//	
//	public String getId() {
//		return id;
//	}
//	
//	public void setId(String id) {
//		this.id = id;
//	}
//	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
