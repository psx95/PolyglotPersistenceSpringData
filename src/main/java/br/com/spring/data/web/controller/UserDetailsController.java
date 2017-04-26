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
}
