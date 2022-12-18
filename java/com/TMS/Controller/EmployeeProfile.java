package com.TMS.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import com.TMS.Entity.Department;
import com.TMS.Entity.Designation;
import com.TMS.Entity.Division;
import com.TMS.Entity.EmpProfile;
import com.TMS.Entity.EmployeeCategory;
import com.TMS.Entity.JobLevel;
import com.TMS.Entity.PrintCategory;
import com.TMS.common.OrgCommonUtilityDao;
import com.TMS.serviceclass.DepartmentService;
import com.TMS.serviceclass.DesignationService;
import com.TMS.serviceclass.DivisionService;
import com.TMS.serviceclass.EmployeeCategoryService;
import com.TMS.serviceclass.EmployeeProfileService;
import com.TMS.serviceclass.JobLevelService;
import com.TMS.serviceclass.PrintCategoryService;
@RestController
public class EmployeeProfile {
	
	@Autowired
	EmployeeProfileService empProfileService;
	
	@Autowired
	EmployeeCategoryService empCatService;
	
	@Autowired
	PrintCategoryService printCategoryService;
	
	@Autowired
	DepartmentService departmentservice;
	
	@Autowired
	DivisionService divisionservice;
	
	@Autowired
	DesignationService designationservice;

	@Autowired
	JobLevelService joblevelservice;
	
	@Autowired
	OrgCommonUtilityDao orgCommonUtilityDao;
		
