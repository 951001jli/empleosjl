package org.jlhi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jlhi.model.Categoria;
import org.jlhi.model.Vacante;
import org.jlhi.service.IntCategoriasService;
import org.jlhi.service.IntVacantesService;
import org.jlhi.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/view/vacantes")
public class VacantesController {
	
	@Autowired
	private IntCategoriasService categoriasService;
	
	
	@Autowired
	private IntVacantesService vacantesService;
	
	
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	public String editar(@PathVariable("id") int idVacante,@PathVariable("id") int idCategoria, Model model) {
		Vacante vacante = vacantesService.buscarPorId(idVacante);
		Categoria categoria=categoriasService.buscarPorId(idCategoria);
		model.addAttribute("categorias", categoriasService.obtenerTodas());
		model.addAttribute("vacante", vacante);
		return "view/vacantes/formVacante";
	}
	
	
	
	@PostMapping("/guardar")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart,
			Model model) {
		model.addAttribute("categorias", categoriasService.obtenerTodas());
		if(result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "view/vacantes/formVacante";
		}
		//Validacion de imagen
		if (!multiPart.isEmpty()) {
			// String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null) { // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}

		
		
		Integer id = vacantesService.obtenerVacantes().size()+1;
		vacante.setId(id);
		vacantesService.guardar(vacante);
		attributes.addFlashAttribute("msg", "La vacante se guardo de forma correcta");
		System.out.println("Vacante: " + vacante);
		//return "view/vacantes/listaVacantes";
	return "redirect:/view/vacantes/index";
	}
	
	/*@PostMapping("/guardar")
	public String guardar(@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("estatus") String estatus,
			@RequestParam("fecha") String fecha,
			@RequestParam("destacado") int destacado,
			@RequestParam("salario") double salario,
			@RequestParam("detalles") String detalles) {
		
		System.out.println("Nombre vacante : " + nombre);
		System.out.println("Descripcion : " + descripcion);
		System.out.println("Estatus : " + estatus);
		System.out.println("Fecha : " + fecha);
		System.out.println("Destacado : " + destacado);
		System.out.println("Salario : " + salario);
		System.out.println("Detalles : " + detalles);
		return "view/vacantes/listaVacantes";
	}*/
	
	
	@GetMapping("/crear")
			//@RequestMapping(value="/crear",method=RequestMethod.GET)
			public String crear(Vacante vacante, Model model) {
		        model.addAttribute("categorias", categoriasService.obtenerTodas());
				return "view/vacantes/formVacante";
			}
			
	
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Vacante> lista = vacantesService.buscarTodas(page);
	model.addAttribute("vacantes", lista);
	return "view/vacantes/listaVacantes";
	}
	
	
	@GetMapping("/index")
		//@RequestMapping(value="/index", method=RequestMethod.GET)
		public String mostrarIndex(Model model) {
			List<Vacante> lista = vacantesService.obtenerVacantes();
			model.addAttribute("vacantes", lista);
			return "view/vacantes/listaVacantes";
		}	
	
	
	@RequestMapping(value="/eliminar/{id}", method=RequestMethod.GET)
	public String eliminar(@PathVariable("id") int idVacante,
			RedirectAttributes attributes) {
		vacantesService.eliminar(idVacante);
		attributes.addFlashAttribute("borrar",
				"El objeto categoria se elimino de forma correcta");
		return "redirect:/view/vacantes/index";
		
	}
	
	//@GetMapping("/eliminar")
	//public String eliminar(@RequestParam("id") int idVacante,Model model) {
	//	System.out.println("idVacante : " + idVacante);
	//	model.addAttribute("idVacante",idVacante);
	//	return "mensaje";
	//}
	

	@GetMapping("/vista/{id}")
	public String verDetalle(@PathVariable("id") int idVacante,Model model) {
		//System.out.println("idVacante : " + idVacante);
		//model.addAttribute("idVacante",idVacante);
		Vacante vacante = vacantesService.buscarPorId(idVacante);
		System.out.println("Vacante : " + vacante);
		model.addAttribute("vacante",vacante);
		//Cuando se tenga la conexion a la base de datos vamos a utilizar este valor para hacer una consultas y presentar los detalles
		return "view/vacantes/detalle";
	}
	
	//para el formato de las fechas
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}