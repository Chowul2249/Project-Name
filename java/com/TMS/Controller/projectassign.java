package com.TMS.Controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TMS.serviceclass.ProjectService;



@RestController
public class projectassign {

	
	@Autowired
	ProjectService projectservice;
	
	
	@RequestMapping(value="/project_assign",method=RequestMethod.POST)
	public ModelAndView project(Model model,HttpSession session)
	{
		String id=(String) session.getAttribute("username");
		
		model.addAttribute("list", this.projectservice.showbasedonid(id));
		
		return new ModelAndView("ProjectDetails/projectassignshow");
	}
	
	
	
}
