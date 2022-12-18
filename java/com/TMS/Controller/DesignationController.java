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

import com.TMS.Entity.Designation;
import com.TMS.common.OrgCommonUtilityDao;
import com.TMS.serviceclass.DesignationService;



@RestController
public class DesignationController {

	@Autowired
	DesignationService designationservice;
	
	@Autowired
	OrgCommonUtilityDao orgCommonUtilityDao;
	
	@RequestMapping(value = "/designation", method = RequestMethod.POST)
	public ModelAndView activity(Model model) {
		
		
		List<Designation> designationList=designationservice.findAll();
		model.addAttribute("DesignationList", designationList);
		model.addAttribute("edit_det", "No");
		model.addAttribute("designationList", designationList);
		model.addAttribute("DesignationListSize",designationList.size()); 
		
		return new ModelAndView("Designation/designation");
	} 
	@PostMapping(value="/cancelDesignation")
	public ModelAndView page(Model model)
	{
		List<Designation> designationList=designationservice.findAll();
		model.addAttribute("DesignationList", designationList);
		model.addAttribute("edit_det", "No");
		model.addAttribute("designationList", designationList);
		model.addAttribute("DesignationListSize",designationList.size()); 
		
		return new ModelAndView("Designation/designation");
		
	}
	@ResponseBody
	@RequestMapping(value = "/savedesignation", method = RequestMethod.POST)
	public ModelAndView activity1(Model model,@ModelAttribute Designation Designation, HttpServletRequest request,
											   HttpServletResponse response) 
    	{
		
		String return_status = "";
		int add_status = 0;
		
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		Designation.setTimestamp(creationTime);
		Designation.getDesig_name();
		Designation.getStatus();
		Designation.setUser_id(12345);
		Designation.setIp_address(ipAddress);

		try
		{
			add_status=designationservice.add(Designation);
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
		
		List<Designation> designationList=designationservice.findAll();
		model.addAttribute("DesignationList", designationList);
		model.addAttribute("edit_det", "No");
		model.addAttribute("designationList", designationList);
		model.addAttribute("DesignationListSize",designationList.size());
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		//return json;
		return new ModelAndView("Designation/designation");
	}
	@RequestMapping(value="/editdesignation",method=RequestMethod.POST)
	public ModelAndView editPrintCategory(Model model,@RequestParam int id){
			
		Designation editObj=designationservice.findById(id);
		model.addAttribute("editObj",editObj);
		List<Designation> designationList=designationservice.findAll();
		model.addAttribute("DesignationList", designationList);
		model.addAttribute("designationList", designationList);
		model.addAttribute("DesignationListSize",designationList.size());
	
		return new ModelAndView("Designation/designationedit");
	}
	@ResponseBody
	@RequestMapping(value="/updateDesignation",method=RequestMethod.POST)
	public ModelAndView updateDesignation(Model model,@ModelAttribute Designation designation,HttpServletRequest request)
	{
		String return_status = "";
		int add_status = 0;
		
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		
		@SuppressWarnings("unused")
		Designation saveObj=new Designation();
		designation.getDesig_name();
		designation.getStatus();
		designation.setIp_address(ipAddress);
		designation.setUser_id(12345);
		designation.getId();
		designation.setTimestamp(creationTime);
			
		try {
			add_status=designationservice.update(designation);
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
		
		List<Designation> designationList=designationservice.findAll();
		model.addAttribute("DesignationList", designationList);
		model.addAttribute("edit_det", "No");
		model.addAttribute("designationList", designationList);
		model.addAttribute("DesignationListSize",designationList.size());
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		//return json;
		return new ModelAndView("Designation/designation");
		
	}
	@ResponseBody
	@RequestMapping(value="/designationdelete")
	public ModelAndView delete(@RequestParam int id,Model model){
		
	System.out.println(id);
		List<Designation> designationList=designationservice.findAll();
		model.addAttribute("DesignationList", designationList);
		model.addAttribute("edit_det", "No");
		model.addAttribute("designationList", designationList);
		model.addAttribute("DesignationListSize",designationList.size());
		
		designationservice.deleteById(id);
		return new ModelAndView("Designation/designation");
	}
}
