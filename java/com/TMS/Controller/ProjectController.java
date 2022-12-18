package com.TMS.Controller;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TMS.Entity.City;
import com.TMS.Entity.Country;
import com.TMS.Entity.State;
import com.TMS.Entity.activity;
import com.TMS.Entity.addproject;
import com.TMS.Entity.module;
import com.TMS.Entity.projectType;
import com.TMS.Entity.project_activity;
import com.TMS.Entity.project_datechange;
import com.TMS.Entity.projectdetails;
import com.TMS.serviceclass.ActivityService;
import com.TMS.serviceclass.CityService;
import com.TMS.serviceclass.CountryService;
import com.TMS.serviceclass.EmployeeProfileService;
import com.TMS.serviceclass.ModuleService;
import com.TMS.serviceclass.ProjectDateChangeservice;
import com.TMS.serviceclass.ProjectService;
import com.TMS.serviceclass.StateService;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
@RestController
public class ProjectController {

	
	@Autowired
	CountryService countryService;
	
	@Autowired
	EmployeeProfileService eps;
	
	@Autowired
	ProjectService projectservice;
	
	@Autowired
	CityService cityservice;
	
	@Autowired
	CountryService countryservice;
	
	@Autowired
	StateService stateservice;
	
	@Autowired
	ProjectDateChangeservice ProjectDateChangeservice;
	
	@Autowired
	ModuleService moduleservice;
	
