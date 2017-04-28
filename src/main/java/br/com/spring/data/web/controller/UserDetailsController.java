/**
 * 
 */
package br.com.spring.data.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.data.model.User;
import br.com.spring.data.mongo.model.UserDetails;
import br.com.spring.data.mongo.repository.UserDetailsRepository;
import br.com.spring.data.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author pranav
 *
 */
@Controller
@RequestMapping("/user-details")
public class UserDetailsController {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
        
        @Autowired
        private MongoOperations mongoOperations;
	
        // tells the application Context to inject an Instance of the desired bean here
        // all the spring beans are managed classes, i.e. they live inside the context
        // autowiring means that the context will instatiate them, no need to instantiate them explicitely
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView init(){
		ModelAndView modelAndView = new ModelAndView("user-details");
		return modelAndView;
	}
	 
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity save(@RequestParam("name") String name, @RequestParam("serial") Long serial) {
		User user = new User();
                user.setIdJoin(serial);
		UserDetails userDetails;
                if (name == null){
                    userDetails = new UserDetails(serial);
                }
                else {
                    userDetails = new UserDetails(name, serial);
                }
		
		user.setUserDetails(userDetails);
		
		//Salva no MySql e no Mongo
		userRepository.save(user);
		
		//Salva so Mongo na tabela UserDetails
		userDetailsRepository.save(userDetails);
                return new ResponseEntity(HttpStatus.OK);
	}
        
//        @RequestMapping(value = "/save", method = RequestMethod.POST)
//        public @ResponseBody ResponseEntity save (@RequestParam("serial")Long serial){
//            User user = new User();
//            user.setIdJoin(serial);
//            UserDetails userDetails = new UserDetails (serial);
//            user.setUserDetails(userDetails);
//            userRepository.save(user);
//            userDetailsRepository.save(userDetails);
//            return new ResponseEntity(HttpStatus.OK);
//        }
        
        @RequestMapping (value="/getall", headers = "Accept=*/*", method = RequestMethod.GET)
        public @ResponseBody ResponseEntity getAllUsers() {
            Iterable<User> u = userRepository.findAll();           
            List<User> n = new ArrayList<>();            
            if (u!=null){
                 for (User user : u){
                     n.add(user);
                     if (user.getUserDetails()!=null)
                    System.out.println (user.getId()+" "+user.getUserDetails().getUsername());                 
                }
            }          
            return new ResponseEntity(n, HttpStatus.OK);            
        }
        
        // this is working 
        @RequestMapping (value = "/get", headers = "Accept=*/*" , method = RequestMethod.GET)
        public @ResponseBody User getUser (@RequestParam("id") Long id){
           User user = userRepository.findOne(id);
           if (user == null){               
               System.out.println ("hifw");
               return null;
               //return new ResponseEntity("User with id "+id.toString()+" does not exist",HttpStatus.GONE);               
           }
           else {
               // user with id found
               System.out.println ("here"+user.getId());
               return user;
               //return new ResponseEntity(user, HttpStatus.OK);
           }
        }
        
        @RequestMapping (value = "/getDetails", headers = "Accept=*/*", method = RequestMethod.GET)
        public @ResponseBody ResponseEntity getDetails (@RequestParam("id") Long id){            
            User user = userRepository.findOne(id);
            UserDetails ud = userDetailsRepository.findOne(user.getIdJoin());
            //UserDetails details = mongoOperations.findOne(org.springframework.data.mongodb.core.query.Query.query(Criteria.where("joinid").is(id)), UserDetails.class,"UserDetails");            
            if (ud != null){
                // display the user details
                return new ResponseEntity (ud,HttpStatus.OK);
            }
            else {
                return new ResponseEntity(HttpStatus.FOUND);
            }
        }
        
        // this is working
        @RequestMapping (value ="/delete", headers ="Accept=*/*", method = RequestMethod.GET)
        public @ResponseBody ResponseEntity deleteObject (@RequestParam("id") Long id){
            // get the user object from the mysql table
            User u = userRepository.findOne(id);
            // get the user details from the mongodb repository 
            UserDetails userDetails = userDetailsRepository.findOne(u.getIdJoin());
            if (userDetails != null){
                userRepository.delete(id);
                userDetailsRepository.delete(u.getIdJoin());
                return new ResponseEntity(HttpStatus.OK);
            }
            else {
                return new ResponseEntity(HttpStatus.FOUND);
            }
        }
        
        // update operation will work by sending a save request on the same url as the same new object, 
        // save the data onto the same id, the user details will update 
}
