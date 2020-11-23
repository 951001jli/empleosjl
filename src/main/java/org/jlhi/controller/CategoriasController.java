package org.jlhi.controller;

import java.util.List;

import org.jlhi.model.Categoria;
import org.jlhi.service.IntCategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/view/categorias")
public class CategoriasController {
	
	@Autowired
	private IntCategoriasService categoriasService;
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
	public String editar(@PathVariable("id") int idCategoria, Model model) {
		Categoria categoria = categoriasService.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "view/categorias/formCategorias";
	}

	
	@RequestMapping(value="/eliminar/{id}", method=RequestMethod.GET)
	public String eliminar(@PathVariable("id") int idCategoria,
			RedirectAttributes attributes) {
		categoriasService.eliminar(idCategoria);
		attributes.addFlashAttribute("borrar",
				"El objeto categoria se elimino de forma correcta");
		return "redirect:/view/categorias/index";
		
	}
	//Implementar el DataBinding - Vincular campos de un formulario
		//con un objeto de tipo modelo
		@RequestMapping(value="/guardar", method=RequestMethod.POST)
		public String guardar(Categoria categoria,RedirectAttributes attributes,BindingResult result ) {
			
			if ( result.hasErrors()) {
	            System.out.println("Existen errores en los datos de entrada");
	            return "view/categorias/formCategoria";
	        }
			 /*Integer id = categoriasService.obtenerTodas().size()+1;
			 categoria.setId(id);*/
			 categoriasService.guardar(categoria);
		        System.out.println("categoria : " + categoria);
		        attributes.addFlashAttribute("msg",
		                "El objeto categoria se almaceno de forma correcta");
		        return "redirect:/view/categorias/index";
		        
		}
	
	//@PostMapping("/guardar")
	/*@RequestMapping(value="/guardar", method=RequestMethod.POST)
		public String guardar(@RequestParam("nombre") String nombre, 
				@RequestParam("descripcion") String descripcion,
				RedirectAttributes attributes){
			/*BindingResult result   System.out.println("Nombre vacante: " + nombre);
			System.out.println("Descripcion: " + descripcion);     */
			//guardar los datos en la lista dinamica
			/*if(result.hasErrors()) {
				System.out.println("Existen errores en los datos de entrada");
				return "view/categorias/formCategoria";
			}*/
			
			/*Integer id = categoriasService.obtenerTodas().size()+1;
			Categoria categoria = new Categoria();
			categoria.setId(id);
			categoria.setNombre(nombre);
			categoria.setDescripcion(descripcion);
			categoriasService.guardar(categoria);
			System.out.println("categoria : " + categoria);
			//redirect y fash attributes
			/*
			 los atributos flash proporcionan una forma de almacenar
			 atributos para poder ser usados en otra
			 peticion diferentte.
			 Comunmente son utilizados cuando hacemos una
			 redirección utilizando el patrón 
			 post/redirect/get 
			 */
			//return "view/categorias/listaCategorias";	
			/*attributes.addFlashAttribute("msg",
					"El objeto categoria se almceno de forma correcta");
			return "redirect:/view/categorias/index";
		}*/
		
		
		@GetMapping(value = "/indexPaginado")
		public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Categoria> lista = categoriasService.buscarTodas(page);
		model.addAttribute("categorias", lista);
		return "view/categorias/listaCategorias";
		}


	// @GetMapping("/index")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista = categoriasService.obtenerTodas();
		model.addAttribute("categorias", lista);
		return "view/categorias/listaCategorias";
	}
	
	//@GetMapping("/crear")
	@RequestMapping(value="/crear", method=RequestMethod.GET)
	public String crear(Categoria categoria) {
		return "view/categorias/formCategorias";
	}

}