package com.TMS.Controller;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TMS.Entity.Otheractivtiy;
import com.TMS.Entity.TaskEntryModel;
import com.TMS.Entity.activity;
import com.TMS.Entity.addproject;
import com.TMS.Entity.module;
import com.TMS.Entity.project_assign;
import com.TMS.serviceclass.ActivityService;
import com.TMS.serviceclass.ModuleService;
import com.TMS.serviceclass.OtheractivityService;
import com.TMS.serviceclass.ProjectService;
import com.TMS.serviceclass.TaskEntryService;



@RestController
public class TaskEntryController {

	@Autowired
	TaskEntryService taskEntryservice; 
	
	@Autowired
	OtheractivityService otheractivityService;
	
	@Autowired
	ProjectService projectservice;
	
	@Autowired
	ModuleService moduleservice;
	
	@Autowired
	ActivityService activityservice;
	
	
	@PostMapping(value="/taskentry")
	public ModelAndView taskpage(Model model, HttpSession session)
	{
		String id=(String) session.getAttribute("username");
		//int empid=Integer.parseInt(id);
		
		List<Otheractivtiy> otheractivityList=otheractivityService.show();
		model.addAttribute("otheractivityList",otheractivityList);
		
		List<addproject>projectlist=projectservice.showbasedonassignemployee(id);
		model.addAttribute("projectlist", projectlist);
		
		List<TaskEntryModel> tasklist=taskEntryservice.showtasklist(id);
		model.addAttribute("tasklist", tasklist); 
		
		return new ModelAndView("TaskDetails/taskentrypage");
	}
	
