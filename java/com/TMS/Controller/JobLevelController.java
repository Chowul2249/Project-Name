package com.TMS.Controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TMS.Entity.JobLevel;
import com.TMS.common.OrgCommonUtilityDao;
import com.TMS.serviceclass.JobLevelService;

@RestController
public class JobLevelController {
	
	@Autowired
	JobLevelService joblevelservice;
	
	@Autowired
	OrgCommonUtilityDao orgCommonUtilityDao;
	
	@PostMapping("/JobLevel")
	public ModelAndView login(Model model)
	{
		List<JobLevel> joblevellist=joblevelservice.findAll();
		model.addAttribute("JobLevelList", joblevellist);
		model.addAttribute("JobLevelListSize", joblevellist.size());
		return new ModelAndView("JobLevel/joblevel");
	}
	@PostMapping(value="/canceljoblevel")
	public ModelAndView page(Model model)
	{
		List<JobLevel> joblevellist=joblevelservice.findAll();
		model.addAttribute("JobLevelList", joblevellist);
		model.addAttribute("JobLevelListSize", joblevellist.size());
		return new ModelAndView("JobLevel/joblevel");
	}
	@ResponseBody
	@RequestMapping(value = "/savejoblevel", method = RequestMethod.POST)
	public ModelAndView activity1(Model model,@ModelAttribute JobLevel joblevel, HttpServletRequest request,
											   HttpServletResponse response) 
    	{
		
		String return_status = "";
		int add_status = 0;
		
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		
		joblevel.setTimeStamp(creationTime);
		joblevel.setUserId(12345);
		joblevel.setIpAddress(ipAddress);
		joblevel.getLevelName();
		joblevel.getWeightage();
		joblevel.getStatus();

		try
		{
			add_status=joblevelservice.add(joblevel);
		 if (add_status > 0)
				return_status = "OK";
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
		List<JobLevel> joblevellist=joblevelservice.findAll();
		model.addAttribute("JobLevelList", joblevellist);
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		return new ModelAndView("JobLevel/joblevel");
	}
	@RequestMapping(value="/editjoblevel",method=RequestMethod.POST)
	public ModelAndView editPrintCategory(Model model,@RequestParam int id){
			
		JobLevel editObj=joblevelservice.findById(id);
		model.addAttribute("editObj",editObj);
		List<JobLevel> joblevellist=joblevelservice.findAll();
		model.addAttribute("JobLevelList", joblevellist);
	
		return new ModelAndView("JobLevel/jobleveledit");
	}
	@ResponseBody
	@RequestMapping(value="/updatejoblevel",method=RequestMethod.POST)
	public ModelAndView update(Model model,@ModelAttribute JobLevel joblevel,HttpServletRequest request)
	{
		String return_status = "";
		int add_status = 0;
		
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		
		//Designation saveObj=new Designation();
		joblevel.setTimeStamp(creationTime);
		joblevel.setUserId(12345);
		joblevel.setIpAddress(ipAddress);
		joblevel.getLevelName();
		joblevel.getWeightage();
		joblevel.getStatus();
		
			
		try {
			add_status=joblevelservice.update(joblevel);
			if(add_status>0)
				return_status="OK";
			
			
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
		
		List<JobLevel> joblevellist=joblevelservice.findAll();
		model.addAttribute("JobLevelList", joblevellist);
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		//return json;
		return new ModelAndView("JobLevel/joblevel");
	}
	@ResponseBody
	@RequestMapping(value="/jobleveldelete")
	public ModelAndView delete(@RequestParam int id,Model model){
		
	
		List<JobLevel> joblevellist=joblevelservice.findAll();
		model.addAttribute("JobLevelList", joblevellist);
		
		joblevelservice.deleteById(id);

		return new ModelAndView("JobLevel/joblevel");
	}
}
