package com.ite.springsecurity.controller;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
@RequestMapping("/admon")
public class AdminController {

	@Autowired
	private IntTemasDao item;
	@Autowired
	private IntLibroDao ilib;
	@Autowired
	private IntUsuarioDao ius;
	@Autowired
	private IntPerfilDao iper;
	@Autowired
	private IntPedidoDao iped;
	@Autowired
	private IntLineasPedidoDao ilp;

	// ----------------------------------------------------- DATA BINDER
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}

	// ----------------------------------------------------- INICIO
	@GetMapping("/inicio")
	public String inicio(HttpSession session, Model model) {

		int size = ilib.findNovedad().size();
		model.addAttribute("listaLibros", ilib.findNovedad());
		model.addAttribute("size", size);
		model.addAttribute("temas", item.findAll());

		return "index";
	}

	// ----------------------------------------------------- GET - ALTA TEMA
	@GetMapping("/altaTema")
	public String getAlataTema() {

		return "altaTema";
	}

	// ----------------------------------------------------- POST - ALTA TEMA
	@PostMapping("/altaTema")
	public String postAltaTema(HttpSession session, RedirectAttributes attr, Tema tema) {

		int filas = item.insertOne(tema);

		if (filas == 1) {
			attr.addFlashAttribute("msg", "Tema añadido a la BBDD");
		} else {
			attr.addFlashAttribute("msg", "Tema NO añadido a la BBDD");
		}

		return "redirect:/admon/altaTema";
	}

	// ----------------------------------------------------- GET - FINDALL TEMAS
	@GetMapping("/verTemas")
	public String verTemas(HttpSession session, Model model) {

		int size = item.findAll().size();

		model.addAttribute("listaTemas", item.findAll());
		model.addAttribute("size", size);

		return "verTodosTemas";
	}

	// ----------------------------------------------------- GET - FINDID TEMA
	@GetMapping("/verTema/{idTema}")
	public String verTemaId(HttpSession session, Model model, @PathVariable("idTema") int idTema) {

		List<Libro> listaLibros = ilib.findByTema(idTema);
		Tema tema = item.findById(idTema);
		int size = listaLibros.size();

		model.addAttribute("listaLibros", listaLibros);
		model.addAttribute("tema", tema);
		model.addAttribute("size", size);

		return "verTema";
	}

	// ----------------------------------------------------- GET - EDIT TEMA
	@GetMapping("/editTema/{idTema}")
	public String getEditTema(HttpSession session, Model model, @PathVariable("idTema") int idTema) {

		Tema tema = item.findById(idTema);

		model.addAttribute("tema", tema);

		return "editTema";
	}

	// ----------------------------------------------------- POST - EDIT TEMA
	@PostMapping("/editTema/{idTema}")
	public String postEditTema(HttpSession session, RedirectAttributes attr, @PathVariable("idTema") int idTema,
			Tema tema) {

		tema.setIdTema(idTema);

		int filas = item.insertOne(tema);
		if (filas == 1) {
			attr.addFlashAttribute("msg", "Tema Editado correctamente");
		} else {
			attr.addFlashAttribute("msg", "Tema NO editado");
		}

		return "redirect:/admon/editTema/" + idTema;
	}

	// ----------------------------------------------------- GET - ELIM TEMA
	@GetMapping("/eliminarTema/{idTema}")
	public String getEliminarTema(HttpSession session, Model model, @PathVariable("idTema") int idTema) {

		int filas = item.deleteOne(idTema);

		if (filas == 1) {
			model.addAttribute("msg", "Tema " + idTema + " eliminado");
		} else {
			model.addAttribute("msg", "Tema NO eliminado");
		}

		int size = item.findAll().size();

		model.addAttribute("listaTemas", item.findAll());
		model.addAttribute("size", size);
		return "verTodosTemas";
	}

	// ----------------------------------------------------- GET - FINDALL LIBROS
	@GetMapping("/verLibros")
	public String verLibros(HttpSession session, Model model) {

		int size = ilib.findAll().size();
		model.addAttribute("listaLibros", ilib.findAll());
		model.addAttribute("size", size);

		return "verLibros";
	}

	// ----------------------------------------------------- GET - ALTA LIBRO
	@GetMapping("/altaLibro")
	public String getAltaLibro(HttpSession session, Model model) {

		model.addAttribute("listaTema", item.findAll());

		return "altaLibro";
	}

	// ----------------------------------------------------- POST - ALTA LIBRO
	@PostMapping("/altaLibro")
	public String postAltaLibro(HttpSession session, RedirectAttributes attr, @RequestParam("file") MultipartFile file,
			Libro libro) throws Exception {

		if (file == null || file.isEmpty()) {
			libro.setImagen("book.png");
		} else {
			libro.setImagen(file.getOriginalFilename());
		}

		// PORTADA
		String destino = "./src/main/resources/static/img/books/";
		String fileName = file.getOriginalFilename();
		File filePath = new File(destino);
		File img = new File(filePath.getAbsolutePath() + "/" + fileName);
		// DATOS LIBRO
		int idTema = libro.getTema().getIdTema();
		Tema tema = item.findById(idTema);
		libro.setTema(tema);

		int filas = ilib.insertOne(libro);

		if (filas == 1) {
			attr.addFlashAttribute("msg", "Libro añadido a la BBDD");
			try {
				file.transferTo(img);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			attr.addFlashAttribute("msg", "Libro NO añadido a la BBDD");
		}

		return "redirect:/admon/altaLibro";
	}

	// ----------------------------------------------------- GET - EDITAR LIBRO
	@GetMapping("/editar/{isbn}")
	public String getEditarLibro(HttpSession session, Model model, @PathVariable("isbn") long isbn) {

		Libro libro = ilib.findByIsbn(isbn);

		model.addAttribute("libro", libro);
		model.addAttribute("listaTema", item.findAll());

		return "editarLibro";
	}

	// ----------------------------------------------------- POST - EDITAR LIBRO
	@PostMapping("/editar/{isbn}")
	public String postEditLibro(HttpSession session, RedirectAttributes attr, Libro libro,
			@PathVariable("isbn") long isbn, @RequestParam("file") MultipartFile file) {

		Libro oldBook = ilib.findByIsbn(isbn);
		boolean flag = false;
		if (file == null || file.isEmpty()) {
			libro.setImagen(oldBook.getImagen());
			flag = false;
		} else {
			libro.setImagen(file.getOriginalFilename());
			flag = true;
		}
		// PORTADA
		String destino = "./src/main/resources/static/img/books/";
		String fileName = file.getOriginalFilename();
		File filePath = new File(destino);
		File img = new File(filePath.getAbsolutePath() + "/" + fileName);
		// BOOK DATA
		int idTema = libro.getTema().getIdTema();
		Tema tema = item.findById(idTema);
		libro.setTema(tema);

		int filas = ilib.insertOne(libro);

		if (filas == 1) {
			if (flag == false) {
				attr.addFlashAttribute("msg", "Libro Editado correctamente");
			} else {
				try {
					file.transferTo(img);
					attr.addFlashAttribute("msg", "Libro Editado correctamente");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			attr.addFlashAttribute("msg", "Libro NO editado");
		}
		return "redirect:/admon/editar/" + libro.getIsbn();
	}

	// ----------------------------------------------------- GET - ELIMINAR LIBRO
	@GetMapping("/eliminar/{isbn}")
	public String eliminarLibro(HttpSession session, Model model, @PathVariable("isbn") long isbn) {

		int filas = ilib.deleteOne(isbn);

		if (filas == 1) {
			model.addAttribute("msg", "Libro ELIMINADO correctamente");
		} else {
			model.addAttribute("msg", "Libro NO eliminado");
		}

		int size = ilib.findNovedad().size();
		model.addAttribute("listaLibros", ilib.findNovedad());
		model.addAttribute("size", size);
		model.addAttribute("temas", item.findAll());

		return "index";
	}

	// ----------------------------------------------------- GET - DETALLE LIBRO
	@GetMapping("/ver/{isbn}")
	public String verDetalle(HttpSession session, Model model, @PathVariable("isbn") long isbn) {

		Libro libro = ilib.findByIsbn(isbn);

		model.addAttribute("libro", libro);

		return "detalleLibro";
	}

	// ----------------------------------------------------- POST - BUSCAR
	@PostMapping("/buscar")
	public String verBusqueda(HttpSession session, Model model, @RequestParam("filtro") String filtro) {

		List<Libro> listaLibros = ilib.findByTitulo(filtro);
		int size = listaLibros.size();

		model.addAttribute("listaLibros", listaLibros);
		model.addAttribute("size", size);
		model.addAttribute("busqueda", filtro);

		return "verLibrosBusq";
	}

	// ----------------------------------------------------- POST - TEMA
	@PostMapping("/tema")
	public String verTema(HttpSession session, Model model, @RequestParam("tema.idTema") int idTema) {

		List<Libro> listaLibros = ilib.findByTema(idTema);
		Tema tema = item.findById(idTema);
		int size = listaLibros.size();

		model.addAttribute("listaLibros", listaLibros);
		model.addAttribute("tema", tema);
		model.addAttribute("size", size);

		return "verTema";
	}

	// ----------------------------------------------------- GET - PERFILES
	@GetMapping("/perfiles")
	public String verPerfiles(HttpSession session, Model model) {

		int size = iper.findAll().size();

		model.addAttribute("listaPerfiles", iper.findAll());
		model.addAttribute("size", size);

		return "verPerfiles";
	}

	// ----------------------------------------------------- GET - USUARIOS-PERFIL
	@GetMapping("/verUsuarios/{idPerfil}")
	public String verUsuariosperfil(HttpSession session, Model model, @PathVariable("idPerfil") int idPerfil) {

		Perfile perfile = iper.findById(idPerfil);
		List<Usuario> listaUsuarios = ius.findByPerfil(idPerfil);

		int size = listaUsuarios.size();

		model.addAttribute("listaUsuarios", listaUsuarios);
		model.addAttribute("perfil", perfile);
		model.addAttribute("size", size);

		return "verUsuariosPerfil";
	}

	// ----------------------------------------------------- GET - USUARIOS
	@GetMapping("/usuarios")
	public String verUsuarios(HttpSession session, Model model) {

		List<Usuario> listaAdmin = ius.findAdmin();
		List<Usuario> listaCliente = ius.findCliente();

		int sizeAdmin = listaAdmin.size();
		int sizeClient = listaCliente.size();

		model.addAttribute("listaAdmin", listaAdmin);
		model.addAttribute("sizeAdmin", sizeAdmin);
		model.addAttribute("listaCliente", listaCliente);
		model.addAttribute("sizeClient", sizeClient);

		return "verUsuarios";
	}

	// ----------------------------------------------------- GET - USUARIOS-DETALLE
	@GetMapping("/detUser/{username}")
	public String verDetalleUsuario(HttpSession session, Model model, @PathVariable("username") String username) {

		Usuario usuario = ius.findByUsername(username);

		model.addAttribute("user", usuario);

		return "verDetalleCliente";
	}

	// ----------------------------------------------------- GET - CLIENTES
	@GetMapping("/clientes")
	public String verClientes(HttpSession session, Model model) {

		
		List<Usuario> listaClientes = ius.findCliente();
		int size = listaClientes.size();

		model.addAttribute("listaClientes", listaClientes);
		model.addAttribute("size", size);

		return "verClientes";
	}

	// ----------------------------------------------------- GET - VER PEDIDOS
	@GetMapping("/pedidos")
	public String verPedidos(HttpSession session, Model model) {

		List<Pedido> listaPedidos = iped.findAll();
		int size = listaPedidos.size();

		model.addAttribute("listaPedidos", listaPedidos);
		model.addAttribute("size", size);

		return "verPedido";
	}

	// ----------------------------------------------------- GET - DET PEDIDO
	@GetMapping("/detPed/{idPedido}")
	public String detallePedido(HttpSession session, Model model, @PathVariable("idPedido") int idPedido) {

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

	// ----------------------------------------------------- GET - VER PEDIDOS
	@GetMapping("/verPedidos/{username}")
	public String verPedidosCliente(HttpSession session, Model model, @PathVariable("username") String username) {
		
		
		int gasto = ilib.gastoTotalCliente(username);
		int libros = ilib.cantidadLibroCliente(username);
		int temas = ilib.cantidadTemasCliente(username);
		
		System.out.println(temas);
		
		model.addAttribute("libros", libros);
		model.addAttribute("temas", temas);
		model.addAttribute("gasto", gasto);
		model.addAttribute("username", username);
		
		return "detPedCliente";
	}

}
