package com.TMS.Controller;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.TMS.Entity.addproject;
import com.TMS.Entity.module;
import com.TMS.Entity.project_activity;
import com.TMS.Entity.project_assign;
import com.TMS.serviceclass.ActivityService;
import com.TMS.serviceclass.EmployeeProfileService;
import com.TMS.serviceclass.ModuleService;
import com.TMS.serviceclass.ProjectService;

@RestController
public class projecttaskassign {

	@Autowired
	ProjectService projectservice;
	
	@Autowired
	ModuleService moduleservice;
	
	

	@Autowired
	EmployeeProfileService empProfileService;
	
	
	@Autowired
	ActivityService activityservice;
	
	@RequestMapping(value="/project_task_assign",method=RequestMethod.POST)
	public ModelAndView project(Model model,HttpSession session)
	{
		String id=(String) session.getAttribute("username");
		
		List<project_assign> projectassignlist=projectservice.projectassigninloginid(id);
		model.addAttribute("projectassignlist", projectassignlist);		
		model.addAttribute("projectassignlistsize", projectassignlist.size());
		
	
		
		List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
		model.addAttribute("projectlist", projectlist);
		
		
		List<module>moduleshow=moduleservice.display();
		model.addAttribute("moduleshowdetails", moduleshow);
		
		model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
		
		model.addAttribute("projectshow", this.projectservice.show());
		
		model.addAttribute("activityshowdets", this.activityservice.show());
		
		List<project_activity> projectactivitylist=projectservice.project_activityslist();
		model.addAttribute("projectactivitylist", projectactivitylist);
		
		
		return new ModelAndView("ProjectDetails/projecttaskassignshow");
	}
	
	
	
}