	@Autowired
	ActivityService activityservice;
	
	
	@PostMapping(value="/canceladdprojectdetails")
	public ModelAndView page(Model model)
	{
		model.addAttribute("list", this.projectservice.show());
		List<Country> countryList = countryservice.findAllByAscendingOrder("countryName", "Y");
		model.addAttribute("countryList",countryList);
		return new ModelAndView("ProjectDetails/addproject"); 
	}
	
	
	@RequestMapping(value="/projectdetails",method=RequestMethod.POST)
	public ModelAndView projectdetails(Model model,@ModelAttribute addproject pd)
	{
	
			try{
			pd.getName();
			pd.getClient();
			@SuppressWarnings("unused")
			String end=pd.getEndpoint();
			pd.getStartdate();
			pd.getEnddate();
			pd.getExtend();
			pd.getContactnumber();
			@SuppressWarnings("unused")
			String c=pd.getCountry();
			@SuppressWarnings("unused")
			String s=pd.getState();
			@SuppressWarnings("unused")
			String cc=pd.getCity();
			Timestamp ts=new Timestamp(System.currentTimeMillis());
			pd.setCurrentdate(ts);
			pd.getStatus();
			
			projectservice.add(pd);
			model.addAttribute("list",  projectservice.show());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			model.addAttribute("list", this.projectservice.show());
			return new ModelAndView("ProjectDetails/addproject"); 
	}
	
	
	@RequestMapping(value="/Project",method=RequestMethod.POST)
	public ModelAndView project(Model model)
	{
	
		model.addAttribute("list", this.projectservice.show());
		List<Country> countryList = countryservice.findAllByAscendingOrder("countryName", "Y");
		model.addAttribute("countryList",countryList);
		return new ModelAndView("ProjectDetails/addproject");
	}
	@RequestMapping(value="/Upstate")
	public ModelAndView Upstate(Model model,@RequestParam int  cno)
	{
			
		List<State> stateList = stateservice.getListbyCountryID(cno);
		model.addAttribute("stateList",stateList);
		return new ModelAndView("ProjectDetails/addproject");
	}
	@RequestMapping(value="/Upcity")
	public ModelAndView Upcity(Model model,@RequestParam int id)
	{
			
		List<City> cityList = cityservice.getCityByState(id);
		model.addAttribute("cityList",cityList);
		return new ModelAndView("ProjectDetails/addproject");
	}
	
	@RequestMapping(value="/del")
	public ModelAndView deletee(Model model,@RequestParam int id) throws MySQLIntegrityConstraintViolationException
	{
		projectservice.delete(id);
		//countryservice.deleteById(id);
		model.addAttribute("list", this.projectservice.show());
		return new ModelAndView("ProjectDetails/addproject"); 
	}
	
	@RequestMapping(value="/ps",method=RequestMethod.POST)
	public ModelAndView ps(Model model)
	{
		model.addAttribute("list", this.projectservice.show());
		return new ModelAndView("ProjectDetails/projectshow"); 
	}
	
	
	@RequestMapping(value = "/up", method = RequestMethod.POST)
	public ModelAndView up(@RequestParam int id,Model model){
		
		//System.out.println(id);
		addproject list=projectservice.edit(id);
		model.addAttribute("object", list);
		
		int country=Integer.valueOf((list.getCountry().toString())).intValue();
		int state=Integer.valueOf((list.getState().toString())).intValue();
		int city=Integer.valueOf((list.getCity().toString())).intValue();
		
		List<Country> countryList = countryservice.findAllByAscendingOrder("countryName", "Y");
		model.addAttribute("countryList",countryList);
		model.addAttribute("country", country);
		
		
		List<State> stateList = stateservice.getListbyCountryID(country);
		model.addAttribute("stateList",stateList);
		model.addAttribute("state", state);
		
		List<City> cityList = cityservice.getCityByState(state);
		model.addAttribute("cityList",cityList);
		model.addAttribute("city", city);
		
		model.addAttribute("list", this.projectservice.show());
		
		return new ModelAndView("ProjectDetails/addprojectedit");	
	}
	

	@RequestMapping(value = "/projectdetailsedit", method = RequestMethod.POST)
	public ModelAndView projectdetailsedit(@ModelAttribute addproject pd,Model model){
		

		try{
		pd.getId();
		pd.getName();
		pd.getClient();
		pd.getEndpoint();
		pd.getStartdate();
		pd.getEnddate();
		pd.getExtend();
		pd.getContactnumber();
		pd.getCountry();
		pd.getState();
		pd.getCity();
		Timestamp ts=new Timestamp(System.currentTimeMillis());
		pd.setCurrentdate(ts);
		pd.getStatus();
		if(pd.getExtend().equals("Y"))
		{
			addproject list=projectservice.edit(pd.getId());
			project_datechange pc=new project_datechange();
			pc.setProject_id(pd.getId());
			
			pc.setProkchngdt_start(list.getStartdate());
			pc.setProkchngdt_end(list.getEnddate());
			pc.setProkchngdt_changedate(ts);
			ProjectDateChangeservice.add(pc);
		}
		projectservice.update(pd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("list", this.projectservice.show());
		return new ModelAndView("ProjectDetails/addproject"); 
	}
	
//-----------------Project Type---------------------------
	
	
	@RequestMapping(value="/project_type",method=RequestMethod.POST)
	public ModelAndView project_type(Model model)
	{
		model.addAttribute("projecttypelist", this.projectservice.display());
		return new ModelAndView("ProjectDetails/project_type");
	}

	@PostMapping(value="/cancelprojecttype")
	public ModelAndView pagetypereset(Model model)
	{
		model.addAttribute("projecttypelist", this.projectservice.display());
		return new ModelAndView("ProjectDetails/project_type"); 
	}
	
	
	
	@RequestMapping(value="/saveprojecttype",method=RequestMethod.POST)
	public ModelAndView saveprojecttype(Model model,@ModelAttribute projectType saveprojecttype)
	{
		saveprojecttype.getProjecttype_details();
		saveprojecttype.getProjecttype_status();
		try{
			if(saveprojecttype.getProjecttype_details()!=null && saveprojecttype.getProjecttype_status()!=null)	
			{
				projectservice.save(saveprojecttype);
				
			}
		
			model.addAttribute("projecttypelist", this.projectservice.display());
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
		return new ModelAndView("ProjectDetails/project_type");
	}
	
	@RequestMapping(value="/deleteProjecttype",method=RequestMethod.POST)
	public ModelAndView deleteProjecttype(Model model,@RequestParam int id)
	{
		try{
			projectservice.projecttypedelete(id);
			model.addAttribute("projecttypelist", this.projectservice.display());
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
		
		return new ModelAndView("ProjectDetails/project_type");
	}
	
	
	@RequestMapping(value = "/editprojecttype", method = RequestMethod.POST)
	public ModelAndView edit(@RequestParam int id,Model model){
		
		projectType list=projectservice.projectTypeshow(id);
		model.addAttribute("object", list);
		model.addAttribute("projecttypelist", this.projectservice.display());
		return new ModelAndView("ProjectDetails/project_type_update");
	} 
	
	@RequestMapping(value="/updateprojecttype",method=RequestMethod.POST)
	public ModelAndView project_type_update(Model model,@ModelAttribute projectType updateprojecttype)
	{
		updateprojecttype.getId();
		updateprojecttype.getProjecttype_details();
		updateprojecttype.getProjecttype_status();
		try
		{
			projectservice.projecttypeupdate(updateprojecttype);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("projecttypelist", this.projectservice.display());
		return new ModelAndView("ProjectDetails/project_type");
	}
	
	//-----------------Project module merge Details---------------------------
	
	
			@RequestMapping(value="/Project_Details",method=RequestMethod.POST)
			public ModelAndView Project_Details(Model model)
			{
				
				List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
				model.addAttribute("projectlist", projectlist);
				
				
				model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
				
				List<module>moduleshow=moduleservice.display();
				model.addAttribute("moduleshow", moduleshow);
				
				model.addAttribute("projectshow", this.projectservice.show());
				
				
				
				
				return new ModelAndView("ProjectDetails/projectdetail");
			}
			
			
			
			@RequestMapping(value="/projectdetailsloadmodule",method=RequestMethod.POST)
			public ModelAndView projectdetailsloadmodule(Model model,@RequestParam int id)
			{
				
				List<module> li=projectservice.projectdetailsloadmodule(id);
				model.addAttribute("moduleload", li);
				
				
				
				
				return new ModelAndView("ProjectDetails/projectdetail");
			}
			
			
			@PostMapping(value="/cancelPD")
			public ModelAndView page2(Model model)
			{
				List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
				model.addAttribute("projectlist", projectlist);
				
				List<module>modulelist=projectservice.modulefindAllByAscendingOrder();
				model.addAttribute("modulelist", modulelist);
				
				
				List<module>moduleshow=moduleservice.display();
				model.addAttribute("moduleshow", moduleshow);
				
				model.addAttribute("projectshow", this.projectservice.show());
				
				model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
				
				
				return new ModelAndView("ProjectDetails/projectdetail");
			}
			@SuppressWarnings("unused")
			@RequestMapping(value="/addProject_Details")
			public ModelAndView addProject_Details(Model model,@ModelAttribute projectdetails projectdetails,HttpServletResponse response) throws IOException
			{
				projectdetails.getModule_id();
				projectdetails.getProject_id();
				projectdetails.getProjectdet_status();
				projectdetails.getProjectdet_adddt();
				projectdetails.getProjectdet_closedt();
				
				int a=projectservice.addprojectdetails(projectdetails);
			
				List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
				model.addAttribute("projectlist", projectlist);
				
				List<module>modulelist=projectservice.modulefindAllByAscendingOrder();
				model.addAttribute("modulelist", modulelist);
				
				
				List<module>moduleshow=moduleservice.display();
				model.addAttribute("moduleshow", moduleshow);
				
				model.addAttribute("projectshow", this.projectservice.show());
				
				model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
				
				
				return new ModelAndView("ProjectDetails/projectdetail");
			}
			
			@RequestMapping(value="/editProjectdetails",method=RequestMethod.POST)
			public ModelAndView editProjectdetails(Model model,@RequestParam int id)
			{				
				
				model.addAttribute("projectshow", this.projectservice.show());
				
				model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
				
				projectdetails pd=projectservice.projectdetailsedit(id);
				model.addAttribute("editprojectdetails", pd);
				
				List<module>moduleshow=projectservice.editprojectdetailsloadmodule(pd.getProject_id(), pd.getModule_id());
				model.addAttribute("moduleshow", moduleshow);
				
				return new ModelAndView("ProjectDetails/projectdetailedit");
			}
			
			@RequestMapping(value="/editProjectdetail",method=RequestMethod.POST)
			public ModelAndView editProjectdetails(Model model,@ModelAttribute projectdetails projectdetails)
			{
				projectdetails.getId();
				projectdetails.getModule_id();
				projectdetails.getProject_id();
				projectdetails.getProjectdet_status();
				projectdetails.getProjectdet_adddt();
				projectdetails.getProjectdet_closedt();
				try
				{
				projectservice.projectdetailupdate(projectdetails);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
				model.addAttribute("projectlist", projectlist);
				
				List<module>modulelist=projectservice.modulefindAllByAscendingOrder();
				model.addAttribute("modulelist", modulelist);
				
				
				List<module>moduleshow=moduleservice.display();
				model.addAttribute("moduleshow", moduleshow);
				
				model.addAttribute("projectshow", this.projectservice.show());
				
				model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
				
				
				return new ModelAndView("ProjectDetails/projectdetail");
			}
			//-----------------Project activity merge Details---------------------------
			
			@RequestMapping(value="/project_activity")
			public ModelAndView Project_activity(Model model)
			{
				List<projectdetails>projectdetails=projectservice.projectdetailsfindAllByAscendingOrder();
				model.addAttribute("projectdetailslist", projectdetails);
				
				List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
				model.addAttribute("projectlist", projectlist);
			
				List<activity>Activitydetails=activityservice.activityfindAllByAscendingOrder();
				model.addAttribute("activitdetaillist", Activitydetails);
				
				model.addAttribute("projectshow", this.projectservice.show());
				
				model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
				
				model.addAttribute("projectactivityshow", this.projectservice.project_activityshow());
	
				model.addAttribute("activityshow", this.activityservice.show());
				
				List<module>moduleshow=moduleservice.display();
				model.addAttribute("moduledisplay", moduleshow);
				
				
				List<Object[]> list=eps.showaddprojectdetail();
				model.addAttribute("objectshow", list);
			
				return new ModelAndView("ProjectDetails/project_activity");
			}
			
		
			@RequestMapping(value="/loadmodule")
			public ModelAndView loadmodule(Model model,@RequestParam int id)
			{
				
				List<module>moduleshow=moduleservice.display();
				model.addAttribute("moduleshow", moduleshow);
				
				List<Object[]>modulenamedistinct=projectservice.modulenamedistinct();
				model.addAttribute("modulenamedistinct", modulenamedistinct);
				
				
				model.addAttribute("projectdetailshow", this.projectservice.projectdetailmoduleshow(id));
				
				
				return new ModelAndView("ProjectDetails/project_activity");
				
			}
			
			
			@PostMapping(value="/cancelPA")
			public ModelAndView page4(Model model)
			{
				List<projectdetails>projectdetails=projectservice.projectdetailsfindAllByAscendingOrder();
				model.addAttribute("projectdetailslist", projectdetails);
								
				List<Object[]>modulenamedistinct=projectservice.modulenamedistinct();
				model.addAttribute("modulenamedistinct", modulenamedistinct);
				
				List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
				model.addAttribute("projectlist", projectlist);
				
				List<activity>Activitydetails=activityservice.activityfindAllByAscendingOrder();
				model.addAttribute("activitdetaillist", Activitydetails);
				
				model.addAttribute("projectshow", this.projectservice.show());
				
				model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
				
				model.addAttribute("projectactivityshow", this.projectservice.project_activityshow());
	
				model.addAttribute("activityshow", this.activityservice.show());
				
				List<module>moduleshow=moduleservice.display();
				model.addAttribute("moduledisplay", moduleshow);
				
				
				List<Object[]> list=eps.showaddprojectdetail();
				model.addAttribute("objectshow", list);
			
				return new ModelAndView("ProjectDetails/project_activity");
			}
			
			@RequestMapping(value="/saveprojectactivity",method=RequestMethod.POST)
			public ModelAndView projectactivity(Model model,@ModelAttribute project_activity saveprojectactivity)
			{
				
				saveprojectactivity.getProjectdet_id();
				saveprojectactivity.getActivity_id();
				try
				{
					
					
					projectservice.project_activityadd(saveprojectactivity);
					
					
				
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
				List<projectdetails>projectdetails=projectservice.projectdetailsfindAllByAscendingOrder();
				model.addAttribute("projectdetailslist", projectdetails);
				
				List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
				model.addAttribute("projectlist", projectlist);
				
				
				List<activity>Activitydetails=activityservice.activityfindAllByAscendingOrder();
				model.addAttribute("activitdetaillist", Activitydetails);
				
				model.addAttribute("projectshow", this.projectservice.show());
				
				model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
				
				model.addAttribute("projectactivityshow", this.projectservice.project_activityshow());
				
				model.addAttribute("activityshow", this.activityservice.show());
				
				
				
				List<Object[]>modulenamedistinct=projectservice.modulenamedistinct();
				model.addAttribute("modulenamedistinct", modulenamedistinct);
				
			
				List<module>moduleshow=moduleservice.display();
				model.addAttribute("moduledisplay", moduleshow);
				
				
				List<Object[]> list=eps.showaddprojectdetail();
				model.addAttribute("objectshow", list);
				
				
				
				return new ModelAndView("ProjectDetails/project_activity");
			}
			
			
			@RequestMapping(value="/editprojectactivity",method=RequestMethod.POST)
			public ModelAndView editprojectactivity(Model model,@RequestParam int id)
			{
				
				
				//System.out.println(id);
				
				try
				{
					
					
					
									
					List<Object[]>modulenamedistinct=projectservice.modulenamedistinct();
					model.addAttribute("modulenamedistinct", modulenamedistinct);
					
					
				
					List<activity>Activitydetails=activityservice.activityfindAllByAscendingOrder();
					model.addAttribute("activitdetaillist", Activitydetails);
					
					model.addAttribute("projectshow", this.projectservice.show());
					
					//model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
					
					model.addAttribute("projectactivityshow", this.projectservice.project_activityshow());
		
					model.addAttribute("activityshow", this.activityservice.show());
					
					
					
					
					List<Object[]> list=eps.showaddprojectdetail();
					model.addAttribute("objectshow", list);
					
					
					List<projectdetails>projectdetails=projectservice.projectdetailshow();
					model.addAttribute("projectdetailslist", projectdetails);
					
					List<addproject>projectlist=projectservice.show();
					model.addAttribute("projectlist", projectlist);
					
					project_activity pa=projectservice.project_activityedit(id);
					model.addAttribute("project_activityedit", pa);
					
					//model.addAttribute("activityshow", this.activityservice.show());
					
					model.addAttribute("activityshow", this.activityservice.editbasedonprojectid(pa.getProjectdet_id(),pa.getActivity_id()));
					
					List<module>moduleshow=moduleservice.display();
					model.addAttribute("moduleshow", moduleshow);
					
					
					model.addAttribute("projectdetailshow", this.projectservice.updateprojectdetailmoduleshow(pa.getProjectdet_id()));
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				return new ModelAndView("ProjectDetails/project_activityedit");
			}
			
			@RequestMapping(value="/onloadmodule")
			public ModelAndView onloadmodule(Model model,@RequestParam int id)
			{
				
				List<module>moduleshow=moduleservice.display();
				model.addAttribute("moduleshow", moduleshow);
				
				model.addAttribute("projectdetailshow", this.projectservice.updateprojectdetailmoduleshow(id));
				
				
				return new ModelAndView("ProjectDetails/project_activity");
				
			}
			
			
			
			@RequestMapping(value="/update_projectactivity",method=RequestMethod.POST)
			public ModelAndView updateprojectactivity(Model model,@RequestParam int projectact_id,@RequestParam int moduleid,
					@RequestParam int projectdts,@RequestParam int activityid) throws MySQLIntegrityConstraintViolationException
			{
				
				
				
				
				
					project_activity pa=new project_activity();
					
					pa.setActivity_id(activityid);
					pa.setProjectact_id(projectact_id);
					pa.setProjectdet_id(projectdts);
					
					@SuppressWarnings("unused")
					projectdetails pd=new projectdetails();
				
					//pd.setModule_id(moduleid);
					
					projectservice.updateactivitymodule(projectdts,moduleid);
					
					projectservice.update(pa);
					
				
				
				
				List<projectdetails>projectdetails=projectservice.projectdetailsfindAllByAscendingOrder();
				model.addAttribute("projectdetailslist", projectdetails);
				
				List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
				model.addAttribute("projectlist", projectlist);
				
				
				List<activity>Activitydetails=activityservice.activityfindAllByAscendingOrder();
				model.addAttribute("activitdetaillist", Activitydetails);
				
				model.addAttribute("projectshow", this.projectservice.show());
				
				model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
				
				model.addAttribute("project_activityshow", this.projectservice.project_activityshow());
				
				model.addAttribute("activityshow", this.activityservice.show());
							
				model.addAttribute("projectactivityshow", this.projectservice.project_activityshow());
	
				
				List<module>moduleshow=moduleservice.display();
				model.addAttribute("moduledisplay", moduleshow);
				
				
				List<Object[]> list=eps.showaddprojectdetail();
				model.addAttribute("objectshow", list);
			
				return new ModelAndView("ProjectDetails/project_activity");
			}
			
			
			
			@RequestMapping(value="/deleteprojectactivity",method=RequestMethod.POST)
			public ModelAndView deleteprojectactivity(Model model,@RequestParam int id)
			{
				
				
				try
				{
					projectservice.deleteprojectactivity(id);
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				List<projectdetails>projectdetails=projectservice.projectdetailsfindAllByAscendingOrder();
				model.addAttribute("projectdetailslist", projectdetails);
				
				List<addproject>projectlist=projectservice.projectfindAllByAscendingOrder();
				model.addAttribute("projectlist", projectlist);
				
				
				List<activity>Activitydetails=activityservice.activityfindAllByAscendingOrder();
				model.addAttribute("activitdetaillist", Activitydetails);
				
				model.addAttribute("projectshow", this.projectservice.show());
				
				model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
				
				model.addAttribute("project_activityshow", this.projectservice.project_activityshow());
				
				model.addAttribute("activityshow", this.activityservice.show());
				
				
				
				return new ModelAndView("ProjectDetails/project_activity");
			}

			
}
 