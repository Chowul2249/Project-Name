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

import com.TMS.Entity.Department;
import com.TMS.common.OrgCommonUtilityDao;
import com.TMS.serviceclass.DepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentservice;
	
	@Autowired
	OrgCommonUtilityDao orgCommonUtilityDao;
	
	@RequestMapping(value="/department",method=RequestMethod.POST)
	public ModelAndView department(Model model)
	{
		List<Department> departmentList=departmentservice.findAll();
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("edit_det", "No");
		model.addAttribute("departmentListSize",departmentList.size()); 
		return new ModelAndView("Department/Department");
		
	}
	@PostMapping(value="/DepartmentPage")
	public ModelAndView page(Model model)
	{
		List<Department> departmentList=departmentservice.findAll();
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("edit_det", "No");
		model.addAttribute("departmentListSize",departmentList.size()); 
		return new ModelAndView("Department/Department");
	}
	
	@RequestMapping(value="/saveDepartment",method=RequestMethod.POST)
	public ModelAndView saveDepartment(Model model,@ModelAttribute Department saveDepartment,HttpServletRequest request)
	{
		@SuppressWarnings("unused")
		String return_status = "";
		int add_status = 0;
		
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		
		saveDepartment.getDepart_name();
		saveDepartment.getStatus();
		
		saveDepartment.setDepart_ip(ipAddress);
		saveDepartment.setDepart_time(creationTime);
		saveDepartment.setDepart_user(12345);
		
		try {
			add_status=departmentservice.save(saveDepartment);
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
		
		/*JSONObject json = new JSONObject();
		json.put("status", return_status);
		return json;*/
		List<Department> departmentList=departmentservice.findAll();
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("edit_det", "No");
		model.addAttribute("departmentListSize",departmentList.size()); 
		return new ModelAndView("Department/Department");
		
	}
	
	@RequestMapping(value="/editDepartment",method=RequestMethod.POST)
	public ModelAndView editCountry(Model model,@RequestParam int id){
		Department editObj = departmentservice.findById(id);
		model.addAttribute("editObj",editObj);
		List<Department> departmentList=departmentservice.findAll();
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("departmentListSize",departmentList.size());
		
		model.addAttribute("edit_det", "Yes");
		return new ModelAndView("Department/Departmentedit");
	}
	
	
	@RequestMapping(value="/updateDepartment",method=RequestMethod.POST)
	public ModelAndView updateDepartment(Model model,@ModelAttribute Department updateDepartment ,HttpServletRequest request)
	{
		String return_status = "";
		int add_status = 0;
		
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		
		updateDepartment.getId();
		updateDepartment.getDepart_name();
		updateDepartment.getStatus();
		
		updateDepartment.setDepart_ip(ipAddress);
		updateDepartment.setDepart_time(creationTime);
		updateDepartment.setDepart_user(12345);
		
		try {
			add_status=departmentservice.update(updateDepartment);
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
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		//return json;
		List<Department> departmentList=departmentservice.findAll();
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("edit_det", "No");
		model.addAttribute("departmentListSize",departmentList.size()); 
		return new ModelAndView("Department/Department");
	}
	
	@ResponseBody
	@PostMapping(value="/deleteDPTM")
	public ModelAndView deleteDepartment(@RequestParam int id,Model model){
		
		List<Department> departmentList=departmentservice.findAll();
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("edit_det", "No");
		model.addAttribute("departmentListSize",departmentList.size()); 
		
		departmentservice.delete(id);
		return new ModelAndView("Department/Department");
		
	}

}
