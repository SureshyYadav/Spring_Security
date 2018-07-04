package com.spring_security.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	@RequestMapping(value={"/","/welcome"},method=RequestMethod.GET)
	public ModelAndView welcomePage(){
		ModelAndView mv=new ModelAndView();
		mv.addObject("title", "Spring_Security hello world");
		mv.addObject("message", "This is welcome page");
		mv.setViewName("hello");
		return mv;
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public ModelAndView adminLoginPage(){
		ModelAndView mv=new ModelAndView();
		mv.addObject("title", "Spring_Security hello world");
		mv.addObject("message", "This is protected page");
		mv.setViewName("admin");
		return mv;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value="error",required=false) String error,@RequestParam(value="logout",required=false) String logout){
		ModelAndView mv=new ModelAndView();
		if (error != null) {
			mv.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			mv.addObject("msg", "You've been logged out successfully.");
		}
		mv.setViewName("login");
		
		return mv;
		
	}
	//for 403 access denied page
		@RequestMapping(value = "/403", method = RequestMethod.GET)
		public ModelAndView accesssDenied() {

		  ModelAndView model = new ModelAndView();

		  //check if user is login
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		  }

		  model.setViewName("403");
		  return model;

		}
	
	

}
