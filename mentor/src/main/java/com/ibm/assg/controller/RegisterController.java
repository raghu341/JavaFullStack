package com.ibm.assg.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibm.assg.model.User;
import com.ibm.assg.service.EmailService;
import com.ibm.assg.service.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class RegisterController {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserService userService;
	private EmailService emailService;
	
	/*public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        registry.addViewController("/register").setViewName("templates/register.html");
        registry.addViewController("/confirm").setViewName("templates/confirm.html");
        //registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }*/
	
	@Autowired
	public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder,
			UserService userService, EmailService emailService) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userService = userService;
		this.emailService = emailService;
	}
	
	// Return registration form template
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
	 public ModelAndView login(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("login");
	  return model;
	 }
	
	@RequestMapping(value= {"/home"}, method=RequestMethod.GET)
	 public ModelAndView home(User user) {
	  ModelAndView model = new ModelAndView();
	  /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  User user = userService.findByEmail(auth.getName());
	  
	  model.addObject("userName", user.getFirstName() + " " + user.getLastName());*/
	  model.addObject("user",user);
	  model.setViewName("home");
	  return model;
	 }
	
	
	
	@RequestMapping(value= {"/searchtra"}, method=RequestMethod.GET)
	 public ModelAndView search(User user) {
	  ModelAndView model = new ModelAndView();
	  /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  User user = userService.findByEmail(auth.getName());
	  
	  model.addObject("userName", user.getFirstName() + " " + user.getLastName());*/
	  model.addObject("user",user);
	  model.setViewName("searchtra");
	  return model;
	 }
	
	@RequestMapping(value= {"/userlogin"}, method=RequestMethod.GET)
	 public ModelAndView userlogin(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("userlogin");
	  return model;
	 }
	 
	@RequestMapping(value= {"/currenttraining"}, method=RequestMethod.GET)
	 public ModelAndView currenttraining(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("currenttraining");
	  return model;
	 }
	
	@RequestMapping(value= {"/completedtraining"}, method=RequestMethod.GET)
	 public ModelAndView completedtraining(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("completedtraining");
	  return model;
	 }
	@RequestMapping(value= {"/payment_angular"}, method=RequestMethod.GET)
	 public ModelAndView paymentangular(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("payment_angular");
	  return model;
	 }
	@RequestMapping(value= {"/angular"}, method=RequestMethod.GET)
	 public ModelAndView b2b2c(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("angular");
	  return model;
	 }
	
	@RequestMapping(value= {"/payment_bigdata"}, method=RequestMethod.GET)
	 public ModelAndView paymentbigdata(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("payment_bigdata");
	  return model;
	 }
	@RequestMapping(value= {"/payment_testing"}, method=RequestMethod.GET)
	 public ModelAndView paymenttesting(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("payment_testing");
	  return model;
	 }
	@RequestMapping(value= {"/payment_java"}, method=RequestMethod.GET)
	 public ModelAndView paymentjava(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("payment_java");
	  return model;
	 }
	@RequestMapping(value= {"/bigdata"}, method=RequestMethod.GET)
	 public ModelAndView bigdata(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("bigdata");
	  return model;
	 }
	@RequestMapping(value= {"/search"}, method=RequestMethod.GET)
	 public ModelAndView searchcourse(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("search");
	  return model;
	 }
	@RequestMapping(value= {"/testing"}, method=RequestMethod.GET)
	 public ModelAndView testing(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("testing");
	  return model;
	 }
	@RequestMapping(value= {"/java"}, method=RequestMethod.GET)
	 public ModelAndView java(User user) {
	  ModelAndView model = new ModelAndView();
	  model.addObject("user", user);
	  model.setViewName("java");
	  return model;
	 }
	 
	
	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
				
		// Lookup user in database by e-mail
		User userExists = userService.findByEmail(user.getEmail());
		
		System.out.println(userExists);
		
		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("register");		
		} else { // new user so we create user and send confirmation e-mail
					
			// Disable user until they click on confirmation link in email
		    user.setEnabled(false);
		      
		    // Generate random 36-character string token for confirmation link
		    user.setConfirmationToken(UUID.randomUUID().toString());
		        
		    userService.saveUser(user);
				
			String appUrl = request.getScheme() + "://" + request.getServerName();
			
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
					+ appUrl + "/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("noreply@domain.com");
			
			emailService.sendEmail(registrationEmail);
			
			modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
			modelAndView.setViewName("register");
		}
			
		return modelAndView;
	}
	
	// Process confirmation link
	@RequestMapping(value="/confirm", method = RequestMethod.GET)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, @RequestParam("token") String token) {
			
		User user = userService.findByConfirmationToken(token);
			
		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getConfirmationToken());
		}
			
		modelAndView.setViewName("confirm");
		return modelAndView;		
	}
	
	// Process confirmation link
	@RequestMapping(value="/confirm", method = RequestMethod.POST)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
				
		modelAndView.setViewName("confirm");
		
		Zxcvbn passwordCheck = new Zxcvbn();
		
		Strength strength = passwordCheck.measure(requestParams.get("password"));
		
		if (strength.getScore() < 3) {
			//modelAndView.addObject("errorMessage", "Your password is too weak.  Choose a stronger one.");
			bindingResult.reject("password");
			
			redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

			modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
			System.out.println(requestParams.get("token"));
			return modelAndView;
		}
	
		// Find the user associated with the reset token
		User user = userService.findByConfirmationToken(requestParams.get("token"));

		// Set new password
		user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

		// Set user to enabled
		user.setEnabled(true);
		
		// Save user
		userService.saveUser(user);
		
		modelAndView.addObject("successMessage", "Your password has been set!");
		return modelAndView;		
	}
	
}