	@PostMapping(value="/datecontroller")
	public Integer date(Model model, @RequestParam java.sql.Date date1, HttpSession session)
	{
		String id=(String) session.getAttribute("username");
		
		List<TaskEntryModel> changedate=taskEntryservice.chagedatelist(id, date1);
//		int val =changedate.size();
		return changedate.size();
	}
	@PostMapping(value="/canceltaskentrydetails")
	public ModelAndView page(Model model, HttpSession session)
	{
		
		String id=(String) session.getAttribute("username");
		
		List<Otheractivtiy> otheractivityList=otheractivityService.show();
		model.addAttribute("otheractivityList",otheractivityList);
		
		List<addproject>projectlist=projectservice.showbasedonassignemployee(id);
		model.addAttribute("projectlist", projectlist);
		
		List<module>moduleshow=moduleservice.display();
		model.addAttribute("moduleshowdetails", moduleshow);
				
		List<TaskEntryModel> tasklist=taskEntryservice.showtasklist(id);
		model.addAttribute("tasklist", tasklist);
		return new ModelAndView("TaskDetails/taskentrypage");
	}	
	@PostMapping(value="/addtaskentry")
	public ModelAndView addpage(Model model, HttpSession session,
											 @RequestParam String date, @RequestParam String shiftFrom,
											 @RequestParam String shiftTo, @RequestParam String shift,
											 @RequestParam String tasktype, @RequestParam String taskdetails,
											 @RequestParam String taskprbldetails, @RequestParam int otheract_id,
											 @RequestParam int project_id, @RequestParam int module_id,
											 @RequestParam int projectact_id) throws ParseException
	{
		String return_status = "";
		model.addAttribute("edit_status", "yes");
	
		int add_status = 0;
		TaskEntryModel addTentry=new TaskEntryModel();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");		
		Date sqldate = format1.parse(date);
		java.sql.Date sqlD=new java.sql.Date(sqldate.getTime());
		
		String b=null;
		Time sqltotime=null;
		Time sqlfromtime=null;
		if(shiftFrom.contains("PM"))
		{
			String[] arr=shiftFrom.split(" ");
			String[] arr1=arr[0].split(":");
			if(!arr1[0].equals("12"))
			{
			int to=Integer.parseInt(arr1[0]);
			int a=12+to;
			b=String.valueOf(a);
			b=a+":"+arr1[1];
			
			String sqlfrom=b;
			SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
			long ms=sdf.parse(sqlfrom).getTime();
			sqlfromtime=new Time(ms);
			}
			else
			{
				String sqlfrom=shiftFrom;
				SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
				long ms=sdf.parse(sqlfrom).getTime();
				sqlfromtime=new Time(ms);
			}
		}
		else
		{
			String sqlfrom=shiftFrom;
			SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
			long ms=sdf.parse(sqlfrom).getTime();
			sqlfromtime=new Time(ms);
		}
		if(shiftTo.contains("PM"))
		{
			String[] arr=shiftTo.split(" ");
			String[] arr1=arr[0].split(":");
			if(!arr1[0].equals("12"))
			{
			int to=Integer.parseInt(arr1[0]);
			int a=12+to;
			b=String.valueOf(a);
			b=a+":"+arr1[1];
			String sqlto=b;
			SimpleDateFormat sdf1= new SimpleDateFormat("HH:mm");
			long ms1=sdf1.parse(sqlto).getTime();
			sqltotime=new Time(ms1);
			}
			else
			{
				String sqlto=shiftTo;
				SimpleDateFormat sdf1= new SimpleDateFormat("HH:mm");
				long ms1=sdf1.parse(sqlto).getTime();
				sqltotime=new Time(ms1);
			}
		}
		else
		{
			String sqlto=shiftTo;
			SimpleDateFormat sdf1= new SimpleDateFormat("HH:mm");
			long ms1=sdf1.parse(sqlto).getTime();
			sqltotime=new Time(ms1);
		}
		String id=(String) session.getAttribute("username");
		
		List<TaskEntryModel> listDT=taskEntryservice.showbasedontime(id, sqlD, sqlfromtime, sqltotime);
		
		
	
		if(listDT.size()<=0)
		{
		
		model.addAttribute("erorr", "YES");
		addTentry.setTaskentry_date(sqlD);
		addTentry.setTaskentry_frmtime(shiftFrom);
		addTentry.setTaskentry_totime(shiftTo);
		addTentry.setTaskentry_totmin(shift);
		addTentry.setEmp_code(id);
		addTentry.setTaskentry_detail(taskdetails);
		addTentry.setTaskentry_prob(taskprbldetails);
		addTentry.setTaskentry_type(tasktype);
		addTentry.setTaskentry_frmtime24(sqlfromtime);
		addTentry.setTaskentry_totime24(sqltotime);
		if(tasktype.equals("O"))
		{	
			
			addTentry.setOtheract_id(otheract_id);
			try
			{
				
				taskEntryservice.add(addTentry);
			}
			catch(DataIntegrityViolationException dive){
				//System.out.println("dataintigraty"+dive);
				
				dive.printStackTrace();
				return_status="Data Integrity Exception";
			}
			
			catch(ConstraintViolationException cve){
			
				//System.out.println("connstrinnt"+cve);
				cve.printStackTrace();
				return_status="Data already present please check it or null value cannot be inserted!";
			}
			
			catch (HibernateException he) {
				//System.out.println("hibranate"+he);
				he.printStackTrace();
				return_status="Fill The form Properly!";	
			}
			
			catch (Exception e) {
				///System.out.println("exc"+e);
				e.printStackTrace();
				return_status="Sql Exception has been caught";
			}
			
		}
		else
		{
			addTentry.setProject_id(project_id);
			addTentry.setModule_id(module_id);
			addTentry.setProjectact_id(projectact_id);
			try
			{
			   taskEntryservice.add(addTentry);
			}
			catch (ConstraintViolationException dive) {
				return_status = "Data Integrity Exception";
			} catch (DataIntegrityViolationException cve) {
				return_status = "Data already present please check it!";
			} catch (HibernateException he) {
				return_status = "Fill The form Properly!";
			} catch (Exception e) {
				return_status = "Sql Exception has been caught";
			}
		}
		}
		else
		{
			//System.out.println("ssdf");
			model.addAttribute("erorr", "NO");
		}
		//System.out.println(return_status);
        List<Otheractivtiy> otheractivityList=otheractivityService.show();
		model.addAttribute("otheractivityList",otheractivityList);
		
		List<addproject>projectlist=projectservice.showbasedonassignemployee(id);
		model.addAttribute("projectlist", projectlist);
		
		List<TaskEntryModel> tasklist=taskEntryservice.showtasklist(id);
		model.addAttribute("tasklist", tasklist);
		
		model.addAttribute("return_status", return_status);
		return new ModelAndView("TaskDetails/taskentrypage");
	}
	/*@RequestMapping(value = "/edittask", method = RequestMethod.POST)*/
	@PostMapping(value="/edittask")
	public ModelAndView up(@ModelAttribute TaskEntryModel taskentry,@RequestParam int id,Model model, HttpSession session){
		
		
		 String id1=(String) session.getAttribute("username");
		
		 List<Otheractivtiy> otheractivityList=otheractivityService.show();
		 model.addAttribute("otheractivityList",otheractivityList);
		 
		List<TaskEntryModel> tasklist=taskEntryservice.showtasklist(id1);
		model.addAttribute("tasklist", tasklist);
		  
		 model.addAttribute("projectshow", this.projectservice.showbasedonassignemployee(id1));
		
	 	model.addAttribute("projectdetailshow", this.projectservice.projectdetailshow());
		
		TaskEntryModel list=taskEntryservice.edit(id);
		model.addAttribute("object", list);
		
		 model.addAttribute("status", list.getTaskentry_type());
		
		List<addproject>projectlist=projectservice.showbasedonassignemployee(id1);
		model.addAttribute("projectlist", projectlist);
		

		 List<module> moduleshow=moduleservice.showbasedonprojectid(tasklist.get(0).getProject_id());
		 model.addAttribute("modulelist", moduleshow);
		 
		
		model.addAttribute("list", this.activityservice.showbasedonactivity(tasklist.get(0).getModule_id(), tasklist.get(0).getProject_id()));
		
		return new ModelAndView("TaskDetails/taskentryedit");	
	} 
	@PostMapping(value="/updatetask")
	public ModelAndView page(Model model, @RequestParam int taskentryid, @RequestParam String date, @RequestParam String shiftFrom,
			 @RequestParam String shiftTo, @RequestParam String shift,
			 @RequestParam String tasktype, @RequestParam String taskdetails,
			 @RequestParam String taskprbldetails, @RequestParam int otheract_id,
			 @RequestParam int project_id, @RequestParam int module_id,
			 @RequestParam int projectact_id, HttpSession session ) throws ParseException 
	{
		
		String return_status = "";
		model.addAttribute("edit_status", "yes");
			
		int add_status = 0;
		TaskEntryModel updateTentry=new TaskEntryModel();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");		
		Date sqldate = format1.parse(date);
		java.sql.Date sqlD=new java.sql.Date(sqldate.getTime());
		
		String b=null;
		Time sqltotime=null;
		Time sqlfromtime=null;
		if(shiftFrom.contains("PM"))
		{
			String[] arr=shiftFrom.split(" ");
			String[] arr1=arr[0].split(":");
			if(!arr1[0].equals("12"))
			{
			int to=Integer.parseInt(arr1[0]);
			int a=12+to;
			b=String.valueOf(a);
			b=a+":"+arr1[1];
			
			String sqlfrom=b;
			SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
			long ms=sdf.parse(sqlfrom).getTime();
			sqlfromtime=new Time(ms);
			}
			else
			{
				String sqlfrom=shiftFrom;
				SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
				long ms=sdf.parse(sqlfrom).getTime();
				sqlfromtime=new Time(ms);
			}
		}
		else
		{
			String sqlfrom=shiftFrom;
			SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
			long ms=sdf.parse(sqlfrom).getTime();
			sqlfromtime=new Time(ms);
		}
		if(shiftTo.contains("PM"))
		{
			String[] arr=shiftTo.split(" ");
			String[] arr1=arr[0].split(":");
			if(!arr1[0].equals("12"))
			{
			int to=Integer.parseInt(arr1[0]);
			int a=12+to;
			b=String.valueOf(a);
			b=a+":"+arr1[1];
			String sqlto=b;
			SimpleDateFormat sdf1= new SimpleDateFormat("HH:mm");
			long ms1=sdf1.parse(sqlto).getTime();
			sqltotime=new Time(ms1);
			}
			else
			{
				String sqlto=shiftTo;
				SimpleDateFormat sdf1= new SimpleDateFormat("HH:mm");
				long ms1=sdf1.parse(sqlto).getTime();
				sqltotime=new Time(ms1);
			}
		}
		else
		{
			String sqlto=shiftTo;
			SimpleDateFormat sdf1= new SimpleDateFormat("HH:mm");
			long ms1=sdf1.parse(sqlto).getTime();
			sqltotime=new Time(ms1);
		}
		
		
		String id=(String) session.getAttribute("username");
		//System.out.println("taskentryid"+taskentryid);
		List<TaskEntryModel> listDT=taskEntryservice.showbasedontimeupdate(taskentryid, id, sqlD, sqlfromtime, sqltotime);
 		if(listDT.size()<=0)
		{
 		   System.out.println("IF"); 
 		model.addAttribute("erorr", "YES");
		updateTentry.setId(taskentryid);
		updateTentry.setTaskentry_date(sqlD);
		updateTentry.setTaskentry_frmtime(shiftFrom);
		updateTentry.setTaskentry_totime(shiftTo);
		updateTentry.setTaskentry_totmin(shift);
		updateTentry.setEmp_code(id);
		updateTentry.setTaskentry_detail(taskdetails);
		updateTentry.setTaskentry_prob(taskprbldetails);
		updateTentry.setTaskentry_type(tasktype);
		updateTentry.setTaskentry_frmtime24(sqlfromtime);
		updateTentry.setTaskentry_totime24(sqltotime);
		if(updateTentry.equals("O"))
		{	
			System.out.println("if");
			updateTentry.setOtheract_id(otheract_id);
			try
			{
				
				taskEntryservice.update(updateTentry);
			}
			catch(DataIntegrityViolationException dive){
				
				
				dive.printStackTrace();
				return_status="Data Integrity Exception";
			}
			
			catch(ConstraintViolationException cve){
			
			
				cve.printStackTrace();
				return_status="Data already present please check it or null value cannot be inserted!";
			}
			
			catch (HibernateException he) {
				he.printStackTrace();
				return_status="Fill The form Properly!";	
			}
			
			catch (Exception e) {
				
				e.printStackTrace();
				return_status="Sql Exception has been caught";
			}
		}
		else
		{
			updateTentry.setProject_id(project_id);
			updateTentry.setModule_id(module_id);
			updateTentry.setProjectact_id(projectact_id);
			try
			{
			  taskEntryservice.update(updateTentry);
			}
			catch (ConstraintViolationException dive) {
				return_status = "Data Integrity Exception";
			} catch (DataIntegrityViolationException cve) {
				return_status = "Data already present please check it!";
			} catch (HibernateException he) {
				return_status = "Fill The form Properly!";
			} catch (Exception e) {
				return_status = "Sql Exception has been caught";
			}
		}
		}
		else
		{
			System.out.println("else");
			model.addAttribute("erorr", "NO");
		}
		
		List<Otheractivtiy> otheractivityList=otheractivityService.show();
		model.addAttribute("otheractivityList",otheractivityList);
		
		List<addproject>projectlist=projectservice.showbasedonassignemployee(id);
		model.addAttribute("projectlist", projectlist);
		
		List<TaskEntryModel> tasklist=taskEntryservice.showtasklist(id);
		model.addAttribute("tasklist", tasklist);
		
		model.addAttribute("return_status", return_status);
		return new ModelAndView("TaskDetails/taskentrypage");
	}
}
