package org.jlhi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.jlhi.model.Categoria;
import org.jlhi.model.Vacante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VacantesServiceImp implements IntVacantesService{
	
	//atributo de tipo List
	private List<Vacante> lista = null;
	

	public VacantesServiceImp() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
		lista = new LinkedList<Vacante>();
		Categoria c = new Categoria();
		
		//Crear las vacantes
		try {
			
			Vacante v1 = new Vacante();
			
			v1.setId(1);
			v1.setNombre("Auxiliar electricista");
			v1.setDescripcion("Se solicita tecnico para realizar instalaciones");
			v1.setSalario(7500.0);
			v1.setFecha(sdf.parse("12-04-2020"));	
			v1.setDestacado(1);
			v1.setImagen("casette.png");
			v1.setEstatus("Aprobada");
			//v1.setCategoria(v1.getCategoria());
			//**************************************			
			Vacante v2 = new Vacante();
			v2.setId(2);
			v2.setNombre("Contador");
			v2.setDescripcion("Se solicita para realizar contabilidad");
			v2.setSalario(6500.0);
			v2.setFecha(sdf.parse("21-03-2020"));	
			v2.setDestacado(0);
			v2.setImagen("bell.png");
			v2.setEstatus("Eliminada");
			//v2.setCategoria(v2.getCategoria());
           //**************************************			
			Vacante v3 = new Vacante();
			v3.setId(3);
			v3.setNombre("Programador Web");
			v3.setDescripcion("Se solicita programador web");
			v3.setSalario(16500.0);
			v3.setFecha(sdf.parse("13-02-2020"));	
			v3.setDestacado(1);
			v3.setImagen("avi.png");
			v3.setEstatus("Eliminada");
			//v3.setCategoria(v3.getCategoria());
           //**************************************		
			Vacante v4 = new Vacante();
			v4.setId(4);
			v4.setNombre("Ingeniero de sistemas");
			v4.setDescripcion("Se solicita para análisis y desarrollo de aplicaciones");
			v4.setSalario(18500.0);
			v4.setFecha(sdf.parse("10-05-2020"));
			v4.setDestacado(0);
			v4.setImagen("brightness.png");
			v4.setEstatus("Aprobada");
			//v4.setCategoria(v4.getCategoria());
			//guardar los objetos en la lista
			lista.add(v1);
			lista.add(v2);
			lista.add(v3);
			lista.add(v4);
					
		}catch(ParseException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		
	}
	
	@Override
	public List<Vacante> obtenerVacantes() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		
		Vacante vacante = null;
		int i = 0;
		while (i < lista.size()) {
			vacante = lista.get(i);
			if ( vacante.getId() == idVacante) {
				return vacante;
			}
			i++;
			
			
		}
		
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		// TODO Auto-generated method stub
		lista.add(vacante);
		
	}

	@Override
	public void eliminar(Integer idVacante) {
		// TODO Auto-generated method stub
		lista.remove(buscarPorId(idVacante));	
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
