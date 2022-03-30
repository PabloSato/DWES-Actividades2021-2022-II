package com.ite.springsecurity.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite.springsecurity.modelo.entity.Libro;
import com.ite.springsecurity.modelo.entity.LineasPedido;
import com.ite.springsecurity.modelo.entity.Pedido;
import com.ite.springsecurity.modelo.entity.Perfile;
import com.ite.springsecurity.modelo.entity.Tema;
import com.ite.springsecurity.modelo.entity.Usuario;
import com.ite.springsecurity.modelo.service.IntLibroDao;
import com.ite.springsecurity.modelo.service.IntLineasPedidoDao;
import com.ite.springsecurity.modelo.service.IntPedidoDao;
import com.ite.springsecurity.modelo.service.IntPerfilDao;
import com.ite.springsecurity.modelo.service.IntTemasDao;
import com.ite.springsecurity.modelo.service.IntUsuarioDao;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private PasswordEncoder pwenco;

	@Autowired
	private IntTemasDao item;
	@Autowired
	private IntLibroDao ilib;
	@Autowired
	private IntPedidoDao iped;
	@Autowired
	private IntUsuarioDao ius;
	@Autowired
	private IntPerfilDao iper;
	@Autowired
	private IntLineasPedidoDao ilp;

	// ----------------------------------------------------- DATA BINDER
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}

	// ----------------------------------------------------- GET - REGISTRO
	@GetMapping("/registro")
	public String getRegistro() {
		return "reg";
	}

	// ----------------------------------------------------- POST - BUSCAR
	@PostMapping("/registro")
	public String postRegistro(Usuario usuario, RedirectAttributes attr) {

		Usuario user = ius.findByUsername(usuario.getUsername());
		Usuario userEmail = ius.findByEmail(usuario.getEmail());

		if (user == null && userEmail == null) {

			Perfile perfile = iper.findById(2);
			List<Perfile> perfiles = new ArrayList<Perfile>();
			perfiles.add(perfile);

			usuario.setPassword(pwenco.encode(usuario.getPassword()));
			usuario.setFechaAlta(new Date());
			usuario.setPerfiles(perfiles);
			usuario.setEnabled(1);

			int filas = ius.insertOne(usuario);

			if (filas == 1) {
				attr.addFlashAttribute("msg", "Usuario Registroado");

				return "redirect:/login";
			} else {
				attr.addFlashAttribute("msg", "Error al Registrar");

				return "redirect:/cliente/registro";
			}

		} else {

			if (user != null) {
				attr.addFlashAttribute("msg", "Username YA Existente. Usuario NO registrado");
			} else {
				attr.addFlashAttribute("msg", "E-mail YA en uso. Usuario NO registrado");
			}

			return "redirect:/cliente/registro";
		}
	}

	// ----------------------------------------------------- GET - INDEX
	@GetMapping("/inicio")
	public String getIndex(HttpSession session, Model model, Authentication aut) {

		Usuario user = ius.findByUsername(aut.getName());
		if (session.getAttribute("user") == null) {
			session.setAttribute("user", user);
		}

		int size = ilib.findNovedad().size();
		model.addAttribute("listaLibros", ilib.findNovedad());
		model.addAttribute("size", size);
		model.addAttribute("temas", item.findAll());

		return "index";
	}

	// ----------------------------------------------------- GET - FINDALL LIBROS
	@GetMapping("/verLibros")
	public String getVerLibros(HttpSession session, Model model) {

		int size = ilib.findAll().size();
		model.addAttribute("listaLibros", ilib.findAll());
		model.addAttribute("size", size);

		return "verLibros";
	}

	// ----------------------------------------------------- POST - BUSCAR
	@PostMapping("/buscar")
	public String verBusqueda(HttpSession session, RedirectAttributes attr, @RequestParam("filtro") String filtro) {

		if (filtro.equals("")) {

			attr.addFlashAttribute("msg", "Añade un filtro a la búsqueda");
			return "redirect:/cliente/inicio";

		} else {

			List<Libro> listaLibros = ilib.findByTitulo(filtro);
			int size = listaLibros.size();

			attr.addFlashAttribute("listaLibros", listaLibros);
			attr.addFlashAttribute("size", size);
			attr.addFlashAttribute("busqueda", filtro);

			return "redirect:/cliente/busqueda";

		}

	}

	// ----------------------------------------------------- GET - VER BUSQUEDA
	@GetMapping("/busqueda")
	public String getBusqueda(HttpSession session, Model model) {
		return "verLibrosBusq";
	}

	// ----------------------------------------------------- POST - TEMA
	@PostMapping("/tema")
	public String verTema(HttpSession session, RedirectAttributes attr, @RequestParam("tema.idTema") int idTema) {

		if (idTema != 0) {
			List<Libro> listaLibros = ilib.findByTema(idTema);
			Tema tema = item.findById(idTema);
			int size = listaLibros.size();

			attr.addFlashAttribute("listaLibros", listaLibros);
			attr.addFlashAttribute("tema", tema);
			attr.addFlashAttribute("size", size);

			return "redirect:/cliente/search";

		} else {
			attr.addFlashAttribute("msg", "Elige un Tema pra la búsqueda");
			return "redirect:/cliente/inicio";
		}
	}

	// ----------------------------------------------------- VER - SEARCH
	@GetMapping("/search")
	public String getBusquedaLibros(HttpSession session, Model model) {

		return "verTema";
	}

	// ----------------------------------------------------- GET - DETALLE LIBRO
	@GetMapping("/ver/{isbn}")
	public String verDetalle(HttpSession session, Model model, @PathVariable("isbn") long isbn) {

		Libro libro = ilib.findByIsbn(isbn);

		model.addAttribute("libro", libro);

		return "detalleLibro";
	}

	// ----------------------------------------------------- GET - VER CARRITO
	@GetMapping("/verCarrito")
	public String verCarrito(HttpSession session, Model model) {
		List<Libro> carrito = (List<Libro>) session.getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<Libro>();
		}
		BigDecimal total = new BigDecimal(0);
		for (Libro ele : carrito) {
			total = total.add(ele.getPrecioUnitario());
		}
		model.addAttribute("carrito", carrito);
		model.addAttribute("total", total);

		return "verCarrito";
	}

	// ----------------------------------------------------- GET - ADD CARRITO
	@GetMapping("/addCarrito/{isbn}")
	public String getAddCarrito(HttpSession session, Model model, @PathVariable("isbn") long isbn) {

		List<Libro> compra = (List<Libro>) session.getAttribute("carrito");

		Libro libro = ilib.findByIsbn(isbn);
		int pos = compra.indexOf(libro);

		if (pos == -1) {
			compra.add(libro);
		}

		session.setAttribute("carrito", compra);

		List<Libro> carrito = (List<Libro>) session.getAttribute("carrito");
		BigDecimal total = new BigDecimal(0);
		for (Libro ele : carrito) {
			total = total.add(ele.getPrecioUnitario());
		}

		return "forward:/cliente/verCarrito";
	}

	// ----------------------------------------------------- GET - ELIMINAR CARRITO
	@GetMapping("/delCarrito/{isbn}")
	public String delCarrito(HttpSession session, Model model, @PathVariable("isbn") long isbn) {

		Libro libro = ilib.findByIsbn(isbn);

		List<Libro> carrito = (List<Libro>) session.getAttribute("carrito");

		carrito.remove(libro);

		session.setAttribute("carrito", carrito);

		return "forward:/cliente/verCarrito";
	}

	// ----------------------------------------------------- POST - COMPRAR
	@PostMapping("/comprar")
	public String postCompra(HttpSession session, RedirectAttributes attr) {

		List<Libro> carrito = (List<Libro>) session.getAttribute("carrito");
		if (carrito.size() > 0) {

			Usuario user = (Usuario) session.getAttribute("user");
			String direc = user.getDireccion();
			// CREAMOS PEDIDO
			Pedido pedido = new Pedido();
			pedido.setDireccionEntrega(direc);
			pedido.setEstado("terminado");
			pedido.setFechaAlta(new Date());
			pedido.setUsuario(user);
			// CREAMOS LINEAS_PEDIDO
			List<LineasPedido> lineas = new ArrayList<LineasPedido>();
			// ADD LINEAS_PEDIDO
			for (Libro ele : carrito) {
				LineasPedido lp = new LineasPedido();
				lp.setPedido(pedido);
				lp.setFechaAlta(new Date());
				lp.setLibro(ele);
				lp.setCantidad(1);
				lp.setPrecioVenta(ele.getPrecioUnitario());
				lineas.add(lp);
			}
			// ADD LINEAS AL PEDIDO
			pedido.setLineasPedidos(lineas);
			// INSERTAMOS PEDIDO
			int filas = iped.insertOne(pedido);

			if (filas == 1) {
				attr.addFlashAttribute("msg", "Pedido Realizado correctamente");
				List<Libro> lista = new ArrayList<Libro>();
				session.setAttribute("carrito", lista);
			} else {
				attr.addFlashAttribute("msg", "Pedido NO realizado");
			}
		} else {
			attr.addFlashAttribute("msg", "El carrito está VACIO");
		}

		return "redirect:/cliente/verCarrito";
	}

	// ----------------------------------------------------- GET - MIS DATOS
	@GetMapping("/misDatos")
	public String misDatos(HttpSession session, Model model) {
		return "verDetalleCliente";
	}

	// ----------------------------------------------------- GET - EDIT DATOS
	@GetMapping("/editDatos")
	public String getEditDatos(HttpSession session, Model model) {

		return "editUser";
	}

	// ----------------------------------------------------- POST - EDIT DATOS
	@PostMapping("/editDatos")
	public String postEditDatos(HttpSession session, Usuario usuario, RedirectAttributes attr) {

		Usuario user = (Usuario) session.getAttribute("user");
		usuario.setUsername(user.getUsername());
		usuario.setPassword(user.getPassword());

		int filas = ius.insertOne(usuario);

		if (filas == 1) {
			attr.addFlashAttribute("msg", "Usuario Editado Correctamente");
			session.setAttribute("user", usuario);
		} else {
			attr.addFlashAttribute("msg", "Usuario NO Editado");
		}

		return "redirect:/cliente/misDatos";
	}

	// ----------------------------------------------------- GET - MIS PEDIDOS
	@GetMapping("/misPedidos")
	public String verMisPedidos(HttpSession session, Model model) {

		Usuario usuario = (Usuario) session.getAttribute("user");

		List<Pedido> listaPedidos = iped.findByUsuario(usuario.getUsername());

		model.addAttribute("listaPedidos", listaPedidos);

		return "verPedido";
	}

	// ----------------------------------------------------- GET - MI PEDIDO
	@GetMapping("/detPed/{idPedido}")
	public String verDetallePedido(HttpSession session, Model model, @PathVariable("idPedido") int idPedido) {

		List<LineasPedido> listaPedido = ilp.findByPedido(idPedido);
		BigDecimal total = new BigDecimal(0);
		for (LineasPedido ele : listaPedido) {
			total = total.add(ele.getLibro().getPrecioUnitario());
		}

		model.addAttribute("idPedido", idPedido);
		model.addAttribute("listaPedido", listaPedido);
		model.addAttribute("total", total);

		return "detPedido";
	}

}
