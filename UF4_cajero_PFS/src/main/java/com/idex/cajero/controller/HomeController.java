package com.idex.cajero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/inicio")
	public String inicio(Model model) {
		//Mediante el uso de forward:/ redirijo al controller de cuenta para realizar el login desde el controller que toma todas las necesidades de Cuenta
		
		return "forward:/cuenta/login";
	}
	
}
