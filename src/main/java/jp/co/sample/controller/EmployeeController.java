package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
 	private EmployeeService service;

 	@RequestMapping("/showList")
 	public String showList(Model model) {
 		
 		List<Employee> employeeList = service.showList();
 		
 		model.addAttribute("employeeList",employeeList);
 		return "employee/list";
 	}
 	
 	@ModelAttribute
 	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
 	}
 	
 	@RequestMapping("/showDetail")
 	public String showDetail(String id,Model model) {
		Employee employee = service.showDetail(Integer.parseInt(id));
		model.addAttribute("employee",employee);
		return "employee/detail.html";
 		
 	}
 	
 	@RequestMapping("/update")
 	public String update(@Validated UpdateEmployeeForm form ,BindingResult result,Model model) {
 		
 		if(result.hasErrors()) {
 			return showDetail(form.getId(),model);
 		}
 		
 		Employee employee = service.showDetail(form.getIntId());
 		employee.setDependentsCount(form.getIntDentsCount());
 		service.update(employee);
 		
		return "redirect:/employee/showList";
 	}
 	
 	
 }