package com.TMS.Controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.TMS.Entity.EmployeeCategory;
import com.TMS.common.OrgCommonUtilityDao;
import com.TMS.serviceclass.EmployeeCategoryService;

@Controller
public class EmployeeCategoryController {

	@Autowired
	EmployeeCategoryService empCatService;
	
	@Autowired
	OrgCommonUtilityDao orgCommonUtilityDao;
	
    //-----------------------Religion Master------------------------------------------------//
	
	@RequestMapping(value = "/empCategory", method = RequestMethod.POST)
	public ModelAndView empCategory(Model model) {
		model.addAttribute("edit_det", "No");
		List<EmployeeCategory> empCatList = empCatService.findAll();
		model.addAttribute("empCatListSize", empCatList.size());
		model.addAttribute("empCatList", empCatList);
		return new ModelAndView("EmployeeCategory/employeecategory");
	}
	@PostMapping(value="/cancelEmpCategory")
	public ModelAndView page(Model model)
	{
		model.addAttribute("edit_det", "No");
		List<EmployeeCategory> empCatList = empCatService.findAll();
		model.addAttribute("empCatListSize", empCatList.size());
		model.addAttribute("empCatList", empCatList);
		return new ModelAndView("EmployeeCategory/employeecategory");
	}
	@ResponseBody
	@RequestMapping(value="/saveEmpCategory" ,method=RequestMethod.POST)
	public ModelAndView saveEmpCategory(Model model,HttpServletRequest request,@ModelAttribute EmployeeCategory EmployeeCategory){
		String return_status = "";
		int add_status = 0;
		
		String ipAddress =  orgCommonUtilityDao.getIpAddress(request);
		Timestamp timestamp = orgCommonUtilityDao.getTimeStamp();
		
	    EmployeeCategory.getEmpCatName();
	    EmployeeCategory.getStatus();
	    EmployeeCategory.getTds_Status();
	    
	    EmployeeCategory.setIpAddress(ipAddress);
	    EmployeeCategory.setTimeStamp(timestamp);
	    EmployeeCategory.setUserId(12345);
		
		try {
			add_status = empCatService.save(EmployeeCategory);
			
			if (add_status > 0)
				return_status = "OK";
		} catch (ConstraintViolationException dive) {
			return_status = "Data Integrity Exception";
		} catch (DataIntegrityViolationException cve) {
			return_status = "Data already present please check it!";
		} catch (HibernateException he) {
			return_status = "Fill The form Properly!";
		} catch (Exception e) {
			return_status = "Sql Exception has been caught";
		}
		JSONObject json = new JSONObject();
		json.put("status", return_status);	
		
		//return json;	
		model.addAttribute("edit_det", "No");
		List<EmployeeCategory> empCatList = empCatService.findAll();
		model.addAttribute("empCatListSize", empCatList.size());
		model.addAttribute("empCatList", empCatList);
		return new ModelAndView("EmployeeCategory/employeecategory");
	}
	
	@RequestMapping(value="/editEmpCategory",method=RequestMethod.POST)
	public ModelAndView editEmpCategory(@RequestParam Integer id,Model model){
		EmployeeCategory empCatObj =empCatService.findById(id);
		model.addAttribute("editObj", empCatObj);
		model.addAttribute("edit_det", "Yes");
		List<EmployeeCategory> empCatList = empCatService.findAll();
		model.addAttribute("empCatListSize", empCatList.size());
		model.addAttribute("empCatList", empCatList);
		return new ModelAndView("EmployeeCategory/employeecategoryedit");
	}
	
	@ResponseBody
	@RequestMapping(value="/updateEmpCategory" ,method=RequestMethod.POST)
	public ModelAndView updateEmpCategory(Model model,HttpServletRequest request,@ModelAttribute EmployeeCategory EmployeeCategory){
		String return_status = "";
		int add_status = 0;
		
		String ipAddress =  orgCommonUtilityDao.getIpAddress(request);
		Timestamp timestamp = orgCommonUtilityDao.getTimeStamp();
		
		
		EmployeeCategory.getId();
		EmployeeCategory.getEmpCatName();
		EmployeeCategory.getStatus();
		EmployeeCategory.getTds_Status();
		EmployeeCategory.setIpAddress(ipAddress);
		EmployeeCategory.setTimeStamp(timestamp);
		EmployeeCategory.setUserId(12345);
		try {
			add_status = empCatService.update(EmployeeCategory);
			
			if (add_status > 0)
				return_status = "OK";
		} catch (ConstraintViolationException dive) {
			return_status = "Data Integrity Exception";
		} catch (DataIntegrityViolationException cve) {
			return_status = "Data already present please check it!";
		} catch (HibernateException he) {
			return_status = "Fill The form Properly!";
		} catch (Exception e) {
			return_status = "Sql Exception has been caught";
		}
		JSONObject json = new JSONObject();
		json.put("status", return_status);	
		
		//return json;	
		model.addAttribute("edit_det", "No");
		List<EmployeeCategory> empCatList = empCatService.findAll();
		model.addAttribute("empCatListSize", empCatList.size());
		model.addAttribute("empCatList", empCatList);
		return new ModelAndView("EmployeeCategory/employeecategory");
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteEmpCategory", method = RequestMethod.POST)
	public ModelAndView deleteEmpCategory(@RequestParam Integer id, Model model) {
		//EmployeeCategory languageObj = empCatService.findById(id);
		model.addAttribute("edit_det", "No");
		List<EmployeeCategory> empCatList = empCatService.findAll();
		model.addAttribute("empCatListSize", empCatList.size());
		model.addAttribute("empCatList", empCatList);
		empCatService.deleteById(id);
		return new ModelAndView("EmployeeCategory/employeecategory");
		}	
	
	
}
