package com.TMS.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TMS.Entity.EmpProfile;
import com.TMS.Entity.activity;
import com.TMS.Entity.addproject;
import com.TMS.Entity.emp_changedate;
import com.TMS.Entity.module;
import com.TMS.Entity.project_activity;
import com.TMS.Entity.project_assign;
import com.TMS.Entity.projectdetails;
import com.TMS.serviceclass.ActivityService;
import com.TMS.serviceclass.EmployeeProfileService;
import com.TMS.serviceclass.ModuleService;
import com.TMS.serviceclass.ProjectService;

@RestController
public class projectassignEMP {

	//--------------------------------Project Assign--------------------------------------------
	
	@Autowired
	ProjectService projectservice;
	
	@Autowired
	ModuleService moduleservice;
	
	

	@Autowired
	EmployeeProfileService empProfileService;
	
	
	@Autowired
	ActivityService activityservice;
	
	@PostMapping(value="/project_assign_to_Emp")
	public ModelAndView project_assignpage(Model model,HttpSession session)
	{
		
		
		String id=(String) session.getAttribute("username");
			
		List<EmpProfile> Employeelist=empProfileService.basedonreportid(id);
		model.addAttribute("EmployeeList", Employeelist);
		
		
		
		List<addproject>projectlist=projectservice.showbasedonassignemployee(id);
		model.addAttribute("projectlist", projectlist);
	/*	
		addproject projectlist1=projectservice.findbyid(projectlist.get(0).getId());
		System.out.println("StartDate"+projectlist1.getStartdate());
		model.addAttribute("startdate", projectlist1.getStartdate());*/
		model.addAttribute("startdate","-");
		model.addAttribute("enddate", "-");
		model.addAttribute("modulestartdate","-");
		model.addAttribute("moduleenddate", "-");
		
		List<project_assign> projectassignlist=projectservice.projectassignshow(id);
		model.addAttribute("projectassignlist", projectassignlist);		
		model.addAttribute("projectassignlistsize", projectassignlist.size());
		
		List<module>moduleshow=moduleservice.display();
		model.addAttribute("moduleshowdetails", moduleshow);
		
		model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
		
		model.addAttribute("projectshow", this.projectservice.show());
		
		
		model.addAttribute("activityshowdets", this.activityservice.show());
		
		List<project_activity> projectactivitylist=projectservice.project_activityslist();
		model.addAttribute("projectactivitylist", projectactivitylist);
		
		List<EmpProfile> employeelist=empProfileService.show();
		model.addAttribute("employeelist", employeelist);
		
		return new ModelAndView("ProjectDetails/projectassign"); 
	}
	@RequestMapping(value="/loadprojectassingmodule")
	public ModelAndView loadmodule(Model model,@RequestParam int id, HttpSession session)
	{
		
		/*System.out.println("Module Controller");*/
		List<module>moduleshow=moduleservice.showbasedonprojectid(id);
		model.addAttribute("moduleshow", moduleshow);
		
		String id1=(String) session.getAttribute("username");
		
		addproject projectlist=projectservice.findbyid(id);
		/*System.out.println(projectlist.getStartdate()+"...startdate");
		System.out.println(projectlist.getEnddate()+"...enddate");*/
		
		model.addAttribute("projectlistdate", projectlist);
		/*model.addAttribute("projectliststart", projectlist.getStartdate());
		model.addAttribute("projectlistend", projectlist.getEnddate());*/
		SimpleDateFormat sp=new SimpleDateFormat("dd/MM/yyyy");
		String start=sp.format(projectlist.getStartdate());
		String end=sp.format(projectlist.getEnddate());
		model.addAttribute("startdate",start);
		model.addAttribute("enddate", end);
		
		return new ModelAndView("ProjectDetails/projectassign");
		
	}
	@RequestMapping(value="/loadprojectassingactivity")
	public ModelAndView loadactivity(Model model,@RequestParam int id,@RequestParam int projectid)
	{
		
		List<activity> activityshow=activityservice.showbasedonactivity(id,projectid);
		model.addAttribute("activityshow", activityshow);
		
		projectdetails projectdetailslist=projectservice.findbymoduleid(id, projectid);
		
		SimpleDateFormat sp=new SimpleDateFormat("dd/MM/yyyy");
		
		String start=sp.format(projectdetailslist.getProjectdet_adddt());
		String end=sp.format(projectdetailslist.getProjectdet_closedt());
		model.addAttribute("modulestartdate",start);
		model.addAttribute("moduleenddate", end);
		
		
		
		
		return new ModelAndView("ProjectDetails/projectassign");	
	}
	@PostMapping(value="/addprojectassign")
	public ModelAndView addprojectassign(Model model, HttpServletRequest request,
													  HttpServletResponse response,
													  @RequestParam int projectid,
													  @RequestParam int moduleid,
													  @RequestParam int activityid,
													  @RequestParam String projectfrmdt,
													  @RequestParam String projecttodt,
													  @RequestParam int empbcode,
													  HttpSession session)
	{
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		
		
		//@SuppressWarnings("unused")
		Date projectfromdate = null;
		try {
			projectfromdate = (Date) format.parse(projectfrmdt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");		
		
		//@SuppressWarnings("unused")
		Date projecttodate = null;
		try {
			projecttodate = (Date) format1.parse(projecttodt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int projectdetid=projectservice.getprojectdelid(projectid ,moduleid);
		
		project_assign projectassignsave=new project_assign();
		
		
		int projectact_id=projectservice.getprojectactid(projectdetid,activityid);
		
		projectassignsave.setProjectdelid(projectdetid);
		projectassignsave.setActivityid(projectact_id);
		projectassignsave.setEmpcode(empbcode);
		projectassignsave.setProjectassignfrmdt(projectfromdate);
		projectassignsave.setProjectassigntodt(projecttodate);
		
		String projectcomplete="N";
		String projectcompletedatechange="N";
		projectassignsave.setProjectassigncomplt(projectcomplete);
		projectassignsave.setProjectassigncompdtchng(projectcompletedatechange);
		
		
		
		projectservice.projectassignadd(projectassignsave);
		
		String id=(String) session.getAttribute("username");
		
		List<EmpProfile> Employeelist=empProfileService.basedonreportid(id);
		model.addAttribute("EmployeeList", Employeelist);
		
		
		List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
		model.addAttribute("projectlist", projectlist);
		
		List<project_assign> projectassignlist=projectservice.projectassignshow(id);
		model.addAttribute("projectassignlist", projectassignlist);		
		model.addAttribute("projectassignlistsize", projectassignlist.size());
		
		List<module>moduleshow=moduleservice.display();
		model.addAttribute("moduleshowdetails", moduleshow);
		
		model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
		
		model.addAttribute("projectshow", this.projectservice.show());
		
		model.addAttribute("activityshowdets", this.activityservice.show());
		
		List<project_activity> projectactivitylist=projectservice.project_activityslist();
		model.addAttribute("projectactivitylist", projectactivitylist);
		
		List<EmpProfile> employeelist=empProfileService.show();
		model.addAttribute("employeelist", employeelist);
		
		return new ModelAndView("ProjectDetails/projectassign");
	}
	@RequestMapping(value="/editprojectassign")
	public ModelAndView addprojectassign(Model model,@RequestParam int id,HttpSession session)
	{
		
		model.addAttribute("startdate","-");
		model.addAttribute("enddate", "-");
		model.addAttribute("modulestartdate","-");
		model.addAttribute("moduleenddate", "-");
		
		
		
		String empcode=(String) session.getAttribute("username");
		
		List<EmpProfile> Employeelist=empProfileService.basedonreportid(empcode);
		model.addAttribute("EmployeeList", Employeelist);
		
		
		project_assign pa=projectservice.editprojectassign(id);
		model.addAttribute("projectassign", pa);
			
        projectdetails pd=projectservice.projectdetailsedit(pa.projectdelid);
		
		addproject ad=projectservice.edit(pd.getProject_id());
		
		
		SimpleDateFormat sp=new SimpleDateFormat("dd/MM/yyyy");
		String start=sp.format(ad.getStartdate());
		String end=sp.format(ad.getEnddate());
		model.addAttribute("startdate",start);
		model.addAttribute("enddate", end);	
		
        projectdetails projectdetailslist=projectservice.findbymoduleid(pd.getModule_id(),pd.getProject_id());
		
		SimpleDateFormat sp1=new SimpleDateFormat("dd/MM/yyyy");
		
		String start1=sp1.format(projectdetailslist.getProjectdet_adddt());
		String end1=sp1.format(projectdetailslist.getProjectdet_closedt());
		model.addAttribute("modulestartdate",start1);
		model.addAttribute("moduleenddate", end1);
		
		model.addAttribute("projectshow", this.projectservice.showbasedonassignemployee(empcode));
		
		model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
		
		List<module>moduleshow=moduleservice.editshowmoduledtsbasedonprojectid(pa.getProjectdelid());
		model.addAttribute("moduleshow", moduleshow);	
		
		model.addAttribute("projectactivity", this.projectservice.showprojectactivity());
		
		model.addAttribute("activityshow", this.activityservice.showactivitybasedonprojectdetid(pa.getProjectdelid()));
		
		
		return new ModelAndView("ProjectDetails/projectassignedit");	
	}
	
	@PostMapping(value="/updateprojectassignid")
	public ModelAndView updatepage(Model model, 
			  HttpServletRequest request,
			  HttpServletResponse response,
			  @RequestParam int projectassignid,
			  @RequestParam int projectid,
			  @RequestParam int moduleid,
			  @RequestParam int activityid,
			  @RequestParam String projectfrmdt,
			  @RequestParam String projecttodt, 
			  @RequestParam int empbcode,
			  HttpSession session)
	{
				
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		
		 
		
		Date projectfromdate = null;
		try {
			projectfromdate = (Date) format.parse(projectfrmdt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");		
		
		
		
		Date projecttodate = null;
		try {
			projecttodate = (Date) format1.parse(projecttodt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int projectdetid=projectservice.getprojectdelid(projectid ,moduleid);
		
		int projectact_id=projectservice.getprojectactid(projectdetid,activityid);
		
		project_assign PAUpdate=new project_assign();
		
		PAUpdate.setProjectassign(projectassignid);
		PAUpdate.setProjectdelid(projectdetid);
		PAUpdate.setActivityid(projectact_id);
		PAUpdate.setEmpcode(empbcode);
		PAUpdate.setProjectassignfrmdt(projectfromdate);
		
		PAUpdate.setProjectassigntodt(projecttodate);
		
		
		project_assign list=projectservice.editprojectassign(projectassignid);
		Date todate=list.getProjectassigntodt();
		
		
		if(!projecttodate.equals(todate))
		{
			//projectservice.setprojectchangedateinY(projectassignid);
			
			emp_changedate ed=new emp_changedate();
			ed.setProjectassign(projectassignid);
			ed.setEmpchaagedate(todate);
			projectservice.addempldatechange(ed);
		}
		
		projectservice.updateprojectassign(PAUpdate);
		
		String id=(String) session.getAttribute("username");
		
		List<EmpProfile> Employeelist=empProfileService.basedonreportid(id);
		model.addAttribute("EmployeeList", Employeelist);
		
		
		
		List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
		model.addAttribute("projectlist", projectlist);
		
		List<project_assign> projectassignlist=projectservice.projectassignshow(id);
		model.addAttribute("projectassignlist", projectassignlist);		
		model.addAttribute("projectassignlistsize", projectassignlist.size());
		
		
		model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
		
		model.addAttribute("projectshow", this.projectservice.show());
		
		List<project_activity> projectactivitylist=projectservice.project_activityslist();
		model.addAttribute("projectactivitylist", projectactivitylist);
		
		
		List<EmpProfile> employeelist=empProfileService.show();
		model.addAttribute("employeelist", employeelist);
		

		model.addAttribute("activityshowdets", this.activityservice.show());
		

		List<module>moduleshow1=moduleservice.display();
		model.addAttribute("moduleshowdetails", moduleshow1);
		

		return new ModelAndView("ProjectDetails/projectassign");
	}
	
}
