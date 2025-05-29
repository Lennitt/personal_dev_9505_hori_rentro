package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Form;
import com.example.demo.repository.FormRepository;

@Controller
public class TaskAdminController {

	@Autowired
	FormRepository formRepository;

	@GetMapping("/tasks/admin")
	public String create(Model model) {

		List<Form> forms = formRepository.findAll();
		model.addAttribute("forms", forms);

		for (Form form : forms) {
			System.out.println(form);
		}

		return "/admin/forms";
	}
}
