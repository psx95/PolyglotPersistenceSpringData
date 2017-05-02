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
import br.com.spring.data.model.UserDemographics;
import br.com.spring.data.mongo.model.UserDetails;
import br.com.spring.data.mongo.model.UserFeedback;
import br.com.spring.data.mongo.repository.UserDetailsRepository;
import br.com.spring.data.mongo.repository.UserFeedbackRepository;
import br.com.spring.data.repository.UserDemographicsRepository;
import br.com.spring.data.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author pranav
 *
 */
@Controller
public class UserDetailsController {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
        
        @Autowired
        private MongoOperations mongoOperations;
        
        @Autowired
        private UserFeedbackRepository userFeedbackRepository;
        
        @Autowired
        private UserDemographicsRepository userDemographicsRepository;
	
        // tells the application Context to inject an Instance of the desired bean here
        // all the spring beans are managed classes, i.e. they live inside the context
        // autowiring means that the context will instatiate them, no need to instantiate them explicitely
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView init(){
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
        
        @RequestMapping(value = "/redirect", method = RequestMethod.POST)
        public String sendRedirect (@RequestParam(value = "location") String location){
            return location;
        }

//        @RequestMapping(value = "/view", method = RequestMethod.POST)
//        public String viewUser (@ModelAttribute("test") UserDemographics demographics, Map<String, Object> map){
//            long id = userDemographicsRepository.count();
//            if (userDemographicsRepository.exists(id)){
//                // send data from controller to jsp                 
//                map.put ("test",demographics);
//            }
//            return "viewResponse";
//        }
        
        @RequestMapping(value = "/viewUnstructured", method = RequestMethod.POST)
        public ModelAndView displayDocuments (){
            String json = null;
            ObjectMapper mapper = new ObjectMapper();            
            List<UserFeedback> feedbacks = userFeedbackRepository.findAll();
            try {
                mapper.setSerializationInclusion(Include.NON_NULL);
                json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(feedbacks);                                
            } catch (JsonProcessingException ex) {
                Logger.getLogger(UserDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ModelAndView model = new ModelAndView("allDocuments");
            model.addObject("list_feedbacks",json);
            return model;
        }
        
        @RequestMapping(value ="/viewFeedback", method = RequestMethod.GET)
        public ModelAndView displayFeedback (@RequestParam("id")Long id){
            ObjectMapper mapper = new ObjectMapper();
            UserFeedback feedback = userFeedbackRepository.findOne(id);
            String jsonString = null;
            try {
                String json = mapper.writeValueAsString(feedback);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(UserDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                mapper.setSerializationInclusion(Include.NON_NULL);
                jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(feedback);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(UserDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ModelAndView model = new ModelAndView("viewFeedback");
            model.addObject("feedback",feedback);
            model.addObject("feedbackAsJson",jsonString);
            return model;
        }
        
        @RequestMapping(value="/viewAll", method = RequestMethod.POST)
        public ModelAndView getAllUserInfo (){
            Iterable<UserDemographics> i = userDemographicsRepository.findAll();
            List<UserDemographics> list = new ArrayList<>();
            Iterator<UserDemographics> iterator = i.iterator();            
            UserFeedback feedback = new UserFeedback();
            UserDemographics ud = new UserDemographics();
            while (iterator.hasNext()){              
                ud = iterator.next();
                feedback = userFeedbackRepository.findOne(ud.getId());
                ud.setFeedback(feedback);
                list.add(ud);
            }                       
            ModelAndView model = new ModelAndView("viewResponse");
            model.addObject("lists",list);
            return model;
        }
        
        @RequestMapping(value = "/add", method = RequestMethod.POST)
        public String addEntry (@RequestParam(value = "title", required = false) String title, @RequestParam(value = "name") String name, @RequestParam(value = "email")String email, @RequestParam(value = "birthdate") String birthdate, @RequestParam(value = "country")String country, @RequestParam(value = "phonenumber")String phonenumber, @RequestParam(value = "answer1", required = false) String answer1, @RequestParam(value = "answer2", required = false)String answer2, @RequestParam(value = "answer3", required = false)String answer3, @RequestParam(value = "answer4", required = false)String answer4, @RequestParam(value = "answer5", required = false)String answer5, @ModelAttribute("test") UserDemographics d, ModelMap modelMap){
            UserDemographics demographics = new UserDemographics(title,name,email,birthdate,country,phonenumber);            
            UserFeedback userFeedback = new UserFeedback();
            if (!"".equals(answer1)){
                userFeedback.setAnswer1(answer1);
            }
            if (!"".equals(answer2)){            
                userFeedback.setAnswer2(answer2);
            }
            if (!"".equals(answer3)){
                userFeedback.setAnswer3(answer3);
            }
            if (!"".equals(answer4)){
                userFeedback.setAnswer4(answer4);
            }
            if (!"".equals(answer5)){
                userFeedback.setAnswer5(answer5);
            }
            // linking mongodb entries to mysql
            if (userFeedback.equals(null)){
                System.out.println ("not good");
            }           
            demographics.setFeedback(userFeedback);
            userDemographicsRepository.save(demographics); 
            userFeedback.setId(demographics.getId());
            userFeedbackRepository.save(userFeedback);
            modelMap.addAttribute("title",demographics.getTitle());
            modelMap.addAttribute("email",demographics.getEmail());
            modelMap.addAttribute("id",demographics.getId());
            modelMap.addAttribute("name",demographics.getName());
            modelMap.addAttribute("birthdate", demographics.getBirthdate());
            modelMap.addAttribute("phone",demographics.getPhonenumber());
            modelMap.addAttribute("country",demographics.getCountry());
            if (answer1 != "")
            modelMap.addAttribute("answer1",answer1);
            if (answer2 != "") 
            modelMap.addAttribute("answer2",answer2);
            if (answer3 != "")
            modelMap.addAttribute("answer3",answer3);
            if (answer4 != "")
            modelMap.addAttribute("answer4",answer4);
            if (answer5 != "")
            modelMap.addAttribute("answer5", answer5);
            return "success";
        }    
        
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity save(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "serial") Long serial) {
		User user = new User();
                user.setIdJoin(serial);
		UserDetails userDetails = new UserDetails();                
		if (name=="")
                {
                    userDetails.setJoinid(serial);
                }
                else if (name!=null){      
                    
                    userDetails.setUsername(name);
                    userDetails.setJoinid(serial);
                }
                else {
                    userDetails.setJoinid(serial);
                }               
                userDetails.setJoinid(serial);
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
//        }62
        
        @RequestMapping (value = "/getdemographics", headers = "Accept=*/*", method = RequestMethod.GET)
        public @ResponseBody ResponseEntity getAllDetails (){
            Iterable<UserDemographics> demographicses = userDemographicsRepository.findAll();
            List <UserDemographics> demographicsList = new ArrayList<>();
            if (demographicsList != null){
                for (UserDemographics demographics : demographicses){
                    demographicsList.add(demographics);                    
                }
            }
            return new ResponseEntity(demographicses, HttpStatus.OK);
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
        
        @RequestMapping (value = "/userDemograph", headers = "Accept=*/*", method = RequestMethod.GET)
        public @ResponseBody ResponseEntity<Object> getUserDemographs (@RequestParam("id") Long id){
            UserDemographics demographics = userDemographicsRepository.findOne(id);   
            UserFeedback feedback = userFeedbackRepository.findOne(id);           
//            String s = new Gson().toJson(demographics);
//            String s2 = new Gson().toJson(feedback);
//            s = s.concat(s2);
            List<Object> objects = new ArrayList<>();
            objects.add(demographics);
            objects.add(feedback);
            return new ResponseEntity (objects, HttpStatus.OK);
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
