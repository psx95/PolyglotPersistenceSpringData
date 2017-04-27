/**
 * 
 */
package br.com.spring.data.web.controller;

import java.util.UUID;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author cad_rfirmino
 *
 */
@Controller
@RequestMapping("/user-details")
public class UserDetailsController {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
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
	public @ResponseBody void save(@RequestParam("name") String name) {
		User user = new User();
		UserDetails userDetails = new UserDetails(UUID.randomUUID().toString(), name);
		
		user.setUserDetails(userDetails);
		
		//Salva no MySql e no Mongo
		userRepository.save(user);
		
		//Salva so Mongo na tabela UserDetails
		userDetailsRepository.save(userDetails);
	}
        
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
}
