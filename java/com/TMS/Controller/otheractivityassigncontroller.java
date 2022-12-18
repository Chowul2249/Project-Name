package com.TMS.Controller;

import java.sql.Timestamp;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TMS.Entity.EmpProfile;
import com.TMS.Entity.Otheractivtiy;
import com.TMS.Entity.otheractivity_assign;
import com.TMS.Entity.otheractivity_change;
import com.TMS.serviceclass.EmployeeProfileService;
import com.TMS.serviceclass.OtherActivityAssignService;
import com.TMS.serviceclass.OtheractivityService;


@RestController  
public class otheractivityassigncontroller {
	
	
	@Autowired
	EmployeeProfileService empProfileService;
	
	@Autowired
	OtheractivityService otheractivityService;
	
	
	@Autowired 
	OtherActivityAssignService otheractivityassignservice;

	@RequestMapping(value = "/otheractivityassign", method = RequestMethod.POST)
	public ModelAndView activity(Model model , HttpSession session) {
		
		String id=(String) session.getAttribute("username");
		
		List<EmpProfile> Employeelist=empProfileService.basedonreportid(id);
		model.addAttribute("EmployeeList", Employeelist);
		System.out.println("size1"+Employeelist.size());
		List<otheractivity_assign> otheractivityassignlist=otheractivityassignservice.show(id);
		model.addAttribute("otheractivityassignlist", otheractivityassignlist);
		
		System.out.println("name"+otheractivityassignlist.size());
		
		List<Otheractivtiy> otheractivityList=otheractivityService.show();
		model.addAttribute("otheractivityList",otheractivityList);
		
		System.out.println("size"+otheractivityList.size());
		return new ModelAndView("ProjectDetails/otheractivityassign");
	} 
	@PostMapping(value="/saveotheractivityassign")
	public ModelAndView otheractivityassign(Model model, @RequestParam int empb_code, 
														 @RequestParam int otheract_id,
														 @RequestParam String otheractassign_status,
														 HttpSession session)
	{
		otheractivity_assign saveotheractivityassignAssign=new otheractivity_assign();
		
		saveotheractivityassignAssign.setEmpb_id(empb_code);
		saveotheractivityassignAssign.setOtheract_id(otheract_id);
		saveotheractivityassignAssign.setOtheractassign_status(otheractassign_status);
		
		otheractivityassignservice.addotheractivityassign(saveotheractivityassignAssign);
		
		String id=(String) session.getAttribute("username");
		
		List<EmpProfile> Employeelist=empProfileService.basedonreportid(id);
		model.addAttribute("EmployeeList", Employeelist);
		
		List<otheractivity_assign> otheractivityassignlist=otheractivityassignservice.show(id);
		model.addAttribute("otheractivityassignlist", otheractivityassignlist);
		
		List<Otheractivtiy> otheractivityList=otheractivityService.show();
		model.addAttribute("otheractivityList",otheractivityList);
		
		
		
		
		return new ModelAndView("ProjectDetails/otheractivityassign");
	}
	@PostMapping(value="/editotheractivityassignid")
	public ModelAndView editpage(Model model, @RequestParam int id, HttpSession session)
	{
		
         String empid=(String) session.getAttribute("username");
		
		List<EmpProfile> Employeelist=empProfileService.basedonreportid(empid);
		model.addAttribute("EmployeeList", Employeelist);
		
		List<otheractivity_assign> otheractivityassignlist=otheractivityassignservice.show(empid);
		model.addAttribute("otheractivityassignlist", otheractivityassignlist);
		
		List<Otheractivtiy> otheractivityList=otheractivityService.show();
		model.addAttribute("otheractivityList",otheractivityList);
		
		
		
		otheractivity_assign list=otheractivityassignservice.edit(id);
		model.addAttribute("object", list);
		
		return new ModelAndView("ProjectDetails/otheractivityassignedit");
	}
	@PostMapping(value="/cancelOAS")
	public ModelAndView calcel(Model model, HttpSession session)
	{
		 	String empid=(String) session.getAttribute("username");
			
			List<EmpProfile> Employeelist=empProfileService.basedonreportid(empid);
			model.addAttribute("EmployeeList", Employeelist);
			
			List<otheractivity_assign> otheractivityassignlist=otheractivityassignservice.show(empid);
			model.addAttribute("otheractivityassignlist", otheractivityassignlist);
			
			List<Otheractivtiy> otheractivityList=otheractivityService.show();
			model.addAttribute("otheractivityList",otheractivityList);
			
		
		return new ModelAndView("ProjectDetails/otheractivityassign");
	}
	@PostMapping(value="/saveotheractivityassignupdate")
	public ModelAndView update(Model model, @RequestParam int oactivityassignid,
											@RequestParam int empb_code, 
											@RequestParam int otheract_id,										
											@RequestParam String otheractassign_status,
											HttpSession session )
	{
		otheractivity_assign updatefield=new otheractivity_assign();
		
		
		
		updatefield.setOtheractassign_id(oactivityassignid);
		updatefield.setEmpb_id(empb_code);
		updatefield.setOtheract_id(otheract_id);
		updatefield.setOtheractassign_status(otheractassign_status);
		
		if(updatefield.getOtheractassign_status().equals("Y"))
		{
			otheractivity_change change=new otheractivity_change();
			Timestamp ts=new Timestamp(System.currentTimeMillis());
			change.setOtheractassignid(oactivityassignid);
			change.setOtheractchng_status(otheractassign_status);
			change.setOtheractchngdate(ts);
			otheractivityassignservice.addchangedate(change);
		}

		String empid=(String) session.getAttribute("username");
		
		List<EmpProfile> Employeelist=empProfileService.basedonreportid(empid);
		model.addAttribute("EmployeeList", Employeelist);
		
		List<otheractivity_assign> otheractivityassignlist=otheractivityassignservice.show(empid);
		model.addAttribute("otheractivityassignlist", otheractivityassignlist);
		
		List<Otheractivtiy> otheractivityList=otheractivityService.show();
		model.addAttribute("otheractivityList",otheractivityList);
		
		otheractivityassignservice.update(updatefield);
		
		return new ModelAndView("ProjectDetails/otheractivityassign");
	}
}
