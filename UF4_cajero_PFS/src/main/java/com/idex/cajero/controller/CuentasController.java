package com.idex.cajero.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.idex.cajero.modelo.beans.Cuenta;
import com.idex.cajero.modelo.beans.Movimiento;
import com.idex.cajero.modelo.dao.IntCuentaDao;
import com.idex.cajero.modelo.dao.IntMoviemientoDao;

@Controller
@RequestMapping("/cuenta")
public class CuentasController {

	@Autowired
	private IntCuentaDao icu;
	@Autowired
	private IntMoviemientoDao imov;

	// ----------------------------------------------------- DATA BINDER
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}

	// ----------------------------------------------------- GET - LOGIN
	@GetMapping("/login")
	public String getLogin(HttpSession session) {

		session.invalidate();//Al entrar en la URL de login, invalidamos cualquier sesion que tengamos, forzando que se tenga que volver a dar de alta

		return "index";
	}

	// ----------------------------------------------------- POST - LOGIN
	@PostMapping("/login")
	public String postLogin(HttpSession session, Model model, Cuenta laCuenta, RedirectAttributes attr) {
		
		
		int idCuenta = laCuenta.getIdCuenta();//Guardamos la idCuenta del objeto Cuenta que recogemos del formualrio

		Cuenta cuenta = icu.findById(idCuenta); //Localizamos con findById si ese cuenta exite

		if (cuenta != null) { //Si a cuenta existe, iniciamos session y redirigimos al menu

			session.setAttribute("cuenta", cuenta);

			return "redirect:/cuenta/menu";

		} else {
			//Si no existe, nos mantenemos en el login y sacamos el mensaje de error
			attr.addFlashAttribute("mensaje", "Número de Cuenta invalido");

			return "redirect:/cuenta/login";
		}
	}

	// ----------------------------------------------------- CERRAR SESIÓN
	@GetMapping("/salir")
	public String cerrarSession(HttpSession session) {

		session.invalidate();//invalidamos la sesion que tengamos, forzando que se tenga que volver a dar de alta y redirigimos al controller inicial


		return "forward:/inicio";
	}
	
	// ----------------------------------------------------- MENU
	@GetMapping("/menu")
	public String inicioMenu() {
		return "menu";
	}

	// ----------------------------------------------------- CERRAR SESIÓN
	@GetMapping("/todos")
	public String verMovimientos(HttpSession session, Model model) {

		Cuenta cuenta = (Cuenta) session.getAttribute("cuenta"); //recogemos el atributo cuenta que tenemos guardado en sesion

		if (cuenta != null) { // si la cuenta existe continuamos

			int idCuenta = cuenta.getIdCuenta(); //recuperamos el id de la Cuenta guardad en session
			
			model.addAttribute("movimientos", imov.findByCuenta(idCuenta)); // Pintamos todos los movimientos correspondientes a la cuenta

			return "movimientos";

		} else { //Si la cuenta no existe, redirigimos a la pantalla inicial y sacamos el mensaje pertinente

			model.addAttribute("mensaje", "Acceso denegado");

			return "forward:/inicio";
		}
	}

	// ----------------------------------------------------- GET - OPERACION
	@GetMapping("/operacion/{op}")
	public String getOperacion(HttpSession session, Model model, @PathVariable("op") String op) {//recogemos por path variable el nombre de la operacion elegida

		Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");//recogemos el atributo cuenta que tenemos guardado en sesion

		if (cuenta != null) {// si la cuenta existe continuamos

			model.addAttribute("orden", op); //pasamos el nombre de la operacion elegida

			return "operacion";

		} else {//Si la cuenta no existe, redirigimos a la pantalla inicial y sacamos el mensaje pertinente

			model.addAttribute("mensaje", "Acceso denegado");

			return "forward:/inicio";
		}
	}

	// ----------------------------------------------------- POST - OPERACION
	@PostMapping("/operacion/{orden}")
	public String postOperacion(RedirectAttributes attr, HttpSession session, Model model,
			@PathVariable("orden") String orden, @RequestParam("cantidad") double cantidad) {

		Cuenta cuenta = (Cuenta) session.getAttribute("cuenta"); //recogemos la cuenta que tenemos guardada en sesion
		Movimiento mov = new Movimiento(); //creamos un nuevo movimiento
		Date date = new Date(); //Creamos una fecha del dia de hoy

		if (cantidad <= 0) { //Si la cantidad pasada por el formulario es menor o igual a cero

			attr.addFlashAttribute("mensaje", "Importe erróneo"); //sacamos el mensaje de importe erroneo

			return "redirect:/cuenta/operacion/" + orden; //redirigimos otra vez a la pagina

		} else {//si la cantidad es superior a 0

			switch (orden) {

			case "ingreso": //En caso de que la operacion elegida sea ingreso

				mov.setCantidad(cantidad);//añadimos la cantidad al movimiento
				mov.setCuenta(cuenta); //añadimos la cuenta al moviminto
				mov.setFecha(date); //añadimos la fecah al movimiento
				mov.setOperacion(orden); //añadimos el nombre de la operacion

				imov.insertOne(mov); //Insertamos en la bbdd el movimiento

				double saldoOriginal = cuenta.getSaldo(); //Recoemos el saldo orginal de la cuenta
				double saldoFinal = saldoOriginal + cantidad; //y lo sumamos a la cantidad ingresada
				cuenta.setSaldo(saldoFinal); //actualizamos el saldo con la suma final

				icu.insertOne(cuenta); //actualizamos la cuenta en la bbdd

				return "redirect:/cuenta/todos"; //redirigimos a la pantalla de todos los movimientos

			case "extracto": //En caso de que la operacion elegida sea ingreso

				double saldo = cuenta.getSaldo(); //recogemos el saldo original de la cuenta
				double resultado = saldo - cantidad; //lo restamos de la cantidad pasada

				if (resultado < 0) { //en caso de que el resultado de la resta sea inferior a 0

					attr.addFlashAttribute("mensaje", "Saldo insuficiente"); //damos un mensaje de error de saldo insuficiente

					return "redirect:/cuenta/operacion/" + orden; //redirigimos de vuelva a la pagina

				} else { //en caso de que el resultado sea igual o superior a 0

					mov.setCantidad(cantidad); //aadimos la cantidad al movimiento
					mov.setCuenta(cuenta); //añadimos la cuenta al movimiento
					mov.setFecha(date); //añadimos la fecha al movimiento
					mov.setOperacion(orden); //añadimos el nombre de la operacion al movimiento

					imov.insertOne(mov); //Insertamos en la bbdd el movimiento

					cuenta.setSaldo(resultado); //actualizamos el saldo de la cuenta

					icu.insertOne(cuenta);//actualizamos la cuenta en la bbdd

					return "redirect:/cuenta/todos";//redirigimos a la pantalla de todos los mivimientos
				}

			default: //en caso de que se haya entrado en esta url sin declarar operacion

				attr.addFlashAttribute("mensaje", "Error"); //pintamos un mensaje de error

				return "redirect:/inicio";//redirigimos a la pagina inicial
			}
		}
	}

	// ----------------------------------------------------- GET - TRANSFERENCIA
	@GetMapping("/transfer")
	public String getTransfer(HttpSession session, Model model) {

		Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");//recogemos la cuenta que tenemos guardada en sesion

		if (cuenta != null) {// si la cuenta existe continuamos

			int origen = cuenta.getIdCuenta(); //recogemos el id cuenta 

			model.addAttribute("origen", origen); //lo pasamos

			return "transfer";

		} else {

			model.addAttribute("mensaje", "Acceso denegado");//Si la cuenta no existe, redirigimos a la pantalla inicial y sacamos el mensaje pertinente

			return "forward:/inicio";
		}
	}

	// ----------------------------------------------------- GET - TRANSFERENCIA
	@PostMapping("/transfer")
	public String postTransfer(HttpSession session, RedirectAttributes attr, @RequestParam("cantidad") double cantidad,
			@RequestParam("cuentaDestino") int destino) {

		Cuenta cuentaOrigen = (Cuenta) session.getAttribute("cuenta"); //recogemos la cuenta guardada en sesion
		Cuenta cuentaDestino = icu.findById(destino); //buscamos la cuenta cuya id nos pasan por el formulario
		double saldoOrigen = cuentaOrigen.getSaldo(); //recogemos el saldo de la cuenta origen
		//si la resta entre el saldo original y la cantidad que queremos transferir es mayor o igual a 0
		//Y si la cuenta de destino es distinta de null
		//Y si la cuent de origen es distint a la cuenta de destino
		if (((saldoOrigen - cantidad) >= 0) && cuentaDestino != null && (cuentaOrigen.getIdCuenta() != cuentaDestino.getIdCuenta())) {
			
			Date date = new Date();//Creamos una fecha del dia de hoy
			String operacion1 = "transferencia saliente"; //nombre de operacion
			String operacion2 = "transferencia entrante";//nombre de operacion
			
			double saldoDestino = cuentaDestino.getSaldo(); //conseguimos el saldo de la cuenta destino
			saldoDestino += cantidad; //sumamos la cantidad a transferir al saldo de destino
			saldoOrigen -= cantidad; //restamos la cantidad a transerir al saldo de origen
			
			Movimiento movIn = new Movimiento();//Creamos un nuevo movimiento
			movIn.setCantidad(cantidad); //le añadimos la cantidad
			movIn.setCuenta(cuentaDestino); //le añadimos la cuenta de destio
			movIn.setFecha(date); //le añadimos la fecha
			movIn.setOperacion(operacion2); //Le añadimos el nombre de la operacion
			
			
			Movimiento movOut = new Movimiento();//Creamos un nuevo movimiento
			movOut.setCantidad(cantidad); //Le añadimos la cantidad
			movOut.setCuenta(cuentaOrigen); //Le añadimos la cuenta de origen
			movOut.setFecha(date); //le añadimos la fecha
			movOut.setOperacion(operacion1); //le añadimos el nombre de la operacion
			
			imov.insertOne(movIn);//insertamos el primer movimiento en la bbdd
			imov.insertOne(movOut);//insertamos el segundo movimiento en la bbdd
			
			cuentaDestino.setSaldo(saldoDestino); //actualiamos el saldo de la cuenta de destino
			icu.insertOne(cuentaDestino); // actualizamos la cuenta de destino en la bbdd
			
			cuentaOrigen.setSaldo(saldoOrigen);//actualizamos el saldo de la cuenta de origen
			icu.insertOne(cuentaOrigen); //actualizamos la cuenta de origen en la bbdd
			
			session.setAttribute("cuenta", cuentaOrigen); //actualizamos la cuenta que tenemos en session
						
			return "redirect:/cuenta/todos"; //redirigimos a la venanta de todos los movimientos

		} else if(cuentaDestino == null){ // O si la cuenta destino no existe

			attr.addFlashAttribute("mensaje", "Cuenta de destino NO existe"); //pintamos mensaje de error);

			return "redirect:/cuenta/transfer"; //redirigimos a la web
			
		} else if(cuentaOrigen.getIdCuenta() != cuentaDestino.getIdCuenta()){ // O si la cuenta de origen es igual a la cuenta de destino
			
			attr.addFlashAttribute("mensaje", "Cuenta de destino es igual a la Cuenta de Origen"); //pintamos mensaje de error
			
			return "redirect:/cuenta/transfer"; //redirigimos a la web
			
		} else { //O si no es nada de lo anterior
			
			attr.addFlashAttribute("mensaje", "Saldo insuficiente"); //pintamos mensaje de saldo insuficiente
			
			return "redirect:/cuenta/transfer"; //redirigimos a la web
			
		}
	}

}
