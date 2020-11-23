package org.jlhi.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.jlhi.model.Vacante;
import org.jlhi.service.IntVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class HomeController {
	
	@Autowired
	private IntVacantesService vacantesService;
	
	
	@GetMapping("/listaVacantes")
	public String mostrarListaVacantes(Model model) {
		
		return "view/vacantes/listaVacantes";
	}
	
	
	
	
	@GetMapping("/acerca")
	public String mostrarAcerca(Model model) {
		
		return "acerca";
	}
	
	
	@GetMapping("/vacerca/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		//System.out.println("idVacante: " + idVacante);
		//model.addAttribute("idVacante", idVacante);
		Vacante vacante = vacantesService.buscarPorId(idVacante);
		System.out.println("Vacante: " + vacante);
		model.addAttribute("vacante", vacante);
		//cuando se tenga la conexion a la base de datos
		//vamos a utilizar este valor para hacer la consulta
		return "view/acerca/detalle";
	}
	
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		//Crear un objeto de tipo vacante
		Vacante vacante = new Vacante();
		vacante.setId(1);
		vacante.setNombre("Auxiliar de contabilidad");
		vacante.setDescripcion("Contador titulado para contabilidad");
		vacante.setFecha(new Date());
		vacante.setSalario(1050.0);
		//objeto hacia la vista
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		//Creamos una lista de vacantes
		List<String> listado = new LinkedList<String>();
		listado.add("Vendedor de productos de cosmeticos");
		listado.add("Programador web");
		listado.add("Ingeniero de soporte tecnico");
		listado.add("Secretaria ejecutiva");
		listado.add("Programador junior");
		model.addAttribute("lista", listado);
		return "listado";
	}
	
	@GetMapping("/")
	public String mostrarIndex(Model model) {
		/*model.addAttribute("mensaje", "Lista de vacantes");
		model.addAttribute("fecha", new Date());*/
		
		//Diferentes tipos de datos
		//String nombreVacante = "Ingeniero Electricista";
	//	Date fechaPublicacion = new Date();
		//Double salario = 12000.00;
		//Boolean vigente = true;
	//	model.addAttribute("nombre", nombreVacante);
	//	model.addAttribute("fecha", fechaPublicacion);
	//	model.addAttribute("salario", salario);
	//	model.addAttribute("vigente", vigente);
		
		List<Vacante> lista = vacantesService.obtenerVacantes();
		model.addAttribute("vacantes", lista);
		
		return "home";
	}
	
	//metodo para crear vacantes
	
	//private List<Vacante> obtenerVacantes(){
		
	//}

}
