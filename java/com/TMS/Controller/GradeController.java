package com.TMS.Controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.TMS.Entity.Grade;
import com.TMS.common.OrgCommonUtilityDao;
import com.TMS.serviceclass.GradeService;
@Controller
public class GradeController {
	
@Autowired
GradeService gradeService;

@Autowired
OrgCommonUtilityDao hrmsCommonUtilityService;




@RequestMapping(value="/grade")
public ModelAndView showGrade(Model model)

{
				String edit_det="no";
				List<Grade> gradeList=gradeService.getGrade();
				    model.addAttribute("gradeList", gradeList);
				    model.addAttribute("gradeListSize", gradeList.size());
				    model.addAttribute("edit_det",edit_det);
				return new ModelAndView("JobGrade/Grade");
}
@PostMapping(value="/cancelgrade")
public ModelAndView page(Model model)
{
	String edit_det="no";
	List<Grade> gradeList=gradeService.getGrade();
	    model.addAttribute("gradeList", gradeList);
	    model.addAttribute("gradeListSize", gradeList.size());
	    model.addAttribute("edit_det",edit_det);
	return new ModelAndView("JobGrade/Grade");
}


@RequestMapping(value="/addGrade",method=RequestMethod.POST)
public ModelAndView showaddGrade(  Model model, HttpServletRequest request,@ModelAttribute Grade addGrade)
{
	String return_status = "";
	int add_status = 0;
	
    /*
    	error_msg="add";
    	List<Grade> gradeList=gradeService.getGrade();
				model.addAttribute("gradeList", gradeList);
				model.addAttribute("error_msg",error_msg);
				model.addAttribute("edit_det",update_status);
       */
    
   
 
   
    	String ipAddress     = hrmsCommonUtilityService.getIpAddress(request);
    	Timestamp timestamp  = hrmsCommonUtilityService.getTimeStamp();
    	addGrade.getJobGradeName();
    	addGrade.getStatus();
    	addGrade.getWeightage();
    		
    	addGrade.setIpAddress(ipAddress);
    	addGrade.setTimestamp(timestamp);
    	addGrade.setUserId((int) 0);

	try
	
	{
		add_status=gradeService.addGrade(addGrade);
		 System.out.println("saved");
	}
	
	catch(DataIntegrityViolationException dive){
		   dive.printStackTrace();
		   System.out.println(".....");
		   return_status="Data Integrity Exception";
	   }
	   
	   catch(ConstraintViolationException cve){
		   cve.printStackTrace();
		   System.out.println("aaaaa");
		   return_status="Data already present please check it or null value cannot be inserted!";
	   }
	   
	   catch (HibernateException he) {
		   he.printStackTrace();
		   System.out.println("bbbbbbb");
		   return_status="Fill The form Properly!";	
	}
	   
	   catch (Exception e) {
	    	 e.printStackTrace();
	    	 System.out.println(".....ccccccccc");
	    	 return_status="Sql Exception has been caught";
	}
	
				List<Grade> gradeList=gradeService.getGrade();
						model.addAttribute("gradeList", gradeList);
						 model.addAttribute("gradeListSize", gradeList.size());
						model.addAttribute("error_msg",return_status);
						model.addAttribute("edit_det",add_status);
				return new ModelAndView("JobGrade/Grade");
	}


//Edit Operations

  @RequestMapping(value="/editjobgrade")
  public ModelAndView showeditGrade(@RequestParam int id,@ModelAttribute Grade grade,Model model)
  {
				  Grade gradeObject=gradeService.getGrade(id);
				  				  
			      List<Grade> gradeList=gradeService.getGrade();
				  String update_status="yes";
						  model.addAttribute("gradeList", gradeList);
						  model.addAttribute("editObj", gradeObject);
						  String weight=Integer.toString(gradeObject.getWeightage());
						  model.addAttribute("weight", weight);
					      model.addAttribute("edit_det",update_status);
					      model.addAttribute("gradeListSize", gradeList.size());
			return new ModelAndView("JobGrade/Gradeedit");
			  }
			  
			  //Update Operations
			  
@RequestMapping(value="/updateGrade")
public ModelAndView showupdateGrade(@ModelAttribute Grade UpGrade, Model model, HttpServletRequest request)

{
	@SuppressWarnings("unused")
	int update_Status=0;
			
				  String error_msg="";
				  String update_status="no";
				  				  
				  
				
				  UpGrade.getId();
				  UpGrade.getJobGradeName();
				  UpGrade.getWeightage();
				  UpGrade.getStatus();
					  
						Timestamp creationTime = hrmsCommonUtilityService.getTimeStamp();
						String ipAddress = hrmsCommonUtilityService.getIpAddress(request);
						
						UpGrade.setTimestamp(creationTime);
						UpGrade.setUserId(12345);
						UpGrade.setIpAddress(ipAddress);
					  
				 
				  
					  try
					  {
						  //gradeService.update(UpGrade);
						  update_Status=gradeService.update(UpGrade);
						  
					  }
				  
					  catch(DataIntegrityViolationException dive){
						   dive.printStackTrace();
						   error_msg="Data Integrity Exception";
					   }
					   
					   catch(ConstraintViolationException cve){
						   cve.printStackTrace();
						   error_msg="Data already present please check it or null value cannot be inserted!";
					   }
					   
					   catch (HibernateException he) {
						   he.printStackTrace();
						   error_msg="Fill The form Properly!";	
					}
					   
					   catch (Exception e) {
					    	 e.printStackTrace();
					    	 error_msg="Sql Exception has been caught";
					}
					
					  
			List<Grade> gradeList=gradeService.getGrade();
				  model.addAttribute("error_msg",error_msg);
				  model.addAttribute("edit_det",update_status);
				  model.addAttribute("gradeList",gradeList);
				  model.addAttribute("gradeListSize", gradeList.size());
			return new ModelAndView("JobGrade/Grade");
				  }		  
				  
 
  
  
  //Delete Operations
  
  @RequestMapping(value="/deleteGrade")
  public ModelAndView showdeleteGrade(@RequestParam int id,@ModelAttribute Grade grade,Model model)
  {
	  //long delete_status=0;
	  String error_msg="";
	  String update_status="no";
	  
	  try
	  {
		  @SuppressWarnings("unused")
		int delete_status=gradeService.deleteGrade(id);
	  }
	  
	  catch(DataIntegrityViolationException dive){
		   dive.printStackTrace();
		   error_msg="Data Integrity Exception";
	   }
	   
	   catch(ConstraintViolationException cve){
		   cve.printStackTrace();
		   error_msg="Data already present please check it or null value cannot be inserted!";
	   }
	   
	   catch (HibernateException he) {
		   he.printStackTrace();
		   error_msg="Fill The form Properly!";	
	}
	   
	   catch (Exception e) {
	    	 e.printStackTrace();
	    	 error_msg="Sql Exception has been caught";
	}
	
	  
				List<Grade> gradeList=gradeService.getGrade();
					  model.addAttribute("gradeList",gradeList);
					  model.addAttribute("error_msg", error_msg);
					  model.addAttribute("edit_det",update_status);
					  model.addAttribute("gradeListSize", gradeList.size());
				return new ModelAndView("JobGrade/Grade");
	  
  }
  



	
}
	
