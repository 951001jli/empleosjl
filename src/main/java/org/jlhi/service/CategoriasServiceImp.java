package org.jlhi.service;

import java.util.LinkedList;
import java.util.List;

import org.jlhi.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriasServiceImp implements IntCategoriasService{

	private List<Categoria> lista = null;
	
	//constructor
	
	public CategoriasServiceImp() {
		lista = new LinkedList<Categoria>();
		//objetos tipo cateogira
		Categoria c1 = new Categoria();
		c1.setId(1);
		c1.setNombre("Ventas");
		c1.setDescripcion("Relacionado con venta de productos");

		Categoria c2 = new Categoria();
		c2.setId(2);
		c2.setNombre("Diseño web");
		c2.setDescripcion("Relacionado con profesionales de diseño de páginas Web");

		Categoria c3 = new Categoria();
		c3.setId(3);
		c3.setNombre("Programadores");
		c3.setDescripcion("Relacionado en el dessarrollo de aplicaciones móviles");

		Categoria c4 = new Categoria();
		c4.setId(4);
		c4.setNombre("Contabilidad");
		c4.setDescripcion("Relacionado con contabilidad y autoria");

		Categoria c5 = new Categoria();
		c5.setId(5);
		c5.setNombre("Medico");
		c5.setDescripcion("Relacionado con medicos y autoria");

	    //agregarlos a la lista dinamica
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		lista.add(c4);
		lista.add(c5);
	
	}
	
	@Override
	public List<Categoria> obtenerTodas() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		
		for(Categoria c: obtenerTodas()) {
			if ( c.getId() == idCategoria ) {
				return c;
			}
		}
		return null;
	}

	@Override
	public void guardar(Categoria categoria) {
		
		lista.add(categoria);
	}

	@Override
	public void eliminar(Integer idCategoria) {
		
		lista.remove(buscarPorId(idCategoria));	
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