	@RequestMapping(value="/profile")
	public ModelAndView modelAndView1(Model model,HttpSession session)
	{	
		String id=(String) session.getAttribute("username");
		int empid=Integer.parseInt(id);
		
		List<EmployeeCategory> empList=empCatService.findAllByAscendingOrder("empCatName","Y");
		model.addAttribute("empList", empList);
		
		List<PrintCategory> pcList=printCategoryService.findAllByAscendingOrder("printCat_name","Y");
		model.addAttribute("pcList", pcList);
		
		List<Department> depList=departmentservice.findAllByAscendingOrder("depart_name","Y");
		model.addAttribute("depList", depList);
		
	
		List<Designation> desiList=designationservice.findAllByAscendingOrder("desig_name","Y");
		model.addAttribute("desiList", desiList);
		
		List<JobLevel> levelList=joblevelservice.findAllByAscendingOrder("levelName","Y");
		model.addAttribute("levelList", levelList);
		
        List<EmpProfile> list1=empProfileService.show();
		model.addAttribute("list1", list1);
		
		
		
	
		EmpProfile list=empProfileService.edit(empid);
		model.addAttribute("list", list);
		String landline=list.getEmpb_landline();
		String[] name=landline.split("-");
		model.addAttribute("landlinecode", name[0]);
		model.addAttribute("landlineno", name[1]);
		
		
		List<EmpProfile> empListByLevel =empProfileService.getReportToPerson(list.getLevelToemp());
		model.addAttribute("empListByLevel", empListByLevel);
		
		List<Division> divList=divisionservice.getDivisionBasedDepartment(list.getDeptToempBasic());
		model.addAttribute("divList", divList);
	
		
			int report=Integer.valueOf((list.getEmpb_report().toString())).intValue();
			model.addAttribute("report", report);
			
		
		return new ModelAndView("EmployeeProfile/EmployeeProfileedit");
	}
	@RequestMapping(value="/Back")
	public ModelAndView back(Model model)
	{
		List<EmployeeCategory> empList=empCatService.findAllByAscendingOrder("empCatName","Y");
		model.addAttribute("empList", empList);
		
		List<PrintCategory> pcList=printCategoryService.findAllByAscendingOrder("printCat_name","Y");
		model.addAttribute("pcList", pcList);
		
		List<Department> depList=departmentservice.findAllByAscendingOrder("depart_name","Y");
		model.addAttribute("depList", depList);
		
	
		List<Designation> desiList=designationservice.findAllByAscendingOrder("desig_name","Y");
		model.addAttribute("desiList", desiList);
		
		List<JobLevel> levelList=joblevelservice.findAllByAscendingOrder("levelName","Y");
		model.addAttribute("levelList", levelList);
		
        List<EmpProfile> list1=empProfileService.show();
		model.addAttribute("list1", list1);
		
		return new ModelAndView("EmployeeProfile/employeeprofile");
		
	}
	@PostMapping(value="/cancelEmployeeProfile")
	public ModelAndView page(Model model)
	{
		List<EmployeeCategory> empList=empCatService.findAllByAscendingOrder("empCatName","Y");
		model.addAttribute("empList", empList);
		
		List<PrintCategory> pcList=printCategoryService.findAllByAscendingOrder("printCat_name","Y");
		model.addAttribute("pcList", pcList);
		
		List<Department> depList=departmentservice.findAllByAscendingOrder("depart_name","Y");
		model.addAttribute("depList", depList);
		
	
		List<Designation> desiList=designationservice.findAllByAscendingOrder("desig_name","Y");
		model.addAttribute("desiList", desiList);
		
		List<JobLevel> levelList=joblevelservice.findAllByAscendingOrder("levelName","Y");
		model.addAttribute("levelList", levelList);
		
        List<EmpProfile> list1=empProfileService.show();
		model.addAttribute("list1", list1);
		
		return new ModelAndView("EmployeeProfile/employeeprofile");
	}
	
	
	
@PostMapping(value="/addprofile")
public ModelAndView modelAndView(Model model, HttpServletRequest request,
												   HttpServletResponse response,
	
												   @RequestParam String fname,
												   @RequestParam String lname,
												   @RequestParam String dob,
												   @RequestParam String gender,
												   @RequestParam String mobile,
												   @RequestParam String landline,
												   @RequestParam String email,
												   @RequestParam int empcat,
												   @RequestParam int printcat,
												   @RequestParam int dept,
												   @RequestParam int div,
												   @RequestParam int desig,
												   @RequestParam String doj,
												   @RequestParam int level,
												   @RequestParam String report,
												   @RequestParam MultipartFile photo,
												   @RequestParam String status,
												   @RequestParam String reason
												   )
	{
		Blob blob =null;
		try
		{
			byte[] bytearray = photo.getBytes();
			
			blob = new SerialBlob(bytearray);
			
		}catch(Exception e)
		{	
			e.printStackTrace();
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		
		
		Date sqlDate = null;
		try {
			sqlDate = (Date) format.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		Date sqlDate1=null;
		try
		{
		sqlDate1 = (Date) format1.parse(doj);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		
		EmpProfile save=new EmpProfile();
		
		
		save.setEmpb_fname(fname);
		save.setEmpb_lname(lname);
		save.setDob(sqlDate);
		save.setEmpb_gend(gender);
		save.setEmpb_mobile(mobile);
		save.setEmpb_landline(landline);
		save.setEmpb_email(email);
		save.setEmpCatToEmp(empcat);
		save.setPrintCatId(printcat);
		save.setDeptToempBasic(dept);
		save.setDivToempBasic(div);
		save.setDesigToEmp(desig);
		save.setLevelToemp(level);
		save.setDateOfJoin(sqlDate1);
		save.setEmpb_photo(blob);
		save.setStatus(status);
		save.setEmpb_report(report);
		save.setInactive_reason(reason);
		
		empProfileService.add(save);
		
		List<EmployeeCategory> empList=empCatService.findAllByAscendingOrder("empCatName","Y");
		model.addAttribute("empList", empList);
		
		List<PrintCategory> pcList=printCategoryService.findAllByAscendingOrder("printCat_name","Y");
		model.addAttribute("pcList", pcList);
		
		List<Department> depList=departmentservice.findAllByAscendingOrder("depart_name","Y");
		model.addAttribute("depList", depList);
		
	
		List<Designation> desiList=designationservice.findAllByAscendingOrder("desig_name","Y");
		model.addAttribute("desiList", desiList);
		
		List<JobLevel> levelList=joblevelservice.findAllByAscendingOrder("levelName","Y");
		model.addAttribute("levelList", levelList);
		
        List<EmpProfile> list1=empProfileService.show();
		model.addAttribute("list1", list1);
	  return new ModelAndView("EmployeeProfile/employeeprofile");
	}
@RequestMapping(value="/RefreshDivsion")
public ModelAndView pagereload(@RequestParam int deprID, Model model)
{
	
	List<Division> divList=divisionservice.getDivisionBasedDepartment(deprID);
	model.addAttribute("divList", divList);
	
	return new ModelAndView("EmployeeProfile/employeeprofile");
}
@RequestMapping(value="/changeReportToByLevel")
public ModelAndView changeReportToByLevel(@RequestParam int level,Model model){
	List<EmpProfile> empListByLevel =empProfileService.getReportToPerson(level);
	model.addAttribute("empListByLevel", empListByLevel);
	return new ModelAndView("EmployeeProfile/employeeprofile");
}
	@PostMapping(value="/edit")
	public ModelAndView edit(Model model, @ModelAttribute EmpProfile empProfile,@RequestParam int id)
	{
		
		List<EmployeeCategory> empList=empCatService.findAllByAscendingOrder("empCatName","Y");
		model.addAttribute("empList", empList);
		
		List<PrintCategory> pcList=printCategoryService.findAllByAscendingOrder("printCat_name","Y");
		model.addAttribute("pcList", pcList);
		
		List<Department> depList=departmentservice.findAllByAscendingOrder("depart_name","Y");
		model.addAttribute("depList", depList);
		
		
		List<Designation> desiList=designationservice.findAllByAscendingOrder("desig_name","Y");
		model.addAttribute("desiList", desiList);
		
		List<JobLevel> levelList=joblevelservice.findAllByAscendingOrder("levelName","Y");
		model.addAttribute("levelList", levelList);
	
		EmpProfile list=empProfileService.edit(id);
		model.addAttribute("list", list);
		String landline=list.getEmpb_landline();
		String[] name=landline.split("-");
		model.addAttribute("landlinecode", name[0]);
		model.addAttribute("landlineno", name[1]);
		
		
		List<EmpProfile> empListByLevel =empProfileService.getReportToPerson(list.getLevelToemp());
		model.addAttribute("empListByLevel", empListByLevel);
		
		List<Division> divList=divisionservice.getDivisionBasedDepartment(list.getDeptToempBasic());
		model.addAttribute("divList", divList);
	
		
			int report=Integer.valueOf((list.getEmpb_report().toString())).intValue();
			model.addAttribute("report", report);
			
			
		List<EmpProfile> list1=empProfileService.show();
		model.addAttribute("list1", list1);
		return new ModelAndView("EmployeeProfile/EmployeeProfileedit");
	
	}
	@PostMapping(value="/updateprofile")
	public ModelAndView updateprofile(Model model, HttpServletRequest request,
			   HttpServletResponse response,
			   @RequestParam int id,
			   @RequestParam String fname,
			   @RequestParam String lname,
			   @RequestParam String dob,
			   @RequestParam String gender,
			   @RequestParam String mobile,
			   @RequestParam String landline,
			   @RequestParam String email,
			   @RequestParam int empcat,
			   @RequestParam int printcat,
			   @RequestParam int dept,
			   @RequestParam int div,
			   @RequestParam int desig,
			   @RequestParam String doj,
			   @RequestParam int level,
			   @RequestParam String report,
			   @RequestParam MultipartFile photo,
			   @RequestParam String status,
			   @RequestParam String reason
			   )
	{		
		Blob blob =null;
		try
		{
			byte[] bytearray = photo.getBytes();
			
			blob = new SerialBlob(bytearray);
			
		}catch(Exception e)
		{	
			e.printStackTrace();
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		
		
		Date sqlDate = null;
		try {
			sqlDate = (Date) format.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		Date sqlDate1=null;
		try
		{
		sqlDate1 = (Date) format1.parse(doj);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
     EmpProfile updatefield=new EmpProfile();
		
     updatefield.setEmpb_id(id);
     updatefield.setEmpb_fname(fname);
     updatefield.setEmpb_lname(lname);
     updatefield.setDob(sqlDate);
     updatefield.setEmpb_gend(gender);
     updatefield.setEmpb_mobile(mobile);
     updatefield.setEmpb_landline(landline);
     updatefield.setEmpb_email(email);
     updatefield.setEmpCatToEmp(empcat);
     updatefield.setPrintCatId(printcat);
     updatefield.setDeptToempBasic(dept);
     updatefield.setDivToempBasic(div);
     updatefield.setDesigToEmp(desig);
     updatefield.setLevelToemp(level);
     updatefield.setDateOfJoin(sqlDate1);
     updatefield.setEmpb_report(report);
     updatefield.setEmpb_photo(blob);
     
     updatefield.setStatus(status);
     updatefield.setInactive_reason(reason);
     
     empProfileService.update(updatefield);
		

     List<EmployeeCategory> empList=empCatService.findAllByAscendingOrder("empCatName","Y");
		model.addAttribute("empList", empList);
     
		List<EmpProfile> list1=empProfileService.show();
		model.addAttribute("list1", list1);
		
		List<Designation> desiList=designationservice.findAllByAscendingOrder("desig_name","Y");
		model.addAttribute("desiList", desiList);
     
		return new ModelAndView("EmployeeProfile/employeeprofile");
	}
	@RequestMapping(value="/searchEmployee",method=RequestMethod.POST)
	public ModelAndView searchEmployee(Model model)
	{
		
		return new ModelAndView("EmployeeProfile/searchEmployee");
	}
	
	@RequestMapping(value="/getEmployee",method=RequestMethod.POST)
	public ModelAndView getEmployee(Model model,@RequestParam int id)
	{
	
		List<EmpProfile> list1=(List<EmpProfile>) empProfileService.display(id);
		model.addAttribute("list1", list1);
		List<EmployeeCategory> empCatList = empCatService.findAll();
		model.addAttribute("empCatList", empCatList);
		
		List<Designation> designationList=designationservice.findAll();
		model.addAttribute("DesignationList", designationList);
		
		List<PrintCategory> printCategory=printCategoryService.findAll();
		model.addAttribute("printCategoryList", printCategory);
		
		List<Department> departmentList=departmentservice.findAll();
		model.addAttribute("departmentList", departmentList);
		
		List<Division> divisionList = divisionservice.findAll();
		model.addAttribute("divisionList",divisionList);
		
		
		
		List<JobLevel> joblevellist=joblevelservice.findAll();
		model.addAttribute("JobLevelList", joblevellist);
		
		
		
		return new ModelAndView("EmployeeProfile/searchEmployee");
	}	
	@RequestMapping(value="/reportEmployee",method=RequestMethod.POST)
	public ModelAndView reportEmployee(Model model,HttpSession session)
	{
		String id=(String) session.getAttribute("username");
		int empid=Integer.parseInt(id);
		List<Object[]> reportlist=empProfileService.showreport(empid);
		 
		 model.addAttribute("reportlist", reportlist);
		 model.addAttribute("reportlistsize", reportlist.size());
		 
		 List<Designation> designationList=designationservice.findAll();
	     model.addAttribute("desiList", designationList);
		 
		
		return new ModelAndView("EmployeeProfile/EmployeeReport");
	}
	@RequestMapping(value="/showEmployee",method=RequestMethod.POST)
	public ModelAndView showreport(Model model,@RequestParam int id)
	{
		 List<Object[]> reportlist=empProfileService.showreport(id);
		 
		 model.addAttribute("reportlist", reportlist);
		 model.addAttribute("reportlistsize", reportlist.size());
		 
		 List<Designation> designationList=designationservice.findAll();
	     model.addAttribute("desiList", designationList);
		 
		return new ModelAndView("EmployeeProfile/EmployeeReport");
	}
	@PostMapping(value="/showprofile")
	public ModelAndView showpage(Model model, @RequestParam int id)
	{
			
		List<Department> departmentList=departmentservice.findAll();
		model.addAttribute("departmentList", departmentList);
		
		List<EmployeeCategory> empCatList = empCatService.findAll();
		model.addAttribute("empCatList", empCatList);
		
		List<Designation> designationList=designationservice.findAll();
		model.addAttribute("DesignationList", designationList);
		
		List<PrintCategory> printCategory=printCategoryService.findAll();
		model.addAttribute("printCategoryList", printCategory);
		
		//List<Department> departmentList=departmentservice.findAll();
		//model.addAttribute("departmentList", departmentList);
		
		List<Division> divisionList = divisionservice.findAll();
		model.addAttribute("divisionList",divisionList);
		
		List<JobLevel> joblevellist=joblevelservice.findAll();
		model.addAttribute("JobLevelList", joblevellist);
		
		List<EmpProfile> list1=(List<EmpProfile>) empProfileService.display(id);
		model.addAttribute("list1", list1);
		
		return new ModelAndView("EmployeeProfile/Employeeshow");
	}
}
	
 