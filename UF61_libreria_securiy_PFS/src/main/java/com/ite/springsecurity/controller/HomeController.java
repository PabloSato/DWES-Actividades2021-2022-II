package com.ite.springsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ite.springsecurity.modelo.entity.Libro;
import com.ite.springsecurity.modelo.entity.Usuario;
import com.ite.springsecurity.modelo.service.IntLibroDao;
import com.ite.springsecurity.modelo.service.IntTemasDao;
import com.ite.springsecurity.modelo.service.IntUsuarioDao;

@Controller
public class HomeController {

	@Autowired
	private IntUsuarioDao ius;
	@Autowired
	private IntLibroDao ilib;
	@Autowired
	private IntTemasDao item;

	// ----------------------------------------------------- GET - INICIO
	@GetMapping("/inicio")
	public String inicio() {
		return "home";
	}

	// ----------------------------------------------------- GET - LOGIN
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	// ----------------------------------------------------- GET - INDEX
	@GetMapping("/")
	public String procLogin(Authentication aut, Model model, HttpSession session) {

		Usuario user = ius.findByUsername(aut.getName());
		session.setAttribute("user", user);
		List<Libro> carrito = new ArrayList<Libro>();
		session.setAttribute("carrito", carrito);

		int size = ilib.findNovedad().size();
		model.addAttribute("listaLibros", ilib.findNovedad());
		model.addAttribute("size", size);
		model.addAttribute("temas", item.findAll());

		return "index";
	}

	// ----------------------------------------------------- GET - LOGOUT
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {

		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

		logoutHandler.logout(request, null, null);
		return "redirect:/inicio";
	}

}
