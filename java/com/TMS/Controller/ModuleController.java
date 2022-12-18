package com.TMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TMS.Entity.module;
import com.TMS.serviceclass.ModuleService;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


@RestController
public class ModuleController {

	@Autowired
	ModuleService moduleservice;
	
	@RequestMapping(value="/module", method=RequestMethod.POST)
	public ModelAndView module(Model model)
	{
		model.addAttribute("list", this.moduleservice.display());
		return new ModelAndView("module/module");
	}
	@PostMapping(value="/cancelM")
	public ModelAndView page(Model model)
	{
		model.addAttribute("list", this.moduleservice.display());
		return new ModelAndView("module/module");
	}
	@RequestMapping(value="/savemodule", method=RequestMethod.POST)
	public ModelAndView model(Model model, @ModelAttribute module savemodule)
	{
		savemodule.getModule_detail();
		savemodule.getModule_status();
		try
		{
			if(savemodule.getModule_detail()!=null && savemodule.getModule_status()!=null)
			{
				moduleservice.add(savemodule);
			}
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
		}
		
		model.addAttribute("list", this.moduleservice.display());
		return new ModelAndView("module/module");
		}
		@RequestMapping(value = "/moduleupdate", method = RequestMethod.POST)
		public ModelAndView up(@RequestParam int id,Model model){
	
		module list=moduleservice.edit(id);
		model.addAttribute("object", list);
		model.addAttribute("list", this.moduleservice.display());
		return new ModelAndView("module/moduleedit");	
		}
		@RequestMapping(value = "/moduleedit", method = RequestMethod.POST)
		public ModelAndView activityupdate(@ModelAttribute module module,Model model) {
			
			module.getId();
			module.getModule_detail();
			module.getModule_status();
			moduleservice.update(module);
			model.addAttribute("list", this.moduleservice.display());
			return new ModelAndView("module/module");
		}
		@RequestMapping(value = "/moduledelete", method = RequestMethod.POST)
		public ModelAndView delete(@RequestParam int id,Model model)  throws MySQLIntegrityConstraintViolationException {
			
			
			moduleservice.delete(id);
			model.addAttribute("list", this.moduleservice.display());
			return new ModelAndView("module/module");
		}
}
