package com.TMS.Controller;
import javax.servlet.http.HttpServletRequest;
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

import com.TMS.Entity.activity;
import com.TMS.serviceclass.ActivityService;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

@RestController
public class ActivityController {

	@Autowired
	ActivityService activityservice; 
	
	@RequestMapping(value = "/activity", method = RequestMethod.POST)
	public ModelAndView activity(Model model) {
		
		model.addAttribute("list", this.activityservice.show());
		return new ModelAndView("Activity/activity");
	} 
	@PostMapping(value="/cancelA")
	public ModelAndView page(Model model)
	{
		model.addAttribute("list", this.activityservice.show());
		return new ModelAndView("Activity/activity");
	}
	@RequestMapping(value = "/saveactivity", method = RequestMethod.POST)
	public ModelAndView activity1(Model model,@ModelAttribute activity activity, HttpServletRequest request,
											   HttpServletResponse response) 
    	{
		
		activity.getActivity_dts();
		activity.getActivity_status();
		try{
			if(activity.getActivity_dts()!=null && activity.getActivity_status()!=null)	
			{
				activityservice.add(activity);		
			}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("list", this.activityservice.show());
		return new ModelAndView("Activity/activity");
	}
	@RequestMapping(value = "/activitydelete", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
	public ModelAndView delete(@ModelAttribute activity activity,@RequestParam int id,Model model) throws MySQLIntegrityConstraintViolationException  {
		
		
		activityservice.delete(id);
		model.addAttribute("list", this.activityservice.show());
		return new ModelAndView("Activity/activity");
	} 
	@RequestMapping(value = "/editactivity", method = RequestMethod.POST)
	public ModelAndView up(@ModelAttribute activity activity,@RequestParam int id,Model model){
		
		
 
		
		activity list=activityservice.edit(id);
		model.addAttribute("object", list);
		model.addAttribute("list", this.activityservice.show());
		return new ModelAndView("Activity/activityupdate");	
	} 
	@RequestMapping(value = "/activityupdate", method = RequestMethod.POST)
	public ModelAndView activityupdate(@ModelAttribute activity activity, @RequestParam int id,Model model)
	{
		
		activity.getId();
		activity.getActivity_dts();
		activity.getActivity_status(); 
		activityservice.update(activity);
		model.addAttribute("list", this.activityservice.show());
		return new ModelAndView("Activity/activity");
	}
}
