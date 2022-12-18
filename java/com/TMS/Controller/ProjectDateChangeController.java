package com.TMS.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class ProjectDateChangeController {

	@PostMapping(value="/PDC")
	public ModelAndView PDC(Model model)
	{
		//("PDC Controller");
		return new ModelAndView("ProjectDC/ProjectDC");
	}
	
}
